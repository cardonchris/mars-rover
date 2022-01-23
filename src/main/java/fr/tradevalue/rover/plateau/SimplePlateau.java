package fr.tradevalue.rover.plateau;

import fr.tradevalue.rover.definition.Position;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimplePlateau implements Plateau {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    @Override
    public boolean isPositionValid(final Position position) {
        if (position.getX() < this.minX || position.getX() > this.maxX) {
            return false;
        }
        if (position.getY() < this.minY || position.getY() > this.maxY) {
            return false;
        }
        return true;
    }
}
