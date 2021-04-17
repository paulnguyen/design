/*
Copyright 2009 Ramnivas Laddad

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 
    http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License. 
*/

//Listing 15.14 Test to verify field-level security
package ajia.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ajia.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AccessFieldSecurityAspectTest {
    private Product product;

    @Before
    public void setup() {
        setupAuthentication("ROLE_SUPERVISOR");
        product = new Product("TestProduct", "Test Description", 20);
    }

    @Test
    public void regularAccess() {
        setupAuthentication("ROLE_ANONYMOUS");
        product.getName();
        product.setName("new name");
    }

    @Test
    public void securedAccessSufficientAuthority() {
        setupAuthentication("ROLE_SUPERVISOR");
        product.getPrice();
        product.setPrice(0);
    }

    @Test(expected = AccessDeniedException.class)
    public void securedReadAccessInsufficientAuthority() {
        setupAuthentication("ROLE_USER");
        product.getPrice();
        product.setPrice(0);
    }

    @Test(expected = AccessDeniedException.class)
    public void securedWriteAccessInsufficientAuthority() {
        setupAuthentication("ROLE_USER");
        product.setPrice(0);
    }

    private void setupAuthentication(String role) {
        Authentication authentication = new TestingAuthenticationToken(
                "ramnivas", "", role);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}