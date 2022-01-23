package fr.tradevalue.rover.mission;

import fr.tradevalue.rover.definition.Command;
import fr.tradevalue.rover.definition.Situation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Each rover is assigned a mission described by an initial situation (position + orientation)
 * and a list of commands
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoverMission {
    private Situation initialSituation;
    private List<Command> instructions;
}
