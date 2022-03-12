package mg.andrycf.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class GameTest {

    private Game game;

    @BeforeEach
    void init() {
        game = new Game();
    }

    @Test
    void score_should_be_300() {
        // Given
        playManyFrames(12, 10, 0);
        // When
        int score = game.getScore();
        // Then
        Assertions.assertEquals(300, score);
    }

    @Test
    void score_should_be_90() {
        // Given
        playManyFrames(20, 9, 0);
        // When
        int score = game.getScore();
        // Then
        Assertions.assertEquals(90, score);
    }

    @Test
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