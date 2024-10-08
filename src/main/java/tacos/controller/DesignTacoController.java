package tacos.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import tacos.entity.Ingredient;
import tacos.entity.Ingredient.Type;
import tacos.entity.Taco;
import tacos.entity.TacoOrder;
import tacos.entity.User;
import tacos.service.IngredientService;
import tacos.service.TacoService;
import tacos.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping ("/design")
@SessionAttributes ("tacoOrder")
public class DesignTacoController {

    private final IngredientService ingredientService;
    private final TacoService tacoService;
    private final UserService userService;

    public DesignTacoController(IngredientService ingredientService, TacoService tacoService, UserService userService) {
        this.ingredientService = ingredientService;
        this.tacoService = tacoService;
        this.userService = userService;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientService.findAllIngredients();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute ("tacoOrder")
    public TacoOrder createTacoOrder() {
        return new TacoOrder();
    }

    @ModelAttribute ("taco")
    public Taco createTaco() {
        return new Taco();
    }

    @ModelAttribute ("user")
    public User getUser(Principal principal) {
        String username = principal.getName();
        return userService.findUserByUsername(username);
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        taco = tacoService.create(taco);
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}