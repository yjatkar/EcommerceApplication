package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Model.Product;

import java.util.List;


public interface ProductService {

    public Product getSingleProduct(Long id);
    public List<Product> getAllProducts();
    public Product CreateProduct(Long id,
                                 String title,
                                 Double price,
                                 String description,
                                 String image,
                                 String category);
public List<String> getAllCategory();
}
