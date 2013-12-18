import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class ReversiPlay {

	public static void main(String[] args) {
	int[][] test = new int[3][5];
		
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[0].length; j++) {
				test[i][j] =(int)( Math.random() * 10);
			}
		}
		
		
		printMatrix(test);
		
		
		int[][] cotest = copyMatrix(test);

		System.out.println(test.length + ", " + test[0].length + ", " + test[2].length);
		System.out.println(isEqual(cotest, cotest));
		System.out.println(isEqual(test, cotest));
		System.out.println(isEqual(cotest, test));
		
		//printMatrix(cotest);
		
		
		/*
		 * REMEMBER - the small is legal functions do not check if the specific coordinates are occupied
		 * 
		 * 
		 * 
		 */
		
		int[][] m=createBoard(8);
		int[][] n={{0,0,0,0,0,0}, {0,2,1,0,0,0},{0,0,2,1,0,0},{0,2,1,2,0,0},{0,1,0,0,0,0},{0,0,0,0,0,0}};
			//printMatrix(createBoard(4));
		n[1][1] = 1;
		n[3][1] = 1;
		n[1][0]=2;
		printMatrix(n);

		System.out.println(hasMoves(n, 1));
		int[][] k = possibleMoves(n, 3);
		
		
		
		
		System.out.println(benefit(n, 2,1, 3));
		play(n, 1,1, 3);
		printMatrix(n);
		
		System.out.println("Aaaaaaaaaaaaaaannnnnnnnnnnnnnnddddddd the winner is playerrrr numberrrrrrrrr: " + findTheWinner(n));
		System.out.println(benefit(n, 1,0, 4));
		//System.out.println(isLegalHorizontalLeft(n, 2, 3,3));
		
	    int[][] temp = new int[12][12];
	    for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				temp[i][j] = findStrength(temp, i, j);
			}
		}
	    
	    printMatrix(temp);
		
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j]+"  |  ");
			}
			System.out.println("");
			System.out.println("");
			System.out.println("");
		}
	}

	public static boolean isEqual(int[][] matrix1, int[][] matrix2) {
		if(!(matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length))
		{
			return false;
		}
		else 
		{
			for (int i = 0; i < matrix1.length; i++) {
				for (int j = 0; j < matrix1[0].length; j++) {
					if(matrix1[i][j]!=matrix2[i][j])
						return false;
				}
			}
		
		}
		return true;
	}

	public static int[][] copyMatrix(int[][] matrix) {
		int[][] comatrix = new int[matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				comatrix[i][j]=matrix[i][j];
					
			}
		}
		return comatrix;
	}

	public static int[][] createBoard(int size) {
		int[][] matrix=new int[size][size];
		
		matrix[(size/2)-1][(size/2)-1]=2;
		matrix[(size/2)-1][(size/2)]=1;
		matrix[(size/2)][(size/2)-1]=1;
		matrix[(size/2)][(size/2)]=2;
 		return matrix;
	}

	public static boolean isLegal(int[][] board, int player, int row, int column) {
		if(board[row][column]!=0)
			return false;
		
		return(isLegalVerticalBelow(board, player, row, column) || isLegalVerticalAbove(board, player, row, column) || isLegalHorizontalRight(board, player, row, column) || isLegalHorizontalLeft(board, player, row, column) || isLegalDiagonalRightDown(board, player, row, column) || isLegalDiagonalRightUp(board, player, row, column) || isLegalDiagonalLeftDown(board, player, row, column) || isLegalDiagonalLeftUp(board, player, row, column));
			
	}
	
	public static boolean isLegalVerticalBelow(int[][] board, int player, int row, int column){//this function checks that the move is legal for those that are below it (it checks for all of the rows below the given row)
	
		int enemy=(player%2)+1;//enemy is the opposite of player (1 --> 2 and 2 --> 1)
		boolean foundEnemy=false;
		for (int i = row + 1; i < board.length; i++) {
			if(board[i][column]==enemy)
				foundEnemy=true;
			else{ 
				if(board[i][column]==player&&foundEnemy)
					return true;
				else return false;
						
				}
			}
		return false;
		}
	
	public static boolean isLegalVerticalAbove(int[][] board, int player, int row, int column){ //this function checks that the move is legal for those that are above it (it checks for all of the rows above the given row)
		
		int enemy=(player%2)+1;//enemy is the opposite of player (1 --> 2 and 2 --> 1)
		boolean foundEnemy=false;
		for (int i = row - 1; i >= 0; i--) {
			if(board[i][column]==enemy)
				foundEnemy=true;
			else{ 
				if(board[i][column]==player&&foundEnemy)
					return true;
				else return false;
						
				}
			}
		return false;
		}
		
	public static boolean isLegalHorizontalRight(int[][] board, int player, int row, int column){//this function checks that the move is legal for those that are right to it (it checks for all of the columns right of the given column)
		
		int enemy=(player%2)+1;//enemy is the opposite of player (1 --> 2 and 2 --> 1)
		boolean foundEnemy=false;
		for (int i = column + 1; i < board[0].length; i++) {
			if(board[row][i]==enemy)
				foundEnemy=true;
			else{ 
				if(board[row][i]==player&&foundEnemy)
					return true;
				else return false;
						
				}
			}
		return false;
		}
	
