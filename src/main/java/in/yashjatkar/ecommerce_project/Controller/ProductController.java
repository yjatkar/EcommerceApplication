package in.yashjatkar.ecommerce_project.Controller;

import in.yashjatkar.ecommerce_project.Dto.CreateProductDto;
import in.yashjatkar.ecommerce_project.Dto.ErrorDto;
import in.yashjatkar.ecommerce_project.Exception.ProductNotFoundException;
import in.yashjatkar.ecommerce_project.Model.Product;
import in.yashjatkar.ecommerce_project.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//    -----------------------responseEntity----------------------
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id)
//    {
//        -----------------way 1--------------------------------------
//       Product product=productService.getSingleProduct(id);
////       ResponseEntity<Product> response=new ResponseEntity<>(product,HttpStatus.NOT_FOUND);//status=404
//        ResponseEntity<Product> response=new ResponseEntity<>(product,HttpStatus.OK);//status=200
//        return response;
//        ------------------way2 ----------------------------------
//        try {
//            Product product = productService.getSingleProduct(id);
//
//            if (product != null) {
//                return ResponseEntity.ok(product); // 200 OK
//            } else {
//                return ResponseEntity.notFound().build(); // 404 Not Found
//            }
//        }
//        catch (Exception e) {
//            // Log the exception and return a generic error response
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
//        }
//    }
//    ----------------------------------------------------------------------
    //get single product
    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id)
    throws ProductNotFoundException
    {
        return productService.getSingleProduct(id);
    }


    //get All products
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product> products=productService.getAllProducts();
        ResponseEntity<List<Product>> response=new ResponseEntity<>(products, HttpStatus.OK);//status=200
//        ResponseEntity<List<Product>> response=new ResponseEntity<>(products, HttpStatus.NOT_FOUND);//status=404
        return response;
        //Returns a 200 OK status with the list of products when
        // the list is not empty.
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
    //get All Products related to Specific Category
    @GetMapping("/category/{title}")
    public List<Product> getAllProductsForCategory(@PathVariable("title")
                                                   String title)
    {
        return productService.getAllProductsForCategory(title);
    }

//    Update a product
//    fetch('https://fakestoreapi.com/products/7')
    @PatchMapping("/{id}")
    public Product updateProduct(@RequestBody CreateProductDto createProductDto,
                                 @PathVariable("id") Long id)
    {
//        return productService.updateProduct(
//                createProductDto.getTitle(),
//                createProductDto.getPrice(),
//                createProductDto.getDescription(),
//                createProductDto.getImage(),
//                createProductDto.getCategory(),
//                id);
        return productService.updateProduct(
                createProductDto.getTitle(),
                createProductDto.getPrice(),
                createProductDto.getDescription(),
                createProductDto.getCategory(),
                createProductDto.getImage(),
                id
        );
    }
//DELETE A PRODUCT
//fetch('https://fakestoreapi.com/products/6',
@DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id)
    {
         productService.deleteProduct(id);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception)
//    {
//        ErrorDto errorDto=new ErrorDto();
//        errorDto.setMessage(exception.getMessage());
//        return new ResponseEntity<>(errorDto,HttpStatus.NOT_FOUND);
//    }
}

