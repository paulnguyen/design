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

//Listing A.19 Controller to add and edit a product
package ajia.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import ajia.domain.Product;
import ajia.service.ProductService;

//import ...

@Controller
@SessionAttributes("product")
@RequestMapping("admin/productEdit.htm")
public class EditProductController {
    @Autowired private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public Product setupForm(@RequestParam(required=false) Long productId) {
        if (productId != null) {
            return productService.findProduct(productId);
        } else {
            return new Product();
        }
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String productEdit(Product product) {
        productService.updateProduct(product);
        return "redirect:productSummary.htm";
    }
}
