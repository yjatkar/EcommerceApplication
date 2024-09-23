package in.yashjatkar.ecommerce_project.Model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    //both models(product and category dont have primary key
    // so create a primary key here so models can extend that)
    @Id
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted;
}
