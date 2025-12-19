package hello.objectprinciple;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public static Position of(int x, int y) {
        // 값 객체처럼 작은 클래스는 생성자 대신 정적 팩토리 메서드를 사용하여 사용성과 가독성 개선
        return new Position(x, y);
    }

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }
    public int y() {
        return y;
    }

    public Position shift(Direction direction) {
        return switch (direction) {
            case NORTH -> Position.of(x, y-1);
            case EAST -> Position.of(x + 1, y);
            case SOUTH -> Position.of(x, y + 1);
            case WEST -> Position.of(x -1 , y);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
