package org.abondar.experimental.javaeedemo.jmsdemo.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@MessageDriven(mappedName = "jms/demoTopic",activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "messageSelector",propertyValue = "orderAmount < 3000"),

})
public class BillingMDB implements MessageListener {

    @Resource
    private MessageDrivenContext context;

    @PersistenceContext
    private EntityManager em;

    public void onMessage(Message message){
        try {
            System.out.println("Got message: "+message.getBody(String.class));
        } catch (JMSException ex){
            ex.printStackTrace();
        }
    }
}
