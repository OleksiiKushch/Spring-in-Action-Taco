package tacos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Document (collection = "tacos")
public class Taco {

    @Id
    private String id;

    @NotBlank (message = "Name is required")
    @Size (min = 5, message = "Name must be at least {min} characters long")
    private String name;

    private Date createdAt = new Date();

    @Size (min = 1, message = "You must choose at least {min} ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
