package tacos.service;

import tacos.entity.Ingredient;

import java.util.Collection;

public interface IngredientService {

    Collection<Ingredient> findAllIngredients();
}
