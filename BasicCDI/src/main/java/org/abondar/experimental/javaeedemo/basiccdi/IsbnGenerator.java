package org.abondar.experimental.javaeedemo.basiccdi;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator{

    @Inject
    Logger logger;

    @Override
    @Loggable
    public String generateNumber() {
        String isbn =  "17-21724-"+Math.abs(new Random().nextInt());
        logger.info("Generated ISBN: "+isbn);
        return isbn;
    }
}
