package org.abondar.experimental.javaeedemo.ejbdemo.ejb;

import org.abondar.experimental.javaeedemo.ejbdemo.model.Book;
import org.abondar.experimental.javaeedemo.ejbdemo.model.CD;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ItemLocal {
    List<Book> findBooks();
    List<CD> findCDs();
}
