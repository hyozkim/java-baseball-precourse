package baseball.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PitcherTest {
    private String number;

    @BeforeEach
    void beforeEach() {
        Pitcher pitcher = new Pitcher();
        pitcher.pitch();

        number = "";
        for( Integer n : pitcher.getNumbers() ) {
            number += String.valueOf(n);
        }
    }

    @DisplayName("랜덤 생성 숫자 서로 다른 숫자로 이루어진지 확인")
    @Test
    void 랜덤_생성_서로_다른_숫자로_이루어진지_확인() {
        assertThat(validateLength(number.split(""))).isTrue();
    }

    @DisplayName("랜덤 생성 숫자 범위 확인")
    @Test
    void 랜덤_생성된_숫자_범위_확인() {
        int numberAsInt = Integer.parseInt(number);

        assertThat(numberAsInt).isBetween(100,999);
    }

    @DisplayName("1~9 서로 다른 숫자로 이루어진 숫자 확인")
    @ParameterizedTest
    @CsvSource(value = {"132","934","713","597","589"})
    void 서로_다른_숫자로_이루어진지_확인(String number) {
        String[] splitNumber = number.split("");
        assertThat(validateLength(splitNumber)).isTrue();
    }

    /**
     * InputView class method
     * @param splitNumber
     * @return
     * @throws IllegalArgumentException
     */
    private boolean validateLength(String[] splitNumber) throws IllegalArgumentException {
        Set<Integer> set = new HashSet<>();
        for( String hitNumber : splitNumber ) {
            int hitNumberAsInt = Integer.parseInt(hitNumber);
            if( set.contains(hitNumberAsInt) ) {
                return true;
            }
            set.add(hitNumberAsInt);
        }

        return true;
    }
}