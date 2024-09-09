package in.yashjatkar.ecommerce_project.Controller;

import in.yashjatkar.ecommerce_project.Dto.ProductRequestDto;
import in.yashjatkar.ecommerce_project.Dto.ProductResponseDto;
import in.yashjatkar.ecommerce_project.Exception.ProductNotFoundException;
import in.yashjatkar.ecommerce_project.Model.Product;
import in.yashjatkar.ecommerce_project.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController//i.e this is not normal controller but take rest requests
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    ModelMapper modelMapper;
    public ProductController(ProductService productService,ModelMapper modelMapper)
    {
        this.productService=productService;
        this.modelMapper=modelMapper;
    }
//-----------------------responseEntity-----------------------------------------------
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
//--------------------------------------------------------------------------------------


//    get single product
    @GetMapping("/{id}")
    public ProductResponseDto getSingleProduct(@PathVariable("id") Long id)
    throws ProductNotFoundException
    {
        Product product=productService.getSingleProduct(id);
//        return modelMapper.map(product, ProductResponseDto.class);//added in exception
        return convertToProductResponseDto(product);
    }



//---------------------------------GET ALL PRODUCTS---------------------------------------
//    get All products
//    @GetMapping()
//    public ResponseEntity<List<Product>> getAllProducts()
//    {
//        List<Product> products=productService.getAllProducts();
//        ResponseEntity<List<Product>> response=new ResponseEntity<>(products, HttpStatus.OK);//status=200
////        ResponseEntity<List<Product>> response=new ResponseEntity<>(products, HttpStatus.NOT_FOUND);//status=404
//        return response;
//        //Returns a 200 OK status with the list of products when
//        // the list is not empty.
//
//    }

    //   get All products covert to productResponseDto
    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts()
    {
        List<ProductResponseDto> productRepsonseDto=new ArrayList<>();
        List<Product> products=productService.getAllProducts();
        //covert to productResponseDto
        for(Product product:products)
        {
            productRepsonseDto.add(convertToProductResponseDto((product)));
        }
        ResponseEntity<List<ProductResponseDto>> response=new ResponseEntity<>(productRepsonseDto, HttpStatus.OK);//status=200
//        ResponseEntity<List<Product>> response=new ResponseEntity<>(products, HttpStatus.NOT_FOUND);//status=404
        return response;
        //Returns a 200 OK status with the list of products when the list is not empty.

    }



//------------------------CREATE PRODUCT---------------------------------------------------
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
//    @PostMapping()
//    public Product CreateProduct(@RequestBody ProductRequestDto requestDto)
//    {
//        //call method from productService--->CreateProduct Method
//        return productService.CreateProduct(
//
//                requestDto.getTitle(),
//                requestDto.getPrice(),
//                requestDto.getDescription(),
//                requestDto.getImage(),
//                requestDto.getCategory()
//
//        );
//
//    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> CreateProduct(@RequestBody ProductRequestDto requestDto)
    {
        //call method from productService--->CreateProduct Method
        Product product= productService.CreateProduct(

                requestDto.getTitle(),
                requestDto.getPrice(),
                requestDto.getDescription(),
                requestDto.getImage(),
                requestDto.getCategory()

        );
        ProductResponseDto productResponseDto=convertToProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto,HttpStatus.CREATED);//stATUS CODE=201

    }


//    -------------------------GET ALL CATEGORIES------------------------------------------
    @GetMapping("/categories")
    public List<String> getAllCategory()
    {
        return productService.getAllCategory();
    }
//----------------------------GET ALL PRODUCTS FOR A CATEGORY-------------------------------
//    fetch('https://fakestoreapi.com/products/category/jewelery')
    //get All Products related to Specific Category
    ////way1/////////////////////////////////////////////////
//    @GetMapping("/category/{title}")
//    public List<Product> getAllProductsForCategory(@PathVariable("title")
//                                                   String title)
//    {
//        return productService.getAllProductsForCategory(title);
//    }
    ///////////way2 using product responsedto
@GetMapping("/category/{title}")
public List<ProductResponseDto> getAllProductsForCategory(@PathVariable("title") String title)
{
    List<ProductResponseDto> productResponseDto=new ArrayList<>();
    List<Product> products= productService.getAllProductsForCategory(title);
    for(Product product:products)
    {
        productResponseDto.add(convertToProductResponseDto(product));
    }
    return productResponseDto;
}


//    ---------------------UPDATE A PRODUCT------------------------------------------------
//    Update a product
//    fetch('https://fakestoreapi.com/products/7')
    //way 1//////////////////////////////////////
//    @PatchMapping("/{id}")
//    public Product updateProduct(@RequestBody ProductRequestDto productRequestDto,
//                                 @PathVariable("id") Long id)
//    {
//        return productService.updateProduct(
//                productRequestDto.getTitle(),
//                productRequestDto.getPrice(),
//                productRequestDto.getDescription(),
//                productRequestDto.getCategory(),
//                productRequestDto.getImage(),
//                id
//        );
//    }

    //////////////way2
    @PatchMapping("/{id}")
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productRequestDto,
                                 @PathVariable("id") Long id)
    {
        Product product= productService.updateProduct(
                productRequestDto.getTitle(),
                productRequestDto.getPrice(),
                productRequestDto.getDescription(),
                productRequestDto.getCategory(),
                productRequestDto.getImage(),
                id

        );
        return convertToProductResponseDto(product);
    }

//-------------------------DELETE A PRODUCT------------------------------------------------
//DELETE A PRODUCT
//fetch('https://fakestoreapi.com/products/6',
@DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id)
    {
         productService.deleteProduct(id);
    }
//-------------------------------------------------------------------------------------------
    private ProductResponseDto convertToProductResponseDto(Product product){
        String categoryTitle=product.getCategory().getTitle();
        ProductResponseDto productResponseDto=modelMapper.map(product, ProductResponseDto.class);
        productResponseDto.setCategory(categoryTitle);
        return productResponseDto;
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception)
//    {
//        ErrorDto errorDto=new ErrorDto();
//        errorDto.setMessage(exception.getMessage());
//        return new ResponseEntity<>(errorDto,HttpStatus.NOT_FOUND);
//    }
}

