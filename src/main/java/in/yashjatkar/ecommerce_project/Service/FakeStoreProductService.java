package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Dto.FakeStoreDto;
import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.stereotype.Service;
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
    public Product CreateProduct(Long id, String title,Double price, String description,
                                 String image, String category) {
        FakeStoreDto fakeStoreDto = new FakeStoreDto();
        fakeStoreDto.setId(id);
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






}
