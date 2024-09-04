package in.yashjatkar.ecommerce_project.Dto;

import in.yashjatkar.ecommerce_project.Model.Category;
import in.yashjatkar.ecommerce_project.Model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
    private Long id;
    private String Title;
    private Double price;
    private String description;
    private String image;
    private String category;

    public Product convertToProduct(){
        Product product=new Product();
        product.setId(id);
        product.setTitle(Title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImage(image);
        Category productCategory=new Category();
        productCategory.setTitle(category);
        product.setCategory(productCategory);
        return product;

    }
}
