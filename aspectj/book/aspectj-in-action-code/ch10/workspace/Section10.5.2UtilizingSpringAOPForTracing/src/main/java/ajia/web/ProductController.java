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

package ajia.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ajia.domain.Product;
import ajia.service.ProductService;

@Controller
public class ProductController {
    @Autowired private ProductService productService;

	@RequestMapping("/productSummary.htm")
	@ModelAttribute("products")
	public List<Product> productSummary() {
		return productService.findProducts();
	}

	@RequestMapping("/productDetails.htm")
	public Product productDetails(Long productId) {
		return productService.findProduct(productId);
	}
}
