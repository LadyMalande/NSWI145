/*
 * Copyright 2001-2010 The Apache Software Foundation. Edited by Tereza Miklosova 2022.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.miklosova;

import javax.jws.WebService;

import org.apache.juddi.v3.annotations.UDDIService;
import org.apache.juddi.v3.annotations.UDDIServiceBinding;

/**
 * This example show you how to use UDDI Annotations to decorate a class.
 * When the Servlet Listener
 * 
 */

@UDDIService(
		businessKey="uddi:${keyDomain}:${department}",
		serviceKey="uddi:${keyDomain}:services-discount${department}", 
		description = "Associate Discount service")
@UDDIServiceBinding(
		bindingKey="uddi:${keyDomain}:${serverName}-${serverPort}-discount${department}-wsdl",
	    description="WSDL endpoint for the discount${department} Service. This service is used for "
				  + "testing the jUDDI annotation functionality",
	    accessPointType="wsdlDeployment",
	    accessPoint="http://${serverName}:9080/uddi-annotations/services/associatediscount?wsdl")
@WebService(
		endpointInterface = "com.miklosova.AssociateDiscount",
        serviceName = "AssociateDiscount")

public class AssociateDiscountImpl implements AssociateDiscount {
    
    public int getDiscount(String code) {
    	int discount = 0;
    	
    	String normalizedCode = code.toLowerCase();
    	
    	switch(normalizedCode) {
    	case "birthday": discount = 10;
    	break;
    	case "supersale": discount = 30;
    	break;
    	case "bff": discount = 70;
    	break;
    	case "halflife": discount = 50;
    	break;
    	default: discount = 0;
    	}
    	
        System.out.println("Discount: " + discount);
        return discount;
    }
	
}
