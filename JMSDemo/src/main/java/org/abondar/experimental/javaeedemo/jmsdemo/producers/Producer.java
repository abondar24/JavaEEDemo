package org.abondar.experimental.javaeedemo.jmsdemo.producers;

import org.apache.log4j.BasicConfigurator;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.InputStream;
import java.util.Properties;


public class Producer {


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
        jmsContext.createProducer().send(queue, "Message");

    }
}

