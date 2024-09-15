package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Cow;
import model.Team;
import view.GameView;

public class BowlingController {
    private Team whiteTeam;
    private Team blackTeam;
    private Team brownTeam;
    private GameView view;
    private Random random;

    public BowlingController() {
        whiteTeam = new Team("White");
        blackTeam = new Team("Black");
        brownTeam = new Team("Brown");
        // view = new GameView();
        view = GameView.getInstance();
        random = new Random();
    }

    public void playGame() {
        List<Cow> allCows = new ArrayList<>();
        allCows.addAll(whiteTeam.getCows());
        allCows.addAll(blackTeam.getCows());
        allCows.addAll(brownTeam.getCows());

        for (int round = 1; round <= 10; round++) {
            for (Cow cow : allCows) {
                int pins = 10;
                int firstThrow = random.nextInt(pins + 1);
                pins -= firstThrow;
                int secondThrow = random.nextInt(pins + 1);

                // Check for black cow lying
                if (cow.getColor().equals("Black") && random.nextDouble() < 0.2) {
                    if (firstThrow == 10 || secondThrow == pins) {
                        firstThrow = 0;
                        secondThrow = 0;
                    }
                }

                // Check for white cow lying
                if (cow.getColor().equals("White") && random.nextDouble() < 0.1) {
                    if (firstThrow == 0 && pins > 0) {
                        firstThrow = pins;
                    }
                }

                // Scoring Logic
                if (firstThrow == 10) {
                    cow.setStrike();
                    cow.addScore(10);
                } else if (firstThrow + secondThrow == 10) {
                    cow.setSpare();
                    cow.addScore(10);
                } else {
                    cow.addScore(firstThrow + secondThrow);
                }

                view.displayRound(cow, round, firstThrow, secondThrow);
                cow.resetRound();
            }
        }

        view.displayFinalRanking(allCows);
        Team winner = getWinningTeam();
        view.displayTeamWinner(winner);
    }

    private Team getWinningTeam() {
        Team winner = whiteTeam;
        if (blackTeam.getTotalScore() > winner.getTotalScore()) {
            winner = blackTeam;
        }
        if (brownTeam.getTotalScore() > winner.getTotalScore()) {
            winner = brownTeam;
        }
        return winner;
    }

}
