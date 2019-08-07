package org.abondar.experimental.thorntaildemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.thorntaildemo.model.Book;
import org.abondar.experimental.thorntaildemo.model.Books;
import org.abondar.experimental.thorntaildemo.model.Customer;
import org.abondar.experimental.thorntaildemo.model.Customers;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class MarshallingTest {
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void marshallBookTest() throws Exception {
        Book book = new Book("Cars", 12.5F, "Science fiction comedy book",
                "1-24023-742-2", 400, false);
        String res = mapper.writeValueAsString(book);


        assertEquals(readFile("src/main/resources/test_book.json"), res);

    }

    @Test
    public void marshallListOfBooks() throws Exception {
        Books books = new Books();
        books.add(new Book("Cars", 12.5F, "Science fiction comedy book",
                "1-24023-742-2", 400, false));
        books.add(new Book("Cars", 12.5F, "Science fiction comedy book",
                "1-24023-742-2", 400, false));

        String res = mapper.writeValueAsString(books);


        assertEquals(readFile("src/main/resources/test_books.json"), res);

    }

    @Test
    public void marshallCustomerTest() throws Exception {
        Customer customer = new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
                new Date(), new Date(), 25, "95134", "San Jose");
        String res = mapper.writeValueAsString(customer);


        assertEquals(readFile("src/main/resources/test_customer.json"), res);

    }

    @Test
    public void marshallListOfCustomers() throws Exception {
        Customers customers = new Customers(Arrays.asList(
                new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
                        new Date(), new Date(), 25, "95134", "San Jose"),
                new Customer("Alex", "Bondar", "alex@mail.com", String.valueOf(12121211),
                        new Date(), new Date(), 25, "95134", "San Jose")));

        String res = mapper.writeValueAsString(customers);


        assertEquals(readFile("src/main/resources/test_customers.json"), res);

    }


    private String readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            return sb.toString().replace("  ", "");
        }
    }


}
