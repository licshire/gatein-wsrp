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

package org.gatein.wsrp.test.protocol.v1.behaviors;

import org.gatein.pc.api.Mode;
import org.gatein.pc.api.WindowState;
import org.gatein.wsrp.spec.v1.WSRP1ExceptionFactory;
import org.gatein.wsrp.test.protocol.v1.BehaviorRegistry;
import org.gatein.wsrp.test.protocol.v1.MarkupBehavior;
import org.oasis.wsrp.v1.V1AccessDenied;
import org.oasis.wsrp.v1.V1Extension;
import org.oasis.wsrp.v1.V1GetMarkup;
import org.oasis.wsrp.v1.V1InvalidCookie;
import org.oasis.wsrp.v1.V1InvalidRegistration;
import org.oasis.wsrp.v1.V1OperationFailed;
import org.oasis.wsrp.v1.V1RegistrationContext;

import javax.jws.WebParam;
import java.util.List;

/**
 * @author <a href="mailto:chris.laprun@jboss.com">Chris Laprun</a>
 * @version $Revision: 10610 $
 * @since 2.6
 */
public abstract class InitCookieMarkupBehavior extends MarkupBehavior
{
   protected String portletHandle;
   protected int initCookieCallCount;
   private boolean initCookieCalled;

   public InitCookieMarkupBehavior(BehaviorRegistry registry)
   {
      super(registry);
      initPortletHandle();
      registerHandle(portletHandle);
   }

   protected abstract void initPortletHandle();


   protected String getMarkupString(Mode mode, WindowState windowState, String navigationalState, V1GetMarkup getMarkup) throws V1OperationFailed, V1InvalidCookie
   {
      if (!initCookieCalled)
      {
         throw new IllegalStateException("initCookie should have been called first!");
      }

      String handle = getMarkup.getPortletContext().getPortletHandle();

      if (portletHandle.equals(handle))
      {
         return getMarkupString(handle);
      }

      // shouldn't happen
      throw WSRP1ExceptionFactory.throwWSException(V1OperationFailed.class, "Shouldn't happen", null);
   }

   protected String getMarkupString(String handle) throws V1InvalidCookie, V1OperationFailed
   {
      switch (callCount++)
      {
         case 0:
            // simulate change of configuration between calls: upon receiving this, the consumer should invoke initCookie
            throw WSRP1ExceptionFactory.throwWSException(V1InvalidCookie.class, "Simulate invalid cookie", null);

         case 1:
            return handle;

         default:
            // shouldn't be called more than twice
            throw WSRP1ExceptionFactory.throwWSException(V1OperationFailed.class, "Shouldn't be called more than twice", null);
      }
   }

   @Override
   public List<V1Extension> initCookie(@WebParam(name = "registrationContext", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types") V1RegistrationContext registrationContext) throws V1InvalidRegistration, V1AccessDenied, V1OperationFailed
   {
      initCookieCalled = true;
      initCookieCallCount++;
      return null;
   }

   public int getInitCookieCallCount()
   {
      return initCookieCallCount;
   }
}
