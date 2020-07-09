package com.drobot.task6.model.comparator;

import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;

public class BookNameComparator implements Comparator<CustomBook> {

    @Override
    public int compare(CustomBook o1, CustomBook o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
