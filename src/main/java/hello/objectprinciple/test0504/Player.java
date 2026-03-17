package hello.objectprinciple.test0504;

public class Player {
    private WorldMap worldMap;
    private Position position;
    private Game game;

    public Player(WorldMap worldMap, Position position) {
        this.worldMap = worldMap;
        this.position = position;
    }

    public void tryMove(Direction direction) {
        if (worldMap.isBlocked(position.shift(direction))) {
            game.showBlocked();
        } else {
            position = position.shift(direction);
            game.showRoom();
        }
    }

    public WorldMap worldMap() {
        return worldMap;
    }

    public Position position() {
        return position;
    }


}
