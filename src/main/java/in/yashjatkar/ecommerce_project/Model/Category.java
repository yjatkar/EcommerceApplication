package in.yashjatkar.ecommerce_project.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category  extends BaseModel{
//    private Long Category_id;
    private String title;

    @OneToMany(mappedBy="category",cascade={CascadeType.REMOVE})//i.e.one category can have many Products
    //mapped by-->used to tell spring that we have already mapped each product with category
    // in Product table only
    //CascadeType.REMOVE-->if someone try to remove category then all products related
    // to that category will be removed
    private List<Product> products;//i.e.one category contains how many products
}
