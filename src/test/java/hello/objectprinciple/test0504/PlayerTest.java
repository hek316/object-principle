package hello.objectprinciple.test0504;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;


class PlayerTest {

    @Test
    public void move_east() {
        OutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        WorldMap worldMap = new WorldMap(
                Size.with(2, 2),
                new Room(Position.of(0, 0), "(0,0)", " 방 (0,0)"),
                new Room(Position.of(1, 0), "(1,0)", " 방 (1,0)"),
                new Room(Position.of(1, 1), "(1,1)", " 방 (1,1)")
        );

        Player player = new Player(worldMap, Position.of(0, 0));

        player.tryMove(Direction.EAST);
        assertThat(output.toString()).containsSequence(
               "당신은 [(1,0)]에 있습니다.\n" ,
                       " 방 (1,0)"
        );


    }

}