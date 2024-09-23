package in.yashjatkar.ecommerce_project.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
//    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    @ManyToOne(cascade = {CascadeType.PERSIST})//i.e.Many Products can have same category
//    CascadeType.PERSIST-->if someone try to create new product and category for
//    that product does not exist in that case category will be cretaed for that product
//    first and then Product will be created

    private Category category;

}
