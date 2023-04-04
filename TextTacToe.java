/*
 * TextTacToe v4.0: Human vs Computer TicTacToe
 * Contains backends for TextTacToe
 * This program applies a minimax algorithm to evaluate scenarios
 * 
 * Version 4.0; 31 March 2023
 * Copyright: (c) 2023, Matt | All rights reserved
 */

class TextTacToe{
    //Data Members
    char board[][];
    public final char PLAYER_SYMBOL = 'X';
    public final char COMPUTER_SYMBOL = 'O';
    private static final char EMPTY_SYMBOL = '-';

    public TextTacToe(char arr[][]){
        board = arr;
    }

    //BEGIN FUNCTIONS RELATED TO BASIC GAME ENVIRONMENT

    //function createBoard may be called outside the object
    //if the user wishes to automate board creation. Recommended
    public static char[][] createBoard(){
        char blankBoard[][] = new char[3][3];
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                blankBoard[row][col] = EMPTY_SYMBOL;
            }
        }

        return(blankBoard);
    }

    public boolean isValidMove(int row, int col){
        if(row < 0 || row > board.length)
            return(false);
        if(col < 0 || col > board[0].length)
            return(false);

        //check if element is already filled
        if(board[row][col] != EMPTY_SYMBOL)
            return(false);

        return(true);
    }

    //BEGIN FUNCTIONS EVALUATING GAME STATUS
    
    public boolean hasPlayerWon(char player){
        //applying checking algorithm used in previous versions
        //row check
        for(int row = 0; row < board.length; row++){
            if(board[row][0] != player)
                continue;
            
            if(board[row][0] == board[row][1] && board[row][1] == board[row][2])
                return(true);
        }

        //column check
        for(int col = 0; col < board[0].length; col++){
            if(board[0][col] != player)
                continue;
            
            if(board[0][col] == board[1][col] && board[1][col] == board[2][col])
                return(true);
        }

        //check center element before checking diagonals
        if(board[1][1] != player)
            return(false);
        //principal diagonal
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return(true);
        //minor diagonal
        if(board[2][0] == board[1][1] && board[1][1] == board[0][2])
            return(true);
        
        //if no value has been returned yet, player has not won game
        return(false);
    }

    private boolean isBoardFull(){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                if(board[row][col] == EMPTY_SYMBOL)
                    return(false);
            }
        }

        return(true);
    }

    public boolean isGameOver(){
        if(isBoardFull())
            return(true);

        if(hasPlayerWon(PLAYER_SYMBOL) || hasPlayerWon(COMPUTER_SYMBOL))
            return(true);
        
        return(false);
        
    }

    //BEGIN FUNCTIONS TO DETERMINE COMPUTER'S MOVE
    
    //evaluate may also be used as an alternative to hasPlayerWon 
    //to check whether the game has been won, hence the public access specifier
    public int evaluate(){
        //returns 1 if computer wins, -1 if player wins, 0 in all other cases
        if(hasPlayerWon(COMPUTER_SYMBOL))
            return(1);
        
        if(hasPlayerWon(PLAYER_SYMBOL))
            return(-1);

        return(0);
    }

    private int minimax(int depth, boolean isMaximizingPlayer){
        //base cases to terminate recursion
        int score = evaluate();
        if(score != 0)
            return(score);
        if(isBoardFull())
            return(0);

        if(isMaximizingPlayer){
            int bestScore = Integer.MIN_VALUE;
            for(int row = 0; row < board.length; row++){
                for(int col = 0; col < board[row].length; col++){
                    if(board[row][col] == EMPTY_SYMBOL){
                        board[row][col] = COMPUTER_SYMBOL;
                        
                        int currentScore = minimax(depth+1, false);

                        board[row][col] = EMPTY_SYMBOL;
                        bestScore = Math.max(bestScore, currentScore);
                    }
                }
            }
            return(bestScore);
        } else{
            int bestScore = Integer.MAX_VALUE;
            for(int row = 0; row < board.length; row++){
                for(int col = 0; col < board[row].length; col++){
                    if(board[row][col] == EMPTY_SYMBOL){
                        board[row][col] = PLAYER_SYMBOL;

                        int currentScore = minimax(depth+1, true);
                        
                        board[row][col] = EMPTY_SYMBOL;
                        bestScore = Math.min(bestScore, currentScore);
                    }
                }
            }
            return(bestScore);
        }
    }

    public int[] getComputerMove(){
        //computerMove[0] is the row and computerMove[1] is the column 
        //corresponding to the cell the computer wishes to fill
        int computerMove[] = new int[2];

        int bestScore = Integer.MIN_VALUE;
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                if(board[row][col] == EMPTY_SYMBOL){
                    board[row][col] = COMPUTER_SYMBOL;

                    int score = minimax(0, false);

                    board[row][col] = EMPTY_SYMBOL;

                    //ie. if favours computer
                    if(score > bestScore){
                        bestScore = score;
                        computerMove[0] = row;
                        computerMove[1] = col;
                    }
                }
            }
        }

        return(computerMove);
    }
}