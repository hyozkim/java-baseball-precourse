package baseball.model;

import nextstep.utils.Randoms;

import static baseball.common.Constant.*;

public class Pitcher extends Player {

    public Pitcher() {}

    public void pitch() {
        createNoneDuplicateNumber();
    }

    private int createNumber() {
        return Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
    }

    private void createNoneDuplicateNumber() {
        while( this.numberList.size() < MAX_LENGTH ) {
            int n = createNumber();
            if( !this.numberList.contains(n) ) {
                this.numberList.add(n);
            }
        }
    }
}
