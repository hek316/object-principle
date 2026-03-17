package hello.objectprinciple.test0504;

public class Player {
    private WorldMap worldMap;
    private Position position;

    public Player(WorldMap worldMap, Position position) {
        this.worldMap = worldMap;
        this.position = position;
    }


    public void move(Position position) {
        this.position = position;
    }



    public WorldMap worldMap() {
        return worldMap;
    }

    public Position position() {
        return position;
    }


}
