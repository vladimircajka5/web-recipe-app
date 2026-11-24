package com.cajkaisinovi.recipes.controller;

import com.cajkaisinovi.recipes.dto.RecipeDto;
import com.cajkaisinovi.recipes.dto.UserDto;
import com.cajkaisinovi.recipes.service.IRecipeService;
import com.cajkaisinovi.recipes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = RequestMethod.GET)
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRecipeService recipeService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getOne(@PathVariable("id") Long id) {

        UserDto one = this.userService.findById(id);

        // NOT_FOUND
        if (one == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // OK
        return new ResponseEntity<>(one, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeDto>> getRecipes(@PathVariable("id") Long id) {

        List<RecipeDto> recipes = this.recipeService.findByAuthorId(id);

        // NOT_FOUND
        if (recipes == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // OK
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
}