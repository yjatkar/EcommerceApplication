package in.yashjatkar.ecommerce_project.Controller;

import in.yashjatkar.ecommerce_project.Dto.CreateProductDto;
import in.yashjatkar.ecommerce_project.Model.Product;
import in.yashjatkar.ecommerce_project.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //get All products
    @GetMapping()
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    //create a Product
//    1.we need to take one object to insert data that we give in postman let say-->createObjectDto
//    {
//        "id":21,
//            "title":"Iphone 14",
//            "price":70000,
//            "description":"we are adding new column",
//            "image":"localhost://image.com",
//            "category":"Electronics"
//    }
    @PostMapping()
    public Product CreateProduct(@RequestBody CreateProductDto requestDto)
    {
        return productService.CreateProduct(
            requestDto.getId(),
                requestDto.getTitle(),
                requestDto.getPrice(),
                requestDto.getDescription(),
                requestDto.getImage(),
                requestDto.getCategory()

        );

    }

    @GetMapping("/categories")
    public List<String> getAllCategory()
    {
        return productService.getAllCategory();
    }

//    fetch('https://fakestoreapi.com/products/category/jewelery')
//            .then(res=>res.json())
//            .then(json=>console.log(json))
    //get All Products related to Specific Category
    @GetMapping("/category/{title}")
    public List<Product> getAllProductsForCategory(@PathVariable("title")
                                                   String title)
    {
        return productService.getAllProductsForCategory(title);
    }

}

