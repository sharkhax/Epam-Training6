package com.drobot.task6.model.entity;

import java.util.ArrayList;
import java.util.List;

public class CustomBook {

    private final String name;
    private final int releaseYear;
    private final int pages;
    private final List<String> authors;

    private long id;

    public CustomBook(String name, int releaseYear, int pages, List<String> authors) {
        this.id = -1;
        this.name = name;
        this.releaseYear = releaseYear;
        this.pages = pages;
        this.authors = authors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getPages() {
        return pages;
    }

    public List<String> getAuthors() {
        return new ArrayList<>(authors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomBook customBook = (CustomBook) o;

        if (releaseYear != customBook.releaseYear) {
            return false;
        }

        if (pages != customBook.pages) {
            return false;
        }

        if (id != customBook.id) {
            return false;
        }

        if (!name.equals(customBook.name)) {
            return false;
        }

        return authors.equals(customBook.authors);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + authors.hashCode();
        result = 31 * result + releaseYear;
        result = 31 * result + pages;
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomBook{");

        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", releaseYear=").append(releaseYear);
        sb.append(", pages=").append(pages);
        sb.append(", FirstAuthor=").append(authors.get(0));
        sb.append('}');
        return sb.toString();
    }
}