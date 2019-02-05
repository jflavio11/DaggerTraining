package com.jflavio1.daggerexample.domain.model;

/**
 * GithubRepositoryEntity
 *
 * @author Jose Flavio - jflavio90@gmail.com
 * @since 5/2/17
 */
public class GithubRepositoryEntity {

    private String username;
    private String repoName;
    private String repoUrl;

    public GithubRepositoryEntity(String username, String repoName, String repoUrl) {
        this.username = username;
        this.repoName = repoName;
        this.repoUrl = repoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }
}
