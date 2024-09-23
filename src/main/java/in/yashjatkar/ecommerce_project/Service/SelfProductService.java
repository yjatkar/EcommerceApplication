package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Exception.ProductNotFoundException;
import in.yashjatkar.ecommerce_project.Model.Category;
import in.yashjatkar.ecommerce_project.Model.Product;
import in.yashjatkar.ecommerce_project.repository.CategoryRepository;
import in.yashjatkar.ecommerce_project.repository.ProductRespository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private RestTemplate restTemplate;
    private ProductRespository productRespository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRespository productRespository,
                              RestTemplate restTemplate,
                              CategoryRepository categoryRepository
                              )
    {
        this.productRespository=productRespository;
        this.restTemplate=restTemplate;
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product CreateProduct(String title, Double price, String description, String image, String category) {
        Product product=new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);

        //check if category already present
        Category categoryFromDatabase=categoryRepository.findByTitle(category);

//        --------------we are using persist in Product table so even if category not present
//                no need to save seperately it will be done by cascade.Persist-----------
        if(categoryFromDatabase==null)
        {
            Category newCategory=new Category();
            newCategory.setTitle(category);
//            categoryFromDatabase=categoryRepository.save(newCategory);//if without cascade.Persist
//            use this above line to save Category first
            categoryFromDatabase=newCategory;//no need to save category it is done by persist
        }
        product.setCategory(categoryFromDatabase);
        Product savedProduct=productRespository.save(product);

        return savedProduct;


    }

    @Override
    public List<String> getAllCategory() {
        return List.of();
    }

    @Override
    public List<Product> getAllProductsForCategory(String title) {
        return List.of();
    }

    @Override
    public Product updateProduct(String title, Double price, String description, String category, String image, Long id) {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        return null;
    }
}
