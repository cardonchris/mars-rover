package fr.tradevalue.rover.parser;

import fr.tradevalue.rover.definition.Direction;
import fr.tradevalue.rover.definition.Position;
import fr.tradevalue.rover.definition.Situation;
import fr.tradevalue.rover.exception.InvalidRoverMissionException;
import fr.tradevalue.rover.mission.Mission;
import fr.tradevalue.rover.mission.RoverMission;
import fr.tradevalue.rover.mission.RoverMissionFactory;
import fr.tradevalue.rover.plateau.Plateau;
import fr.tradevalue.rover.plateau.PlateauFactory;
import fr.tradevalue.rover.plateau.SimplePlateau;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static fr.tradevalue.rover.definition.Command.L;
import static fr.tradevalue.rover.definition.Command.M;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.times;


public class FlatFileMissionParserTest {

    private PlateauFactory plateauFactory;
    private RoverMissionFactory roverMissionFactory;
    private MissionParser parser;

    @Before
    public void init() throws InvalidRoverMissionException {
        plateauFactory = Mockito.mock(PlateauFactory.class);
        Mockito.when(plateauFactory.createPlateau(Mockito.matches("5 5"))).thenReturn(new SimplePlateau(0, 0, 5, 5));
        roverMissionFactory = Mockito.mock(RoverMissionFactory.class);
        Mockito.when(roverMissionFactory.createMission(Mockito.matches("1 2 N"), Mockito.matches("LMLMLMLMM")))
                .thenReturn(new RoverMission(new Situation(new Position(1, 2), Direction.N), Arrays.asList(L, M, L, M, L, M, L, M, M)));


        parser = new FlatFileMissionParser(plateauFactory, roverMissionFactory);
    }

    @Test
    public void testCreateMissionParsingAFile() throws Exception {

        // load description file
        URL configurationUrl = getClass().getClassLoader().getResource(
                "testRover.txt");



        // parse file to create a mission object
        Mission missionResult = parser.readMission(new File(configurationUrl.getFile()));

        Mockito.verify(plateauFactory, times(1)).createPlateau(Mockito.anyString());
        Mockito.verify(roverMissionFactory, times(1)).createMission(Mockito.anyString(), Mockito.anyString());

        // check the mission
        assertNotNull(missionResult);

        // check the board
        Plateau plateau = missionResult.getPlateau();
        assertNotNull(plateau);
        assertTrue(plateau instanceof SimplePlateau);
        SimplePlateau simplePlateau = (SimplePlateau) plateau;
        assertEquals(0, simplePlateau.getMinX());
        assertEquals(0, simplePlateau.getMinY());
        assertEquals(5, simplePlateau.getMaxX());
        assertEquals(5, simplePlateau.getMaxY());

        // check the rover's missions
        List<RoverMission> roverMissions = missionResult.getMissionsDescription();
        assertNotNull(roverMissions);
        assertEquals(1, roverMissions.size());
        RoverMission roverMission = roverMissions.get(0);
        Situation roverSituation = roverMission.getInitialSituation();
        assertNotNull(roverSituation);
        assertEquals(Direction.N, roverSituation.getDirection());
        Position roverPosition = roverSituation.getPosition();
        assertNotNull(roverPosition);
        assertEquals(1, roverPosition.getX());
        assertEquals(2, roverPosition.getY());


    }
}
