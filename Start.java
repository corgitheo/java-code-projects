/**
 * Programming Assignment #1 
 * 8-Queens
 * Intro to AI – ITCS 3153
 * Visa Xiong
 */
 
public class Start{
    
	public static void main(String[] args) {
	    //Create queen
	    Queens q = new Queens();
	    
	    // Create random board 8x8
	    q.randBoard();
	    
	    // State Changes
	    q.changeQueens();
	}
}