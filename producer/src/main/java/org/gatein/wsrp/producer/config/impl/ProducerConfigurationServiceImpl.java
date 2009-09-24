/*
 * JBoss, a division of Red Hat
 * Copyright 2009, Red Hat Middleware, LLC, and individual
 * contributors as indicated by the @authors tag. See the
 * copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.gatein.wsrp.producer.config.impl;

import org.gatein.common.net.URLTools;
import org.gatein.registration.RegistrationPolicyChangeListener;
import org.gatein.registration.RegistrationPropertyChangeListener;
import org.gatein.wsrp.producer.config.ProducerConfiguration;
import org.gatein.wsrp.producer.config.ProducerConfigurationChangeListener;
import org.gatein.wsrp.producer.config.ProducerConfigurationFactory;
import org.gatein.wsrp.producer.config.ProducerConfigurationProvider;
import org.gatein.wsrp.producer.config.ProducerConfigurationService;
import org.gatein.wsrp.producer.config.ProducerRegistrationRequirements;
import org.jboss.xb.binding.JBossXBException;
import org.jboss.xb.binding.ObjectModelFactory;
import org.jboss.xb.binding.ObjectModelProvider;
import org.jboss.xb.binding.Unmarshaller;
import org.jboss.xb.binding.UnmarshallerFactory;
import org.jboss.xb.binding.XercesXsMarshaller;
import org.jboss.xb.binding.sunday.unmarshalling.DefaultSchemaResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a>
 * @version $Revision: 12276 $
 * @since 2.6
 */
public class ProducerConfigurationServiceImpl implements ProducerConfigurationService
{
   private static final Logger log = LoggerFactory.getLogger(ProducerConfigurationServiceImpl.class);

   private String configLocation;
   private ProducerConfiguration configuration;

   private File config;

   private static DefaultSchemaResolver RESOLVER;

   private static final String PRODUCER_NAMESPACE = "urn:jboss:portal:wsrp:producer:v2_7";
   private static final String PRODUCER_XSD = "jboss-wsrp-producer_2_7.xsd";

   static
   {
      RESOLVER = new DefaultSchemaResolver();
      RESOLVER.setCacheResolvedSchemas(true);
      RESOLVER.addSchemaLocation("http://www.w3.org/XML/1998/namespace", "xsd/xml.xsd");
      RESOLVER.addSchemaLocation("urn:jboss:portal:wsrp:producer:v2_6", "xsd/jboss-wsrp-producer_2_6.xsd");
      RESOLVER.addSchemaLocation(PRODUCER_NAMESPACE, "xsd/" + PRODUCER_XSD);
   }

   public String getConfigLocation()
   {
      return configLocation;
   }

   public void setConfigLocation(String configLocation)
   {
      this.configLocation = configLocation;
   }


   public ProducerConfiguration getConfiguration()
   {
      return configuration;
   }

   public void start() throws Exception
   {
      File dataDir;
      /*MBeanServer server = MBeanServerLocator.locateJBoss();
      ObjectName oname = ObjectNameFactory.create("jboss.system:type=ServerConfig");
      try
      {
         dataDir = (File)server.getAttribute(oname, "ServerDataDir");
      }
      catch (Exception e)
      {
         throw new RuntimeException("Couldn't locate server data dir!", e);
      }*/

      // todo: replace by proper location or JCR-based persistence 
      dataDir = new File(System.getProperty("java.io.tmpdir"));

      // if "portal" directory doesn't exist already in data, create it (JBPORTAL-2229)
      File portalDir = new File(dataDir, "portal");
      if (!portalDir.exists())
      {
         if (!portalDir.mkdir())
         {
            throw new RuntimeException("Couldn't create 'portal' directory in " + dataDir.getAbsolutePath());
         }
      }
      else
      {
         if (!portalDir.isDirectory())
         {
            throw new RuntimeException("Was expecting a directory named 'portal' in " + dataDir.getAbsolutePath()
               + ", not a simple file! Cannot continue.");
         }
      }

      config = new File(portalDir, "wsrp-producer-config.xml");

      reloadConfiguration();
   }

   public void reloadConfiguration() throws Exception
   {
      URL configURL = getConfigLocationURL();

      try
      {
         loadConfigurationAt(configURL);
      }
      catch (Exception e)
      {
         if (config.exists())
         {
            log.debug("Configuration saved at " + config.getCanonicalPath()
               + " is not loading properly. Falling back to default configuration.");
            config.delete(); // delete improper config so that we retrieve the default configuration
            loadConfigurationAt(getConfigLocationURL());
         }
      }
   }

