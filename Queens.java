import java.util.*;
/**
 * Queen Class that functions for the Start.java
 * */
public class Queens{
    
    // set final variables
    final int [][] board = new int[8][8];
    final int [][] test = new int[8][8];
    
    // set variable values
    
    int neighbors = 8;
    int logic = 0;
    int restart = 0;
    int changes = 0;
    int num = 8;
    //int c = 0; creating global counter causes OUTofBounds error
    int c2 = 0;
    boolean newBoard = true;
    //boolean conflict = false;
  
  // Start board with 8x8 0's
    public void Queens(){
        for(int a = 0; a < 8; a++){
            for(int b = 0; b < 8; b++){
                board[a][b] = 0;
            }
        }
    }
  
  // Creates a random board
    public void randBoard( ){
      int temp;
      Random r = new Random( );
      
      if(c2 < 8){
            for(int a = 0; a < 8; a++){
                board[r.nextInt(7)][a] = 1;
                c2++;
                }
            }
      logic = logic(board);
    }
    
    
    /** 
     * 
     * Hill-Climbing with random restarts algorithm
     * 
     * */
     
     public boolean checkColumns(int [][] test, int y){
        int c = 0;
        boolean conflict = false;
        
        for(int b = 0; b < 8; b++){
            if(test[y][b] == 1){
                c++;
            }
        }
        if(c > 1){
          conflict = true;
        }
        return conflict;
    }
    
    public boolean checkRows(int [][] test, int x){
        int c = 0;
        boolean conflict = false;
      
        for(int a = 0; a < 8; a++){
            if(test[a][x] == 1){
                c++;
            }
        }
        if(c > 1){
            conflict = true;
        }
        return conflict;
    }
    
  
    public boolean checkDiagonal(int [][] test, int x, int y){
        int c = 0;
        boolean conflict = false;
        

        for(int a = 1; a < 8; a++){
            
            if(conflict){
                break;
            }
            if((x-a >= 0)&&(y-a >= 0)){
                if(test[x-a][y-a] == 1){
                    conflict = true;
                }
            } 
            
            if((x+a < 8)&&(y+a < 8)){
                if(test[x+a][y+a] == 1){
                    conflict = true;
                }
            } 
            
            if((x-a >= 0)&&(y+a < x)){
                if(test[x-a][y+a] == 1){
                    conflict = true;
                }
            } 
            
            if((x+a < 8)&&(y-a >= 0)){
                if(test[x+a][y-a] == 1){
                    conflict = true;
                }
            } 
        }
        return conflict;
    }
  
    public int logic(int [][] test){
    int c = 0; // counter
    boolean cConflict; // column conflicts
    boolean rConflict; // row conflicts
    boolean dConflict; // diagonal conflicts
      
        for(int a = 0;a < 8; a++){
            for(int b= 0;b < 8; b++){
                if(test[a][b] == 1){
                    cConflict = checkColumns(test, a);
                    rConflict = checkRows(test, b);
                    dConflict = checkDiagonal(test, a, b);
                  
                    if(rConflict || cConflict || dConflict){
                        c++;
                    }
                }
            }
        }
        return c;
    }
    
    
    public void changeQueens( ){
        int c = 0;
        int[][] board2 = new int[8][8];
        int minimaCol;
        int minimaRow;
        
        int last = 0;
      
        while(true){
            
            
            for(int a = 0; a < 8; a++){
                System.arraycopy(board[a], 0, test[a], 0, 8);
            }
            c = 0;
            while(c < 8){
                for(int a = 0; a < 8;a++){
                    test[a][c] = 0;
                    if(board[a][c] == 1){
                        last = a;
                    }
                    
                    test[a][c] = 1;
                    board2[a][c] = logic(test);
                    test[a][c] = 0;
                }
                test[last][c] = 1;
                c++;
            }
          
            if(ifRestart(board2)){
                c2 = 0;
                for(int a = 0; a < 8; a++){
                    for(int b = 0; b < 8; b++){
                        board[a][b] = 0;
                    }
                }
                randBoard( );
                System.out.println("RESTART");
                restart++;
            }
      
            minimaCol = findMC(board2);
            minimaRow = findMR(board2);
      
            for(int i = 0; i < 8; i++){
                board[i][minimaCol] = 0;
            }
      
            board[minimaRow][minimaCol] = 1;
            changes++;
            logic = logic(board);
          
            if(logic(board) == 0){
                System.out.println("\n Current State");
                for(int a = 0; a < 8; a++){
                    for(int b = 0; b < 8; b++){
                        System.out.print(board[a][b] + ", ");
                    }
                    System.out.print("\n");
                }
            System.out.println("Solution Found!");
            System.out.println("State changes: " + changes);
            System.out.println("Restarts: " + restart);
            break;
            }

            System.out.println("\n");
            System.out.println("Current h: " + logic);
            System.out.println("Current State");
            for(int a = 0; a < 8; a++){
                for(int b = 0; b < 8; b++){
                    System.out.print(board[a][b] + ", ");
                }
                System.out.print("\n");
            }
            System.out.println("Neighbors found with lower h: " + neighbors);
            System.out.println("Setting new current State");
        }
    }
    
    
    public int findMR(int[][] test){
        
        int num = 8;
        int c = 0;
        
        
        int minimaRow = 8;

      
        for(int a = 0; a < 8; a++){
          for(int b = 0; b < 8; b++){
              if(test[a][b] < num){
                  num = test[a][b];
                  minimaRow = a;
              }
          }
        }
        return minimaRow;
    }
    
    public int findMC(int[][] test){
        int c = 0;
        int num = 8;
        
        int minimaCol = 8;
      
        for(int a = 0; a < 8; a++){
          for(int b = 0; b < 8; b++){
              if(test[a][b] < num){
                  num = test[a][b];
                  minimaCol = b;
              }
              if(test[a][b] < logic){
                  c++;
              }
          }
        }
        neighbors = c;
        return minimaCol;
    }
    
    public boolean ifRestart(int [][] test){
        boolean restart = false;
        int num = 8;
      
        for(int a = 0; a < 8; a++){
            for(int b = 0; b < 8; b++){
                if(test[a][b] < num){
                    num = test[a][b];
                }
            }
        }
        
        if(neighbors == 0){
            restart = true;
        }
        return restart;
    }
}