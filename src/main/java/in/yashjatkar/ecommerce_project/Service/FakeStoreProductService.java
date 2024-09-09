package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Dto.FakeStoreDto;
import in.yashjatkar.ecommerce_project.Exception.ProductNotFoundException;
import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
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
// ----------------get single Product without exception-----------------------------------------------------
//        @Override
//    public Product getSingleProduct(Long id) throws ProductNotFoundException {
            //to store the response we get from fakestoredto
//      FakeStoreDto fakeStoreDto=
//                restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
//                        FakeStoreDto.class);//convert json response to FakeStoreDto.class
//
//        return fakeStoreDto.convertToProduct();
//        }
//-------------------------get sigle Product with exception--------------------------------------------------------
//@Override
//public Product getSingleProduct(Long id) throws ProductNotFoundException {
//            // Fetch the product from the external API
//            ResponseEntity<FakeStoreDto> response =
//                    restTemplate.getForEntity("https://fakestoreapi.com/products/" + id,
//                            FakeStoreDto.class);//convert json response to FakeStoreDto.class
//            try {
//                // Check if the response status is OK
//                if (response.getStatusCode() == HttpStatus.OK) {
//                    FakeStoreDto fakeStoreDto = response.getBody();
//
//                    // Check if the response body is not null
//                    if (fakeStoreDto != null) {
//                        // Convert FakeStoreDto to Product
//                        return fakeStoreDto.convertToProduct();
//                    } else {
//                        // Throw an exception if the product is not found in the response
//                        throw new ProductNotFoundException("Product with id " + id + " does not exist");
//                    }
//                } else {
//                    // Handle unexpected HTTP status codes
//                    throw new ProductNotFoundException("Product with id " + id + " does not exist");
//                }
//            } catch (RestClientException e) {
//                // Handle errors related to REST client communication (e.g., network issues)
//                throw new ProductNotFoundException("Product with id " + id + " does not exist");
//            }
//        }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException{
        // Fetch the product from the external API
        FakeStoreDto fakeStoreDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreDto.class);//convert json response to FakeStoreDto.class
        if(fakeStoreDto==null)
        {
            throw new ProductNotFoundException("get product less than 21");
        }
        return fakeStoreDto.convertToProduct();
    }



    @Override
    public List<Product> getAllProducts(){
            List<Product> products=new ArrayList<>();
        // Fetch the response from the external API
        FakeStoreDto[] response = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreDto[].class);

        // Check if the response is not null
        if (response != null)
        {
            // Convert each FakeStoreDto to Product and add to the list
            for (FakeStoreDto fakeStoreDto : response)
            {
                products.add(fakeStoreDto.convertToProduct());
            }
        }
        else
        {
            // Handle the case where the response is null
            // Throwing an exception may not be necessary if the API simply returns an empty list
            System.out.println("Products do not exist");
        }

        return products;
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
        if(response==null)
        {
            return null;
        }
        return response.convertToProduct();
    }
//--------------------------------Get All Category-------------------------------------------------------
public List<String> getAllCategory() {
    // Fetch the category names from the external API
    String[] categoryNames = restTemplate.getForObject(
            "https://fakestoreapi.com/products/categories",
            String[].class
    );
    if(categoryNames==null)
    {
        return null;
    }

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
//        if(fakeStoreDtos==null){return null;}
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

// -------------------DeleteProductUsingException-----------------------------------------------------------
//public void deleteProduct(Long id) {
//    // URL to access the product
//    String url = "https://fakestoreapi.com/products/" + id;
////    restTemplate.delete(url);
//    try
//    {
//        // Perform the DELETE request to remove the product from the external API
//        restTemplate.delete(url);
//
//        // Verify if the product was deleted
//        try
//        {
//            // Attempt to retrieve the product
//            FakeStoreDto fakeStoreDto = restTemplate.getForObject(url, FakeStoreDto.class);
//            if (fakeStoreDto != null)
//            {
//                throw new RuntimeException("Product deletion failed, product still exists.");
//            }
//        }
//        catch (HttpClientErrorException.NotFound e)
//        {
////HttpClientErrorException.NotFound is a specific exception in the Spring Framework's
////RestTemplate that indicates a 404 Not Found HTTP status response from a web service.
//// Product was not found, which means it was successfully deleted
//// You can log this if needed
//            System.out.println("Product successfully deleted.");
//        }
//
//    }
//    catch (HttpClientErrorException e)
//    {
//        // Handle other HTTP errors
//        if (e.getStatusCode() == HttpStatus.NOT_FOUND)
//        {
//            // The product was not found, which may indicate it was already deleted
//            System.out.println("Product was already deleted or does not exist.");
//        }
//        else
//        {
//            // Other HTTP errors
//            throw e; // Re-throw if it's not a 404 Not Found error
//        }
//    }
//    catch (Exception e)
//    {
//        // Handle unexpected exceptions
//        System.err.println("An unexpected error occurred while deleting the product: " + e.getMessage());
//        throw e; // Re-throw unexpected exceptions
//    }
//}
//-------------------------------------------------------------------------------------




}
