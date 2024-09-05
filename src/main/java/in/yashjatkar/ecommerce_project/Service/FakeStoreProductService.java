package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Dto.FakeStoreDto;
import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class FakeStoreProductService implements ProductService{
        RestTemplate restTemplate;
        public FakeStoreProductService(RestTemplate restTemplate)
        {
            this.restTemplate=restTemplate;
        }
        @Override
    public Product getSingleProduct(Long id)
        {
            //to store the response we get from fakestoredto
      FakeStoreDto fakeStoreDto=
                restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                        FakeStoreDto.class);//convert json response to FakeStoreDto.class

        return fakeStoreDto.convertToProduct();
        }

//        --------------------------------------------------
//        ResponseEntity<FakeStoreDto> response=
//                restTemplate.getForEntity("https://fakestoreapi.com/products/"+id,
//                        FakeStoreDto.class);//convert json response to FakeStoreDto.class
//        try{
//            if(response.getStatusCode()==HttpStatus.OK)
//            {
//                FakeStoreDto fakeStoreDto=response.getBody();
//                if(fakeStoreDto!=null)
//                {
//                    return fakeStoreDto.convertToProduct();
//                }
//                else
//                {
//                    throw new ResourceNotFoundException("")
//                }
//            }
//        }
//    }

    @Override
    public List<Product> getAllProducts(){
            List<Product> product=new ArrayList<>();
            FakeStoreDto[] fakeStoreDtos=
                    restTemplate.getForObject("https://fakestoreapi.com/products",
                            FakeStoreDto[].class);
            for(FakeStoreDto fakeStoreDto:fakeStoreDtos)
            {
                product.add(fakeStoreDto.convertToProduct());
            }
            return product;
    }

    @Override
    public Product CreateProduct(String title,Double price, String description,
                                 String image, String category) {
        FakeStoreDto fakeStoreDto = new FakeStoreDto();
        fakeStoreDto.setTitle(title);
        fakeStoreDto.setPrice(price);
        fakeStoreDto.setDescription(description);
        fakeStoreDto.setImage(image);
        fakeStoreDto.setCategory(category);

        FakeStoreDto response =
                restTemplate.postForObject(
                        "https://fakestoreapi.com/products",//fakeStoreurl for create product
                        fakeStoreDto, //object to be inserted
                        FakeStoreDto.class   //convert json response to this class format
                );
        return response.convertToProduct();
    }
//---------------------------------------------------------------------------------------
public List<String> getAllCategory() {
    // Fetch the category names from the external API
    String[] categoryNames = restTemplate.getForObject(
            "https://fakestoreapi.com/products/categories",
            String[].class
    );

    // Convert the array to a List
    return Arrays.asList(categoryNames);
}
//-------------------------------------------------------------------------------------------

    public List<Product> getAllProductsForCategory(String title)
    {
        List<Product> products=new ArrayList<>();
        FakeStoreDto[] fakeStoreDtos=
                restTemplate.getForObject(
                        "https://fakestoreapi.com/products/category/"+title,
                        FakeStoreDto[].class
                );
        for(FakeStoreDto fakeStoreDto:fakeStoreDtos)
        {
            products.add(fakeStoreDto.convertToProduct());
        }
        return products;


    }
    public Product updateProduct(String title,Double price,String description,
                                 String category,String image,Long id)
    {
        FakeStoreDto fakeStoreDto=
                restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                        FakeStoreDto.class);
        if (fakeStoreDto == null) {
            throw new RuntimeException("Product not found with id: " + id+"try id less than 20");
        }
        if(title!=null)
        {
              fakeStoreDto.setTitle(title);
        }
        if(price!=null)
        {
            fakeStoreDto.setPrice(price);
        }
        if(description!=null)
        {
            fakeStoreDto.setDescription(description);
        }
        if(category!=null)
        {
            fakeStoreDto.setCategory(category);
        }
        if(image!=null)
        {
            fakeStoreDto.setImage(image);
        }
        return fakeStoreDto.convertToProduct();
    }

    public void deleteProduct(Long id){
            restTemplate.delete("https://fakestoreapi.com/products/"+id);

    }
// ------------------------------------------------------------------------------
//public void deleteProduct(Long id) {
//    // URL to access the product
//    String url = "https://fakestoreapi.com/products/" + id;
////    restTemplate.delete(url);
//    try {
//        // Perform the DELETE request to remove the product from the external API
//        restTemplate.delete(url);
//
//        // Verify if the product was deleted
//        try {
//            // Attempt to retrieve the product
//            FakeStoreDto fakeStoreDto = restTemplate.getForObject(url, FakeStoreDto.class);
//            if (fakeStoreDto != null) {
//                throw new RuntimeException("Product deletion failed, product still exists.");
//            }
//        }
//        catch (HttpClientErrorException.NotFound e) {
////HttpClientErrorException.NotFound is a specific exception in the Spring Framework's
////RestTemplate that indicates a 404 Not Found HTTP status response from a web service.
//            // Product was not found, which means it was successfully deleted
//            // You can log this if needed
//            System.out.println("Product successfully deleted.");
//        }
//
//    }
//    catch (HttpClientErrorException e) {
//        // Handle other HTTP errors
//        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
//            // The product was not found, which may indicate it was already deleted
//            System.out.println("Product was already deleted or does not exist.");
//        } else {
//            // Other HTTP errors
//            throw e; // Re-throw if it's not a 404 Not Found error
//        }
//    }
//    catch (Exception e) {
//        // Handle unexpected exceptions
//        System.err.println("An unexpected error occurred while deleting the product: " + e.getMessage());
//        throw e; // Re-throw unexpected exceptions
//    }
//}
//-------------------------------------------------------------------------------------




}
