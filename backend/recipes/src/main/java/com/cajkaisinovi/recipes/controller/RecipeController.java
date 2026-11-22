package com.cajkaisinovi.recipes.controller;

import com.cajkaisinovi.recipes.dto.RecipeDto;
import com.cajkaisinovi.recipes.service.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private IRecipeService recipeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeDto>> getAll() {

        List<RecipeDto> all = this.recipeService.getAll();

        // OK
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeDto> getOne(@PathVariable("id") Long id) {

        RecipeDto one = this.recipeService.findById(id);

        // NOT_FOUND
        if (one == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // OK
        return new ResponseEntity<>(one, HttpStatus.OK);
    }
}