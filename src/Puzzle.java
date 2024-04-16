import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Puzzle {
	
	public static void menu() {
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the menu, select an option.");
		System.out.print("1. Exit\n2. Print Puzzles\n3. Solve a puzzle\n");
		int choice = in.nextInt();
		boolean continueMenu = true;
		while(continueMenu == true) {
			
			if (choice==1) {
				System.out.println("Quiting");
				continueMenu = false;
				break;
			} else if(choice==2) {
				printAllPuzzles(in);
				choice=4;
			} else if(choice==3) {
				String[][] puzzle = puzzleSelector(in);
				solvePuzzle(in, puzzle);
				choice=4;
			}else if(choice==4) {
				System.out.print("1. Exit\n2. Print Puzzles\n3. Solve a puzzle\n");
				choice = in.nextInt();
			} else {
				System.out.println("Incorrect selection, select another option.");
				choice=4;
			}
			
		}
		in.close();
		
	}
	
	public static void printAllPuzzles(Scanner in) {
		System.out.print("3x3:");
		printPuzzle(fileReader(in, "3x3"));
		System.out.print("4x4:");
		printPuzzle(fileReader(in, "4x4"));
		System.out.print("5x5:");
		printPuzzle(fileReader(in, "5x5"));
	}
	
	public static void solvePuzzle(Scanner in, String puzzle[][]) {
		boolean solved = false;
		int row=0;
		int col=0;
		int colBlank=0;
		int rowBlank=0;
		while(solved == false) {
			printPuzzle(puzzle);
			System.out.println(Arrays.deepToString(puzzle));

			System.out.println("Which piece do you want to shift?");
			String choice = in.next();
			for (int i = 0; i < puzzle.length; i++) { 
			    for (int j = 0; j < puzzle[i].length; j++) { 
			        if (puzzle[i][j].equals(choice)) { 
			        	row = i;
			        	col = j;
			        }
			        if (puzzle[i][j].equals(" ")) { 
			        	rowBlank = i;
			        	colBlank = j;
			        }
			        
			    }
			}
			if((row == rowBlank-1 && col == colBlank)||(row == rowBlank+1 && col == colBlank)||(row == rowBlank && col == colBlank-1)||(row == rowBlank && col == colBlank+1)) {
	        	String temp = puzzle[row][col];
	        	puzzle[row][col] = " ";
	        	puzzle[rowBlank][colBlank] = temp;
	        }
			solved = solved(puzzle);
		}
		System.out.println("Congradulations you win!!! What now?");
	}
	
	public static boolean solved(String[][] puzzle) {
		
		if(puzzle.length == 3) {
			String[][] solved = {{"1","2","3"},{"4","5","6"},{"7","8"," "}};
			return(equals(puzzle,solved));
		}else if(puzzle.length == 4) {
			String[][] solved = {{"1","2","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14","15"," "}};
			return(equals(puzzle,solved));
		} else if(puzzle.length == 5) {
			String[][] solved = {{"1","2","3","4","5"},{"6","7","8","9","10"},{"11","12","13","14","15"},{"16","17","18","19","20"},{"21","22","23","24"," "}};
			return(equals(puzzle,solved));
		}
		return(false);
	}
	
	public static boolean equals(String[][] puzzle, String[][] answer) {
		for (int i = 0; i < puzzle.length; i++) {
			if (!Arrays.equals(puzzle[i], answer[i])) {
			    return false;
			}
		}
		return true;
	}
	
	public static void printPuzzle(String[][] puzzle) {
		
		String divider="";
		if(puzzle.length == 3) {
			divider = "\n+----+----+----+";
			System.out.print(divider+"\n");
		}else if(puzzle.length == 4) {
			divider = "\n+----+----+----+----+";
			System.out.print(divider+"\n");
		}else if(puzzle.length == 5) {
			divider = "\n+----+----+----+----+----+";
			System.out.print(divider+"\n");
		}
		for( String[] row : puzzle ) {
			 
			  for( String n : row) {
			    System.out.print( String.format("|"+"%" + 3 + "s ", n) ); //creates the format string "%2d" for 2 digits maximum
			  }
			  System.out.println("|"+divider); //takes a new line before each new print
		}
		
	}
	
	public static String[][] fileReader(Scanner in, String puzzleSelect){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(puzzleSelect+".txt"));
			int size = Integer.parseInt(puzzleSelect.split("x")[0]);
			String input;
			String[] array = new String[size*size];
			String[][] puzzleArr = new String[size][size];
			while ((input=reader.readLine())!= null) {
				array = input.split(",");
			}
			
			int counter = 0;
			for(int i = 0; i<size;i++) {
				for(int j=0; j<size; j++) {
					puzzleArr[i][j]=array[counter];
					counter++;
				}
			}
			
			reader.close();
			return(puzzleArr);
			
		} catch (IOException e) {
			System.out.print(e);
		}
		return(null);
	}
	public static String[][] puzzleSelector(Scanner in) {
			System.out.println("Which puzzle do you want to Solve[3x3,4x4,5x5]");
			String puzzleSelect = in.next();
			boolean selected = false;
			while(selected == false) {
				if(puzzleSelect.equals("3x3")||puzzleSelect.equals("4x4")||puzzleSelect.equals("5x5")) {
					System.out.println("You entered: " + puzzleSelect);
					selected = true;
					return(fileReader(in, puzzleSelect));
				}  else {
					System.out.println("Select a correct puzzle");
					puzzleSelect = in.next();
				}
			}
			return null;
			
	}
}
