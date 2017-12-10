package org.abondar.experimental.javaeedemo.jmsdemo.producers;

import org.abondar.experimental.javaeedemo.jmsdemo.dto.OrderDTO;
import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class ProducerTopic {

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        Properties env = new Properties();
        InputStream is = Producer.class.getResourceAsStream("/jms.topic.properties");
        env.load(is);
        Context context = new InitialContext(env);
        ConnectionFactory factory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");

        Destination topic = (Destination) context.lookup("jms/orderTopic");

        JMSContext jmsContext = factory.createContext(env.getProperty("java.naming.security.principal"),
                env.getProperty("java.naming.security.credentials"));

        OrderDTO order = new OrderDTO(1234L, new Date(), "Alex Bondar", 43.0F);
        ObjectMessage message = jmsContext.createObjectMessage(order);

        jmsContext.createProducer().send(topic, message);
        System.out.println("Order sent : " + order);

    }
}
