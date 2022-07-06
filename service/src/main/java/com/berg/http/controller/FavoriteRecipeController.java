package com.berg.http.controller;

import com.berg.dto.favorite.FavoriteRecipeCreateDto;
import com.berg.service.FavoriteRecipeService;
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
@RequestMapping("/favorits")
@RequiredArgsConstructor
public class FavoriteRecipeController {

    private final FavoriteRecipeService favoriteService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("favorites", favoriteService.findAll());
        return "favorite/favorits";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return favoriteService.findById(id)
                .map(entity -> {
                    model.addAttribute("favorite", entity);
                    return "favorite/favorite";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute FavoriteRecipeCreateDto favoriteRecipeCreateDto) {
        return "redirect:/favorits/" + favoriteService.create(favoriteRecipeCreateDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute FavoriteRecipeCreateDto favoriteCreateDto) {
        return favoriteService.update(id, favoriteCreateDto)
                .map(it -> "redirect:/favorits/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!favoriteService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/favorits";
    }
}
