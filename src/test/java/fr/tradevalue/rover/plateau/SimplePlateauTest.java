package fr.tradevalue.rover.plateau;

import fr.tradevalue.rover.definition.Position;
import fr.tradevalue.rover.exception.InvalidPlateauException;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertThrows;

public class SimplePlateauTest {

    private PlateauFactory plateauFactory = new SimplePlateauFactory();

    @Test
    public void testPlateauCreation() {
        Plateau plateau = plateauFactory.createPlateau("5 10");
        assertTrue(plateau instanceof SimplePlateau);
        SimplePlateau simplePlateau = (SimplePlateau) plateau;
        assertEquals(0, simplePlateau.getMinX());
        assertEquals(0, simplePlateau.getMinY());
        assertEquals(5, simplePlateau.getMaxX());
        assertEquals(10, simplePlateau.getMaxY());
    }

    @Test
    public void testValidPositionIntoPlateau() {
        Plateau plateau = plateauFactory.createPlateau("5 10");
        Position p = new Position(2, 2);
        assertTrue(plateau.isPositionValid(p));
    }

    @Test
    public void testValidPositionOutsidePlateau() {
        Plateau plateau = plateauFactory.createPlateau("5 10");
        Position p = new Position(10, 10);
        assertFalse(plateau.isPositionValid(p));
    }

    @Test
    public void testValidPositionOnBorder() {
        Plateau plateau = plateauFactory.createPlateau("5 10");
        Position p = new Position(0, 5);
        assertTrue(plateau.isPositionValid(p));
    }

    @Test
    public void testInvalidPlateauCreation() {
        assertThrows(InvalidPlateauException.class, () -> {
            plateauFactory.createPlateau("5 X");
        });
    }
}
