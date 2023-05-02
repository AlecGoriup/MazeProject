import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test 
{
	
	public static void main(String[] args) 
	{
//		//Initialize maze object
//		Maze maze = null;
//		//Place all characters including start and finish of maze
//		maze = maze.readTextFile("textMaze.txt");
//		//Register all variables needed for go direction methods
//		maze.readPoints("textMaze.txt");
//		//Print initial unsolved maze
//		System.out.println(maze.toString());
//		//Solve maze
//		maze.goNorth(maze);
//		//Print final solved maze
//		System.out.println(maze.toString());
		
		int num = 1, max = 20;
		while(num < max)
		{
			if(num%2 == 0)
			{
				System.out.println(num);
				
			}
			num = num+5;
			
		}
		
	}

}