public static boolean isLegalHorizontalLeft(int[][] board, int player, int row, int column){//this function checks that the move is legal for those that are left to it (it checks for all of the columns left of the given column)
		
		int enemy=(player%2)+1;//enemy is the opposite of player (1 --> 2 and 2 --> 1)
		boolean foundEnemy=false;
		for (int i = column - 1; i >= 0; i--) {
			if(board[row][i]==enemy)
				foundEnemy=true;
			else{ 
				if(board[row][i]==player&&foundEnemy)
					return true;
				else return false;
						
				}
			}
		return false;
		}

public static boolean isLegalDiagonalRightDown(int[][] board, int player, int row, int column){//this function checks that the move is legal for those that are diagonal right to it 
	int enemy=(player%2)+1;//enemy is the opposite of player (1 --> 2 and 2 --> 1)
	boolean foundEnemy=false;
	int j= column+1;
	for (int i = row + 1; i < board[0].length && j< board[0].length; i++, j++) {
		if(board[i][j]==enemy)
			foundEnemy=true;
		else{ 
			if(board[i][j]==player&&foundEnemy)
				return true;
			else return false;
					
			}
		}
	return false;
	}
	

public static boolean isLegalDiagonalRightUp(int[][] board, int player, int row, int column){//this function checks that the move is legal for those that are diagonal right to it 
	int enemy=(player%2)+1;//enemy is the opposite of player (1 --> 2 and 2 --> 1)
	boolean foundEnemy=false;
	int j= column+1;
	for (int i = row - 1; i >= 0 && j < board[0].length; i--, j++) {
		if(board[i][j]==enemy)
			foundEnemy=true;
		else{ 
			if(board[i][j]==player&&foundEnemy)
				return true;
			else return false;
					
			}
		}
	return false;
	}

public static boolean isLegalDiagonalLeftDown(int[][] board, int player, int row, int column){//this function checks that the move is legal for those that are diagonal right to it 
	int enemy=(player%2)+1;//enemy is the opposite of player (1 --> 2 and 2 --> 1)
	boolean foundEnemy=false;
	int j= column-1;
	for (int i = row + 1; j >= 0 && i < board[0].length; i++, j--){
		if(board[i][j]==enemy)
			foundEnemy=true;
		else{ 
			if(board[i][j]==player&&foundEnemy)
				return true;
			else return false;
					
			}
		}
	return false;
	}

