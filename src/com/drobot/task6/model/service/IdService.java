package com.drobot.task6.model.service;

import com.drobot.task6.model.dao.BookListDao;
import com.drobot.task6.model.dao.impl.BookListDaoImpl;
import com.drobot.task6.model.entity.CustomBook;

import java.util.List;

public class IdService {

    public long generateId() {
        long result = 0;
        List<CustomBook> storageList;
        BookListDao dao = new BookListDaoImpl();
        storageList = dao.sortBooksById();

        for (CustomBook book : storageList) {
            if (book.getId() == result) {
                result++;
            } else {
                break;
            }
        }
        return result;
    }
}
