package com.drobot.task6.model.comparator;

import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;
import java.util.Map;
import java.util.UUID;

public enum BookMapComparatorType {
    NAME(new BookMapNameComparator()), PAGES(new BookMapPagesComparator()),
    RELEASE_YEAR(new BookMapReleaseYearComparator()), AUTHORS(new BookMapAuthorComparator());

    private final Comparator<Map.Entry<UUID, CustomBook>> comparator;

    BookMapComparatorType(Comparator<Map.Entry<UUID, CustomBook>> comparator) {
        this.comparator = comparator;
    }

    public Comparator<Map.Entry<UUID, CustomBook>> getComparator() {
        return comparator;
    }
}
