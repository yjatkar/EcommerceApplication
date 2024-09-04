package in.yashjatkar.ecommerce_project.Service;

import in.yashjatkar.ecommerce_project.Model.Category;
import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface ProductService {

    public Product getSingleProduct(Long id);
}
