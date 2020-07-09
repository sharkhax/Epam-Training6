package com.drobot.task6.model.comparator;

import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;

public class BookReleaseYearComparator implements Comparator<CustomBook> {

    @Override
    public int compare(CustomBook o1, CustomBook o2) {
        return Integer.compare(o1.getReleaseYear(), o2.getReleaseYear());
    }
}
