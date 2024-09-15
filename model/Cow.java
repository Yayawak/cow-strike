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

    public void setStrike() {
        this.isStrike = true;
    }

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

    public void resetRound() {
        isStrike = false;
        isSpare = false;
    }
}
