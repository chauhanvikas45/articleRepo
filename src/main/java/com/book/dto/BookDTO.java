package com.book.dto;

import javax.validation.constraints.NotNull;

public class BookDTO {
    private String title;
    private String description;
    private String body;
    private String[] tagList;
    private String slug;
    private boolean favorited;
    private int favorities;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getTagList() {
        return tagList;
    }

    public void setTagList(String[] tagList) {
        this.tagList = tagList;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public int getFavorities() {
        return favorities;
    }

    public void setFavorities(int favorities) {
        this.favorities = favorities;
    }
}
