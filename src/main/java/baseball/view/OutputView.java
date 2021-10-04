package baseball.view;

import baseball.dto.Progress;
import baseball.dto.Result;

import static baseball.common.Constant.MAX_LENGTH;

public class OutputView {
    private static final String STRIKE_WORD = "스트라이크";
    private static final String BALL_WORD = "볼";
    private static final String NOTHING_WORD = "낫싱";
    private static final String END_GAME = (MAX_LENGTH+"개 숫자를 모두 맞히셨습니다! 게임 끝");

    public void printResult(Result result) {
        if( Progress.NOTHING.equals(result.getProgress()) ) {
            System.out.println(NOTHING_WORD);
            return;
        }

        printScore(result);
    }

    private void printScore(Result result) {
        if( Progress.STRIKE_BALL.equals(result.getProgress()) ) {
            System.out.println(result.getStrike()+STRIKE_WORD + " " + result.getBall()+BALL_WORD);
        } else if( Progress.BALL.equals(result.getProgress()) ) {
            System.out.println(result.getBall()+BALL_WORD);
        } else if( Progress.STRIKE.equals(result.getProgress()) ) {
            System.out.println(result.getStrike()+STRIKE_WORD);
        }
    }

    public void printEnd() {
        System.out.println(END_GAME);
    }
}
