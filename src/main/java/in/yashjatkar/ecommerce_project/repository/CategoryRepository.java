package in.yashjatkar.ecommerce_project.repository;

import in.yashjatkar.ecommerce_project.Model.Category;
import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category save(Category category);
    Category findByTitle(String title);
    List<Category> findAll();
//    List<Product> findAllByCategory(@Param("category") String category);
}
