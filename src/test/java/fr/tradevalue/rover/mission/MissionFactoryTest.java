package fr.tradevalue.rover.mission;

import fr.tradevalue.rover.definition.Command;
import fr.tradevalue.rover.definition.Direction;
import fr.tradevalue.rover.definition.Position;
import fr.tradevalue.rover.definition.Situation;
import fr.tradevalue.rover.exception.InvalidRoverMissionException;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertThrows;

public class MissionFactoryTest {

    @Test
    public void testCreateMission() throws InvalidRoverMissionException {
        RoverMissionFactory roverMissionFactory = new RoverMissionFactory();
        RoverMission roverMission = roverMissionFactory.createMission("2 4 N", "LM");
        assertNotNull(roverMission);
        Situation situation = roverMission.getInitialSituation();
        assertNotNull(situation);
        Position position = situation.getPosition();
        assertNotNull(position);
        assertEquals(2, position.getX());
        assertEquals(4, position.getY());
        assertEquals(Direction.N, situation.getDirection());

        List<Command> commands = roverMission.getInstructions();

        assertEquals(2, commands.size());
        assertEquals(Command.L, commands.get(0));
        assertEquals(Command.M, commands.get(1));
    }

    @Test
    public void testCreateMissionWithBadIntialPosition() {
        RoverMissionFactory roverMissionFactory = new RoverMissionFactory();
        assertThrows(InvalidRoverMissionException.class, () -> {
            roverMissionFactory.createMission("2 X N", "LM");
        });
    }

    @Test
    public void testCreateMissionWithBadIntialOrientation() {
        RoverMissionFactory roverMissionFactory = new RoverMissionFactory();
        assertThrows(InvalidRoverMissionException.class, () -> {
            roverMissionFactory.createMission("2 2 X", "LM");
        });
    }

    @Test
    public void testCreateMissionWithBadInstruction() {
        RoverMissionFactory roverMissionFactory = new RoverMissionFactory();
        assertThrows(InvalidRoverMissionException.class, () -> {
            roverMissionFactory.createMission("2 2 N", "LX");
        });
    }
}
