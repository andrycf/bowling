package mg.andrycf.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class GameTest {

    private Game game;

    @BeforeEach
    void init() {
        game = new Game();
    }

    @Test
    @DisplayName("Show between 1 and 5")
    void show_range_1_5() {
        IntStream.rangeClosed(1, 5).forEach(i -> System.out.println(String.format("Range : #%s",i)));
    }

    @Test
    @DisplayName("Sum between 1 and 4")
    void sum_range_1_4() {
        int res = IntStream
                .range(1, 5)
                .reduce(0, (sum, index) -> sum + index);
        Assertions.assertEquals(res,10);
    }

    @Test
    @DisplayName("12 rolls: 12 strikes")
    void score_should_be_300() {
        // Given
        playManyFrames(12, 10, 0);
        // When
        int score = game.getScore();
        // Then
        Assertions.assertEquals(300, score);
    }

    @Test
    @DisplayName("20 rolls: 10 pairs of 9 and miss")
    void score_should_be_90() {
        // Given
        playManyFrames(20, 9, 0);
        // When
        int score = game.getScore();
        // Then
        Assertions.assertEquals(90, score);
    }

    @Test
    @DisplayName("21 rolls: 10 pairs of 5 and spare, with a final 5")
    void score_should_be_150() {
        // Given
        playManyFrames(21, 5, 5);
        // When
        int score = game.getScore();
        // Then
        Assertions.assertEquals(150, score);
    }

    private void playStrike() {
        playFrame(10, 0);
    }

    private void playSpare() {
        playFrame(5, 5);
    }

    private void playManyFrames(int nbFrames, int firstRoll, int secondRoll) {
        IntStream.rangeClosed(1, nbFrames).forEach(i -> playFrame(firstRoll, secondRoll));
    }

    private void playFrame(int firstRoll, int secondRoll) {
        game.playFrame(new Frame(firstRoll, secondRoll));
    }
}