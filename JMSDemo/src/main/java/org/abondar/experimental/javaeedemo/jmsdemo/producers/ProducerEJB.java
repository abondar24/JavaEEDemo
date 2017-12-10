package org.abondar.experimental.javaeedemo.jmsdemo.producers;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;

@Stateless
public class ProducerEJB {

    @Resource(lookup = "jms/demoQueue")
    private Queue queue;

    @Inject
    @JMSConnectionFactory("jms/RemoteConnectionFactory")
    @JMSSessionMode(JMSContext.AUTO_ACKNOWLEDGE)
    private JMSContext context;

    public void sendMessage() throws Exception{
        context.createProducer().send(queue,"MSG");
    }

}
