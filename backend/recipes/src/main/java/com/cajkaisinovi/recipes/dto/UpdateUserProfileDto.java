package com.cajkaisinovi.recipes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserProfileDto {

    @Size(max = 255)
    private String profilePictureUrl;

    @NotBlank
    @Size(max = 255)
    private String region;

    @Size(max = 2000)
    private String biography;

    public UpdateUserProfileDto() {
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}