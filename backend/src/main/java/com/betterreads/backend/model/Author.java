package com.betterreads.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String openLibraryId;

    protected Author() {}

    public Author(String name, String openLibraryId) {
        this.name = name;
        this.openLibraryId = openLibraryId;
    }

    public String getName() {
        return name;
    }

    public String getOpenLibraryId() {
        return openLibraryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpenLibraryId(String openLibraryId) {
        this.openLibraryId = openLibraryId;
    }
}
