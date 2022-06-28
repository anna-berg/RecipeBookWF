package com.berg.http.controller;

import com.berg.dto.GroupsCreateDto;
import com.berg.service.GroupsService;
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
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupsController {

    private final GroupsService groupsService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("groups", groupsService.findAll());
        return "group/groups";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        return groupsService.findById(id)
                .map(groupsReadDto -> {
                    model.addAttribute("group", groupsReadDto);
                    return "group/group";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute GroupsCreateDto groupsCreateDto) {
        return "redirect:/recipes/" + groupsService.create(groupsCreateDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute GroupsCreateDto groupsCreateDto) {
        return groupsService.update(id, groupsCreateDto)
                .map(it -> "redirect:/groups/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!groupsService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/groups";
    }
}
