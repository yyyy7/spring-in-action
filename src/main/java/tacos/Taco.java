package tacos;

import java.util.List;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.*;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  @ManyToMany(targetEntity = Ingredient.class)
  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<Ingredient> ingredients;


  private Date createdAt;

  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
}