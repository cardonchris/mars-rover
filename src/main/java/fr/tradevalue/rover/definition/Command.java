package fr.tradevalue.rover.definition;

public enum Command {
    L {
        public Situation evaluateSituation(Situation situation) {
            Direction currentDirection = situation.getDirection();
            Direction newDirection = currentDirection.turnLeft();
            return new Situation(situation.getPosition(), newDirection);
        }
    },
    R {
        public Situation evaluateSituation(Situation situation) {
            Direction currentDirection = situation.getDirection();
            Direction newDirection = currentDirection.turnRight();
            return new Situation(situation.getPosition(), newDirection);
        }
    },
    M {
        public Situation evaluateSituation(Situation situation) {
            Direction currentDirection = situation.getDirection();
            Position potentialNewPosition = currentDirection.evaluateMoveForward(situation.getPosition());
            return new Situation(potentialNewPosition, currentDirection);
        }
    };

    public abstract Situation evaluateSituation(Situation situation);

}
