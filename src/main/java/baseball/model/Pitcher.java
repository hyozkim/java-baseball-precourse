package baseball.model;

import nextstep.utils.Randoms;

import java.util.List;

public class Pitcher {
    private List<Integer> pitchNumberList;

    private static final int MAX_LENGTH = 3;
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 9;

    public Pitcher(List<Integer> pitchNumberList) {
        this.pitchNumberList = pitchNumberList;
    }

    public void pitch() {
        while( pitchNumberList.size() < MAX_LENGTH ) {
            int randomNumber = Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
            if( !pitchNumberList.contains(randomNumber) ) {
                pitchNumberList.add(randomNumber);
            }
        }
    }

    public List<Integer> getBalls() {
        return pitchNumberList;
    }
}
