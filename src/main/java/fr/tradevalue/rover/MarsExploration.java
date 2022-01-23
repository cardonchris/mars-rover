package fr.tradevalue.rover;

import fr.tradevalue.rover.definition.Rover;
import fr.tradevalue.rover.definition.Situation;
import fr.tradevalue.rover.exception.InvalidCallException;
import fr.tradevalue.rover.launcher.MissionExecutor;
import fr.tradevalue.rover.mission.Mission;
import fr.tradevalue.rover.mission.RoverMissionFactory;
import fr.tradevalue.rover.parser.FlatFileMissionParser;
import fr.tradevalue.rover.parser.MissionParser;
import fr.tradevalue.rover.plateau.PlateauFactory;
import fr.tradevalue.rover.plateau.SimplePlateauFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

@Slf4j
public class MarsExploration {

    private static PlateauFactory plateauFactory = new SimplePlateauFactory();
    private static RoverMissionFactory missionFactory = new RoverMissionFactory();
    private static MissionParser parser = new FlatFileMissionParser(plateauFactory, missionFactory);
    private static MissionExecutor executor = new MissionExecutor();

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            File instructionsFile = new File(args[0]);
            if (instructionsFile != null && instructionsFile.isFile()) {
                Mission mission;
                try {
                    mission = parser.readMission(instructionsFile);
                } catch (Exception e) {
                    throw new Exception("Error reading mission instructions", e);
                }
                List<Rover> rovers = executor.launchMission(mission);
                rovers.stream().map(Rover::getSituation).forEach(Situation::printSituation);
            } else {
                throw new InvalidCallException("A valid path is expected as an argument");
            }
        } else {
            throw new InvalidCallException("This program requires exactly one parameter.");
        }
    }
}
