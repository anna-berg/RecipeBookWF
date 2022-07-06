package com.berg.mapper.recipe;

import com.berg.dto.recipe.RecipeCreateDto;
import com.berg.entity.Product;
import com.berg.entity.Recipe;
import com.berg.mapper.Mapper;
import com.berg.repositary.AuthorRepository;
import com.berg.repositary.CategoryRepository;
import com.berg.repositary.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class RecipeCreateDtoToRecipeMapper implements Mapper<RecipeCreateDto, Recipe> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Recipe map(RecipeCreateDto object) {
        return Recipe.builder()
                .author(authorRepository.findById(object.getAuthorId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .categoryRecipe(categoryRepository.findById(object.getCategoryId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .title(object.getTitle())
                .description(object.getDescription())
                .measure(object.getMeasure())
                .products(object.getProducts().stream()
                        .map(this::getProduct)
                        .toList())
                .build();
    }

    @Override
    public Recipe map(RecipeCreateDto fromObject, Recipe toObject) {
        toObject.setAuthor(authorRepository.findById(fromObject.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        toObject.setCategoryRecipe(categoryRepository.findById(fromObject.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        toObject.setTitle(fromObject.getTitle());
        toObject.setDescription(fromObject.getDescription());
        toObject.setMeasure(fromObject.getMeasure());
        toObject.setProducts(fromObject.getProducts().stream()
                .map(this::getProduct)
                .collect(toList()));
        return toObject;
    }

    public Product getProduct(Long productId) {
        return Optional.ofNullable(productId)
                .flatMap(productRepository::findById)
                .orElse(null);
    }
}