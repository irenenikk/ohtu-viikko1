package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    Reader readerStub = () -> {
        ArrayList<Player> players = new ArrayList<Player>();

        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri",   "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));

        return players;
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchFindsFirstWithPartOfPlayersName() {
        Player player = stats.search("em");
        assertEquals(readerStub.getPlayers().get(0), player);
    }

    @Test
    public void searchReturnsNullIfNoPlayerWithName() {
        Player player = stats.search("asdf");
        assertEquals(null, player);
    }

    @Test
    public void teamFindsBasedOnTeamName() {
        List<Player> team = stats.team("EDM");
        assertEquals(readerStub.getPlayers().get(0), team.get(0));
        assertEquals(readerStub.getPlayers().get(2), team.get(1));
        assertEquals(readerStub.getPlayers().get(4), team.get(2));
    }

    @Test
    public void listEmptyIfNoPlayersInTeam() {
        List<Player> team = stats.team("asdf");
        assertEquals(0, team.size());
    }

    @Test
    public void topScorerersReturnsPlayersInRightOrder() {
        List<Player> scorerers = stats.topScorers(2);
        assertEquals(readerStub.getPlayers().get(4), scorerers.get(0));
        assertEquals(readerStub.getPlayers().get(1), scorerers.get(1));
    }
}

