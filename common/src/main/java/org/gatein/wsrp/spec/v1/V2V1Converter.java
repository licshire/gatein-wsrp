/*
 * JBoss, a division of Red Hat
 * Copyright 2010, Red Hat Middleware, LLC, and individual
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

package org.gatein.wsrp.spec.v1;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.gatein.common.NotYetImplemented;
import org.gatein.pc.api.OpaqueStateString;
import org.gatein.wsrp.WSRPExceptionFactory;
import org.gatein.wsrp.WSRPTypeFactory;
import org.oasis.wsrp.v1.V1ClientData;
import org.oasis.wsrp.v1.V1CookieProtocol;
import org.oasis.wsrp.v1.V1Extension;
import org.oasis.wsrp.v1.V1ItemDescription;
import org.oasis.wsrp.v1.V1LocalizedString;
import org.oasis.wsrp.v1.V1MarkupContext;
import org.oasis.wsrp.v1.V1MarkupParams;
import org.oasis.wsrp.v1.V1MarkupType;
import org.oasis.wsrp.v1.V1ModelDescription;
import org.oasis.wsrp.v1.V1PortletContext;
import org.oasis.wsrp.v1.V1PortletDescription;
import org.oasis.wsrp.v1.V1RegistrationContext;
import org.oasis.wsrp.v1.V1ResourceList;
import org.oasis.wsrp.v1.V1RuntimeContext;
import org.oasis.wsrp.v1.V1ServiceDescription;
import org.oasis.wsrp.v1.V1UserContext;
import org.oasis.wsrp.v2.ClientData;
import org.oasis.wsrp.v2.CookieProtocol;
import org.oasis.wsrp.v2.Extension;
import org.oasis.wsrp.v2.ItemDescription;
import org.oasis.wsrp.v2.LocalizedString;
import org.oasis.wsrp.v2.MarkupContext;
import org.oasis.wsrp.v2.MarkupParams;
import org.oasis.wsrp.v2.MarkupType;
import org.oasis.wsrp.v2.ModelDescription;
import org.oasis.wsrp.v2.PortletContext;
import org.oasis.wsrp.v2.PortletDescription;
import org.oasis.wsrp.v2.RegistrationContext;
import org.oasis.wsrp.v2.ResourceList;
import org.oasis.wsrp.v2.RuntimeContext;
import org.oasis.wsrp.v2.ServiceDescription;
import org.oasis.wsrp.v2.UserContext;

/**
 * @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a>
 * @version $Revision$
 */
public class V2V1Converter
{
   public static final V1ToV2Extension V1_TO_V2_EXTENSION = new V1ToV2Extension();
   public static final V2ToV1Extension V2_TO_V1_EXTENSION = new V2ToV1Extension();
   public static final V2ToV1MarkupType V2_TO_V1_MARKUPTYPE = new V2ToV1MarkupType();

   public static final V2ToV1PortletDescription V2_TO_V1_PORTLETDESCRIPTION = new V2ToV1PortletDescription();
   public static final V2ToV1LocalizedString V2_TO_V1_LOCALIZEDSTRING = new V2ToV1LocalizedString();
   public static final V2ToV1ItemDescription V2_TO_V1_ITEMDESCRIPTION = new V2ToV1ItemDescription();

   public static V1PortletDescription toV1PortletDescription(PortletDescription portletDescription)
   {
      throw new NotYetImplemented();
   }

   public static V1PortletContext toV1PortletContext(PortletContext portletContext)
   {
      throw new NotYetImplemented();
   }

   public static MarkupParams toV2MarkupParams(V1MarkupParams v1MarkupParams)
   {
      if (v1MarkupParams != null)
      {
         MarkupParams markupParams = WSRPTypeFactory.createMarkupParams(v1MarkupParams.isSecureClientCommunication(),
            v1MarkupParams.getLocales(), v1MarkupParams.getMimeTypes(), v1MarkupParams.getMode(),
            v1MarkupParams.getWindowState());
         markupParams.setClientData(toV2ClientData(v1MarkupParams.getClientData()));
         markupParams.setNavigationalContext(WSRPTypeFactory.createNavigationalContextOrNull(
            new OpaqueStateString(v1MarkupParams.getNavigationalState()), null));
         markupParams.setValidateTag(v1MarkupParams.getValidateTag());

         markupParams.getMarkupCharacterSets().addAll(v1MarkupParams.getMarkupCharacterSets());
         markupParams.getValidNewModes().addAll(v1MarkupParams.getValidNewModes());
         markupParams.getValidNewWindowStates().addAll(v1MarkupParams.getValidNewWindowStates());

         markupParams.getExtensions().addAll(Lists.transform(v1MarkupParams.getExtensions(), V1_TO_V2_EXTENSION));
         return markupParams;
      }
      else
      {
         return null;
      }
   }


