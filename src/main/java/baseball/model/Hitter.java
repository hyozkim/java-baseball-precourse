package baseball.model;

public class Hitter extends Player {

    private String[] splitNumber;
    public Hitter(String[] splitNumber) {
        this.splitNumber = splitNumber;
    }

    public void hit() throws IllegalArgumentException {
        for( String hitNumber : splitNumber ) {
            this.numberList.add(Integer.parseInt(hitNumber));
        }
    }

    public String[] getNumber() {
        return this.splitNumber;
    }

}
