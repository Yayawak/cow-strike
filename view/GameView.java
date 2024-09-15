package view;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import controller.BowlingController;

import model.Cow;
import model.Team;

public class GameView {
    private JFrame frame;
    private JTextArea gameLog;
    private JButton startButton;
    private JLabel rankingLabel;
    private JLabel teamWinnerLabel;
    private static GameView instance;

    public static void StartView() {
        instance = new GameView();
    }

    public static GameView getInstance() {
        if (instance == null) {
            StartView();
        }
        return instance;
    }

    private GameView() {
        // Setting up the frame
        frame = new JFrame("Cow Bowling Game");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Setting up game log area
        gameLog = new JTextArea();
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);

        // Setting up the buttons and labels
        startButton = new JButton("Start New Game");
        startButton.addActionListener(e -> onGameStart());

        rankingLabel = new JLabel("Final Rankings:");
        teamWinnerLabel = new JLabel("Winning Team:");

        // Adding components to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);
        frame.add(rankingLabel, BorderLayout.NORTH);
        frame.add(teamWinnerLabel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    // Method called when the start button is pressed
    private void onGameStart() {
        // Create a new game and start playing
        BowlingController game = new BowlingController();
        game.playGame();
    }

    // Display the round details in the game log
    public void displayRound(Cow cow, int round, int firstThrow, int secondThrow) {
        gameLog.append("Round " + round + ": " + cow.getName() + " (" + cow.getColor() + ") throws: "
                + firstThrow + " + " + secondThrow + "\n");
    }

    // Display the final ranking of all cows
    public void displayFinalRanking(List<Cow> allCows) {
        StringBuilder rankingText = new StringBuilder("<html><b>Final Rankings:</b><br>");
        allCows.sort((c1, c2) -> c2.getScore() - c1.getScore());
        int rank = 1;
        for (Cow cow : allCows) {
            rankingText.append("Rank ").append(rank).append(": ").append(cow.getName()).append(" (")
                    .append(cow.getColor()).append(") with ").append(cow.getScore()).append(" points.<br>");
            rank++;
        }
        rankingText.append("</html>");
        rankingLabel.setText(rankingText.toString());
    }

    // Display the winning team
    public void displayTeamWinner(Team winningTeam) {
        teamWinnerLabel.setText("<html><b>Winning Team:</b> " + winningTeam.getColor()
                + " with " + winningTeam.getTotalScore() + " points!</html>");
    }

}
