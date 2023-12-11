package tacos.service.impl;

import org.springframework.stereotype.Service;
import tacos.entity.Ingredient;
import tacos.repository.IngredientRepository;
import tacos.service.IngredientService;

import java.util.Collection;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Collection<Ingredient> findAllIngredients() {
        return (Collection<Ingredient>) ingredientRepository.findAll();
    }
}
