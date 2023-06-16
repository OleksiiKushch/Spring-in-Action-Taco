package tacos.udt.util;

import tacos.model.Ingredient;
import tacos.model.Taco;
import tacos.udt.IngredientUDT;
import tacos.udt.TacoUDT;

public class UDTUtils {

    private UDTUtils() {}

    public static TacoUDT toTacoUDT(Taco taco) {
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

}
