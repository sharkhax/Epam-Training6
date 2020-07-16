package com.drobot.task6.model.comparator;

import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

public class BookMapPagesComparator implements Comparator<Map.Entry<UUID, CustomBook>> {

    @Override
    public int compare(Map.Entry<UUID, CustomBook> o1, Map.Entry<UUID, CustomBook> o2) {
        int pages1 = o1.getValue().getPages();
        int pages2 = o2.getValue().getPages();
        return Integer.compare(pages1, pages2);
    }
}
