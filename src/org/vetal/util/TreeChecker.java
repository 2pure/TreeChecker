package org.vetal.util;

enum Color {
	White, Grey, Black
}

public class TreeChecker {
	
	//fields
	private Color[] status;
		//During the check, we will change the color 
		//of nodes in order to check if we checked this
		//node or not 
		//White - unstepped
		//Grey - stepped during this search
		//Black - Already checked for being good node
	private int[][] matrix;
		//This matrix [i][j] represents whether we have 
		//i->j edge,or not. 1 if we have - 0 if we don`t
	private int x;
		//The number of the nodes
	
	
	//constructor
	public TreeChecker(int[][] matrix, int x){
		status = new Color[x];
		this.x = x;
		this.matrix = matrix;
		
		//Paint all the nodes in white color to remember,
		//that we haven`t checked them yet
		for(int i = 0;i < x; i++){
			status[i] = Color.White;
		}
	}
	
	//The method to check, whether our matrix represents acyclic graph
	//or not
	public boolean check(){
		
		//If graph is empty or consists only of 1 node 
		//We would say it`s correct tree
		if(x == 1|| x == 0) throw new IllegalArgumentException("Matrix size is too small");
		
		//Next we need to check, wheather the matrix symmetric, consist only of 1 and 0
		//and does it have self loops
		for(int i = 0; i < x; i++){
			if(matrix[i][i] == 1) throw new IllegalArgumentException("No self loops alowed");
			for(int j = 0; j < x; j++){
				
				if(matrix[i][j] !=0 && matrix[i][j] != 1){
					throw new IllegalArgumentException("Matrix should consist only of 1 and 0");
				}
				
				if(matrix[i][j] != matrix[j][i]){
					System.out.println(i + " " + j + " " + matrix[i][j] + " " + matrix[j][i]);
					throw new IllegalArgumentException("Graph shouldn`t be oriented, so matrix supposed to be symmetric");
				}
				
			}
		}
		
		for(int i = 0; i < x; i++){
			if(status[i].equals(Color.White)){
				//For each unchecked node start depthSearch
				if(depthSearch(i, -1) == false){
					return false;
				}
			}
		}
		
		//haven`t find any cycles
		return true;
	}
	
	//This method performs search in depth. 
	//In order to find only simple cycles, 
	//we should remember previous node,
	//so we will not pass same edge twice.
	private boolean depthSearch(int i, int from){
		//Marking current node as gray to remember, that we are
		//currently investigating it
		for(int j = 0 ; j < x; j++){
			
			if(matrix[i][j] == 1 && j != from){
				switch(status[j]){
					
					case Grey :{
						//We found connection, between 2 Grey nodes.
						//This means we found a cycle.
						return false;
					}
				
					case White:
					{
						//We found white edge, connecting current node
						//with White one, so we should check it
						//recursively
						status[i] = Color.Grey;
						if(depthSearch(j, i) == false){
						return false;
						}
						break;
					}
					
					case Black:
					{
						//We found acyclic node, so it won`t lead
						//to cycle
					}
				}
			}
		}
		
		//Ok, this node do not participate in any cycle,
		//so there is no reasons to check this node anymore
		
		status[i] = Color.Black;
		return true;
	}

	
	
}
