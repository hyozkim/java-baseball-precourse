package baseball.view;

import baseball.model.Hitter;
import nextstep.utils.Console;

import java.util.HashSet;
import java.util.Set;

import static baseball.common.Constant.MAX_LENGTH;

public class InputView {
    private String[] splitNumber;

    public InputView() {}

    public Hitter inputNumber() throws IllegalArgumentException {
        System.out.print("숫자를 입력해주세요 : ");
        String number = Console.readLine();
        splitNumber = number.split("");

        validateInputNumber(number);
        validateLength();
        validateDuplicate();
        return new Hitter(splitNumber);
    }

    private void validateInputNumber(String number) throws IllegalArgumentException {
        if( number.indexOf('0') >= 0 ) {
            throw new IllegalArgumentException("숫자는 1~9 중에서 입력 가능합니다.");
        }
    }

    private void validateLength() throws IllegalArgumentException {
        if( splitNumber.length < MAX_LENGTH || splitNumber.length > MAX_LENGTH ) {
            throw new IllegalArgumentException("숫자는 "+MAX_LENGTH+"자로 입력되어야 합니다.");
        }
    }

    private void validateDuplicate() throws IllegalArgumentException {
        Set<Integer> set = new HashSet<>();
        for( String hitNumber : splitNumber ) {
            int hitNumberAsInt = Integer.parseInt(hitNumber);
            if( set.contains(hitNumberAsInt) ) {
                throw new IllegalArgumentException("서로 다른 숫자 3개를 입력해야 합니다.");
            }
            set.add(hitNumberAsInt);
        }
    }

    public boolean isContinue() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String continueYn = Console.readLine();

        return "1".equals(continueYn);
    }
}
