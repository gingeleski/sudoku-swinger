package sudoku.solver;

/**
 * @author Randy Gingeleski
 */
public class Sudoku {
    
    // User enters this grid
    static int[][] userGrid = new int[][] {
        {0, 0, 0, 7, 0, 0, 0, 0, 8},
        {0, 4, 0, 0, 0, 0, 0, 6, 1},
        {0, 0, 0, 1, 2, 0, 0, 3, 0},
        {7, 9, 0, 0, 6, 0, 0, 0, 0},
        {8, 0, 0, 0, 0, 0, 0, 0, 3},
        {0, 3, 0, 0, 8, 0, 0, 7, 2},
        {0, 6, 0, 0, 9, 7, 0, 0, 0},
        {3, 2, 0, 0, 0, 0, 0, 5, 0},
        {5, 0, 0, 0, 0, 4, 0, 0, 0}};
        
    // Computer experiments on this grid to solve
    static int[][] computerGrid = new int[9][9];
    
    public static void main(String[] args) {
        
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                computerGrid[i][j] = userGrid[i][j];
            }
        }
        
        printToConsole(computerGrid);
        double timeStart = System.currentTimeMillis();
        System.out.print("\n\n");
        computerGrid = solverLoop(0,0,computerGrid);
        printToConsole(computerGrid);
        double timeEnd = System.currentTimeMillis();
        System.out.println("That took " + (timeEnd - timeStart));
        System.out.print(" milliseconds to complete.");
    }
    
    /**
     * Recursively bruteforces the Sudoku grid for a valid solution.
     * 
     * @param y
     * @param x
     * @param grid
     * @return Solved grid
     */
    public static int[][] solverLoop(int y, int x, int[][] grid) {
        
        // While not solved
        while ((!testValid(8, 8, grid)) || (grid[8][8] == 0)) {
        
            if(userGrid[y][x] != 0) {
                
                int yy, xx;
                
                if (x == 8) { yy = y + 1; xx = 0; }
                else { yy = y; xx = x + 1; }
                
                solverLoop(yy, xx, grid); // Recursion
                
            } else {
                
                if (grid[y][x] < 9) { // Going forward
                    
                    grid[y][x]++;
                    
                    if(testValid(y, x, grid)) {
                        
                        int yy, xx;
                        
                        if (x == 8) { yy = y + 1; xx = 0; }
                        else { yy = y; xx = x + 1; }
                        
                        solverLoop(yy, xx, grid); // Recursion
                    
                    } else {
                        
                        grid[y][x] = 0;
                        break;
                    }
                }
                
            }
        }
        
        return computerGrid;
    }
    
    /**
     * Tests whether a space in the given grid is unique horizontally
     * and vertically.
     * 
     * @return Boolean regarding validity
     */
    public static boolean testValid(int x, int y, int[][] grid) {
        String temp = "";
        
        for (int i = 0; i < 9; i++) {
            
            temp += Integer.toString(grid[i][y]); // Horizontal
            temp += Integer.toString(grid[x][i]); // Vertical
            
            // Square
            temp += Integer.toString(grid[(x/3) + (i/3)][(y/3) * 3 + (i%3)]);
        }
        
        int count = 0, index = 0;
        
        while ((index = temp.indexOf(Integer.toString(grid[x][y]), index)) 
                != -1) {
            
            index++; count++;
        }
        
        return (count == 3);
    }
    
    /**
     * Prints a given integer grid to the console, with some formatting.
     * 
     * @param grid 
     */
    public static void printToConsole(int[][] grid) {
        
        for (int i = 0; i < 9; i++) {
            
            for (int j = 0; j < 9; j++) { System.out.print(grid[i][j] + " "); }
            
            System.out.print("\n");
        }
    }
}
