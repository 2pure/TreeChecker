/**
 * 
 */
package org.vetal.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.vetal.util.TreeChecker;

/**
 * @author Виталий
 *
 */
public class TreeCheckerTest {

	/**
	 * Test methods for {@link org.vetal.util.TreeChecker#check()}.
	 */
	
	
	//Empty matrix is not correct argument
	@Test(expected = IllegalArgumentException.class)
	public final void emptyMatrixTest(){

		int[][] emptyMatrix = {{}};

		TreeChecker myChecker = new TreeChecker(emptyMatrix, 0);
		myChecker.check();
	}
	
	//We expect 1 node to be incorrect input
	@Test(expected = IllegalArgumentException.class)
	public final void oneNodeTest(){
		int[][] oneNode = {{0}};
		TreeChecker myChecker = new TreeChecker(oneNode, 0);
		myChecker.check();
	}
	
	//We expect matrix to not have any selff loops
	@Test(expected = IllegalArgumentException.class)
	public final void selfLoopTest(){
		int[][] selfLoop = {
				{1, 1, 1, 0},
				{1, 0, 0, 1},
				{1, 0, 0, 0},
				{0, 1, 0, 0}
		};
		
		TreeChecker myChecker = new TreeChecker(selfLoop, 0);
		myChecker.check();
	}
	
	//Matrix supposed to have 1 if it has an edge and 0 if doesn`t,
	//So no other numbers expected
	@Test(expected = IllegalArgumentException.class)
	public final void illegalValuesTest(){
		int[][] matrix = {
				{0, 1, 3, 0},
				{1, 0, 0, 1},
				{3, 0, 0, 0},
				{0, 1, 0, 0}
		};
		
		TreeChecker myChecker = new TreeChecker(matrix, 0);
		myChecker.check();
	}
	
	//2 Simple 2x2 checks
	@Test
	public final void smallSizedTest() {
		
		int[][] matrix1 = {
				{0, 1},
				{1, 0}
		};
		
		TreeChecker myChecker = new TreeChecker(matrix1, 2);		
		assertTrue(myChecker.check());
		
		int[][] matrix2 = {
				{0, 0},
				{0, 0}
		};
		myChecker = new TreeChecker(matrix2, 2);		
		assertTrue(myChecker.check());
		
	}
	
	//Check behavior on isolated nodes
	@Test
	public void largeNotConnectedTest(){
		int[][] matrix = {
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0}
		};
		
		TreeChecker myChecker = new TreeChecker(matrix, 6);
		assertTrue(myChecker.check());
	}
	
	//Check behavior on small sized tree behavior
	@Test
	public void simpleTreeTest(){
		int[][] matrix = {
				{0, 1, 1, 0},
				{1, 0, 0, 1},
				{1, 0, 0, 0},
				{0, 1, 0, 0}
		};
		
		TreeChecker myChecker = new TreeChecker(matrix, 4);
		
		assertTrue(myChecker.check());
	}
	
	//Check behavior on small sized cycled graph
	@Test
	public void simpleCycleTest(){
		int[][] matrix = {
				{0, 1, 1, 0, 0},
				{1, 0, 1, 1, 0},
				{1, 1, 0, 0, 1},
				{0, 1, 0, 0, 0},
				{0, 0, 1, 0, 0}
		};
		
		TreeChecker myChecker = new TreeChecker(matrix, 5);
		
		assertFalse(myChecker.check());
	}
	
	//Large cycled graph
	@Test
	public void hardCycleTest(){
		//0-1-6-4-5 is the cycle
		int[][] matrix = {
			{0, 1, 1, 1, 0, 1, 0, 0, 1, 0},
			{1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
			{1, 0, 0, 0, 0, 0, 0, 1, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
			{1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
			{0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
			{1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
		};
		
		TreeChecker myChecker = new TreeChecker(matrix, 10);
		
		assertFalse(myChecker.check());
	}
	
	//Large tree
	@Test
	public void hardTreeTest(){
		/*
		 * 	 	  7
		 * 		 /|\
		 * 		1 2 3
		 * 	   /|	|\ \
		 *	  9 10	4 5 6
		 *			  |\
		 *			 11 8 
		 */
		int[][] matrix = {
			{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
			{0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1},
			{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
		};
		
		TreeChecker myChecker = new TreeChecker(matrix, 11);
		
		assertTrue(myChecker.check());
	}
}
