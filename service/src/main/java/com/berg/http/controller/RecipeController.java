package com.berg.http.controller;

import com.berg.dto.recipe.RecipeCreateDto;
import com.berg.dto.recipe.RecipeFilter;
import com.berg.entity.Role;
import com.berg.service.AuthorService;
import com.berg.service.CategoryRecipeService;
import com.berg.service.ProductService;
import com.berg.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final ProductService productService;
    private final AuthorService authorService;
    private final CategoryRecipeService categoryRecipeService;

    @GetMapping
    public String findAll(Model model, RecipeFilter filter) {
        model.addAttribute("recipes", recipeService.findAll());
        return "recipe/recipes";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", Role.values());
        return recipeService.findById(id)
                .map(recipe -> {
                    model.addAttribute("recipe", recipe);
                    return "recipe/recipe";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated RecipeCreateDto recipe,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipe", recipe);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/recipes/create";
        }
        return "redirect:/recipes/" + recipeService.create(recipe).id();
    }

    @GetMapping("/create")
    public String create(Model model,
                         RecipeCreateDto recipe) {
        model.addAttribute("recipe", recipe);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("categories", categoryRecipeService.findAll());
        model.addAttribute("products", productService.findAll());
        return "recipe/create";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute RecipeCreateDto recipeDto) {
        return recipeService.update(id, recipeDto)
                .map(it -> "redirect:/recipes/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         Model model,
                         @CurrentSecurityContext SecurityContext securityContext,
                         @AuthenticationPrincipal UserDetails userDetails) {
        return recipeService.findById(id)
                .map(recipe -> {
                    model.addAttribute("recipe", recipe);
                    model.addAttribute("categories", categoryRecipeService.findAll());
                    model.addAttribute("authors", authorService.findAll());
                    model.addAttribute("products", productService.findAll());
                    return "/recipe/update";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!recipeService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/recipes";
    }
}
