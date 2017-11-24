package org.abondar.experimental.javaeedemo.ejbdemo.ejb;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import java.util.concurrent.Callable;

@Stateless
@RunAs("admin")
@PermitAll
public class ItemAdmin {


    public <V> V call(Callable<V> callable) throws Exception {

        return callable.call();

    }


}
