# 숫자 야구 게임
## 진행 방법
* 숫자 야구 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

### 기능 단위 Commit 리스트
- `init` 요구사항 및 기능 단위 작성
- `random` 컴퓨터 숫자 3개 선택
  - 1 ~ 9 서로 다른 수 작성
- `play` 사용자 입력 & 일치 여부 확인 & 게임 종료
  - 사용자 잘못된 입력 Error 내용 출력
  - 같은 자릿수 같은 숫자가 있으면 `Strike`
  - 다른 자릿수 같은 숫자가 있으면 `Ball`
  - 숫자가 하나도 같은게 없으면 `낫싱`
  - 자릿수, 숫자 모두 같으면 게임 종료
  - 게임 끝난 후 게임을 다시 시작하거나 완전히 종료할 수 있음
- `refactor` 코드 리팩토링
  - **리팩토링** static class -> static 제거
  - **리팩토링** 공통 중복 사용된 코드 제거. (각 메소드 10줄이하)
  - **리팩토링** Input,Output UI 와 핵심 로직 분리
  - **리팩토링** depth-2 들여쓰기 넘지않도록 수정
  
- `exception` 잘못 입력된 경우에도 Exception 처리 후 게임 진행
- `test` TC 작성
  - `숫자는 최대 3자로 입력되어야 합니다.` > 1,2자도 입력되면 안되기에 문구 수정.
  - 점수 확인 및 Exception 테스트 케이스 작성 `pdf 파일 참고`

