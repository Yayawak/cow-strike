package model;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String color;
    private List<Cow> cows;

    public Team(String color) {
        this.color = color;
        cows = new ArrayList<>();
        // create team for 3 cows
        for (int i = 1; i <= 3; i++) {
            cows.add(new Cow(color + " Cow " + i, color));
        }
    }

    public List<Cow> getCows() {
        return cows;
    }

    public int getTotalScore() {
        return cows.stream().mapToInt(Cow::getScore).sum();
    }

    public String getColor() {
        return color;
    }
}
