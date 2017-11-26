package org.abondar.experimental.javaeedemo.docdemo;

import org.junit.Test;

import static org.abondar.experimental.javaeedemo.docdemo.DocumentUtil.readFile;
import static org.junit.Assert.assertEquals;

public class JsonTest {

    @Test
    public void buildOrderJsonTest() throws Exception {
        assertEquals(readFile("src/main/resources/order.json").replace(" ",""),DocumentUtil.buildOrderJson());
    }

    @Test
    public void parseOrderJsonAndGetEmailTest() throws Exception {
        assertEquals("abondar@me.com",DocumentUtil.parseOrderJsonAndReturnEmail());
    }


    @Test
    public void generateOrderJsonTest() throws Exception {
        assertEquals(readFile("src/main/resources/order.json").replace(" ",""),DocumentUtil.generateOrderJson());
    }

}
