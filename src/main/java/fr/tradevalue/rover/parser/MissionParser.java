package fr.tradevalue.rover.parser;


import fr.tradevalue.rover.mission.Mission;

import java.io.File;

public interface MissionParser {

	Mission readMission(final File missionsFile) throws Exception;
}
