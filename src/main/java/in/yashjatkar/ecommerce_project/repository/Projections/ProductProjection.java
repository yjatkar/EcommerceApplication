package in.yashjatkar.ecommerce_project.repository.Projections;

import in.yashjatkar.ecommerce_project.Model.Category;

public interface ProductProjection {
    Long getId();
    String getTitle();
    String getDescription();
    Double getPrice();
    Category getCategory();
    String getImage();

}
