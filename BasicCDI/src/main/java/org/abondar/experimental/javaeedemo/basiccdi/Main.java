package org.abondar.experimental.javaeedemo.basiccdi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
    public static void main(String[] args) {

        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        BookService service = container.select(BookService.class).get();

        Book book = service.createBook("Salo",5.0f,"A book of salo");
        System.out.println(book);

        weld.shutdown();
    }
}
