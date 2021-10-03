package baseball.model;

import nextstep.utils.Console;

import java.util.ArrayList;
import java.util.List;

public class Hitter {
    private static final int MAX_LENGTH = 3;
    private List<Integer> hitNumberList;

    public Hitter() {}

    public void hit() throws IllegalArgumentException {
        hitNumberList = new ArrayList<>();

        String hitNumbersString = Console.readLine();
        String[] hitNumbers = hitNumbersString.split("");
        if( hitNumbers.length > MAX_LENGTH ) {
            throw new IllegalArgumentException("숫자는 최대 3자 입력할 수 있습니다.");
        }

        for( String hitNumber : hitNumbers ) {
            int hitNumberAsInt = Integer.parseInt(hitNumber);
            if( hitNumberList.contains(hitNumberAsInt) ) {
                throw new IllegalArgumentException("서로 다른 숫자 3개를 입력해야 합니다.");
            }
            hitNumberList.add(hitNumberAsInt);
        }
    }

    public List<Integer> getHits() {
        return hitNumberList;
    }
}
