package baseball.service;

import baseball.dto.Progress;
import baseball.dto.Result;
import baseball.model.Hitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayTest {
    private List<Integer> gameBalls;

    @BeforeEach
    void beforeEach() {
        String[] numbers = new String[] {"1","4","5"};

        gameBalls = new ArrayList<>();
        for( String number : numbers ) {
            gameBalls.add(Integer.parseInt(number));
        }
    }

    @ParameterizedTest
    @DisplayName("1스트라이크 1볼 확인")
    @CsvSource(value = {"124","134","174","159","465","341","941","341"})
    void 점수_1스트라이크_1볼(String number) {
        String[] splitNumber = number.split("");
        Hitter hitter = new Hitter(splitNumber);
        hitter.hit();

        Play play = new Play();
        Result result = play.score(gameBalls, hitter.getNumbers());

        assertTrue(Progress.STRIKE_BALL.equals(result.getProgress()));
        assertTrue(1 == result.getStrike());
        assertTrue(1 == result.getBall());
    }

    @ParameterizedTest
    @DisplayName("1스트라이크 여부")
    @CsvSource(value = {"129","138","172","139","765","346","947","349"})
    void 점수_1스트라이크(String number) {
        String[] splitNumber = number.split("");
        Hitter hitter = new Hitter(splitNumber);
        hitter.hit();

        Play play = new Play();
        Result result = play.score(gameBalls, hitter.getNumbers());

        assertTrue(Progress.STRIKE.equals(result.getProgress()));
        assertTrue(1 == result.getStrike());
        assertTrue(0 == result.getBall());
    }

    @ParameterizedTest
    @DisplayName("3스트라이크 여부")
    @CsvSource(value = {"145"})
    void 점수_3스트라이크(String number) {
        String[] splitNumber = number.split("");
        Hitter hitter = new Hitter(splitNumber);
        hitter.hit();

        Play play = new Play();
        Result result = play.score(gameBalls, hitter.getNumbers());

        assertTrue(Progress.STRIKE.equals(result.getProgress()));
        assertTrue(3 == result.getStrike());
        assertTrue(0 == result.getBall());
    }

    @ParameterizedTest
    @DisplayName("낫싱 여부")
    @CsvSource(value = {"987"})
    void 점수_낫싱(String number) {
        String[] splitNumber = number.split("");
        Hitter hitter = new Hitter(splitNumber);
        hitter.hit();

        Play play = new Play();
        Result result = play.score(gameBalls, hitter.getNumbers());

        assertTrue(Progress.NOTHING.equals(result.getProgress()));
        assertTrue(0 == result.getStrike());
        assertTrue(0 == result.getBall());
    }

    @ParameterizedTest
    @DisplayName("투수, 타자 입력 숫자 IndexOutOfBoundsException 처리")
    @CsvSource(value = {"98","1293","1","1231245"})
    void 문자열_n번째_갯수(String number) {
        String[] splitNumber = number.split("");
        Hitter hitter = new Hitter(splitNumber);
        hitter.hit();

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> {
                    new Play().score(gameBalls, hitter.getNumbers());
                }).withMessageMatching("투수, 타자 입력 숫자 길이가 3이 아닙니다.");
    }
}