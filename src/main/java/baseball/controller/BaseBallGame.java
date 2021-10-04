package baseball.controller;

import baseball.dto.Progress;
import baseball.dto.Result;
import baseball.model.Hitter;
import baseball.model.Pitcher;
import baseball.service.Play;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.List;

import static baseball.common.Constant.MAX_LENGTH;

public class BaseBallGame {
    private InputView inputView;
    private OutputView outputView;

    public BaseBallGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(Pitcher pitcher) {
        pitcher.pitch();

        Progress progress = Progress.NOTHING;
        while( !Progress.END.equals(progress) ) {
            progress = play(pitcher.getNumbers());
            if( Progress.STOP.equals(progress) ) {
                continue;
            }
        }

        gameOver();
    }

    private Progress play(List<Integer> balls) {
        Hitter hitter = null;
        try {
            hitter = inputView.inputNumber();
            hitter.hit();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return Progress.STOP;
        }

        Result result = new Play().score(balls, hitter.getNumbers()); // 점수 결과
        outputView.printResult(result);
        return finalResult(result);
    }

    private Progress finalResult(Result result) {
        if(result.getStrike() == MAX_LENGTH
                && Progress.STRIKE.equals(result.getProgress())) {
            return Progress.END;
        } // 게임 종료 조건 - 3스트라이크
        return result.getProgress();
    }

    private void gameOver() {
        outputView.printEnd();
        if( inputView.isContinue() ) {
            start(new Pitcher());
        }
    }
}
