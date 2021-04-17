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

//Listing 11.15 Testing a JPA-based repository implementation
package ajia.repository.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ajia.domain.Product;
import ajia.repository.ProductRepository;

//import ...

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class ProductRepositoryTest {
    private @Autowired
    ProductRepository productRepository;
    private @Autowired
    SimpleJdbcOperations jdbcTemplate;

    @Test
    public void update() {
        Product product = productRepository.find(1001L);
        product.setName("foo");
        productRepository.update(product);
        String updatedName = jdbcTemplate.queryForObject(
                "select name from products p where p.id = ?", String.class,
                1001L);
        assertEquals("foo", updatedName);
    }
}