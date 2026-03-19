package hello.objectprinciple.test0504;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;


class PlayerTest {

    @Test
    public void move_east() {


        WorldMap worldMap = new WorldMap(
                Size.with(2, 2),
                new Room(Position.of(0, 0), "(0,0)", " 방 (0,0)"),
                new Room(Position.of(1, 0), "(1,0)", " 방 (1,0)"),
                new Room(Position.of(1, 1), "(1,1)", " 방 (1,1)")
        );

        Player player = new Player(worldMap, Position.of(0, 0));

        Assertions.assertThat(player.move(Direction.EAST)).isTrue();
        Assertions.assertThat(player.position()).isEqualTo(Position.of(1, 0));


    }

}