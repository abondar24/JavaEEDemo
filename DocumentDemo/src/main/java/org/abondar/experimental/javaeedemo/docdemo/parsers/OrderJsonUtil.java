package org.abondar.experimental.javaeedemo.docdemo.parsers;


import org.abondar.experimental.javaeedemo.docdemo.DocumentUtil;
import org.abondar.experimental.javaeedemo.docdemo.model.CreditCard;
import org.abondar.experimental.javaeedemo.docdemo.model.Customer;
import org.abondar.experimental.javaeedemo.docdemo.model.Order;
import org.abondar.experimental.javaeedemo.docdemo.model.OrderLine;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class OrderJsonUtil {

    public JsonObject buildOrderJson(Customer customer, Order order, List<OrderLine> orderLines, CreditCard card) {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        JsonArrayBuilder orderLineBuilder = Json.createArrayBuilder();
        orderLines.forEach(ol -> orderLineBuilder.add(Json.createObjectBuilder()
                .add("item", ol.getItem())
                .add("quantity", ol.getQuantity())
                .add("unit_price", ol.getUnitPrice())));

        builder.add("order", Json.createObjectBuilder()
                .add("id", order.getId())
                .add("date", DocumentUtil.convertDateToString(order.getDate()))
                .add("customer", Json.createObjectBuilder()
                        .add("first_name", customer.getFirstName())
                        .add("last_name", customer.getLastName())
                        .add("email", customer.getEmail())
                        .add("phoneNumber", customer.getPhoneNumber()))
                .add("content", Json.createObjectBuilder()
                        .add("order_line", orderLineBuilder))
                .add("credit_card", Json.createObjectBuilder()
                        .add("number", card.getNumber())
                        .add("expiry_date", card.getExpiryDate())
                        .add("control_number", card.getControlNumber())
                        .add("type", card.getType())));


        return builder.build();

    }

    public String parseOrderJsonAndReturnEmail(String fileName) throws Exception {
        String email=null;

        JsonParser parser = Json.createParser(new FileReader(fileName));
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key = parser.getString();
                    parser.next();
                    if (key.equals("email")){
                        email = parser.getString();
                    }
            }
        }

        return email;
    }


    public String generatePurchaseOrder(Customer customer, Order order, List<OrderLine> orderLines, CreditCard card) throws IOException {
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        generator.writeStartObject()
                .writeStartObject("order")
                .write("id", order.getId())
                .write("date", DocumentUtil.convertDateToString(order.getDate()))
                .writeStartObject("customer")
                .write("first_name", customer.getFirstName())
                .write("last_name", customer.getLastName())
                .write("email", customer.getEmail())
                .write("phoneNumber", customer.getPhoneNumber())
                .writeEnd();


        generator.writeStartObject("content")
                .writeStartArray("order_line");
        orderLines.forEach(ol-> generator.writeStartObject()
                .write("item", ol.getItem())
                .write("quantity", ol.getQuantity())
                .write("unit_price", ol.getUnitPrice())
                .writeEnd());

           generator.writeEnd()
                   .writeEnd()
                .writeStartObject("credit_card")
                .write("number", card.getNumber())
                .write("expiry_date", card.getExpiryDate())
                .write("control_number", card.getControlNumber())
                .write("type", card.getType())
                .writeEnd()
                .writeEnd()
                   .writeEnd()
                .close();

        return writer.toString();
    }
}
