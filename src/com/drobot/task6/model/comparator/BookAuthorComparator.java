package com.drobot.task6.model.comparator;

import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;

public class BookAuthorComparator implements Comparator<CustomBook> {

    @Override
    public int compare(CustomBook o1, CustomBook o2) {
        String author1 = o1.getAuthors().get(0);
        String author2 = o2.getAuthors().get(0);
        return author1.compareTo(author2);
    }
}
