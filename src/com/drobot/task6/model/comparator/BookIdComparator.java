package com.drobot.task6.model.comparator;

import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;

public class BookIdComparator implements Comparator<CustomBook> {

    @Override
    public int compare(CustomBook o1, CustomBook o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
}
