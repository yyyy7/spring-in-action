package tacos.data;

import tacos.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository {

  //* Iterable<Ingredient> findAll();

  //* Ingredient findOne(String id);

  //* Ingredient save(Ingredient ingredient);

  public interface InnerIngredientRepository extends CrudRepository<Ingredient, String> {
  
    
  }
}