   private static ClientData toV2ClientData(V1ClientData clientData)
   {
      throw new NotYetImplemented();
   }

   public static PortletContext toV2PortletContext(V1PortletContext portletContext)
   {
      throw new NotYetImplemented();
   }

   public static RegistrationContext toV2RegistrationContext(V1RegistrationContext registrationContext)
   {
      throw new NotYetImplemented();
   }

   public static RuntimeContext toV2RuntimeContext(V1RuntimeContext runtimeContext)
   {
      throw new NotYetImplemented();
   }

   public static UserContext toV2UserContext(V1UserContext userContext)
   {
      throw new NotYetImplemented();
   }

   public static V1MarkupContext toV1MarkupContext(MarkupContext markupContext)
   {
      throw new NotYetImplemented();
   }

   public static ServiceDescription toV2ServiceDescription(V1ServiceDescription v1ServiceDescription)
   {
      throw new NotYetImplemented();
   }

   public static V1RegistrationContext toV1RegistrationContext(RegistrationContext registrationContext)
   {
      if (registrationContext != null)
      {
         V1RegistrationContext result = WSRP1TypeFactory.createRegistrationContext(registrationContext.getRegistrationHandle());
         result.setRegistrationState(registrationContext.getRegistrationState());
         result.getExtensions().addAll(Lists.transform(registrationContext.getExtensions(), V2_TO_V1_EXTENSION));
         return result;
      }
      else
      {
         return null;
      }
   }

   public static <E extends Exception> E toV2Exception(Class<E> v2ExceptionClass, Exception v1Exception)
   {
      if (!"org.oasis.wsrp.v2".equals(v2ExceptionClass.getPackage().getName()))
      {
         throw new IllegalArgumentException("Specified exception class is not a WSRP 2 exception: " + v2ExceptionClass);
      }

      Class<? extends Exception> v1ExceptionClass = v1Exception.getClass();
      String v1Name = v1ExceptionClass.getSimpleName();
      int v1Index = v1Name.indexOf("V1");
      if (v1Index != 0 && !"org.oasis.wsrp.v1".equals(v1ExceptionClass.getPackage().getName()))
      {
         throw new IllegalArgumentException("Specified exception is not a WSRP 1 exception: " + v1Exception);
      }

      String v2Name = v2ExceptionClass.getSimpleName();
      // V2 class name should match V1 class name minus "V1"
      if (!v2Name.equals(v1Name.substring(2)))
      {
         throw new IllegalArgumentException("Exception names do not match. Requested: " + v2Name
            + ", was given: " + v1Name);
      }

      return WSRPExceptionFactory.createWSException(v2ExceptionClass, v1Exception.getMessage(), v1Exception.getCause());
   }

   public static <E extends Exception> E toV1Exception(Class<E> v1ExceptionClass, Exception v2Exception)
   {
      if (!"org.oasis.wsrp.v1".equals(v1ExceptionClass.getPackage().getName()))
      {
         throw new IllegalArgumentException("Specified exception class is not a WSRP 1 exception: " + v1ExceptionClass);
      }

      Class<? extends Exception> v2ExceptionClass = v2Exception.getClass();
      String v1Name = v2ExceptionClass.getSimpleName();
      if (!"org.oasis.wsrp.v2".equals(v2ExceptionClass.getPackage().getName()))
      {
         throw new IllegalArgumentException("Specified exception is not a WSRP 2 exception: " + v2Exception);
      }

      String v2Name = v2ExceptionClass.getSimpleName();
      // V1 class name should match V2 class name plus "V1"
      if (!v2Name.equals(v1Name.substring(2)))
      {
         throw new IllegalArgumentException("Exception names do not match. Requested: " + v1Name
            + ", was given: " + v2Name);
      }

      return WSRP1ExceptionFactory.createWSException(v1ExceptionClass, v2Exception.getMessage(), v2Exception.getCause());
   }

