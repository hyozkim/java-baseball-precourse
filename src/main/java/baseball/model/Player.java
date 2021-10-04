package baseball.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected List<Integer> numberList;

    public Player() {
        this.numberList = new ArrayList<>();
    }

    public List<Integer> getNumbers() {
        return numberList;
    }
}
