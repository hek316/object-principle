package hello.objectprinciple.test0504;

public class Player {
    private WorldMap worldMap;
    private Position position;

    public Player(WorldMap worldMap, Position position) {
        this.worldMap = worldMap;
        this.position = position;
    }

    public void tryMove(Direction direction) {
        if (worldMap.isBlocked(position.shift(direction))) {
            showBlocked();
        } else {
            position = position.shift(direction);
            showRoom();
        }
    }

    private void showRoom() {
        System.out.println("당신은 [" + worldMap.roomAt(position).name() + "]에 있습니다.");
        System.out.println(worldMap.roomAt(position).description());
    }

    private void showBlocked() {
        System.out.println("이동할 수 없습니다.");
    }


}
