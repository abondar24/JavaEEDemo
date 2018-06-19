package org.abondar.experimental.wfswarmdemo;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

public class Main {
    public static void main(String[] args) throws Exception {

        Swarm swarm = new Swarm();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addClass(App.class);
        deployment.addPackage("org.abondar.experimental.wfswarmdemo.ejb");
        deployment.addPackage("org.abondar.experimental.wfswarmdemo.service");
        deployment.addPackage("org.abondar.experimental.wfswarmdemo.model");
        System.out.println(deployment.getContent());
        swarm.start().deploy(deployment);

    }
}
