package baseball;

import baseball.controller.BaseBallGame;
import baseball.model.Pitcher;
import baseball.view.InputView;
import baseball.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // 야구 게임 시작
        BaseBallGame baseBallGame = new BaseBallGame(new InputView(), new OutputView());
        baseBallGame.start(new Pitcher());
    }
}
