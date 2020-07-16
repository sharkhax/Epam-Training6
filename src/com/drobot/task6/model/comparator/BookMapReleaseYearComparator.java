package com.drobot.task6.model.comparator;

import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

public class BookMapReleaseYearComparator implements Comparator<Map.Entry<UUID, CustomBook>> {

    @Override
    public int compare(Map.Entry<UUID, CustomBook> o1, Map.Entry<UUID, CustomBook> o2) {
        int releaseYear1 = o1.getValue().getReleaseYear();
        int releaseYear2 = o2.getValue().getReleaseYear();
        return Integer.compare(releaseYear1, releaseYear2);
    }
}
