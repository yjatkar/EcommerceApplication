package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Exception.ProductNotFoundException;
import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface ProductService {

    public Product getSingleProduct(Long id) throws ProductNotFoundException;
    public List<Product> getAllProducts();
    public Product CreateProduct(
                                 String title,
                                 Double price,
                                 String description,
                                 String image,
                                 String category);
public List<String> getAllCategory();
    public List<Product> getAllProductsForCategory(String title);
    public Product updateProduct(String title,
                                 Double price,
                                 String description,
                                 String category,
                                 String image,
                                 Long id);

    public void deleteProduct(Long id);
}
