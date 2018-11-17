/* assignment: A09 Game of Life
program: GridRenderer
author: Daniel Gorney
created: Jul 17, 2018
*/

package gameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class GridRenderer extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private CellAvatar[][] avatars;

	public GridRenderer(Grid grid) {
		setLayout(new GridLayout(grid.getCurrent().length, grid.getCurrent().length, 0, 0));
		avatars = new CellAvatar[grid.getCurrent().length][grid.getCurrent().length];
		for (int i = 0; i < grid.getCurrent().length; i++) {
			for (int j =0; j < grid.getCurrent().length; j++) {
				avatars[i][j] = new CellAvatar(grid, i, j);
				avatars[i][j].setOpaque(true);
				avatars[i][j].setPreferredSize(new Dimension(10,10));
				add(avatars[i][j]);
				
			}
		}
		renderGrid(grid.getCurrent());
	}	
	
	public void renderGrid(Cell[][] currentGrid) {
		for (int i = 0; i < currentGrid.length; i++) {
			for (int j =0; j < currentGrid.length; j++) {
				if (currentGrid[i][j].getState() == CellState.ALIVE) {
					if(avatars[i][j].getColor() == Color.WHITE) { 
						avatars[i][j].toggleColor();
					}
				} else if (currentGrid[i][j].getState() == CellState.DEAD) {
					if(avatars[i][j].getColor() == Color.BLACK) { 
						avatars[i][j].toggleColor();
					}
				}
				
			}
		}
	}
}
