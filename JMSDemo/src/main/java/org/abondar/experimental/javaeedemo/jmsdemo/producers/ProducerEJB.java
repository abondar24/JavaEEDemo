package org.abondar.experimental.javaeedemo.jmsdemo.producers;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class ProducerEJB {

    @Resource(lookup = "org.jboss.naming.remote.client.InitialContextFactory")
    private ConnectionFactory factory;

    @Resource(lookup = "jms/demoQueue")
    private Queue queue;

    public void sendMessage() throws Exception{
        JMSContext context = factory.createContext();
        context.createProducer().send(queue,"MSG");
    }

}
