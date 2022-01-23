package fr.tradevalue.rover.definition;

public enum Direction {
    N {
        @Override
        public Direction turnLeft() {
            return W;
        }

        @Override
        public Direction turnRight() {
            return E;
        }

        @Override
        public Position evaluateMoveForward(final Position position) {
            return new Position(position.getX(), position.getY() + 1);
        }

    },
    E {
        @Override
        public Direction turnLeft() {
            return N;
        }

        @Override
        public Direction turnRight() {
            return S;
        }

        @Override
        public Position evaluateMoveForward(final Position position) {
            return new Position(position.getX() + 1, position.getY());
        }

    },
    S {
        @Override
        public Direction turnLeft() {
            return E;
        }

        @Override
        public Direction turnRight() {
            return W;
        }

        @Override
        public Position evaluateMoveForward(final Position position) {
            return new Position(position.getX(), position.getY() - 1);
        }

    },
    W {
        @Override
        public Direction turnLeft() {
            return S;
        }

        @Override
        public Direction turnRight() {
            return N;
        }

        @Override
        public Position evaluateMoveForward(final Position position) {
            return new Position(position.getX() - 1, position.getY());
        }

    };

    public abstract Direction turnLeft();

    public abstract Direction turnRight();

    public abstract Position evaluateMoveForward(final Position position);
}
