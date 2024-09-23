package in.yashjatkar.ecommerce_project.repository;

import in.yashjatkar.ecommerce_project.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category save(Category category);
    Category findByTitle(String title);
}
