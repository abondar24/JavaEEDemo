package org.abondar.experimental.richdemo.controllers;

import org.abondar.experimental.richdemo.dataieration.GameDescriptor;
import org.abondar.experimental.richdemo.dataieration.GameParser;

import javax.enterprise.context.RequestScoped;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("olympicsController")
@RequestScoped
public class OlympicsController implements Serializable {
    @ManagedProperty("#{gameParser.gamesList}")
    private List<GameDescriptor> games = new ArrayList<>();



    public List<GameDescriptor> getGames() {
        System.out.println("HUI");
        games.forEach(g-> System.out.println(g.getNumber()));
        return games;
    }

    public void setGames(List<GameDescriptor> games) {
        this.games = games;
    }
}
