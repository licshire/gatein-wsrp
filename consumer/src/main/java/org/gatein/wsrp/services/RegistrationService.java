/*
* JBoss, a division of Red Hat
* Copyright 2008, Red Hat Middleware, LLC, and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
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

package org.gatein.wsrp.services;

import org.oasis.wsrp.v2.AccessDenied;
import org.oasis.wsrp.v2.Extension;
import org.oasis.wsrp.v2.GetRegistrationLifetime;
import org.oasis.wsrp.v2.InvalidHandle;
import org.oasis.wsrp.v2.InvalidRegistration;
import org.oasis.wsrp.v2.Lifetime;
import org.oasis.wsrp.v2.MissingParameters;
import org.oasis.wsrp.v2.ModifyRegistrationRequired;
import org.oasis.wsrp.v2.OperationFailed;
import org.oasis.wsrp.v2.OperationNotSupported;
import org.oasis.wsrp.v2.RegistrationContext;
import org.oasis.wsrp.v2.RegistrationData;
import org.oasis.wsrp.v2.ResourceSuspended;
import org.oasis.wsrp.v2.SetRegistrationLifetime;
import org.oasis.wsrp.v2.UserContext;
import org.oasis.wsrp.v2.WSRPV2RegistrationPortType;

import javax.xml.ws.Holder;
import java.util.List;

/**
 * @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a>
 * @version $Revision$
 */
public abstract class RegistrationService<T> extends WSRPService<T> implements WSRPV2RegistrationPortType
{
   public RegistrationService(T service)
   {
      super(service);
   }

   public abstract void register(RegistrationData registrationData, Lifetime lifetime, UserContext userContext,
                                 Holder<byte[]> registrationState, Holder<Lifetime> scheduledDestruction,
                                 Holder<List<Extension>> extensions, Holder<String> registrationHandle)
      throws MissingParameters, OperationFailed, OperationNotSupported;

   public abstract List<Extension> deregister(RegistrationContext registrationContext, UserContext userContext)
      throws InvalidRegistration, OperationFailed, OperationNotSupported, ResourceSuspended;

   public abstract void modifyRegistration(
      RegistrationContext registrationContext, RegistrationData registrationData, UserContext userContext,
      Holder<byte[]> registrationState,
      Holder<Lifetime> scheduledDestruction,
      Holder<List<Extension>> extensions)
      throws InvalidRegistration, MissingParameters, OperationFailed, OperationNotSupported, ResourceSuspended;

   public abstract Lifetime getRegistrationLifetime(GetRegistrationLifetime getRegistrationLifetime)
      throws AccessDenied, InvalidHandle, InvalidRegistration, ModifyRegistrationRequired, OperationFailed,
      OperationNotSupported, ResourceSuspended;

   public abstract Lifetime setRegistrationLifetime(SetRegistrationLifetime setRegistrationLifetime)
      throws AccessDenied, InvalidHandle, InvalidRegistration, ModifyRegistrationRequired, OperationFailed,
      OperationNotSupported, ResourceSuspended;
}
