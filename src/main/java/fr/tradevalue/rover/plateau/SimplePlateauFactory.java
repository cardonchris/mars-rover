package fr.tradevalue.rover.plateau;

import fr.tradevalue.rover.exception.InvalidPlateauException;
import lombok.extern.slf4j.Slf4j;

import java.util.StringTokenizer;

@Slf4j
public class SimplePlateauFactory implements PlateauFactory {

    @Override
    public Plateau createPlateau(final String instructionsLine) {
        StringTokenizer tokenizer = new StringTokenizer(instructionsLine);
        try {
            Integer maxX = Integer.parseInt(tokenizer.nextToken());
            Integer maxY = Integer.parseInt(tokenizer.nextToken());
            return new SimplePlateau(0, 0, maxX, maxY);
        }
        catch (NumberFormatException e){
            log.error("We don't understand the instructions given to create the plateau", e);
            throw new InvalidPlateauException("We don't understand the instructions given to create the plateau", e);
        }
    }
}
