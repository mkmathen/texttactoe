//Defines the game frontend for TextTacToe
//Copyright: (c) 2023, Matt | All rights reserved

import java.util.Scanner;

class game{
    private static void printOpeningMessage(){
        System.out.println("\fWelcome to TextTacToe");
        System.out.println("TextTacToe is a simple text-based human vs computer game of TicTacToe");

        System.out.println("\nVersion: 4.0; 31 March 2023");
        System.out.println("NOTE: This program uses ClearPrint v1.0");
        System.out.println("      Certain snippets of code were adapted from content created using OpenAI ChatGPT");

        System.out.println("\nCopyright: (c)2023 Matt | All rights reserved.");

        System.out.println("\nEnjoy the game!\n");
    }

    private static void printGame(char board[][]){
        CleanPrint printer = new CleanPrint();

        System.out.println("\fTextTaxToe v4.0 Frontend");
        System.out.println("Human Player's symbol: X");
        System.out.println("Computer's symbol    : O\n\n");

        printer.printArray(board);
    }

    public static void main(String[] args){
        char board[][] = TextTacToe.createBoard();
        TextTacToe tttObject = new TextTacToe(board);

        printOpeningMessage();
        System.out.print("Starting game in: ");
        //countdown
        try{
            for(byte i = 5; i >= 0; i--){
                System.out.print(i + " ~ ");
                Thread.sleep(1000);
            }
        } catch(Exception ExpnObj){
            System.out.println("Unexpected exception encountered");
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean playerTurn = true;

        printGame(board);
        while(!tttObject.isGameOver()){
            if(playerTurn){
                System.out.print("\nEnter desired row    number (1-3): ");
                int row = sc.nextInt()-1;
                System.out.print("Enter desired column number (1-3): ");
                int col = sc.nextInt()-1;

                if(tttObject.isValidMove(row, col)){
                    board[row][col] = tttObject.PLAYER_SYMBOL;
                    playerTurn = false;
                    printGame(board);
                } else{
                    System.out.println("Invalid move. Please try again.");
                }
            } else{
                //computer's turn
                int computerMove[] = tttObject.getComputerMove();
                board[computerMove[0]][computerMove[1]] = tttObject.COMPUTER_SYMBOL;
                printGame(board);
                System.out.println("Computer moved at row " + computerMove[0] + ", column " + computerMove[1] + ".");
                playerTurn = true;
            }
        }

        //close Scanner object
        sc.close();

        //display final outcome
        printGame(board);
        System.out.println("\nGame Over");
        if(tttObject.hasPlayerWon(tttObject.PLAYER_SYMBOL)){
            System.out.println("You won!");
            return;
        }
        if(tttObject.hasPlayerWon(tttObject.COMPUTER_SYMBOL)){
            System.out.println("Sorry, you lost.");
            return;
        }
        System.out.println("It's a draw!");
    }
}