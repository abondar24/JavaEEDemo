package org.abondar.experimental.javaeedemo.restdemo.test;

import org.abondar.experimental.javaeedemo.restdemo.service.BookRestService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class ApplicationConfig extends Application {
    private final Set<Class<?>> classes;


    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(BookRestService.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
