package fr.tradevalue.rover.launcher;

import fr.tradevalue.rover.definition.Command;
import fr.tradevalue.rover.definition.Rover;
import fr.tradevalue.rover.definition.Situation;
import fr.tradevalue.rover.exception.DangerousCommandException;
import fr.tradevalue.rover.exception.InvalidInitialPositionException;
import fr.tradevalue.rover.exception.MissionInstructionsException;
import fr.tradevalue.rover.mission.Mission;
import fr.tradevalue.rover.mission.RoverMission;
import fr.tradevalue.rover.plateau.Plateau;

import java.util.LinkedList;
import java.util.List;


public class MissionExecutor {

    public List<Rover> launchMission(Mission mission) throws DangerousCommandException, MissionInstructionsException {
        List<Rover> squad = new LinkedList<>();
        if (mission != null) {
            Plateau plateau = mission.getPlateau();
            // fetch rovers collection to apply commands on it
            for (RoverMission roverMission : mission.getMissionsDescription()) {
                Rover rover = new Rover();
                squad.add(rover);
                Situation initialSituation = roverMission.getInitialSituation();

                if (plateau.isPositionValid(initialSituation.getPosition())) {
                    rover.setSituation(initialSituation);
                } else {
                    System.out.println("The position you chose to land this rover is invalid. This error can be very expansive." + initialSituation.getPosition());
                    throw new InvalidInitialPositionException("The position you chose to land this rover is invalid. This error can be very expansive.");
                }

                for (Command instruction : roverMission.getInstructions()) {
                    Situation currentSituation = rover.getSituation();
                    Situation potentialSituation = instruction.evaluateSituation(currentSituation);
                    if (plateau.isPositionValid(potentialSituation.getPosition())) {
                        rover.setSituation(potentialSituation);
                    } else {
                        System.out.println("Are you crazy enough to destroy a rover???");
                        throw new DangerousCommandException("Are you crazy enough to destroy a rover???");
                    }
                }
            }
        } else {
            throw new MissionInstructionsException("No mission instruction received.");
        }
        return squad;
    }
}
