package org.abondar.experimental.javaeedemo.ejbdemo.ejb;

import org.abondar.experimental.javaeedemo.ejbdemo.model.Book;
import org.abondar.experimental.javaeedemo.ejbdemo.model.CD;
import org.abondar.experimental.javaeedemo.ejbdemo.model.Item;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ItemRemote {
    List<Book> findBooks();
    List<CD> findCDs();
    Book createBook(Item book);
    CD createCD(Item cd);
}
