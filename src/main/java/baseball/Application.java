package baseball;

import baseball.model.Pitcher;
import baseball.service.BaseBallGame;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        // 야구 게임 시작
        BaseBallGame.start(new Pitcher(new ArrayList<>()));
    }
}
