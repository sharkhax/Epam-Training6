package com.drobot.task6.model.comparator;

import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

public class BookMapAuthorComparator implements Comparator<Map.Entry<UUID, CustomBook>> {

    @Override
    public int compare(Map.Entry<UUID, CustomBook> o1, Map.Entry<UUID, CustomBook> o2) {
        String firstAuthor1 = o1.getValue().getAuthors().get(0);
        String firstAuthor2 = o2.getValue().getAuthors().get(0);
        return firstAuthor1.compareTo(firstAuthor2);
    }
}
