package tacos.converter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tacos.entity.Ingredient;
import tacos.repository.IngredientRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientByIdConverterTest {

    private static final String EXIST_INGREDIENT_ID = "AAAA";
    private static final String NOT_EXIST_INGREDIENT_ID = "BBBB";

    @InjectMocks
    private IngredientByIdConverter ingredientByIdConverter;
    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        when(ingredientRepository.findById(EXIST_INGREDIENT_ID)).thenReturn(Optional.of(ingredient));
        when(ingredientRepository.findById(NOT_EXIST_INGREDIENT_ID)).thenReturn(Optional.empty());
    }

    @Test
    public void shouldReturnValueWhenPresent() {
        Ingredient actual = ingredientByIdConverter.convert(EXIST_INGREDIENT_ID);

        assertEquals(actual, ingredient);
    }

    @Test
    public void shouldReturnNullWhenMissing() {
        Ingredient actual = ingredientByIdConverter.convert(NOT_EXIST_INGREDIENT_ID);

        assertNull(actual);
    }
}
