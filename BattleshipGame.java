import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BattleshipGame extends JFrame {
    private static final int BOARD_SIZE = 3;
    private JButton[][] buttons;
    private int[][] board;
    private int shipsRemaining;

    public BattleshipGame() {
        initializeUI();
        initializeGame();
    }

    private void initializeUI() {
        setTitle("Battleship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        board = new int[BOARD_SIZE][BOARD_SIZE];
        shipsRemaining = 5;

        // Create the buttons and add them to the frame
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setBackground(Color.BLUE);
                buttons[row][col].setOpaque(true);
                buttons[row][col].setBorderPainted(false);

                final int r = row;
                final int c = col;
                buttons[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(r, c);
                    }
                });

                add(buttons[row][col]);
            }
        }

        setSize(500, 500);
        setVisible(true);
    }

    private void initializeGame() {
        // Randomly place 5 ships on the board
        for (int i = 0; i < 5; i++) {
            int row = (int) (Math.random() * BOARD_SIZE);
            int col = (int) (Math.random() * BOARD_SIZE);
            board[row][col] = 1;
        }
    }

    private void buttonClicked(int row, int col) {
        if (board[row][col] == 1) {
            // Hit a ship
            buttons[row][col].setBackground(Color.GREEN);
            board[row][col] = -1;
            shipsRemaining--;
            if (shipsRemaining == 0) {
                JOptionPane.showMessageDialog(this, "Congratulations! You have won the game!");
                resetGame();
            }
        } else if (board[row][col] == 0) {
            // Missed
            buttons[row][col].setBackground(Color.RED);
            board[row][col] = -1;
        }
    }

    private void resetGame() {
        // Reset the board and ships remaining count
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                buttons[row][col].setBackground(Color.BLUE);
                board[row][col] = 0;
            }
        }
        shipsRemaining = 5;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BattleshipGame();
            }
        });
    }
}
