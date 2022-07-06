package com.berg.http.controller;

import com.berg.dto.dailyMenu.DailyMenuCreateDto;
import com.berg.service.DailyMenuService;
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
@RequestMapping("/menus")
@RequiredArgsConstructor
public class DailyMenuController {

    private final DailyMenuService dailyMenuService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("menus", dailyMenuService.findAll());
        return "menu/menus";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return dailyMenuService.findById(id)
                .map(entity -> {
                    model.addAttribute("menu", entity);
                    return "menu/menu";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute DailyMenuCreateDto dailyMenuCreateDto) {
        return "redirect:/menus/" + dailyMenuService.create(dailyMenuCreateDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute DailyMenuCreateDto dailyMenuCreateDto) {
        return dailyMenuService.update(id, dailyMenuCreateDto)
                .map(it -> "redirect:/menus/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!dailyMenuService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/menus";
    }
}
