package hello.objectprinciple;

//
// 지도의 구조를 관리하는 책임만 맞는 아주 작은 클래스
public class WorldMap {

    private Size size;
    private Room[] rooms;

    public WorldMap(Size area, Room ... rooms) {
        this.size = area;
        this.rooms = new Room[size.area()];
        for (Room room : rooms) {
            this.rooms[size.indexOf(room.position())] = room;
        }
    }

    public boolean isBlocked(Position position) {
        return isExcluded(position) || roomAt(position) == null;
    }

    private boolean isExcluded(Position position) {
        return !size.contains(position);
    }

    public Room roomAt(Position position) {
        return rooms[size.indexOf(position)];
    }
}
