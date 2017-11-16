package org.abondar.experimental.javaeedemo.basiccdi;

import javax.inject.Inject;
import java.util.logging.Logger;

@Loggable
public class BookService {

    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;


    @Inject
    Logger logger;

    public Book createBook(String title, Float price, String description){

        Book book = new Book(title,price,description);
        book.setNumber(numberGenerator.generateNumber());
        logger.info("Book created: "+book);
        return book;
    }
}
