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

    // Create Singleton instance for other module to use
    public static GameView getInstance() {
        if (instance == null) {
            StartView();
        }
        return instance;
    }

    // Set up all views
    private GameView() {
        frame = new JFrame("Cow Bowling Game");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        gameLog = new JTextArea();
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);

        startButton = new JButton("Start New Game");
        startButton.addActionListener(e -> onGameStart());

        rankingLabel = new JLabel("Final Rankings:");
        teamWinnerLabel = new JLabel("Winning Team:");

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);
        frame.add(rankingLabel, BorderLayout.NORTH);
        frame.add(teamWinnerLabel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    // Called controller when start-button clicked
    private void onGameStart() {
        BowlingController game = new BowlingController();
        game.playGame();
    }

    // Display the round details in the game log
    public void displayRound(Cow cow, int round, int firstThrow, int secondThrow) {
        gameLog.append("Round " + round + ": " + cow.getName() + " (" + cow.getColor() + ") throws: "
                + firstThrow + " + " + secondThrow + "\n");
    }

    // Display ranking by order
    public void displayFinalRanking(List<Cow> allCows) {
        // put some color for the out
        StringBuilder rankingText = new StringBuilder(
                "<html><b style='color: blue;'>Final Rankings:</b><br>");
        allCows.sort((c1, c2) -> c2.getScore() - c1.getScore());

        int rank = 1;
        int displayedRank = 1;
        int previousScore = -1;

        for (int i = 0; i < allCows.size(); i++) {
            Cow cow = allCows.get(i);

            // checkif there are mutual ranking
            if (i > 0 && cow.getScore() == previousScore) {
                // Maintain the displayed rank if scores are equal
                rank--;
                displayedRank = rank;
            } else {
                // Update the displayed rank to the current rank
                displayedRank = rank;
            }

            rankingText.append("Rank ").append(displayedRank).append(": ").append(cow.getName()).append(" (")
                    .append(cow.getColor()).append(") with ").append(cow.getScore()).append(" points.<br>");
            previousScore = cow.getScore();
            rank++;
        }

        rankingText.append("</html>");
        rankingLabel.setText(rankingText.toString());
    }

    // Display the winning team
    public void displayTeamWinner(Team winningTeam) {
        teamWinnerLabel.setText("<html><b style='color: #742A2D'>Winning Team:</b> " + winningTeam.getColor()
                + " with " + winningTeam.getTotalScore() + " points!</html>");
    }

}
