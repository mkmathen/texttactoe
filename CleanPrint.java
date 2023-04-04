/*
 * CleanPrint: Prints a 2D array
 * Version 1.0; 31 March 2023
 * Copyright: (c) 2023, Matt | All rights reserved
 */

class CleanPrint{
    public void printArray(char arr[][]){
        System.out.printf("  %6s%6s%6s\n\n","1", "2", "3");
        for(int row = 0; row < arr.length; row++){
            System.out.printf("%d ", (row+1));
            for(int col = 0; col < arr[row].length; col++){
                System.out.printf("%6c", arr[row][col]);
            }
            System.out.printf("\n\n");
        }
    }
}