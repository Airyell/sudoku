package Financial;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class board extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[][] nums = new int[9][9]; // 9x9 grid for Sudoku numbers
    private boolean[][] fixedCells = new boolean[9][9]; // Tracks fixed cells

    public board() {
        // fixed cells with numbers
        nums[0][0] = 9; fixedCells[0][0] = true;
        nums[0][1] = 1; fixedCells[0][1] = true;
        nums[0][2] = 3; fixedCells[0][2] = true;
        nums[1][0] = 6; fixedCells[1][0] = true;
        nums[1][2] = 7; fixedCells[1][2] = true;
        nums[2][1] = 5; fixedCells[2][1] = true;
        nums[3][1] = 7; fixedCells[3][1] = true;
        nums[3][2] = 9; fixedCells[3][2] = true;
        nums[4][2] = 2; fixedCells[4][2] = true;
        nums[6][1] = 4; fixedCells[6][1] = true;
        nums[7][0] = 7; fixedCells[7][0] = true;
        nums[7][2] = 6; fixedCells[7][2] = true;
        nums[8][2] = 1; fixedCells[8][2] = true;
        nums[2][4] = 8; fixedCells[2][4] = true;
        nums[4][4] = 9; fixedCells[4][4] = true;
        nums[5][5] = 4; fixedCells[5][5] = true;
        nums[6][5] = 1; fixedCells[6][5] = true;
        nums[7][5] = 9; fixedCells[7][5] = true;
        nums[8][5] = 6; fixedCells[8][5] = true;
        nums[0][6] = 5; fixedCells[0][6] = true;
        nums[1][7] = 2; fixedCells[1][7] = true;
        nums[1][8] = 4; fixedCells[1][8] = true;
        nums[2][7] = 7; fixedCells[2][7] = true;
        nums[4][7] = 4; fixedCells[4][7] = true;
        nums[4][8] = 3; fixedCells[4][8] = true;
        nums[5][7] = 9; fixedCells[5][7] = true;
        nums[6][6] = 9; fixedCells[6][6] = true;
        nums[7][8] = 5; fixedCells[7][8] = true;
        nums[8][6] = 4; fixedCells[8][6] = true;
        nums[8][8] = 7; fixedCells[8][8] = true;
        nums[0][4] = 2; fixedCells[0][4] = true;
    }

    // Method to update board with user-entered values
    public boolean updateBoard(int row, int col, int value) {
        if (isValidMove(row, col, value) && !fixedCells[row][col]) {
            nums[row][col] = value;
            repaint(); // Redraw the board with the updated values
            return true;
        }
        return false;
    }

    // Check if move is valid
    public boolean isValidMove(int row, int col, int value) {
        return value >= 1 && value <= 9; //if values are greater than 1 and lesser than or equal to 9
    }

    // Check if cell is fixed
    public boolean isFixedCell(int row, int col) {
        return fixedCells[row][col];
    }

    // Check if puzzle is solved 
    public boolean isSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (nums[i][j] == 0) return false; // Unfilled cell
            }
        }
        return true;
    }
    public boolean invalidMove(Border border, int row, int col, int value) {
        // Check for duplicate in the row
        for (int i = 0; i < 9; i++) {
            if (i != col && nums[row][i] == value) { // Skip the current column
                return true; // Duplicate found in row
            }
        }

        // Check for duplicate in the column
        for (int i = 0; i < 9; i++) {
            if (i != row && nums[i][col] == value) { // Skip the current row
                return true; // Duplicate found in column
            }
        }

        // Check for duplicate in the 3x3 sub grid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if ((i != row || j != col) && nums[i][j] == value) { // Skip the current cell
                    return true; // Duplicate found in sub grid
                }
            }
        }

        return false; // No duplicate found
    }
 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Grid cell size
        int cellSize = getWidth() / 9;

        // Draw grid lines
        g.setColor(Color.BLACK);
        for (int i = 0; i <= 9; i++) {
            // Bold lines for 3x3 blocks
            if (i % 3 == 0) {
                ((Graphics2D) g).setStroke(new BasicStroke(3));
            } else {
                ((Graphics2D) g).setStroke(new BasicStroke(1));
            }
            g.drawLine(i * cellSize, 0, i * cellSize, getHeight()); // Vertical lines
            g.drawLine(0, i * cellSize, getWidth(), i * cellSize); // Horizontal lines
        }

        // Draw numbers in the grid
        g.setFont(new Font("Arial", Font.BOLD, cellSize / 2));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (nums[row][col] != 0) {
                    String text = String.valueOf(nums[row][col]);
                    int x = col * cellSize + cellSize / 3;
                    int y = row * cellSize + cellSize / 2 + 10;
                    g.drawString(text, x, y);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(450, 450);
    }
}
