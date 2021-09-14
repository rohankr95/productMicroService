package com.example.demoproduct.controller;

import com.example.demoproduct.entity.MerchantPriQuaDetails;
import com.example.demoproduct.entity.Product;
import com.example.demoproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    KafkaTemplate<String, Object> template;
    @GetMapping(value="/{productId}")
    public Product get(@PathVariable(name="productId") String productId)
    {

        return productService.get(productId);
    }

    @PostMapping(value="/{mId}")
    public Product save(@RequestBody Product product,@PathVariable(name="mId") String mId)
    {
        System.out.println("in product post");
        MerchantPriQuaDetails details = new MerchantPriQuaDetails();
        details.setPrice(product.getProductPrice());
        details.setQuantity(product.getProductQuantity());
        

        Product savedProduct = productService.save(product);
        template.send("product",product);
        //Get Product details from merchant and send the product id to the merchant

        return savedProduct;

    }

    @PutMapping(value = "/{productId}")
    public Product update(@PathVariable(name="productId") String productId,@RequestBody Product product)
    {
        //
        Product p=this.get(productId);
        if(p!=null)
        {
            if(p!=null){
                if(product.getProductId()!=null){
                    p.setProductId(product.getProductId());
                }else if(product.getProductName()!=null){
                    p.setProductName(product.getProductName());
                }
                else if(product.getProductImage()!=null){
                    p.setProductImage(product.getProductImage());
                }
                else if(product.getProductCategory()!=null){
                    p.setProductCategory(product.getProductCategory());
                }
                p.setProductQuantity(product.getProductQuantity());
                p.setProductPrice(product.getProductPrice());

            }else{
                System.out.println("The object doesn't exist");
                return null;
            }

        }
        Product savedProduct = productService.save(p);
        template.send("product-update",p);
        return productService.save(savedProduct);
    }

    //Adding of things from merchant

    @DeleteMapping(value = "/{productId}")
    public void delete(@PathVariable(name="productId") String productId)
    {
        template.send("product-delete",productId);
        productService.delete(productId);
    }
}
