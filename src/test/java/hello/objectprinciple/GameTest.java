package hello.objectprinciple;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    // 	•	System.setOut(...) → 표준 출력 내용을 테스트에서 읽을 수 있게 만듦
    //	•	System.setIn(...) → 키보드 입력 없이도 자동 명령어 실행 가능
    @Test
    public void contains_welcome() {
        // 1. 출력 스트림 캡처를 위한 OutputStream 생성
        OutputStream output = new ByteArrayOutputStream();

        // 2. System.out을 우리가 만든 스트림으로 변경 → 표준 출력 캡처 가능
        System.setOut(new PrintStream(output, true));

        // 3. System.in을 가짜 입력 스트림으로 대체 → "quit" 명령어 자동 입력
        System.setIn(new ByteArrayInputStream("quit\n".getBytes()));

        Game game = new Game();
        game.run();
        // 출력된 문자열을 줄바꿈 기준으로 나눠서
        // 배열로 만든 후 예상되는 출력 순서가 포함되어 있는지 확인
        assertThat(output.toString().split("\\R")).containsSequence(
                "환영합니다!",
                "당신은 [언덕]에 있습니다.",
                "저 멀리 성이 보이고 언덕 아래로 좁은 길이 나 있습니다.",
                "다음 명령어를 사용할 수 있습니다.",
                "go {north|east|south|west} - 이동, quit - 게임 종료",
                "> ",
                "게임을 종료합니다.");
    }

    @Test
    public void move_north_passed() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("go north\nquit\n".getBytes()));

        Game game = new Game();
        game.run();

        assertThat(output.toString().split("\\R")).containsSequence(
                "> 당신은 [다리]에 있습니다.",
                "큰 강 위에 돌로 만든 커다란 다리가 있습니다.",
                "> ",
                "게임을 종료합니다.");
    }

    @Test
    public void move_east_passed() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("go east\nquit\n".getBytes()));

        Game game = new Game();
        game.run();

        assertThat(output.toString().split("\\R")).containsSequence(
                "> 당신은 [동굴]에 있습니다.",
                "어둠에 잠긴 동굴 안에 작은 화톳불이 피어 있습니다.",
                "> ",
                "게임을 종료합니다.");
    }

    @Test
    public void move_east_blocked() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("go east\ngo east\nquit\n".getBytes()));

        Game game = new Game();
        game.run();

        assertThat(output.toString().split("\\R")).containsSequence(
                "> 당신은 [동굴]에 있습니다.",
                "어둠에 잠긴 동굴 안에 작은 화톳불이 피어 있습니다.",
                "> 이동할 수 없습니다.",
                "> ",
                "게임을 종료합니다.");
    }

    @Test
    public void move_south_passed() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("go north\ngo south\nquit\n".getBytes()));

        Game game = new Game();
        game.run();

        assertThat(output.toString().split("\\R")).containsSequence(
                "> 당신은 [다리]에 있습니다.",
                "큰 강 위에 돌로 만든 커다란 다리가 있습니다.",
                "> 당신은 [언덕]에 있습니다.",
                "저 멀리 성이 보이고 언덕 아래로 좁은 길이 나 있습니다.",
                "> ",
                "게임을 종료합니다.");
    }

    @Test
    public void move_south_blocked() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("go south\nquit\n".getBytes()));

        Game game = new Game();
        game.run();

        assertThat(output.toString().split("\\R")).containsSequence(
                "> 이동할 수 없습니다.",
                "> ",
                "게임을 종료합니다.");
    }

    @Test
    public void move_west_passed() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("go east\ngo west\nquit\n".getBytes()));

        Game game = new Game();
        game.run();

        assertThat(output.toString().split("\\R")).containsSequence(
                "> 당신은 [동굴]에 있습니다.",
                "어둠에 잠긴 동굴 안에 작은 화톳불이 피어 있습니다.",
                "> 당신은 [언덕]에 있습니다.",
                "저 멀리 성이 보이고 언덕 아래로 좁은 길이 나 있습니다.",
                "> ",
                "게임을 종료합니다.");
    }

    @Test
    public void move_west_blocked() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("go west\nquit\n".getBytes()));

        Game game = new Game();
        game.run();

        assertThat(output.toString().split("\\R")).containsSequence(
                "> 이동할 수 없습니다.",
                "> ",
                "게임을 종료합니다.");
    }

    @Test
    public void move_empty() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("go north\ngo north\ngo east\nquit\n".getBytes()));

        Game game = new Game();
        game.run();

        assertThat(output.toString().split("\\R")).containsSequence(
                "> 당신은 [다리]에 있습니다.",
                "큰 강 위에 돌로 만든 커다란 다리가 있습니다.",
                "> 당신은 [샘]에 있습니다.",
                "아름다운 샘물이 흐르는 곳입니다. 이곳에서 휴식을 취할 수 있습니다.",
                "> 이동할 수 없습니다.",
                "> ",
                "게임을 종료합니다.");
    }

}