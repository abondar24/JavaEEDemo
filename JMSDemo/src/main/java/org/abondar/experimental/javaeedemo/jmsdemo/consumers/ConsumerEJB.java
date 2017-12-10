package org.abondar.experimental.javaeedemo.jmsdemo.consumers;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Stateless
public class ConsumerEJB {

    @Inject
    @Resource(lookup = "org.wildfly.naming.client.WildFlyInitialContextFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/javaee7/Queue")
    private Queue queue;

    public  void getMessage() throws Exception {
        JMSContext context = connectionFactory.createContext();
        while (true) {
            String message = context.createConsumer(queue).receiveBody(String.class);
            System.out.println("Message received: " + message);
        }

    }
}
