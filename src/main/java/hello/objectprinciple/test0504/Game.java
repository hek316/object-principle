package hello.objectprinciple.test0504;


import java.util.Scanner;

public class Game {
    private Player player;
    private CommandParser commandParser;
    private boolean running;

    public Game() {
        this.commandParser = new CommandParser();
    }

    public void run() {
        welcome();
        play();
        farewell();
    }

    private void welcome() {
        showGreetings();
        showRoom();
        showHelp();
    }

    private void showGreetings() {
        System.out.println("환영합니다!");
    }

    private void showHelp() {
        System.out.println("다음 명령어를 사용할 수 있습니다.");
        System.out.println("go {north|east|south|west} - 이동, look - 보기, help - 도움말, quit - 게임 종료");
    }

    private void farewell() {
        System.out.println("\n게임을 종료합니다.");
    }


    private void play() {
        Scanner scanner = new Scanner(System.in);

        start();
        while (isRunning()) {
            String input = inputCommand(scanner);
            Command command = commandParser.parseCommand(input);
            executeCommand(command);
        }
    }

    private void executeCommand(Command command) {
        switch(command) {
            case Command.Move move -> player.tryMove(move.direction());
            case Command.Look() -> showRoom();
            case Command.Help() -> showHelp();
            case Command.Quit() -> stop();
            case Command.Unknown() -> showUnknownCommand();
        }
    }

    private boolean isRunning() {
        return running == true;
    }

    private String inputCommand(Scanner scanner) {
        showPrompt();
        return input(scanner);
    }

    private void start() {
        running = true;
    }

    private void stop() {
        this.running = false;
    }

    private void showUnknownCommand() {
        System.out.println("이해할 수 없는 명령어입니다.");
    }

    private String input(Scanner scanner) {
        return scanner.nextLine();
    }

    private void showPrompt() {
        System.out.print("> ");
    }


    public void showRoom() {
        System.out.println("당신은 [" + player.worldMap().roomAt(player.position()).name() + "]에 있습니다.");
        System.out.println(player.worldMap().roomAt(player.position()).description());
    }

    public void showBlocked() {
        System.out.println("이동할 수 없습니다.");
    }
}
