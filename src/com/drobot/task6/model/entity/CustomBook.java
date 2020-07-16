package com.drobot.task6.model.entity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CustomBook {

    private UUID bookId;
    private String name;
    private int releaseYear;
    private int pages;
    private final List<String> authors;

    public CustomBook(String name, int releaseYear, int pages, List<String> authors) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.pages = pages;
        this.authors = authors;
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
        return Collections.unmodifiableList(authors);
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
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
        if (!name.equals(customBook.name)) {
            return false;
        }
        return authors.equals(customBook.authors);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + releaseYear;
        result = 31 * result + pages;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomBook{");
        sb.append("id=").append(bookId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", releaseYear=").append(releaseYear);
        sb.append(", pages=").append(pages);
        sb.append(", authors=").append(authors);
        sb.append('}');
        return sb.toString();
    }
}