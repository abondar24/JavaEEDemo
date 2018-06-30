package org.abondar.experimental.richdemo.dataieration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.FacesException;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URL;
import java.util.List;

@Named("gameParser")
@ApplicationScoped
public class GameParser {
    private List<GameDescriptor> gamesList;

    @XmlRootElement(name = "games")
    private static final class GameHolder {
        private List<GameDescriptor> games;

        @XmlElement(name = "game")
        public List<GameDescriptor> getGames() {
            return games;
        }

        public void setGames(List<GameDescriptor> games) {
            this.games = games;
        }
    }


    public synchronized List<GameDescriptor> getGamesDescrList() {
        if (gamesList == null) {
            ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            URL resource = ccl.getResource("games.xml");
            JAXBContext context;
            try {
                context = JAXBContext.newInstance(GameHolder.class);
                GameHolder gameHolder = (GameHolder) context.createUnmarshaller().unmarshal(resource);
                gamesList = gameHolder.getGames();
            } catch (JAXBException e) {
                throw new FacesException(e.getMessage(), e);
            }
        }

        return gamesList;
    }


    public List<GameDescriptor> getGamesList() {
        if (gamesList == null) {
            gamesList = getGamesDescrList();
        }
        return gamesList;
    }

    public void setGamesList(List<GameDescriptor> gamesList) {
        this.gamesList = gamesList;
    }
}
