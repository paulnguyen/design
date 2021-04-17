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

//Listing 16.18 An aspect to introduce the id-based API
package ajia.domain;

import ajia.repository.ProductRepository;

//...

public aspect OrderSimplificationAspect {
    private ProductRepository productRepository;

    public void Order.addProduct(Long productId, int quantity) {
        Product product = OrderSimplificationAspect.aspectOf().getProduct(productId);
        addProduct(product, quantity);
    }

    public void Order.removeProduct(Long productId, int quantity) {
        Product product = OrderSimplificationAspect.aspectOf().getProduct(productId);
        removeProduct(product, quantity);
    }
    
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private Product getProduct(Long productId) {
        return OrderSimplificationAspect.aspectOf()
                .productRepository.find(productId);
    }
}
