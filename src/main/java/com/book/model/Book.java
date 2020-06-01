package com.book.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Entity(name = "book")
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
   @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;
    private String slug;
    @NotNull(message = "Title cannot be missing or empty")
    private String title;
    @NotNull(message = "Description cannot be missing or empty")
    private String description;
    @NotNull(message = "body cannot be missing or empty")
    private String body;
    @JsonProperty(value = "tags")
    private String[] tagList;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private boolean favorited;
    private int favorities;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", tagList=" + Arrays.toString(tagList) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", favorited=" + favorited +
                ", favorities=" + favorities +
                '}';
    }
}
