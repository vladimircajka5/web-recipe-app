package com.cajkaisinovi.recipes.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "meal_type_id", nullable = false)
    private MealType mealType;

    @ManyToOne
    @JoinColumn(name = "dish_type_id", nullable = false)
    private DishType dishType;

    @ManyToOne
    @JoinColumn(name = "dietary_type_id", nullable = false)
    private DietaryType dietaryType;

    @Column(nullable = false)
    private int servings;

    @Column(nullable = false)
    private int preparationTime;

    private String mainPhotoUrl;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(length = 2000)
    private String originStory;

    public Recipe() {
    }

    public Recipe(String name, String description, User author,
                  MealType mealType, DishType dishType,
                  DietaryType dietaryType, int servings,
                  int preparationTime, String mainPhotoUrl,
                  LocalDateTime creationDate, String originStory) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.mealType = mealType;
        this.dishType = dishType;
        this.dietaryType = dietaryType;
        this.servings = servings;
        this.preparationTime = preparationTime;
        this.mainPhotoUrl = mainPhotoUrl;
        this.creationDate = creationDate;
        this.originStory = originStory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public DietaryType getDietaryType() {
        return dietaryType;
    }

    public void setDietaryType(DietaryType dietaryType) {
        this.dietaryType = dietaryType;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getMainPhotoUrl() {
        return mainPhotoUrl;
    }

    public void setMainPhotoUrl(String mainPhotoUrl) {
        this.mainPhotoUrl = mainPhotoUrl;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getOriginStory() {
        return originStory;
    }

    public void setOriginStory(String originStory) {
        this.originStory = originStory;
    }
}