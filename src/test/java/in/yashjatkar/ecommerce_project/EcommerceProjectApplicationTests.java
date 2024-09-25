package in.yashjatkar.ecommerce_project;

import in.yashjatkar.ecommerce_project.Model.Product;
import in.yashjatkar.ecommerce_project.repository.CategoryRepository;
import in.yashjatkar.ecommerce_project.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EcommerceProjectApplicationTests {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    //----test1----
    @Test
    void TestJpaDeclaredQueries(){
        List<Product> products=productRepository.findByCategory_Title("electronics");
//     or   List<Product> products=productRepository.findByCategory_TitleContaining("elec");
        for(Product product:products)
        {
            System.out.println(product.getTitle());
        }
    }
//    created orm queries
//    Hibernate: select p1_0.id,p1_0.category_id,p1_0.created_at,p1_0.description,p1_0.image,p1_0.is_deleted,p1_0.price,p1_0.title,p1_0.updated_at from product p1_0 left join category c1_0 on c1_0.id=p1_0.category_id where c1_0.title=?
//    Hibernate: select c1_0.id,c1_0.created_at,c1_0.is_deleted,c1_0.title,c1_0.updated_at from category c1_0 where c1_0.id=?

    //test2-->using hql
    @Test
    void TestHql(){
        List<Product> products=productRepository.findByCategoryName("electronics");//WHOLE PRODUCT FETCHED
        for(Product product:products)
        {
            System.out.print(product.getTitle()+" its cost is--->");
            System.out.println(product.getPrice());
        }
    }

    //test 3
    @Test
    void TestSpecificFields(){
        List<String> products=productRepository.someTitleMethod("electronics");
        for(String product:products)
        {
            System.out.println(product);
        }
    }
}
