import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze 
{
	//Initialize the maze and the size of it with x, y
	private char[][] maze;
	private int x, y;
	
	//Initialize coordinates and size for maze
	private int exitColumn, 
			exitRow, 
			startColumn,
			startRow,
			width,
			height;
	
	//Initialize boolean variable
	boolean success;
	
	//Set starting coordinates equal to the current position of player
	int currentRow = startRow;
	int currentColumn = startColumn;
	int northNum = 0;
	
	//Initialize final char variables for the maze
	public static final char
		WALL = 'x',
		CLEAR = ' ',
		START = 'S',
		FINISH = 'F',
		PATH = 'O',
		VISITED = '#';
	
	//Initialize maze object with width of x and high of y
	public Maze(int x, int y) 
	{
		this.x = x;
		this.y = y;
		char[][] maze1 = new char[y][x];
		this.maze = maze1;
		
		//Fills in all squares of the maze with CLEAR char
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				maze1[i][j] = CLEAR;
				
			}
		}
		this.maze = maze1;
		

	}
	
	//This method simply reads and registers all variables needed in the maze class
	public void readPoints(String filename)
	{
		try 
		{
			File file = new File("src/" + filename);
			Scanner scan = new Scanner(file);
			
			int width = scan.nextInt();
			int height = scan.nextInt();
			this.width = width;
			this.height = height;
			
			int exitColumn = scan.nextInt();
			int exitRow = scan.nextInt();
			this.exitColumn = exitColumn;
			this.exitRow = exitRow;
			
			int startColumn = scan.nextInt();
			int startRow = scan.nextInt();
			this.startColumn = startColumn;
			this.startRow = startRow;
			
			currentRow = startRow;
			currentColumn = startColumn;
			
			scan.close();
		}
		
		catch(FileNotFoundException e) 
		{
			System.out.println("File not found");
		}
	}
	
	//This method will again read into the file and place all characters found in file
	public static Maze readTextFile(String filename) 
	{	
		int a = -1;
		Maze maze1 = null;
		
		try {
			File file = new File("src/" + filename);
			Scanner scan = new Scanner(file);
			
			int width = scan.nextInt();
			int height = scan.nextInt();
			
			int exitColumn = scan.nextInt();
			int exitRow = scan.nextInt();
			
			int startColumn = scan.nextInt();
			int startRow = scan.nextInt();
			
			maze1 = new Maze(width, height);
			
			//Reads every char found in each line of maze file
			while(scan.hasNext())
			{
				String lineMaze = scan.nextLine();
				char[] parseLine = lineMaze.toCharArray();
								
				for (int b = 0; b < parseLine.length; b ++)
				{
					maze1.maze[a][b] = parseLine[b];
				}
				a++;
			}
			
			//Place start and finish characters
			maze1.maze[exitRow][exitColumn] = FINISH;
			maze1.maze[startRow][startColumn] = START;
			
			return maze1;
			
		}
		
		catch(FileNotFoundException e) 
		{
			System.out.println("File not found");
			return null;
		}
		
		
	}
	
	//This method simply prints out what is found in 2D char array maze
	public String toString()
	{
		String strChar = "";
		
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				strChar += maze[i][j];
			}
			strChar += "\n";
		}
		
		return strChar.toString();
		
	}
	
	//These methods return true or false based on if the player can move in a certain direction.
	//If the square is on the right path than PATH character is placed
	//If the square is on a dead end than VISITED character is placed
	//Algorithm used to go north from current position in maze
	public boolean goNorth(Maze maze)
	{
		if ((maze.maze[currentRow-1][currentColumn] == CLEAR || maze.maze[currentRow-1][currentColumn] == FINISH) && currentRow < height && currentColumn < width && maze.maze[currentRow-1][currentColumn] != VISITED)
		{
			
			currentRow = currentRow - 1;
			maze.maze[currentRow][currentColumn] = PATH;
			
			if (currentRow == exitRow && currentColumn == exitColumn)
			{
				System.out.println("You did it!");
				success = true;
			}
			else 
			{
				success = goNorth(maze);
				if (!success)
				{
					success = goWest(maze);
					if (!success)
					{
						success = goEast(maze);
						if (!success)
						{
							maze.maze[currentRow][currentColumn] = VISITED;
							currentRow ++;
						}
					}
				}
			}
		}
		else 
		{
			success = false;
		}
		return success;
	}
	
	//Algorithm used to go west from current position in maze
	public boolean goWest(Maze maze)
	{
		if ((maze.maze[currentRow][currentColumn-1] == CLEAR || maze.maze[currentRow][currentColumn-1] == FINISH) && currentRow < height && currentColumn < width && maze.maze[currentRow][currentColumn-1] != VISITED)
		{
			currentColumn = currentColumn - 1;
			maze.maze[currentRow][currentColumn] = PATH;
			
			if (currentRow == exitRow && currentColumn == exitColumn)
			{
				success = true;
			}
			else 
			{
				success = goNorth(maze);
				if (!success)
				{
					success = goWest(maze);
					if (!success)
					{
						success = goSouth(maze);
						if (!success)
						{
							maze.maze[currentRow][currentColumn] = VISITED;
							currentColumn ++;
						}
					}
				}
			}
		}
		else 
		{
			success = false;
		}
		return success;
	}
	
	//Algorithm used to go south from current position in maze
	public boolean goSouth(Maze maze)
	{		
		
		if ((maze.maze[currentRow+1][currentColumn] == CLEAR || maze.maze[currentRow+1][currentColumn] == FINISH) && currentRow < height && currentColumn < width && maze.maze[currentRow+1][currentColumn] != VISITED)
		{
			currentRow = currentRow + 1;
			maze.maze[currentRow][currentColumn] = PATH;
			
			if (currentRow == exitRow && currentColumn == exitColumn)
			{
				success = true;
			}
			else 
			{
				success = goSouth(maze);
				if (!success)
				{
					success = goWest(maze);
					if (!success)
					{
						success = goEast(maze);
						if (!success)
						{
							
							maze.maze[currentRow][currentColumn] = VISITED;
							currentRow --;

						}
					}
				}
			}
		}
		else 
		{
			success = false;
		}
		return success;
	}
	
	//Algorithm used to go east from current position in maze
	public boolean goEast(Maze maze)
	{
		if ((maze.maze[currentRow][currentColumn+1] == CLEAR || maze.maze[currentRow][currentColumn+1] == FINISH) && currentRow < height && currentColumn < width && maze.maze[currentRow][currentColumn+1] != VISITED)
		{
			currentColumn = currentColumn + 1;
			maze.maze[currentRow][currentColumn] = PATH;
			
			if (currentRow == exitRow && currentColumn == exitColumn)
			{
				success = true;
			}
			else 
			{
				success = goNorth(maze);
				if (!success)
				{
					success = goEast(maze);
					if (!success)
					{
						success = goSouth(maze);
						
						if (!success)
						{
							
							maze.maze[currentRow][currentColumn] = VISITED;
							currentColumn --;
						}
					}
				}
			}
		}
		else 
		{
			success = false;
		}
		return success;
	}
}
