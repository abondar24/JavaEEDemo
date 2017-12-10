package org.abondar.experimental.javaeedemo.jmsdemo.consumers;

import org.abondar.experimental.javaeedemo.jmsdemo.producers.Producer;
import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.InputStream;
import java.util.Properties;

public class ConsumerAsync implements MessageListener {

    public static void main(String[] args) throws Exception {

        BasicConfigurator.configure();
        Properties env = new Properties();
        InputStream is = Producer.class.getResourceAsStream("/jms.properties");
        env.load(is);
        Context context = new InitialContext(env);

        ConnectionFactory factory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        Destination queue = (Destination) context.lookup("jms/demoQueue");

        JMSContext jmsContext = factory.createContext(env.getProperty("java.naming.security.principal"),
                env.getProperty("java.naming.security.credentials"));

        jmsContext.createConsumer(queue).setMessageListener(new ConsumerAsync());
        Thread.sleep(1000);
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Got message: "+message.getBody(String.class));
        } catch (JMSException ex){
            ex.printStackTrace();
        }

    }
}
