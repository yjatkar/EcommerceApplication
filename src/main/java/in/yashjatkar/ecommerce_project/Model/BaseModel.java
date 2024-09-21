package in.yashjatkar.ecommerce_project.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class BaseModel {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted;
}
