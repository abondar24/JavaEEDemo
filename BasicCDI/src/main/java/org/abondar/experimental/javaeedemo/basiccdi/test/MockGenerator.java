package org.abondar.experimental.javaeedemo.basiccdi.test;

import org.abondar.experimental.javaeedemo.basiccdi.NumberGenerator;
import org.abondar.experimental.javaeedemo.basiccdi.ThirteenDigits;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

/*
* Weld SE doesn't work with beans.xml in test folder so I needed to move to main.
* */
@Alternative
@ThirteenDigits
public class MockGenerator implements NumberGenerator {

    @Inject
    Logger logger;

    @Override
    public String generateNumber() {
        String mock = "Mock-"+Math.abs(new Random().nextInt());
        logger.info("Generated mock: "+mock);
        return mock;
    }
}
