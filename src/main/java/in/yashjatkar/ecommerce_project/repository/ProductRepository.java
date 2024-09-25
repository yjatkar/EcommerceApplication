package in.yashjatkar.ecommerce_project.repository;

import in.yashjatkar.ecommerce_project.Model.Category;
import in.yashjatkar.ecommerce_project.Model.Product;
import in.yashjatkar.ecommerce_project.repository.Projections.ProductProjection;
import in.yashjatkar.ecommerce_project.repository.Projections.ProductWithIdAndTitle;
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

//test2-->using hql queries(fetch whole product)
    @Query("select p from Product p where p.category.title=:categoryName")
    List<Product> findByCategoryName(String categoryName);

    //test3-->hql query fetch specific columns i.e only title from table
    @Query("select p.title from Product p where p.category.title=:categoryName")
    List<String> someTitleMethod(String categoryName);

    //test4-->hql query fetch specific columns i.e only title  and id from table
    @Query("select p.id as id,p.title as title from Product p where p.category.title=:categoryName")
    List<ProductWithIdAndTitle> MethodToGetIdAndTitle( String categoryName);

    //test5--->using productprojection
    @Query("select p.id as id,p.title as title,p.price as price from Product p where p.category.title=:categoryName")
    List<ProductProjection> GetIdAndTitleAndPrice(String categoryName);

    // native sql query
    @Query(value="select * from Product p where p.id=:id",nativeQuery = true)
    Product someNativeSql(Long id);
}
