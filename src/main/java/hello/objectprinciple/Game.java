package hello.objectprinciple;

import java.util.Scanner;

public class Game {

    private int width, height;
    private Room[] rooms;
    private Position position;
    private boolean running = false;

    public Game() {
        position = Position.of(0, 2);
        this.width = 2;
        this.height = 3;
        this.rooms = arrangeRoom(
                new Room(0, 0, "샘", "아름다운 샘물이 흐르는 곳입니다. 이곳에서 휴식을 취할 수 있습니다."),
                new Room(0, 1, "다리", "큰 강 위에 돌로 만든 커다란 다리가 있습니다."),
                new Room(1, 1, "성", "용왕이 살고 있는 성에 도착했습니다."),
                new Room(0, 2, "언덕", "저 멀리 성이 보이고 언덕 아래로 좁은 길이 나 있습니다."),
                new Room(1, 2, "동굴", "어둠에 잠긴 동굴 안에 작은 화톳불이 피어 있습니다.")
        );
    }

    private Room[] arrangeRoom(Room ... rooms) {
        Room[] result = new Room[width * height];
        for (var room : rooms) {
            result[room.x() + room.y()* width] = room;
        }
        return result;
    }


    public void run() {
        welcome();
        play();
        farewell();
    }


    private void welcome() {
        showGreeting();
        showRoom();
        showHelp();
    }

    private void showHelp() {
        System.out.println("다음 명령어를 사용할 수 있습니다.");
        System.out.println("go {north|east|south|west} - 이동, quit - 게임 종료");
    }

    private void showRoom() {
        System.out.println("당신은 ["+ rooms[position.x() + position.y() * width].name() + "]에 있습니다.");
        System.out.println(rooms[position.x() + position.y() * width].description());
    }

    private  void showGreeting() {
        System.out.println("환영합니다!");
    }

    private void play() {
        // 게임 플레이
        Scanner scanner = new Scanner(System.in);
        start();
        while (isRunning()) {
            showPrompt();
            parseCommand(scanner);
        }
    }

    private void parseCommand(Scanner scanner) {
        String[] commands = input(scanner).toLowerCase().trim().split("\\s+");
        switch (commands[0]) {
            case "go" -> {
                switch (commands[1]) {
                    case "north" -> moveNorth();
                    case "south" -> moveSouth();
                    case "east" -> moveEast();
                    case "west" -> moveWest();

                    default -> showUnknown();
                }
            }
            case "quit" -> stop();
            default -> showUnknown();
        }
    }

    private String input(Scanner scanner) {
        return scanner.nextLine();
    }

    private static void showPrompt() {
        System.out.print("> ");
    }

    private static void showUnknown() {
        System.out.println("이해할 수 없는 명령어입니다.");
    }

    private void stop() {
        running = false;
    }

    private boolean isRunning() {
        return running;
    }

    private void start() {
        running = true;
    }

    private void moveWest() {
        tryMove(-1, 0);
    }


    private void moveEast() {
        tryMove(1, 0);
    }



    private void moveSouth() {
        tryMove(0, 1);
    }


    private void moveNorth() {

        tryMove(0, -1);
    }


    private void tryMove(int incX, int incY) {
        if (isBlocked(position.shift(incX, incY))) {
            showBlocked();
        } else {
            position = position.shift(incX, incY);
            showRoom();
        }
    }

    private boolean isBlocked(Position position) {
        return isExcluded(position) || rootAt(position) == null;
    }

    private boolean isExcluded(Position position) {
        return position.y() < 0 || position.y()  >= height || position.x()  < 0 || position.x()  >= width;
    }

    private static void showBlocked() {
        System.out.println("이동할 수 없습니다.");
    }


    private Room rootAt(Position position) {
        return rooms[(position.x()) + position.y() * width];
    }

    private  void farewell() {
        // 작별 문구 출력
        System.out.println("\n게임을 종료합니다.");
    }

}
