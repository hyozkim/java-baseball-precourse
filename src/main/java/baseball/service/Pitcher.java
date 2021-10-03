package baseball.service;

import nextstep.utils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Pitcher {
    private Set<Integer> pitchNumberSet;

    private static final int MAX_LENGTH = 3;
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 9;

    public Pitcher(Set<Integer> pitchNumberSet) {
        this.pitchNumberSet = pitchNumberSet;
    }

    public List<Integer> pitch() {
        while( pitchNumberSet.size() < MAX_LENGTH ) {
            pitchNumberSet.add(Randoms.pickNumberInRange(START_NUMBER, END_NUMBER));
        }

        return new ArrayList<>(pitchNumberSet);
    }
}
