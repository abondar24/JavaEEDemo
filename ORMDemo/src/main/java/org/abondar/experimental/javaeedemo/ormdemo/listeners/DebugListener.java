package org.abondar.experimental.javaeedemo.ormdemo.listeners;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class DebugListener {

    @PrePersist
    public void prePersist(Object object) {
        System.out.println("prePersist");
    }

    @PreUpdate
    public void preUpdate(Object object) {
        System.out.println("preUpdate");
    }

    @PreRemove
    public void preRemove(Object object) {
        System.out.println("preRemove");
    }
}
