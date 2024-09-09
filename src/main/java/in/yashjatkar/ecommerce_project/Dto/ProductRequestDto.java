package in.yashjatkar.ecommerce_project.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
