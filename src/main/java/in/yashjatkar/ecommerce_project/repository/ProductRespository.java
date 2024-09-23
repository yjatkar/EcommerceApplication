package in.yashjatkar.ecommerce_project.repository;

import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
//Product is the class and Long is primary key for class Product
public interface ProductRespository extends JpaRepository<Product,Long>
{
    Product save(Product p);
}
