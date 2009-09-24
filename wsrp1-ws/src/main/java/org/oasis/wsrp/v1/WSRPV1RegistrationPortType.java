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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/** This class was generated by Apache CXF 2.2.2 Mon Aug 03 23:27:29 CEST 2009 Generated source version: 2.2.2 */

@WebService(targetNamespace = "urn:oasis:names:tc:wsrp:v1:intf", name = "WSRP_v1_Registration_PortType")
@XmlSeeAlso({ObjectFactory.class})
public interface WSRPV1RegistrationPortType
{

   @RequestWrapper(localName = "register", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types", className = "org.oasis.wsrp.v1.RegistrationData")
   @ResponseWrapper(localName = "registerResponse", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types", className = "org.oasis.wsrp.v1.RegistrationContext")
   @WebMethod(action = "urn:oasis:names:tc:wsrp:v1:register")
   public void register(
      @WebParam(name = "consumerName", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      java.lang.String consumerName,
      @WebParam(name = "consumerAgent", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      java.lang.String consumerAgent,
      @WebParam(name = "methodGetSupported", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      boolean methodGetSupported,
      @WebParam(name = "consumerModes", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      java.util.List<java.lang.String> consumerModes,
      @WebParam(name = "consumerWindowStates", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      java.util.List<java.lang.String> consumerWindowStates,
      @WebParam(name = "consumerUserScopes", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      java.util.List<java.lang.String> consumerUserScopes,
      @WebParam(name = "customUserProfileData", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      java.util.List<java.lang.String> customUserProfileData,
      @WebParam(name = "registrationProperties", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      java.util.List<org.oasis.wsrp.v1.Property> registrationProperties,
      @WebParam(mode = WebParam.Mode.INOUT, name = "extensions", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      javax.xml.ws.Holder<java.util.List<org.oasis.wsrp.v1.Extension>> extensions,
      @WebParam(mode = WebParam.Mode.OUT, name = "registrationHandle", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      javax.xml.ws.Holder<java.lang.String> registrationHandle,
      @WebParam(mode = WebParam.Mode.OUT, name = "registrationState", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      javax.xml.ws.Holder<byte[]> registrationState
   ) throws MissingParameters, OperationFailed;

   @RequestWrapper(localName = "modifyRegistration", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types", className = "org.oasis.wsrp.v1.ModifyRegistration")
   @ResponseWrapper(localName = "modifyRegistrationResponse", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types", className = "org.oasis.wsrp.v1.RegistrationState")
   @WebMethod(action = "urn:oasis:names:tc:wsrp:v1:modifyRegistration")
   public void modifyRegistration(
      @WebParam(name = "registrationContext", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      org.oasis.wsrp.v1.RegistrationContext registrationContext,
      @WebParam(name = "registrationData", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      org.oasis.wsrp.v1.RegistrationData registrationData,
      @WebParam(mode = WebParam.Mode.OUT, name = "registrationState", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      javax.xml.ws.Holder<byte[]> registrationState,
      @WebParam(mode = WebParam.Mode.OUT, name = "extensions", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      javax.xml.ws.Holder<java.util.List<org.oasis.wsrp.v1.Extension>> extensions
   ) throws MissingParameters, InvalidRegistration, OperationFailed;

   @WebResult(name = "extensions", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
   @RequestWrapper(localName = "deregister", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types", className = "org.oasis.wsrp.v1.RegistrationContext")
   @ResponseWrapper(localName = "deregisterResponse", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types", className = "org.oasis.wsrp.v1.ReturnAny")
   @WebMethod(action = "urn:oasis:names:tc:wsrp:v1:deregister")
   public java.util.List<org.oasis.wsrp.v1.Extension> deregister(
      @WebParam(name = "registrationHandle", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      java.lang.String registrationHandle,
      @WebParam(name = "registrationState", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      byte[] registrationState,
      @WebParam(name = "extensions", targetNamespace = "urn:oasis:names:tc:wsrp:v1:types")
      java.util.List<org.oasis.wsrp.v1.Extension> extensions
   ) throws InvalidRegistration, OperationFailed;
}