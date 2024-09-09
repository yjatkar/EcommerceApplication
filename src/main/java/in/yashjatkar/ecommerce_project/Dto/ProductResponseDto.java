package in.yashjatkar.ecommerce_project.Dto;

import in.yashjatkar.ecommerce_project.Model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;

}
