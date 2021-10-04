package baseball.dto;

import static baseball.common.Constant.MAX_LENGTH;

public class Result {
    private Progress progress;
    private int strike;
    private int ball;

    public Result(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    Result(Progress progress, int strike, int ball) {
        this.progress = progress;
        this.strike = strike;
        this.ball = ball;
    }

    /**
     * 1) 스트라이크 3개 / 볼 0개      | 3스트라이크,게임 끝   |  (END)
     * 2) 스트라이크 n개 / 볼 0개      | n스트라이크         | (STRIKE)
     * 3) 스트라이크 n개 / 볼 (3-n)개  | n스트라이크 (3-n)볼  | (STRIKE)
     * 4) 스트라이크 0개 / 볼 n개      | n볼               | (BALL)
     * 5) 스트라이크 0개 / 볼 0개      | 낫싱              | (NOTHING)
     *
     * @return
     */
    public Result getResult() {
        if ( hitStrikeOnly() ) {
            return strikeResult(); // 1,2)
        } else if ( hitBallOnly() ) {
            return ballResult(); // 4)
        } else if ( hitStrikeAndBall() ) {
            return strikeBallResult(); // 3)
        }
        return nothingResult(); // 5)
    }

    private Result nothingResult() {
        return new Builder()
                .progress(Progress.NOTHING)
                .build();
    }

    private Result strikeBallResult() {
        return new Builder()
                .progress(Progress.STRIKE_BALL)
                .strike(this.strike)
                .ball(this.ball)
                .build();
    }

    private Result strikeResult() {
        return new Builder()
                .progress(Progress.STRIKE)
                .strike(this.strike)
                .build();
    }

    private Result ballResult() {
        return new Builder()
                .progress(Progress.BALL)
                .ball(this.ball)
                .build();
    }

    private boolean hitStrikeOnly() {
        return (this.strike > 0 && this.ball == 0);
    }

    private boolean hitBallOnly() {
        return (this.strike == 0 && this.ball > 0);
    }

    private boolean hitStrikeAndBall() {
        return (this.strike > 0 && this.strike < MAX_LENGTH) && (this.ball > 0 && this.ball < MAX_LENGTH);
    }

    static public class Builder {
        private Progress progress;
        private int strike;
        private int ball;

        public Builder() {}

        public Builder(Result result) {
            this.progress = result.progress;
            this.strike = result.strike;
            this.ball = result.ball;
        }

        public Builder progress(Progress progress) {
            this.progress = progress;
            return this;
        }

        public Builder strike(int strike) {
            this.strike = strike;
            return this;
        }

        public Builder ball(int ball) {
            this.ball = ball;
            return this;
        }

        public Result build() {
            return new Result(progress, strike, ball);
        }
    }

    public Progress getProgress() {
        return progress;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }
}
