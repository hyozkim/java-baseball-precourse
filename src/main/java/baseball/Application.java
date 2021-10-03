package baseball;

import baseball.service.BaseBallGame;
import baseball.service.Pitcher;

import java.util.HashSet;

public class Application {
    public static void main(String[] args) {
        // 야구 게임 시작
        BaseBallGame.start(new Pitcher(new HashSet<>()));
    }
}