   private void loadConfigurationAt(URL configURL) throws JBossXBException, IOException
   {
      log.debug("About to parse producer configuration " + configURL);
      Unmarshaller unmarshaller = UnmarshallerFactory.newInstance().newUnmarshaller();
      ObjectModelFactory factory = new ProducerConfigurationFactory();

      // save listeners if we already have a configuration
      List<ProducerConfigurationChangeListener> listeners = null;
      Set<RegistrationPolicyChangeListener> policyListeners = null;
      Set<RegistrationPropertyChangeListener> propertyListeners = null;
      ProducerRegistrationRequirements registrationRequirements;
      if (configuration != null)
      {
         listeners = configuration.getChangeListeners();
         registrationRequirements = configuration.getRegistrationRequirements();
         if (registrationRequirements != null)
         {
            policyListeners = registrationRequirements.getPolicyChangeListeners();
            propertyListeners = registrationRequirements.getPropertyChangeListeners();
         }
      }

      // reload
      configuration = (ProducerConfiguration)unmarshaller.unmarshal(configURL.openStream(), factory, null);

      // restore listeners
      if (listeners != null)
      {
         for (ProducerConfigurationChangeListener listener : listeners)
         {
            configuration.addChangeListener(listener);
         }
      }
      registrationRequirements = configuration.getRegistrationRequirements();
      if (registrationRequirements != null)
      {
         if (propertyListeners != null)
         {
            for (RegistrationPropertyChangeListener listener : propertyListeners)
            {
               registrationRequirements.addRegistrationPropertyChangeListener(listener);
            }
         }
         if (policyListeners != null)
         {
            for (RegistrationPolicyChangeListener listener : policyListeners)
            {
               registrationRequirements.addRegistrationPolicyChangeListener(listener);
            }
         }
      }
   }

   private URL getConfigLocationURL() throws Exception
   {
      if (!config.exists())
      {
         // Setup URLs
         if (configLocation == null)
         {
            throw new Exception("The config location is null");
         }

         // make sure we pick the furthest down the path in case there's several similarly named resources in the path
         Enumeration resources = Thread.currentThread().getContextClassLoader().getResources(configLocation);
         URL configURL = null;
         while (resources.hasMoreElements())
         {
            configURL = (URL)resources.nextElement();
         }

         if (configURL == null)
         {
            throw new Exception("The config " + configLocation + " does not exist");
         }
         if (!URLTools.exists(configURL))
         {
            throw new Exception("The config " + configURL + " does not exist");
         }
         return configURL;
      }
      else
      {
         return config.toURI().toURL();
      }
   }

   public void saveConfiguration() throws Exception
   {
      // get the output writer to write the XML content
      StringWriter xmlOutput = new StringWriter();

      // get the XML Schema source
      InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("xsd/" + PRODUCER_XSD);

      Reader xsReader = new InputStreamReader(is);

      // create an instance of XML Schema marshaller
      XercesXsMarshaller marshaller = new XercesXsMarshaller();

      marshaller.setSchemaResolver(RESOLVER);

      // we need to specify what elements are top most (roots) providing namespace URI, prefix and local name
      marshaller.addRootElement(PRODUCER_NAMESPACE, "", "producer-configuration");

      // declare default namespace
      marshaller.declareNamespace("wpc", PRODUCER_NAMESPACE);

      // add schema location by declaring xsi namespace and adding xsi:schemaLocation attribute
      marshaller.declareNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
      marshaller.addAttribute("xsi", "schemaLocation", "string",
         PRODUCER_NAMESPACE + " http://www.jboss.org/portal/xsd/" + PRODUCER_XSD);

      // create an instance of Object Model Provider
      ObjectModelProvider provider = new ProducerConfigurationProvider();

      marshaller.setProperty("org.jboss.xml.binding.marshalling.indent", "true");
      marshaller.marshal(xsReader, provider, configuration, xmlOutput);

      // close XML Schema reader
      xsReader.close();

      config.createNewFile();
      Writer configFile = new BufferedWriter(new FileWriter(config));
      configFile.write(xmlOutput.toString());
      configFile.flush();
      configFile.close();
   }
}