package com.berg.integration.http.controller;

import com.berg.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class AuthorControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/authors"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("author/authors"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attribute("authors", hasSize(2)));
    }

    @SneakyThrows
    @Test
    void create() {
        mockMvc.perform(post("/authors")
                .param("name", "Ivan"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/authors/{\\d+}")
                );
    }
}