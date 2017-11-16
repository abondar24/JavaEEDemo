package org.abondar.experimental.javaeedemo.basiccdi;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@EightDigits
public class IssnGenerator implements NumberGenerator{

    @Inject
    Logger logger;

    @Override
    @Loggable
    public String generateNumber() {
        String issn = "8-"+Math.abs(new Random().nextInt());
        logger.info("Generated issn: "+issn);
        return issn;
    }
}
