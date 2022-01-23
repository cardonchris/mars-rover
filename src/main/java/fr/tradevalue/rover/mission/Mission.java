package fr.tradevalue.rover.mission;

import fr.tradevalue.rover.plateau.Plateau;
import lombok.Data;

import java.util.List;

/**
 * A mission is a squad's mission. It contains the description of the plateau
 * and a list of rover's missions
 */
@Data
public class Mission {
	private List<RoverMission> missionsDescription;
	private Plateau plateau;


}
