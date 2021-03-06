// Hangman

import java.util.Scanner;
import java.util.Random;
import java.io.*;

// WARNING: IF THIS COMMENT IS DELETED THE PROGRAM WONT COMPILE

// Current problems: 	guess box is a bit screwed up

public class Hangman {
	public static void main(String[] args) throws IOException {			//wanna make it so it does try catch instead
		
		// Does the file setup
		File wordListFile = new File("words.txt");
		
		if (!wordListFile.exists()) {
			System.out.println("The list of words file can not be found.");
			System.exit(0);
		}
		
		
		// Setups most variables
		Scanner keyboard = new Scanner(System.in);
		Random generator = new Random();
		StringBuilder word = new StringBuilder();
		Scanner inputFile = new Scanner(wordListFile);
		StringBuilder guessBox = new StringBuilder("\t-------------\n" +	// 14 characters long
																			  "\t|           |\n" +
																			  "\t|           |\n" +
																			  "\t-------------\n");
		char guess;
		int wordIndex = generator.nextInt(20000);
		StringBuilder hangman = new StringBuilder(" ---------" + 
												  "\n  |      |" + 
												  "\n         |" +														// 23 and 25
												  "\n         |" +														// 34 and 36
												  "\n         |" +														// 46
												  "\n         |" +														// 56 and 57 and 58
												  "\n         |" +														// 68
												  "\n         |" +													   // 78 and 80
												  "\n_________|"); 			
		
		System.out.println(hangman);
		
		
		// Gets a random word from the file
		for (int c = 0; c < wordIndex - 1; c++) {					// what if wordIndex = 0 or 1?
			inputFile.nextLine();				
		}
		StringBuilder originalString = new StringBuilder(inputFile.nextLine());
		
		
		// Creates the string with _ for letters
		for (int c = 0; c < originalString.length(); c++)  {
				word.append('_');
		}
		
		
		// Starts the real game
		System.out.println("Start guessing letters.\n" + word);
		guess = keyboard.next().charAt(0);
		test(guess, originalString, word, keyboard, hangman, guessBox);
		
		// Final statements
		if (word.toString().equals(originalString.toString()))
			System.out.println("Congrats you won!");
		else	
			System.out.println("Congrats you cheated!");
	}		
	
	public static void test(char guess, StringBuilder originalString, StringBuilder word, Scanner keyboard, StringBuilder hangman, StringBuilder guessBox) {
		
		boolean correct = false;
		int amountWrong = 0;
		int guessBoxIndex = 17;
		
		// Does this while your word doesn't equal the real word
		do {
			
			// Checks if your guess is equal to a letter in the word
			for (int c = 0; c < originalString.length(); c++) {		
				if (guess == originalString.charAt(c)) {					
					word.setCharAt(c, guess);
					correct = true;
				}
			}
			
			if (!correct) {
					guessBox.setCharAt(guessBoxIndex, guess);
					guessBoxIndex += 2;
					if (guessBoxIndex == 29)
						guessBoxIndex = 32;
			}
					
			
			// Builds the body of the hangman
			if (correct == false) {
				amountWrong++;
				hangTheMan(amountWrong, hangman);
			}
			
			// Setup for another guess
			System.out.println(hangman);
			System.out.println(guessBox.toString());
			System.out.println("\n" + word + "\n");
			correct = false;
			
			if (!(word.toString().equals(originalString.toString())))			// need this line or else after you get the word you have to enter another letter
				guess = keyboard.next().charAt(0);

		} while(!(word.toString().equals(originalString.toString())));		// have to use the toString() the normal StringBuilders didn't work
	}
	
	public static void hangTheMan(int amountWrong, StringBuilder hangman) {
		
		switch (amountWrong) {
			case 1: 
				hangman.setCharAt(23, '/');
				break;
			case 2:
				hangman.setCharAt(25, '\\');
				break;
			case 3:
				hangman.setCharAt(34, '\\');
				break;
			case 4:
				hangman.setCharAt(36, '/');
				break;
			case 5:
				hangman.setCharAt(46, '|');
				break;
			case 6:
				hangman.setCharAt(57, '|');
				break;
			case 7:
				hangman.setCharAt(56, '\\');
				break;
			case 8:
				hangman.setCharAt(58, '/');
				break;
			case 9:
				hangman.setCharAt(68, '|');
				break;
			case 10:
				hangman.setCharAt(78, '/');
				break;
			case 11: 
				hangman.setCharAt(80, '\\');
				System.out.println(hangman);
				System.out.println("You Lost.");
				System.exit(0);
			
		}
				
	}
}

/*
 			    _________
			    |	            |
               / \              | 
               \ /		        |
                |		        |
               \|/		        |
                |		        |
               / \  	        |
                ________|_
	
					10x9
*/			
