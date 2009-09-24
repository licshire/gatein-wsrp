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

package org.oasis.wsrp.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registrationContext" type="{urn:oasis:names:tc:wsrp:v1:types}RegistrationContext"/>
 *         &lt;element name="portletContext" type="{urn:oasis:names:tc:wsrp:v1:types}PortletContext"/>
 *         &lt;element name="runtimeContext" type="{urn:oasis:names:tc:wsrp:v1:types}RuntimeContext"/>
 *         &lt;element name="userContext" type="{urn:oasis:names:tc:wsrp:v1:types}UserContext"/>
 *         &lt;element name="markupParams" type="{urn:oasis:names:tc:wsrp:v1:types}MarkupParams"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "registrationContext",
   "portletContext",
   "runtimeContext",
   "userContext",
   "markupParams"
})
@XmlRootElement(name = "getMarkup")
public class GetMarkup
{

   @XmlElement(required = true, nillable = true)
   protected RegistrationContext registrationContext;
   @XmlElement(required = true)
   protected PortletContext portletContext;
   @XmlElement(required = true)
   protected RuntimeContext runtimeContext;
   @XmlElement(required = true, nillable = true)
   protected UserContext userContext;
   @XmlElement(required = true)
   protected MarkupParams markupParams;

   /**
    * Gets the value of the registrationContext property.
    *
    * @return possible object is {@link RegistrationContext }
    */
   public RegistrationContext getRegistrationContext()
   {
      return registrationContext;
   }

   /**
    * Sets the value of the registrationContext property.
    *
    * @param value allowed object is {@link RegistrationContext }
    */
   public void setRegistrationContext(RegistrationContext value)
   {
      this.registrationContext = value;
   }

   /**
    * Gets the value of the portletContext property.
    *
    * @return possible object is {@link PortletContext }
    */
   public PortletContext getPortletContext()
   {
      return portletContext;
   }

   /**
    * Sets the value of the portletContext property.
    *
    * @param value allowed object is {@link PortletContext }
    */
   public void setPortletContext(PortletContext value)
   {
      this.portletContext = value;
   }

   /**
    * Gets the value of the runtimeContext property.
    *
    * @return possible object is {@link RuntimeContext }
    */
   public RuntimeContext getRuntimeContext()
   {
      return runtimeContext;
   }

   /**
    * Sets the value of the runtimeContext property.
    *
    * @param value allowed object is {@link RuntimeContext }
    */
   public void setRuntimeContext(RuntimeContext value)
   {
      this.runtimeContext = value;
   }

   /**
    * Gets the value of the userContext property.
    *
    * @return possible object is {@link UserContext }
    */
   public UserContext getUserContext()
   {
      return userContext;
   }

   /**
    * Sets the value of the userContext property.
    *
    * @param value allowed object is {@link UserContext }
    */
   public void setUserContext(UserContext value)
   {
      this.userContext = value;
   }

   /**
    * Gets the value of the markupParams property.
    *
    * @return possible object is {@link MarkupParams }
    */
   public MarkupParams getMarkupParams()
   {
      return markupParams;
   }

   /**
    * Sets the value of the markupParams property.
    *
    * @param value allowed object is {@link MarkupParams }
    */
   public void setMarkupParams(MarkupParams value)
   {
      this.markupParams = value;
   }

}