package com.drobot.task6.model.comparator;

import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

public class BookMapNameComparator implements Comparator<Map.Entry<UUID, CustomBook>> {

    @Override
    public int compare(Map.Entry<UUID, CustomBook> o1, Map.Entry<UUID, CustomBook> o2) {
        String name1 = o1.getValue().getName();
        String name2 = o2.getValue().getName();
        return name1.compareTo(name2);
    }
}
