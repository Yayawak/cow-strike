package model;

public class Cow {
    private String name;
    private String color;
    private int score;
    private boolean isStrike;
    private boolean isSpare;

    public Cow(String name, String color) {
        this.name = name;
        this.color = color;
        this.score = 0;
        this.isStrike = false;
        this.isSpare = false;
    }

    public void addScore(int pins) {
        score += pins;
    }

    // Set this cow as strike in a round
    public void setStrike() {
        this.isStrike = true;
    }

    // Set this cow as spare in a round
    public void setSpare() {
        this.isSpare = true;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    // each round we reset strike and spare
    public void resetRound() {
        isStrike = false;
        isSpare = false;
    }
}
