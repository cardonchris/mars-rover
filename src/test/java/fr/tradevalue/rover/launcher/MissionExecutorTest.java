package fr.tradevalue.rover.launcher;

import fr.tradevalue.rover.definition.*;
import fr.tradevalue.rover.exception.DangerousCommandException;
import fr.tradevalue.rover.exception.InvalidInitialPositionException;
import fr.tradevalue.rover.mission.Mission;
import fr.tradevalue.rover.mission.RoverMission;
import fr.tradevalue.rover.plateau.SimplePlateau;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;


public class MissionExecutorTest extends TestCase {

    @Test
    public void testMissionExecution() throws Exception {

        // load description file

        Mission mission = new Mission();
        mission.setPlateau(new SimplePlateau(0, 0, 5, 5));

        Situation situation = new Situation(new Position(2, 2), Direction.N);
        RoverMission roverMission = new RoverMission(situation, Arrays.asList(Command.M, Command.L, Command.M, Command.M, Command.R, Command.R));

        mission.setMissionsDescription(List.of(roverMission));

        // launch mission
        MissionExecutor executor = new MissionExecutor();
        List<Rover> rovers = executor.launchMission(mission);

        // check mission final state
        assertEquals(1, rovers.size());
        Rover theRover = rovers.get(0);
        Situation roverSituation = theRover.getSituation();
        assertEquals(Direction.E, roverSituation.getDirection());
        Position roverPosition = roverSituation.getPosition();
        assertNotNull(roverPosition);
        assertEquals(0, roverPosition.getX());
        assertEquals(3, roverPosition.getY());

    }

    @Test
    public void test_mission_fails_when_invalid_initial_position_for_a_rover() {

        // load description file

        Mission mission = new Mission();
        mission.setPlateau(new SimplePlateau(0, 0, 2, 2));

        Situation situation = new Situation(new Position(5, 5), Direction.N);
        RoverMission roverMission = new RoverMission(situation, Arrays.asList(Command.M, Command.M, Command.M, Command.M));

        mission.setMissionsDescription(List.of(roverMission));

        // launch mission
        assertThrows(InvalidInitialPositionException.class, () -> {
            MissionExecutor executor = new MissionExecutor();
            executor.launchMission(mission);
        });

    }

    @Test
    public void test_mission_fails_when_command_would_damage_rover() {

        // load description file

        Mission mission = new Mission();
        mission.setPlateau(new SimplePlateau(0, 0, 2, 2));

        Situation situation = new Situation(new Position(2, 2), Direction.N);
        RoverMission roverMission = new RoverMission(situation, Arrays.asList(Command.M, Command.M, Command.M, Command.M));

        mission.setMissionsDescription(List.of(roverMission));

        // launch mission
        assertThrows(DangerousCommandException.class, () -> {
            MissionExecutor executor = new MissionExecutor();
            executor.launchMission(mission);
        });

    }
}
