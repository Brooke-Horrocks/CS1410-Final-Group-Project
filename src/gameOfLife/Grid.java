/* assignment: A09 Game of Life
program: Grid
author: Daniel Gorney
created: Jul 17, 2018
*/

/**
 * 
 */
package gameOfLife;

import java.io.Serializable;
import java.util.Random;


/**
 * @author DMGorney
 *
 */
public class Grid implements Serializable {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -1112878371680458777L;
	
	private Cell[][] starting;
	private Cell[][] current;
	private boolean[][] nextGenAlive;
	
	public Grid(int size) {
		starting = new Cell[size][size];
		current = new Cell[size][size];
		nextGenAlive = new boolean[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				starting[i][j] = new Cell();
				current[i][j] = new Cell();
				nextGenAlive[i][j] = false;
			}
		}
	}
	
	public Cell[][] getCurrent () {
		return current;
	}
	
	public void setCurrent(Cell[][] newGrid) {
		for (int i = 0; i < current.length; i++) {
			for (int j = 0; j < current.length; j++) {
				if (newGrid[i][j].getState() == CellState.ALIVE) {
					current[i][j].live();
				} else {
					current[i][j].die();
				}
			}
		}
	}
	
	public Cell[][] getStarting() {
		return starting;
	}
	
	public void birthCell(int column, int row) {
		current[column][row].live();
	}
	
	public void killCell(int column, int row) {
		current[column][row].die();
	}
	
	public void toggleCell(int column, int row) {
		if (current[column][row].getState() == CellState.ALIVE) {
			current[column][row].die();
		} else {
			current[column][row].live();
		}
	}
	
	public void reset() {
		for(int i = 0; i < current.length; i++) {
			for (int j = 0; j < current.length; j++) {
				current[i][j].die();
			}
		}
	}
	
	public void randomize() {
		Random r = new Random();
		for (Cell[] cArray : current) {
			for (Cell cell : cArray) {
				if (r.nextBoolean()) {
					cell.live();
				} else {
					cell.die();
				}
			}
		}
	}
	
	public void nextGeneration() {
		
		//Calculate survival of next generation, all cells simultaneously
		//before changing any of them.
		for (int i = 1; i < current.length - 1; i++) {
			for (int j = 1; j < current.length - 1; j++) {
				if (checkSurvival(i, j) == true) {
					nextGenAlive[i][j] = true;
				} else {
					nextGenAlive[i][j] = false;
				}
			}
		}
		
		//Change the cells to match the above calculation.
		for (int i = 1; i < current.length - 1; i++) {
			for (int j = 1; j < current.length - 1; j++) {
				if (nextGenAlive[i][j] == true) {
					birthCell(i, j);
				} else {
					killCell(i, j);
				}
			}
		}
	}
	
	private boolean checkSurvival(int col, int row) {
		int numberOfNeighbors = 0;
		
		//Upper-left neighbor.
		if (current[col - 1][row - 1].getState() == CellState.ALIVE) {
			numberOfNeighbors++;
		}  
		//Upper neighbor.
		if (current[col - 1][row].getState() == CellState.ALIVE) {
			numberOfNeighbors++;
		}  
		//Upper-right neighbor.
		if (current[col - 1][row + 1].getState() == CellState.ALIVE) {
			numberOfNeighbors++;
		} 
		//Left neighbor.
		if (current[col][row - 1].getState() == CellState.ALIVE) {
			numberOfNeighbors++;
		} 
		//Right neighbor.
		if (current[col][row + 1].getState() == CellState.ALIVE) {
			numberOfNeighbors++;
		} 
		//Lower-left neighbor.
		if (current[col + 1][row - 1].getState() == CellState.ALIVE) {
			numberOfNeighbors++;
		} 
		//Lower neighbor.
		if (current[col + 1][row].getState() == CellState.ALIVE) {
			numberOfNeighbors++;
		} 
		//Lower-right neighbor.	
		if (current[col + 1][row + 1].getState() == CellState.ALIVE) {
			numberOfNeighbors++;
		}
		
		if(numberOfNeighbors < 2) {
			return false;
		} else if ((numberOfNeighbors == 2 || numberOfNeighbors == 3) && current[col][row].getState() == CellState.ALIVE) {
			return true;
		} else if (numberOfNeighbors == 3 && current[col][row].getState() == CellState.DEAD) {
			return true;
		} else if (numberOfNeighbors > 3) {
			return false;
		}
		
		return false;
	}
}
