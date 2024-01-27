//Name: Briana McCloud
//Date: 11/16/23
//Project 4

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Wordle4 {
   public static void main(String args []) throws IOException {
   //Variables
    String guess = "";
    //String userGuess = "";
    String newGuess = "";
    int guesses = 0;
    int maxGuess = 0;
    Scanner scan = new Scanner(System.in);
    String [] arrayString = new String[50];
    
      //--------------------
      //Reads in  words from dictionary txt file using BufferReader 
       
       FileReader input = new FileReader("dictionary.txt"); 
       BufferedReader reader = new BufferedReader(input); //keeps running?? won't stop
        
        
        //read in guesses from dictionary txt
        
         try (BufferedReader fileReader = new BufferedReader(new FileReader("dictionary.txt"))) {
            for (int i = 0; i < 50 && fileReader.ready(); i++) {
                arrayString[i] = fileReader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return; // Exit the program if there's an error reading the file
        }

        // Game loop
        for (int attempt = 1; attempt <= 5; attempt++) {
            System.out.println("Guess a five-letter word: ");
            String userGuess = getUserInput();
            //guess = scan.next().toLowerCase();
          

            try {
              if (isGuessCorrect(arrayString, userGuess)) {
                // Check if the guessed word is in the array
                if (userGuess.equals(arrayString)) {
                    System.out.println("You guessed " + userGuess);
                    System.out.println("You got it!");
                    break;
                 }//inner if
                  for (int i = 0; i <= userGuess.length(); i++) {
                    String result = letterComparison(userGuess, i);
                    System.out.println(result);      
                  }
               } else {
                    // If the guessed word is not in the array, throw an exception
                    throw new IllegalArgumentException("Invalid guess. Try again.");
                }
            } catch (IllegalArgumentException e) {
                // Catch the exception and print the error message
                System.out.println(e.getMessage());
                // Decrement the attempt count since this attempt doesn't count
                attempt--;
            }
            
       }//end for
   
            
            // Continue guessing using a while loop
            while (!guess.equals(WORD) && guesses <= maxGuess) {
                // Call the letterComparison method for each position using a for loop
                for (int i = 0; i <= guess.length(); i++) {
                    String result = letterComparison(guess, i);
                    System.out.println(result);
                }

                // Prompts user for the next guess
                System.out.print("Guess a five-letter word: ");
                guess = scan.next().toLowerCase();
                guesses--;
            } 

            // Prints results after final guess in loop
            if (guess.equals(WORD)) {
                System.out.println("You guessed " + WORD);
                System.out.println("You got it!");
            } 
            else {
                System.out.println("Out of guesses.");
                System.out.println("The hidden word was " + WORD + ".");

            }//end else     
   
    }//end main
    
    
    
    // Helper method to get user input using ScannerReader
      private static String getUserInput() {
       Scanner scan = new Scanner(System.in);
       return scan.nextLine();
  
      } 
  
   
   // Helper method to check if the guessed word is in the array
    private static boolean isGuessCorrect(String[] array, String guess) {
        for (String word : array) {
            if (guess.equals(word)) {
                return true;
            }
        }
        return false;
    }
    
    
    //WORD is a static variable declared outside of main
    private static final String WORD = "dream";
    

    //Compares a letter in the guessed word with the hidden word
    private static String letterComparison(String guess, int position) {
        char g = guess.charAt(position); //used g to stand for guessed letter/character


        if (g == WORD.charAt(position)) {
            return g + " is in the correct position!";
        } else if (WORD.contains(g + "")) {
            return g + " is in the word.";
        } else {
            return g + " is not in the word.";
        } //end else  
        
    }//end method 
    
}//end class
