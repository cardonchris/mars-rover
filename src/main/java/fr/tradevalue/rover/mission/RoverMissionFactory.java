package fr.tradevalue.rover.mission;

import fr.tradevalue.rover.definition.Command;
import fr.tradevalue.rover.definition.Direction;
import fr.tradevalue.rover.definition.Position;
import fr.tradevalue.rover.definition.Situation;
import fr.tradevalue.rover.exception.InvalidRoverMissionException;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

@Slf4j
public class RoverMissionFactory {

    public RoverMission createMission(final String initialSituationLine, final String instructionsLine) throws InvalidRoverMissionException {
        RoverMission mission = new RoverMission();
        if (initialSituationLine != null) {
            // parse initial position/direction for a rover's mission and store it

            StringTokenizer tokenizer = new StringTokenizer(initialSituationLine);
            Integer x, y;
            Direction initialDirection;
            try {
                x = Integer.parseInt(tokenizer.nextToken());
                y = Integer.parseInt(tokenizer.nextToken());
            } catch (NumberFormatException e) {
                log.error("The given position is not valid.", e);
                throw new InvalidRoverMissionException("The given position is not valid.", e);
            }
            try {
                initialDirection = Direction.valueOf(tokenizer.nextToken());
            } catch (IllegalArgumentException e) {
                log.error("The given orientation is not valid.", e);
                throw new InvalidRoverMissionException("The given orientation is not valid.", e);
            }
            Position initialPosition = new Position(x, y);
            mission.setInitialSituation(new Situation(initialPosition, initialDirection));
        } else {
            log.error("Invalid mission. No initialSituation line found.");
            throw new InvalidRoverMissionException("Invalid mission. No initialSituation line found.");
        }

        if (instructionsLine != null) {
            char[] instructionChars = new char[instructionsLine.length()];
            instructionsLine.getChars(0, instructionsLine.length(),
                    instructionChars, 0);
            List<Command> commands = new LinkedList<Command>();
            // build list of commands for current rover
            for (char instruction : instructionChars) {
                try {
                    Command command = Command.valueOf("" + instruction);
                    commands.add(command);
                } catch (IllegalArgumentException e) {
                    log.error("The given command is not valid.", e);
                    throw new InvalidRoverMissionException("The given command is not valid.", e);
                }
            }
            mission.setInstructions(commands);
        } else {
            log.error("Invalid mission. No instructions line found.");
            throw new InvalidRoverMissionException("Invalid mission. No instructions line found.");
        }
        return mission;
    }

}
