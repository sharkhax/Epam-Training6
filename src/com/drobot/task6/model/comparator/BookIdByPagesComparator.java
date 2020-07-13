package com.drobot.task6.model.comparator;

import com.drobot.task6.model.dao.BookListDao;
import com.drobot.task6.model.dao.impl.BookListDaoImpl;
import com.drobot.task6.model.entity.CustomBook;

import java.util.Comparator;
import java.util.UUID;

public class BookIdByPagesComparator implements Comparator<UUID> {

    @Override
    public int compare(UUID o1, UUID o2) {
        BookListDao dao = new BookListDaoImpl();
        CustomBook book1 = dao.findById(o1).get();
        CustomBook book2 = dao.findById(o2).get();
        return Integer.compare(book1.getPages(), book2.getPages());
    }
}