public static boolean isLegalDiagonalLeftUp(int[][] board, int player, int row, int column){//this function checks that the move is legal for those that are diagonal right to it 
	int enemy=(player%2)+1;//enemy is the opposite of player (1 --> 2 and 2 --> 1)
	boolean foundEnemy=false;
	int j= column-1;
	for (int i = row - 1; i >= 0 && j >= 0; i--, j--) {
		if(board[i][j]==enemy)
			foundEnemy=true;
		else{ 
			if(board[i][j]==player&&foundEnemy)
				return true;
			else return false;
					
			}
		}
	return false;
	}

	public static int[][] play(int[][] board, int player, int row, int column) {
		if(isLegal(board, player, row, column))
		{
int i, j;
board[row][column] = player;
			
			if(isLegalVerticalBelow(board, player, row, column))
					{
				i=row+1; 
				while(board[i][column] != player){
					board[i][column] = player;
					i++;
				}
					
					}
		
			
			if(isLegalVerticalAbove(board, player, row, column))
			{
		i=row-1; 
		while(board[i][column] != player){
			board[i][column] = player;
			i--;
		}
			
			}
			
			if(isLegalHorizontalRight(board, player, row, column))
			{
		j=column+1; 
		while(board[row][j] != player){
			board[row][j] = player;
			j++;
		}
			
			}
			
			
			if(isLegalHorizontalLeft(board, player, row, column))
			{
		j=column-1; 
		while(board[row][j] != player){
			board[row][j] = player;
			j--;
		}
			
			}
			
		
	
		
		if(isLegalDiagonalRightDown(board, player, row, column))
		{
			i=row+1;
	j=column+1; 
	while(board[i][j] != player){
		board[i][j] = player;
		i++;
		j++;
	}
		
		}
		
		if(isLegalDiagonalRightUp(board, player, row, column))
		{
			i=row-1;
	j=column+1; 
	while(board[i][j] != player){
		board[i][j] = player;
		i--;
		j++;
	}
		
		}
		
		
		if(isLegalDiagonalLeftDown(board, player, row, column))
		{
			i=row+1;
	j=column-1; 
	while(board[i][j] != player){
		board[i][j] = player;
		i++;
		j--;
	}
	
		}
		
		if(isLegalDiagonalLeftUp(board, player, row, column))
		{
			i=row-1;
	j=column-1; 
	while(board[i][j] != player){
		board[i][j] = player;
		i--;
		j--;
	}
	
		}
		
		
		}
		

		return board;
	}

	public static int benefit(int[][] board, int player, int row, int column) {
		int counter=0;
		if(isLegal(board, player, row, column))
		{
int i, j;
			
			if(isLegalVerticalBelow(board, player, row, column))
					{
				i=row+1; 
				while(board[i][column] != player){
					counter++;
					i++;
				}
					
					}
		
			
			if(isLegalVerticalAbove(board, player, row, column))
			{
		i=row-1; 
		while(board[i][column] != player){
			counter++;
			i--;
		}
			
			}
			
			if(isLegalHorizontalRight(board, player, row, column))
			{
		j=column+1; 
		while(board[row][j] != player){
			counter++;
			j++;
		}
			
			}
			
			
			if(isLegalHorizontalLeft(board, player, row, column))
			{
		j=column-1; 
		while(board[row][j] != player){
			counter++;
			j--;
		}
			
			}
			
		
	
		
		if(isLegalDiagonalRightDown(board, player, row, column))
		{
			i=row+1;
	j=column+1; 
	while(board[i][j] != player){
		counter++;
		i++;
		j++;
	}
		
		}
		
		if(isLegalDiagonalRightUp(board, player, row, column))
		{
			i=row-1;
	j=column+1; 
	while(board[i][j] != player){
		counter++;
		i--;
		j++;
	}
		
		}
		
		
		if(isLegalDiagonalLeftDown(board, player, row, column))
		{
			i=row+1;
	j=column-1; 
	while(board[i][j] != player){
		counter++;
		i++;
		j--;
	}
	
		}
		
		if(isLegalDiagonalLeftUp(board, player, row, column))
		{
			i=row-1;
	j=column-1; 
	while(board[i][j] != player){
		counter++;
		i--;
		j--;
	}
	
		}
				
		}
		
		
		return counter;
	}

	public static int getNumPossibleMoves(int[][] board, int player){
		int possibleMoves = 0;
		boolean legal;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				legal = isLegal(board, player ,i, j);
			
				if(legal)
				{
					possibleMoves++;
				}
			}
			
		}
		
		return possibleMoves;
		
	}
	public static int[][] possibleMoves(int[][] board, int player) {
		int possibleMoves = getNumPossibleMoves(board, player);
		int[][] movesMatrix;
	
		
		
		movesMatrix = new int[possibleMoves][2];
		int loc=0;
		
		if(possibleMoves>0)
		{
			boolean legal;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				legal = isLegal(board, player ,i, j);
			
				if(legal)
				{
					movesMatrix[loc][0] = i;
					movesMatrix[loc][1]= j;
					loc++;
				}
			}
		}
		
		}
		
		return movesMatrix;
	}

	public static boolean hasMoves(int[][] board, int player) {
		

		return (getNumPossibleMoves(board, player) > 0);
	}

	public static int findTheWinner(int[][] board) {

		int[] counter = new int[3];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				counter[board[i][j]]++;
			}
			
		}
		
		if(counter[1]>counter[2])
			return 1;
		else if(counter[2]>counter[1])
			return 2;
		
		
		return 0;
	}

	public static boolean gameOver(int[][] board) {
		if(getNumPossibleMoves(board, 1)==0 && getNumPossibleMoves(board, 2)==0)
			return true;
		
	 int[] counter = new int[3];
	 
	 for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				counter[board[i][j]]++;
			}
			
		}
	 
	 if(counter[0]==0 || (counter[1]==0 || counter[2]==0))
		 return true;
	 
		
		return false;
	}

	public static int[] randomPlayer(int[][] board, int player) {
		int[][] possibleMoves = possibleMoves(board, player);
		int random;
		int[] move = new int[2];
		if(possibleMoves.length>0)
		{
		 random = ((int)((Math.random() *( possibleMoves.length))));
		
			 move[0] = possibleMoves[random][0];
			 move[1] = possibleMoves[random][1];
			 
			 return move;
		}
		
		return null;
	}
	

	public static int[] greedyPlayer(int[][] board, int player) {
		int maxbenefit = 0;
		int[] move = new int[2];
		int[][] moves = new int[getNumPossibleMoves(board, player)][2];
		int index = 0;
		int currbenefit;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				currbenefit = benefit(board, player, i, j);
				
				if(currbenefit > maxbenefit)
				{
				maxbenefit = currbenefit;
				moves = new int[getNumPossibleMoves(board, player)][2];
				moves[0][0] = i;
				moves[0][1] = j;
				index=1;
				}
				
				else if(currbenefit == maxbenefit && currbenefit != 0)
				{
					moves[index][0] = i;
					moves[index][1] = j;
					index++;
				}
				}
				
			}
		
		
		
		if(maxbenefit > 0)
		{
			int ranindex = (int)(Math.random() * (index));
			move[0] = moves[ranindex][0];
			move[1] = moves[ranindex][1];
			return move;
		}
		
		return null;
	}

	public static int[] defensivePlayer(int[][] board, int player) {
		int enemy = (player%2) + 1;
		int playbenefit, enebenefit, maxbenefit = Integer.MIN_VALUE;
		int[] move = new int[2], enemove;
		int[][] temp = copyMatrix(board);
		int[][] moves = new int[getNumPossibleMoves(board, player)][2];
		int index = 0;
		
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				if(getNumPossibleMoves(board, player) > 0)
				{
					
				temp = copyMatrix(board);
				playbenefit = benefit(temp, player, i, j);
				play(temp, player, i, j);
				
				enemove = greedyPlayer(temp, enemy);
				if(enemove!=null)
					enebenefit = benefit(temp, enemy, enemove[0], enemove[1]);
				else
					enebenefit =0;
				
				if(maxbenefit < (playbenefit - enebenefit))
				{
					
					moves = new int[getNumPossibleMoves(board, player)][2];
					moves[0][0] = i;
					moves[0][1] = j;
					index=1;
					maxbenefit = (playbenefit - enebenefit);
				}
				else if (maxbenefit == (playbenefit - enebenefit) && playbenefit !=0)
				{
					moves[index][0] = i;
					moves[index][1] = j;
					index++;
				}
				}
			}
		}
		
		if(maxbenefit > Integer.MIN_VALUE)
			
			{
				int ranindex = (int)(Math.random() * (index));
				move[0] = moves[ranindex][0];
				move[1] = moves[ranindex][1];
				return move;
			}

		return null;
	}

	
	public static int findStrength(int[][] board, int i, int j) {

		int startI, startJ;
		if(i< board.length/2)
		{
			startI = board.length / 2 -1;
			if(j< board[0].length/2){
				//upper left quarter
				
				startJ = board[0].length/2 -1;				
			}
			
			else{
				//upper right quarter
				startJ = board[0].length/2;
				
				
			}
			}
		else
		{
			startI = board[0].length/2;
			if(j< board[0].length/2)
			{
				//lower left quarter
				startJ = board[0].length/2 - 1;
			}
			
			
			else
			{
				//lower right quarter
				startJ = board[0].length/2 ;
			}
			
			
			
		}
		

		
		return (1 + Math.abs(startI - i) + Math.abs(startJ - j));		
	}

	public static int[] byLocationPlayer(int[][] board, int player) {

		if(getNumPossibleMoves(board, player) > 0)
		{
			int[][] possibleMoves = possibleMoves(board, player);
			int[][] moves = new int[possibleMoves.length][2];
			int index = 0;
			int minValue = board.length;
			boolean ismax = false;
			int[] strength = new int[possibleMoves.length];
			for (int i = 0; i < possibleMoves.length; i++) {
				
					strength[i] = findStrength(board, possibleMoves[i][0], possibleMoves[i][1]);
					if(strength[i] == board.length - 1)
					{
						if(!ismax)//first time finding max
						{
							moves = new int[possibleMoves.length][2];
							index =0;
						}
						moves[index][0] =  possibleMoves[i][0];
						moves[index][1] =  possibleMoves[i][1];
						ismax = true;
						index++;
					}
					
					else if(!ismax) {
						
						if(strength[i] < minValue)
						{
							moves = new int[possibleMoves.length][2];
							minValue = strength[i];
							index = 0;
							moves[index][0] = possibleMoves[i][0];
							moves[index][1] =  possibleMoves[i][1];
							index++;
						}
						else if(strength[i] == minValue)
						{
							moves[index][0] = possibleMoves[i][0];
							moves[index][1] =  possibleMoves[i][1];
							index++;
						}
						
					}
			}
			
			int[] move = new int[2];
			int ranindex = (int)(Math.random() * (index));
			move[0] = moves[ranindex][0];
			move[1] = moves[ranindex][1];
			return move;
			
		}
		
		
		
		return null;
	}

	public static int[] myPlayer(int[][] board, int player) {
		// a default return statement has been provided. Complete the function's body as you see fit.
		return null;
	}
}