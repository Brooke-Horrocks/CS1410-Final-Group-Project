/*
Assignment: gameoflife
Program: CellAvatar.java
Written by: Jamie
Date: Jul 19, 2018
*/
package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class CellAvatar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Color cellColor;
	private int columnRepresented;
	private int rowRepresented;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(this.cellColor);
		g.fillRect(0, 0, 10, 10);
		}
	
	public void toggleColor() {
		if (this.cellColor == Color.BLACK) {
			this.cellColor = Color.WHITE;
		}
		else {
			this.cellColor = Color.BLACK;
		}
	}
	
	public Color getColor() {
		return cellColor;
	}
	
	public int getColumn() {
		return columnRepresented;
	}
	
	public int getRow() {
		return rowRepresented;
	}
	
	/**
	 * @param cellColor
	 */
	public CellAvatar(Grid grid, int col, int row) {
		super();
		this.cellColor = Color.WHITE;
		this.columnRepresented = col;
		this.rowRepresented = row;
		
		this.addMouseListener(new MouseAdapter()  
		{  
			@Override
		    public void mouseClicked(MouseEvent e)  
		    {  
				CellAvatar avatarClickedOn = (CellAvatar) e.getSource();
				avatarClickedOn.toggleColor();
				avatarClickedOn.repaint();
				grid.toggleCell(avatarClickedOn.getColumn(), avatarClickedOn.getRow());
		    }  
		}); 
	}

}
