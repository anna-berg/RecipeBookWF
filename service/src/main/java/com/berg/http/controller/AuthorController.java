package com.berg.http.controller;

import com.berg.dto.AuthorCreateEditDto;
import com.berg.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "author/authors";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return authorService.findById(id)
                .map(authorReadDto -> {
                    model.addAttribute("author", authorReadDto);
                    return "author/author";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute AuthorCreateEditDto authorDto) {
        return "redirect:authors/" + authorService.create(authorDto).getId();
    }

    //    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute AuthorCreateEditDto authorDto) {
        return authorService.update(id, authorDto)
                .map(authorReadDto -> "redirect:/authors/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id,
                         @ModelAttribute AuthorCreateEditDto authorDto) {
        if (!authorService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/authors";
    }
}
