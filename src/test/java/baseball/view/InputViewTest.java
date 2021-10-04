package baseball.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static baseball.common.Constant.MAX_LENGTH;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class InputViewTest {

    @ParameterizedTest
    @DisplayName("숫자 1부터 9까지 입력 예외처리")
    @CsvSource(value = {"018","108","910"})
    void 숫자_1부터_9까지_입력_예외처리(String number) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    validateInputNumber(number);
                }).withMessageMatching("숫자는 1~9 중에서 입력 가능합니다.");
    }

    @ParameterizedTest
    @DisplayName("숫자 3자리 이외의 길이 입력 예외처리")
    @CsvSource(value = {"18","1","912384729","1824"})
    void 숫자_3자리_이외의_길이_입력_예외처리(String number) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    validateLength(number);
                }).withMessageMatching("숫자는 "+MAX_LENGTH+"자로 입력되어야 합니다.");

    }

    @ParameterizedTest
    @DisplayName("중복된 숫자 입력 예외처리")
    @CsvSource(value = {"122","119","112","787","515","551"})
    void 중복된_숫자_입력_예외처리(String number) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    validateDuplicate(number);
                }).withMessageMatching("서로 다른 숫자 3개를 입력해야 합니다.");
    }


    // ---
    // private method clone

    private void validateInputNumber(String number) throws IllegalArgumentException {
        if( number.indexOf('0') >= 0 ) {
            throw new IllegalArgumentException("숫자는 1~9 중에서 입력 가능합니다.");
        }
    }

    private void validateLength(String number) throws IllegalArgumentException {
        String[] splitNumber = number.split("");
        if( splitNumber.length < MAX_LENGTH || splitNumber.length > MAX_LENGTH ) {
            throw new IllegalArgumentException("숫자는 3자로 입력되어야 합니다.");
        }
    }

    private void validateDuplicate(String number) throws IllegalArgumentException {
        String[] splitNumber = number.split("");

        Set<Integer> set = new HashSet<>();
        for( String hitNumber : splitNumber ) {
            int hitNumberAsInt = Integer.parseInt(hitNumber);
            if( set.contains(hitNumberAsInt) ) {
                throw new IllegalArgumentException("서로 다른 숫자 3개를 입력해야 합니다.");
            }
            set.add(hitNumberAsInt);
        }
    }
}