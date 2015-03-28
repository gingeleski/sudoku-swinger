package sudoku.solver;

/**
 * @author Randy Gingeleski
 */
public class Sudoku {
    
    // User enters this grid
    static int[][] userGrid = new int[][] {
        {1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        
    // Computer experiments on this grid to solve
    static int[][] computerGrid = new int[9][9];
        
    static String[][] possibilitiesGrid = new String[9][9];
    
    public static void main(String[] args) {
        
        printToConsole(userGrid);
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
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            
            System.out.print("\n");
        }
    }
}
