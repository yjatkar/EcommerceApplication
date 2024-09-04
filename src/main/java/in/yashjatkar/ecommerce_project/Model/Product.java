package in.yashjatkar.ecommerce_project.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private Category category;

}
