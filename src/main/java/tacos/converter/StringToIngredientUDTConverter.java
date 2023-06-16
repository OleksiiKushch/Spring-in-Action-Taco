package tacos.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tacos.model.Ingredient;
import tacos.repository.IngredientRepository;
import tacos.udt.IngredientUDT;
import tacos.udt.util.UDTUtils;

import java.util.Optional;

@Component
public class StringToIngredientUDTConverter implements Converter<String, IngredientUDT> {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public StringToIngredientUDTConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public IngredientUDT convert(@NonNull String id) {
        Optional<Ingredient> ingredient = ingredientRepo.findById(id);
        if (ingredient.isPresent()) {
            return ingredient.map(UDTUtils::toIngredientUDT).get();
        }
        return null;
    }
}