   public static V1CookieProtocol toV1CookieProtocol(CookieProtocol requiresInitCookie)
   {
      return V1CookieProtocol.fromValue(requiresInitCookie.value());
   }

   public static V1ModelDescription toV1ModelDescription(ModelDescription modelDescription)
   {
      throw new NotYetImplemented();
   }

   public static V1ResourceList toV1ResourceList(ResourceList resourceList)
   {
      throw new NotYetImplemented();
   }


   private static class V1ToV2Extension implements Function<V1Extension, Extension>
   {
      public Extension apply(V1Extension from)
      {
         if (from != null)
         {
            Extension extension = new Extension();
            extension.setAny(from.getAny());
            return extension;
         }
         else
         {
            return null;
         }
      }
   }

   private static class V2ToV1Extension implements Function<Extension, V1Extension>
   {
      public V1Extension apply(Extension from)
      {
         if (from != null)
         {
            V1Extension extension = new V1Extension();
            extension.setAny(from.getAny());
            return extension;
         }
         else
         {
            return null;
         }
      }
   }

   public static class V2ToV1PortletDescription implements Function<PortletDescription, V1PortletDescription>
   {

      public V1PortletDescription apply(PortletDescription from)
      {
         if (from != null)
         {
            V1PortletDescription result = WSRP1TypeFactory.createPortletDescription(from.getPortletHandle(),
               Lists.transform(from.getMarkupTypes(), V2_TO_V1_MARKUPTYPE));
            result.setDescription(V2_TO_V1_LOCALIZEDSTRING.apply(from.getDescription()));
            result.setDisplayName(V2_TO_V1_LOCALIZEDSTRING.apply(from.getDisplayName()));
            result.getExtensions().addAll(Lists.transform(from.getExtensions(), V2_TO_V1_EXTENSION));
            result.getKeywords().addAll(Lists.transform(from.getKeywords(), V2_TO_V1_LOCALIZEDSTRING));
            result.getUserCategories().addAll(from.getUserCategories());
            result.getUserProfileItems().addAll(from.getUserProfileItems());
            result.setDefaultMarkupSecure(from.isDefaultMarkupSecure());
            result.setDoesUrlTemplateProcessing(from.isDoesUrlTemplateProcessing());
            result.setTemplatesStoredInSession(from.isTemplatesStoredInSession());
            result.setHasUserSpecificState(from.isHasUserSpecificState());
            result.setOnlySecure(from.isOnlySecure());
            result.setUserContextStoredInSession(from.isUserContextStoredInSession());
            result.setUsesMethodGet(from.isUsesMethodGet());
            result.setShortTitle(V2_TO_V1_LOCALIZEDSTRING.apply(from.getShortTitle()));
            result.setTitle(V2_TO_V1_LOCALIZEDSTRING.apply(from.getTitle()));

            result.setGroupID(from.getGroupID());
            return result;
         }
         else
         {
            return null;
         }
      }
   }

   public static class V2ToV1ItemDescription implements Function<ItemDescription, V1ItemDescription>
   {

      public V1ItemDescription apply(ItemDescription from)
      {
         if (from != null)
         {
            V1ItemDescription result = new V1ItemDescription();
            result.setItemName(from.getItemName());
            result.setDescription(V2_TO_V1_LOCALIZEDSTRING.apply(from.getDescription()));
            result.getExtensions().addAll(Lists.transform(from.getExtensions(), V2_TO_V1_EXTENSION));
            return result;
         }
         else
         {
            return null;
         }
      }
   }

   public static class V2ToV1MarkupType implements Function<MarkupType, V1MarkupType>
   {

      public V1MarkupType apply(MarkupType from)
      {
         if (from != null)
         {
            return WSRP1TypeFactory.createMarkupType(from.getMimeType(), from.getModes(), from.getWindowStates(), from.getLocales());
         }
         else
         {
            return null;
         }
      }
   }

   public static class V2ToV1LocalizedString implements Function<LocalizedString, V1LocalizedString>
   {

      public V1LocalizedString apply(LocalizedString from)
      {
         if (from != null)
         {
            return WSRP1TypeFactory.createLocalizedString(from.getLang(), from.getResourceName(), from.getValue());
         }
         else
         {
            return null;
         }

      }
   }
}
