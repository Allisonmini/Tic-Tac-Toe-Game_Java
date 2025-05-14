package TicTacToeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTicTacToe {
    int boardHeight = 350;
    int boardWidth = 300;

    JFrame myFrame = new JFrame("Tic-Tac_Toe: Game");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;

    int turns = 0;
    JButton reset = new JButton("Restart");

    MyTicTacToe(){
        myFrame.setVisible(true);
        myFrame.setSize(boardWidth, boardHeight);
        myFrame.setLocationRelativeTo(null);
        myFrame.setResizable(false);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(new BorderLayout());

        textLabel.setBackground(new Color(240,195,38));
        textLabel.setForeground(Color.BLACK);
        textLabel.setFont(new Font("Impact", Font.BOLD, 20));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Let's Play TicTacToe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        myFrame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.PINK);
        myFrame.add(boardPanel);


        for(int r = 0; r < 3 ; r++){
            for(int c = 0; c< 3 ; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);
                tile.setOpaque(true);

                tile.setContentAreaFilled(true);

                tile.setBackground(new Color(88,192,201));
                tile.setForeground(Color.DARK_GRAY);
                tile.setFont(new Font("Impact", Font.BOLD, 40));

                //tile.setText(currentPlayer);


                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver){
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText((currentPlayer + "'s turn"));
                            }
                        }
                    }
                });

            }
        }
        reset.setPreferredSize(new Dimension(100, 30));
        reset.setForeground(Color.DARK_GRAY);
        reset.setBackground(Color.ORANGE);
        textPanel.add(reset, BorderLayout.SOUTH);


        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });

    }
    void clearAll() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(new Color(88, 192, 201));
                board[r][c].setForeground(Color.DARK_GRAY);
                board[r][c].setBorderPainted(true); // Re-enable borders if disabled
            }
        }
        currentPlayer = playerX;
        textLabel.setText("Let's Play TicTacToe");
        gameOver = false;
        turns = 0;
    }

    void checkWinner(){
        //horizontal
        for (int r = 0; r < 3; r++){
            if (board[r][0].getText().equals("") ) continue;
            if (board[r][0].getText().equals(board[r][1].getText()) && board[r][1].getText().equals(board[r][2].getText())){
                for (int i =0; i<3 ; i++){
                    setWinner (board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        // vertical
        for(int c = 0; c < 3; c++){
            if (board[0][c].getText().equals("")) continue;

            if (board[0][c].getText().equals(board[1][c].getText()) && board[1][c].getText().equals(board[2][c].getText())){
                for (int i = 0; i < 3; i ++){
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        //Diagonally
        if(board[0][0].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][2].getText()) &&
                board[0][0].getText() != ""){
            for (int i = 0; i < 3; i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;

        }

        //Diagonally 2

        if(board[0][2].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][0].getText()) &&
                board[2][0].getText() != ""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;

        }

        if (turns == 9){
            for (int r =0; r <3; r++){
                for (int c=0; c<3; c++){
                    setTile(board[r][c]);
                }
            }
            gameOver = true;
        }

    }
    void setWinner(JButton tile){
        tile.setForeground(Color.black);
        tile.setBackground(new Color(0,193,152));
        tile.setBorderPainted(false);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTile(JButton tile){
        tile.setForeground(Color.RED);
        tile.setBackground(Color.GRAY);
        textLabel.setText("Tie! No Winner");
    }


}

