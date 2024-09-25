package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Exception.ProductNotFoundException;
import in.yashjatkar.ecommerce_project.Model.Product;

import java.util.List;
import java.util.Optional;


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
    public Product replaceProduct(Long id,
                                  String title,
                                  Double price,
                                  String description,
                                  String category,
                                  String image)
            throws ProductNotFoundException;
    public Product deleteProduct(Long id) throws ProductNotFoundException;
}
