package com.cajkaisinovi.recipes.dto;

import com.cajkaisinovi.recipes.domain.User;

public class UserDto {

    private Long id;
    private String username;
    private String profilePictureUrl;
    private String region;
    private String biography;
    private long publishedRecipeCount;

    public UserDto(User user, long publishedRecipeCount) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.profilePictureUrl = user.getProfilePictureUrl();
        this.region = user.getRegion();
        this.biography = user.getBiography();
        this.publishedRecipeCount = publishedRecipeCount;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getRegion() {
        return region;
    }

    public String getBiography() {
        return biography;
    }

    public long getPublishedRecipeCount() {
        return publishedRecipeCount;
    }
}