package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Dto.FakeStoreDto;
import in.yashjatkar.ecommerce_project.Model.Category;
import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

}
