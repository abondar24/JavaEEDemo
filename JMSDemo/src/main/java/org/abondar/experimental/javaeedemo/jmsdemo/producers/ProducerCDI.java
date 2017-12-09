package org.abondar.experimental.javaeedemo.jmsdemo.producers;

import javax.annotation.Resource;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

public class ProducerCDI {

    @JMSConnectionFactory("org.jboss.naming.remote.client.InitialContextFactory")
    private static JMSContext context;

    @Resource(lookup = "jms/demoQueue1")
    private static Queue queue;

    public static void main(String[] args) {
        context.createProducer().send(queue, "More MSGS");
    }
}
