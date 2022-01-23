package fr.tradevalue.rover.definition;

import org.junit.Test;

import static fr.tradevalue.rover.definition.Direction.*;
import static junit.framework.TestCase.assertEquals;

public class RoverDirectionTest {

    @Test
    public void testNorth() {
        // Test north direction
        assertEquals(W, N.turnLeft());
        assertEquals(E, N.turnRight());

        Position pos = new Position(10,10);

        // Test move
        Position positionAfterMovingNorth = N.evaluateMoveForward(pos);
        assertEquals(10, positionAfterMovingNorth.getX());
        assertEquals(11, positionAfterMovingNorth.getY());
    }

    @Test
    public void testSouth() {
        // Test south direction
        assertEquals(S.turnLeft(), E);
        assertEquals(S.turnRight(), W);

        Position pos = new Position(10,10);

        // Test move
        Position positionAfterMovingSouth = S.evaluateMoveForward(pos);
        assertEquals(10, positionAfterMovingSouth.getX());
        assertEquals(9, positionAfterMovingSouth.getY());
    }

    @Test
    public void testWest() {
        // Test west direction
        assertEquals(W.turnLeft(), S);
        assertEquals(W.turnRight(), N);

        Position pos = new Position(10,10);

        // Test move
        Position positionAfterMovingWest = W.evaluateMoveForward(pos);
        assertEquals(9, positionAfterMovingWest.getX());
        assertEquals(10, positionAfterMovingWest.getY());
    }

    @Test
    public void testEast() {
        // Test east direction
        assertEquals(E.turnLeft(), N);
        assertEquals(E.turnRight(), S);

        Position pos = new Position(10,10);

        // Test move
        Position positionAfterMovingEast = E.evaluateMoveForward(pos);
        assertEquals(11, positionAfterMovingEast.getX());
        assertEquals(10, positionAfterMovingEast.getY());
    }
}
