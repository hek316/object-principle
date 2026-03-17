package hello.objectprinciple;

/**
 * 의존성 분리
 */
public class Player {
    private WorldMap worldMap;
    private Position position;
    private Console console;

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
    public void showRoom() {
        console.showLine("당신은 ["+ worldMap.roomAt(position).name() + "]에 있습니다.");
        console.showLine(worldMap.roomAt(position).description());
    }

    private void showBlocked() {
        console.showLine("이동할 수 없습니다.");
    }


}
