package in.yashjatkar.ecommerce_project.Controller;

import in.yashjatkar.ecommerce_project.Model.Product;
import in.yashjatkar.ecommerce_project.Service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//i.e this is not normal controller but take rest requests
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService)
    {
        this.productService=productService;
    }
    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id)
    {
        return productService.getSingleProduct(id);
    }
}
