package in.yashjatkar.ecommerce_project.repository;

import in.yashjatkar.ecommerce_project.Model.Category;
import in.yashjatkar.ecommerce_project.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Product is the class and Long is primary key for class Product
public interface ProductRepository extends JpaRepository<Product,Long>
{
    Product save(Product p);
  void deleteById(Long id);
Product findProductById(@Param("id") Long id);
List<Product> findByCategoryTitle(String title);
List<Product> findByCategory(Category category);
//----test1----
List<Product> findByCategory_Title(String category);//USED TO GO IN OBJECT
List<Product> findByCategory_TitleContaining(String category);//works as for like operator

//test2-->using hql queries
    @Query("select p from Product p where p.category.title=:categoryName")
    List<Product> findByCategoryName(String categoryName);

    @Query("select p.title from Product p where p.category.title=:category")
    List<String> someTitleMethod(String category);
}
