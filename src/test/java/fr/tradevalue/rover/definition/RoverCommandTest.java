package fr.tradevalue.rover.definition;

import org.junit.Test;

import static fr.tradevalue.rover.definition.Command.*;
import static fr.tradevalue.rover.definition.Direction.*;
import static junit.framework.TestCase.assertEquals;

public class RoverCommandTest {

    @Test
    public void testMoveCommand() {
        Position initialPosition = new Position(10,10);

        Situation initialSituation = new Situation(initialPosition, N);

        // Test M command (goForward)
        Situation situationAfterMoveAction = M.evaluateSituation(initialSituation);
        Position positionAfterMove = situationAfterMoveAction.getPosition();
        assertEquals(N, situationAfterMoveAction.getDirection());
        assertEquals(10, positionAfterMove.getX());
        assertEquals(11, positionAfterMove.getY());
    }

    @Test
    public void testTurnLeftCommand() {
        Position initialPosition = new Position(10,10);

        Situation initialSituation = new Situation(initialPosition, N);

        //  Test L command (turn left)
        Situation situationAfterTurnLeft = L.evaluateSituation(initialSituation);
        Position positionAfterTurnLeft = situationAfterTurnLeft.getPosition();
        assertEquals(W, situationAfterTurnLeft.getDirection());
        assertEquals(10, positionAfterTurnLeft.getX());
        assertEquals(10, positionAfterTurnLeft.getY());
    }

    @Test
    public void testTurnRightCommand() {
        Position initialPosition = new Position(10,10);

        Situation initialSituation = new Situation(initialPosition, N);

        // Test R command (turn right)
        Situation situationAfterTurnRight = R.evaluateSituation(initialSituation);
        Position positionAfterTurnRight = situationAfterTurnRight.getPosition();
        assertEquals(E, situationAfterTurnRight.getDirection());
        assertEquals(10, positionAfterTurnRight.getX());
        assertEquals(10, positionAfterTurnRight.getY());
    }
}
