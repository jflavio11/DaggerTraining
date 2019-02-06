package com.jflavio1.daggerexample.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * GithubRepositoryEntity
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public class GithubRepositoryEntity {

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("description")
    private String description;

    @SerializedName("html_url")
    private String url;

    public GithubRepositoryEntity(String fullName, String description, String url) {
        this.fullName = fullName;
        this.description = description;
        this.url = url;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String url) {
        this.url = url;
    }
}
