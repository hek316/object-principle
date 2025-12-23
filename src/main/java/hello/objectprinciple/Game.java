package hello.objectprinciple;

import java.util.Scanner;

// 단일 책임 원칙 사전 작업- 단일 추상화 수준 원칙 적용 (추상화 수준이 동일한 조합 메서드 형태로 만들기)
// 객체 지향 핵심: 자신의 상태를 스스로 책임지는 객체
public class Game {

    private WorldMap worldMap;
    private Position position;
    private boolean running = false;

    public Game() {
        this.position = Position.of(0, 2);
        this.worldMap = new WorldMap(
                Size.with(2,3),
                new Room(Position.of(0,0), "샘", "아름다운 샘물이 흐르는 곳입니다. 이곳에서 휴식을 취할 수 있습니다."),
                new Room(Position.of(0, 1), "다리", "큰 강 위에 돌로 만든 커다란 다리가 있습니다."),
                new Room(Position.of(1, 1), "성", "용왕이 살고 있는 성에 도착했습니다."),
                new Room(Position.of(0,2), "언덕", "저 멀리 성이 보이고 언덕 아래로 좁은 길이 나 있습니다."),
                new Room(Position.of(1,2), "동굴", "어둠에 잠긴 동굴 안에 작은 화톳불이 피어 있습니다.")
        );
    }

    // 1. 책임을 한문장으로 적어본다.
    // 게임이 종료될때까지 루프를 실행하고,
    // 사용자에게서 입력을 받고,
    // 입력을 파싱하고,
    // 명령을 처리하고,
    // 처리 결과를 출력한다.
    private void play() {
        // 게임 플레이
        Scanner scanner = new Scanner(System.in);
        start();
        while (isRunning()) {
            showPrompt();
            parseCommand(scanner);
        }
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
        System.out.println("당신은 ["+ worldMap.roomAt(position).name() + "]에 있습니다.");
        System.out.println(worldMap.roomAt(position).description());
    }

    private  void showGreeting() {
        System.out.println("환영합니다!");
    }


    private void parseCommand(Scanner scanner) {
        String[] commands = input(scanner).toLowerCase().trim().split("\\s+");
        switch (commands[0]) {
            case "go" -> {
                switch (commands[1]) {
                    case "north" -> tryMove(Direction.NORTH);
                    case "south" -> tryMove(Direction.SOUTH);
                    case "east" -> tryMove(Direction.EAST);
                    case "west" -> tryMove(Direction.WEST);

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




    private void tryMove(Direction direction) {
        if (worldMap.isBlocked(position.shift(direction))) {
            showBlocked();
        } else {
            position = position.shift(direction);
            showRoom();
        }
    }




    private static void showBlocked() {
        System.out.println("이동할 수 없습니다.");
    }



    private  void farewell() {
        // 작별 문구 출력
        System.out.println("\n게임을 종료합니다.");
    }

}
