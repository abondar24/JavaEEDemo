package org.abondar.experimental.javaeedemo.basiccdi.test;

import org.abondar.experimental.javaeedemo.basiccdi.Book;
import org.abondar.experimental.javaeedemo.basiccdi.BookService;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BookServiceTest {


    @Test
    public void checkNumberMockTest(){
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        BookService service = container.select(BookService.class).get();
        Book book = service.createBook("Salo",2.5f,"Book of salo");

        assertTrue(book.getNumber().startsWith("Mock"));
    }
}
