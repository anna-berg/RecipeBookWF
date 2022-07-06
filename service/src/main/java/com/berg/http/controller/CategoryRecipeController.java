package com.berg.http.controller;

import com.berg.dto.category.CategoryCreateDto;
import com.berg.service.CategoryRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRecipeController {

    private final CategoryRecipeService categoryService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/categories";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return categoryService.findById(id)
                .map(categoryRecipeReadDto -> {
                    model.addAttribute("category", categoryRecipeReadDto);
                    return "category/category";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute CategoryCreateDto categoryCreateDto) {
        return "redirect:/categories/" + categoryService.create(categoryCreateDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute CategoryCreateDto categoryCreateDto) {
        return categoryService.update(id, categoryCreateDto)
                .map(categoryDto -> "redirect:/categories/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!categoryService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/categories";
    }
}
