/* assignment: A09 Game of Life
program: SaveManager
author: Tyrel Carter
created: July 19, 2018
*/

package gameOfLife;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveManager {

	@SuppressWarnings("unused")
	private Grid grid;
	
	/**
	 * Constructor.
	 * @param currentGrid
	 */
	public SaveManager(Grid currentGrid) {
		grid = currentGrid;
	}
	
	//Save The Current Grid Layout
	public void save(Grid grid,String fileName) {
		try (ObjectOutputStream output= new ObjectOutputStream(new FileOutputStream(fileName + ".gol"))){
    		
	    	output.writeObject(grid);
	    	
	    	} catch (IOException e) {
				System.out.println(e);
			}
	}
	
	//Load The Previously Saved Layout
	public Grid load(String fileName) {

		try (ObjectInputStream input= new ObjectInputStream(new FileInputStream(fileName + ".gol"))){
    		
        	return (Grid) input.readObject();
        	
        } catch (IOException | ClassNotFoundException e) {
    			System.out.println(e);
    		
		}
		return null;
	}
	
	
	
	
}
