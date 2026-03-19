package hello.objectprinciple.test0504;

public class Player {
    private WorldMap worldMap;
    private Position position;

    public Player(WorldMap worldMap, Position position) {
        this.worldMap = worldMap;
        this.position = position;
    }


    public boolean move(Direction direction) {
        if (worldMap.isBlocked(position.shift(direction))) {
            return false;
        }
        this.position = this.position.shift(direction);
        return true;
    }



    public WorldMap worldMap() {
        return worldMap;
    }

    public Position position() {
        return position;
    }

    public Room currentRoom() {
        return worldMap.roomAt(position);
    }

}
