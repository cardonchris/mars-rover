package fr.tradevalue.rover.definition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
@AllArgsConstructor
public class Situation {
    private Position position;
    private Direction direction;

    public void printSituation() {
        Position position = this.getPosition();
        Direction direction = this.getDirection();
        if (position != null && direction != null) {
            System.out.println(new StringBuilder().append(position.getX()).append(" ").append(position.getY()).append(" ").append(direction));
        } else {
            System.out.println("Invalid rover situation. " + this.toString());
        }
    }
}
