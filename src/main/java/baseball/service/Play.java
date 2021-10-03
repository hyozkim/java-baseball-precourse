package baseball.service;

import baseball.dto.Progress;
import baseball.dto.Result;
import baseball.model.Hitter;
import nextstep.utils.Console;

import java.util.List;

public class Play {
    private static final String STRIKE_WORD = "스트라이크";
    private static final String BALL_WORD = "볼";
    private static final String NOTHING_WORD = "낫싱";
    private static final String END_GAME = "3개 숫자를 모두 맞히셨습니다! 게임 끝";

    /**
     * baseball 게임 플레이
     * @param hitter
     * @return
     */
    public static boolean play(Hitter hitter) {
        System.out.print("숫자를 입력해주세요 : ");

        try {
            hitter.hit();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * baseball 점수 결과
     * @param balls
     * @param hits
     * @return
     */
    public static Result score(List<Integer> balls, List<Integer> hits) {
        validateLength(balls.size(), hits.size());

        int length = balls.size();
        int strikeCount = 0;  int ballCount = 0;

        for (int i = 0; i < length; i++) {
            strikeCount += isStrike(balls.get(i), hits.get(i)) ? 1 : 0;
            ballCount   += isBall(balls, balls.get(i), hits.get(i)) ? 1 : 0;
        }

        //System.out.println("strikeCount: " + strikeCount + ", ballCount: " + ballCount);
        return result(strikeCount, ballCount);
    }

    /**
     * 1) 스트라이크 3개 / 볼 0개      | 3스트라이크,게임 끝   |  (END)
     * 2) 스트라이크 n개 / 볼 0개      | n스트라이크         | (STRIKE)
     * 3) 스트라이크 n개 / 볼 (3-n)개  | n스트라이크 (3-n)볼  | (STRIKE)
     * 4) 스트라이크 0개 / 볼 n개      | n볼               | (BALL)
     * 5) 스트라이크 0개 / 볼 0개      | 낫싱              | (NOTHING)
     *
     * @param strikeCount
     * @param ballCount
     * @return
     */
    private static Result result(int strikeCount, int ballCount) {

        if( strikeCount >= 1 && ballCount == 0 ) {
            if( strikeCount == 3 ) {
                System.out.println(strikeCount+STRIKE_WORD);
                // 1)
                return new Result.Builder()
                        .progress(Progress.END)
                        .description(END_GAME)
                        .build();
            }

            // 2)
            return new Result.Builder()
                    .progress(Progress.STRIKE)
                    .description(strikeCount+STRIKE_WORD)
                    .build();

        } else if( strikeCount == 0 && ballCount >= 1 ) {
            // 4)
            return new Result.Builder()
                    .progress(Progress.BALL)
                    .description(ballCount + BALL_WORD)
                    .build();

        } else if( (strikeCount >= 1 && strikeCount < 3) && (ballCount >= 1 && ballCount < 3) ) {
            // 3)
            return new Result.Builder()
                    .progress(Progress.STRIKE)
                    .description(strikeCount + STRIKE_WORD + " " + ballCount + BALL_WORD)
                    .build();

        }

        // 5)
        return new Result.Builder()
                .progress(Progress.NOTHING)
                .description(NOTHING_WORD)
                .build();
    }


    /**
     * Ball 여부
     * 같은 index에서 숫자는 다르지만 ball list에 포함
     * @param ballNumber
     * @param hitNumber
     * @return
     */
    private static boolean isBall(List<Integer> ball, int ballNumber, int hitNumber) {
        if( ball.contains(hitNumber) && ballNumber != hitNumber ) {
            return true;
        }
        return false;
    }

    /**
     * Strike 여부
     * 같은 index에서 숫자가 같음
     * @param ballNumber
     * @param hitNumber
     * @return
     */
    private static boolean isStrike(int ballNumber, int hitNumber) {
        if( ballNumber == hitNumber ) {
            return true;
        }
        return false;
    }

    /**
     * 3개 숫자 비교 전 length Valid
     * @param ballLength
     * @param hitLength
     */
    private static void validateLength(int ballLength, int hitLength) {
        if( !(ballLength == 3 && ballLength == hitLength) ) {
            throw new IllegalArgumentException("투수, 타자 입력 숫자 길이가 3이 아닙니다.");
        }
    }

    public static boolean over() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String continueYn = Console.readLine();

        if( "1".equals(continueYn) ) {
            return false;
        }
        return true;
    }
}
