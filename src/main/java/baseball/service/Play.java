package baseball.service;

import baseball.dto.Result;

import java.util.List;

import static baseball.common.Constant.MAX_LENGTH;

public class Play {
    private int strike;
    private int ball;

    public Play() {
        this.strike = 0;
        this.ball = 0;
    }

    /**
     * baseball 점수 결과
     *
     * @param balls
     * @param hits
     * @return
     */
    public Result score(List<Integer> balls, List<Integer> hits) {
        validateLength(balls.size(), hits.size());

        for (int i = 0; i < balls.size(); i++) {
            this.strike += isStrike(balls.get(i), hits.get(i)) ? 1 : 0;
            this.ball   += isBall(balls, balls.get(i), hits.get(i)) ? 1 : 0;
        }

        return new Result(this.strike, this.ball).getResult();
    }

    /**
     * Ball 여부
     * 같은 index에서 숫자는 다르지만 ball list에 포함
     *
     * @param ballNumber
     * @param hitNumber
     * @return
     */
    private boolean isBall(List<Integer> ball, int ballNumber, int hitNumber) {
        if (ball.contains(hitNumber) && ballNumber != hitNumber) {
            return true;
        }
        return false;
    }

    /**
     * Strike 여부
     * 같은 index에서 숫자가 같음
     *
     * @param ballNumber
     * @param hitNumber
     * @return
     */
    private boolean isStrike(int ballNumber, int hitNumber) {
        if (ballNumber == hitNumber) {
            return true;
        }
        return false;
    }

    /**
     * 3개 숫자 비교 전 length Valid
     *
     * @param ballLength
     * @param hitLength
     */
    private void validateLength(int ballLength, int hitLength) {
        if (!(ballLength == MAX_LENGTH && ballLength == hitLength)) {
            throw new IndexOutOfBoundsException("투수, 타자 입력 숫자 길이가 3이 아닙니다.");
        }
    }

    public int getStrike() {
        return this.strike;
    }

    public int getBall() {
        return this.ball;
    }
}
