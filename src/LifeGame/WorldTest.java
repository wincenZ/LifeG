package LifeGame;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldTest {
	private static World world= new World(20,20);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testWorld() {
		for(int i =0;i<20;i++) {
			for(int j=0;j<20;j++) {
				assertEquals(false,world.GetNextCells(i,j));
				assertEquals(false,world.GetCurrentCells(i,j));
			}
		}
		
	}

/*	@Test
	public void testCellsCheck() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method methods[] = WorldTest.class.getDeclaredMethods();
		for(int i = 0 ; i<methods.length; ++i){
			if(methods[i].getName().equals("CellsCheck")) {
				 methods[i].setAccessible(true); 
				 Object row = 19;
				 Object col = 19;
				 Object o = methods[i].invoke(world,row,col); 
				 assertEquals(true,o);
				 if(world.GetSurroundAliveCells() == 3) {
					 assertEquals(true,world.GetNextCells(19, 19));
				 }
				 else if(world.GetSurroundAliveCells() == 2) {
					 assertEquals(world.GetCurrentCells(19, 19),world.GetNextCells(19, 19));
				 }
				 else {
					 assertEquals(false,world.GetNextCells(19, 19));
				 }
			}
		}
	}*/

	@Test
	public void testRandomInitialize() {
		
	}

}
