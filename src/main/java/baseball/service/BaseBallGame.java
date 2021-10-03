package baseball.service;

import baseball.dto.Progress;
import baseball.dto.Result;
import baseball.model.Hitter;
import baseball.model.Pitcher;

import java.util.ArrayList;
import java.util.List;

public class BaseBallGame {

    public static void start(Pitcher pitcher) {
        // 상대방(컴퓨터) 투구. (서로 다른 수 3개 제시)
        pitcher.pitch();
        List<Integer> balls = pitcher.getBalls();

        // 타자(사용자) 배팅. (입력)
        play(balls, new Hitter());

        // 게임 종료
        gameOver();
    }

    private static void play(List<Integer> balls, Hitter hitter) {
        Progress progress = Progress.NOTHING;

        while( !Progress.END.equals(progress) ) {
            boolean running = Play.play(hitter); // 타자 배팅
            if( !running )
                continue;

            List<Integer> hits = hitter.getHits();

            Result gameResult = Play.score(balls, hits); // 점수 결과
            progress = gameResult.getProgress();
            System.out.println(gameResult.getDescription());
        }
    }

    private static void gameOver() {
        boolean over = Play.over();

        if( !over ) {
            start(new Pitcher(new ArrayList<>()));
        }
    }
}
