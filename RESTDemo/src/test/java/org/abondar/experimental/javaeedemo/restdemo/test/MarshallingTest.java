package org.abondar.experimental.javaeedemo.restdemo.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.javaeedemo.restdemo.model.Book;
import org.abondar.experimental.javaeedemo.restdemo.model.Books;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
