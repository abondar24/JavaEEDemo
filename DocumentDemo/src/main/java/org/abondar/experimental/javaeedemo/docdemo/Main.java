package org.abondar.experimental.javaeedemo.docdemo;


import org.abondar.experimental.javaeedemo.docdemo.model.CreditCard;

public class Main {
    public static void main(String[] args) throws Exception {
        DocumentUtil.xsltTransforming();
        DocumentUtil.marshallCreditCard(new CreditCard("12345678", "10/20", 566, "Visa"));
        System.out.println(DocumentUtil.unMarshallCreditCard("src/main/resources/creditcard.xml"));
        System.out.println(DocumentUtil.createXmlByDom());
        System.out.println(DocumentUtil.buildOrderJson());
        System.out.println("Parsed order.json and found: "+DocumentUtil.parseOrderJsonAndReturnEmail());
        System.out.println(DocumentUtil.generateOrderJson());
    }
}
