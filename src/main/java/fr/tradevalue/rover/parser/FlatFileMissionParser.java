package fr.tradevalue.rover.parser;

import fr.tradevalue.rover.exception.MissionInstructionsException;
import fr.tradevalue.rover.mission.Mission;
import fr.tradevalue.rover.mission.RoverMission;
import fr.tradevalue.rover.mission.RoverMissionFactory;
import fr.tradevalue.rover.plateau.Plateau;
import fr.tradevalue.rover.plateau.PlateauFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


@Slf4j
public class FlatFileMissionParser implements MissionParser {
    private final PlateauFactory plateauFactory;
    private final RoverMissionFactory roverMissionFactory;

    public FlatFileMissionParser(final PlateauFactory plateauFactory, final RoverMissionFactory roverMissionFactory) {
        this.plateauFactory = plateauFactory;
        this.roverMissionFactory = roverMissionFactory;
    }

    @Override
    public Mission readMission(final File instructionsFile) throws Exception {
        if (instructionsFile != null && instructionsFile.isFile()) {
            Mission mission = new Mission();
            List<RoverMission> roverMissions = new LinkedList<>();

            try (final Scanner scanner = new Scanner(instructionsFile)) {

                // read first line (length and width of exploration field) and
                // store it a field object

                Plateau plateau = plateauFactory.createPlateau(scanner.nextLine());


                // read following lines
                // a rover is described by 2 lines:
                // * first line contains initial position and direction of the
                // rover
                // * second line contains commands to be executed by the rover
                while (scanner.hasNextLine()) {
                    try {
                        RoverMission currentMission = roverMissionFactory.createMission(scanner.nextLine(), scanner.nextLine());
                        roverMissions.add(currentMission);
                    } catch (Exception e) {
                        log.error("An error occurred reading a rover's mission. This rover won't be launched ...", e);
                    }

                }
                mission.setPlateau(plateau);
                mission.setMissionsDescription(roverMissions);

            } catch (Exception e) {
                log.error("Error occurred when parsing instructions file.", e);
                throw new MissionInstructionsException("Error occurred when parsing instructions file.", e);
            }
            return mission;
        } else {
            log.error("Instructions were not received.");
            throw new MissionInstructionsException("Instructions were not received.");
        }

    }
}
