package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Exception.ProductNotFoundException;
import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{

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
        return null;
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
