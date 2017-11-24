package org.abondar.experimental.javaeedemo.ejbdemo.ejb;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

@Stateless
@RunAs("admin")
@PermitAll
public class ItemAdmin {

    public void  call(Runnable callable) throws Exception {
        callable.run();
    }


}
