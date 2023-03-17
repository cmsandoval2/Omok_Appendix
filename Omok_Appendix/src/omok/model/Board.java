package omok.model;

import java.util.ArrayList;
import java.util.List;

//import omok.model.Player;

/**
 * Abstraction of an Omok board, which consists of n x n intersections
 * or places where players can place their stones. The board can be 
 * accessed using a pair of 0-based indices (x, y), where x and y 
 * denote the column and row number, respectively. The top-left 
 * intersection is represented by the indices (0, 0), and the
 * bottom-right intersection is represented by the indices (n-1, n-1).
 */
public class Board {
	private final int size;
	protected Player player1=new Player("Player 1");
	protected Player player2=new Player("Player 2"); 
	protected Player player3=new Player("CPU");
	public Place place;
	public Place place1;
	public Place place2;
	public Place place3;
	public Place place4;


	char[][] board=new char[0][0];
    /** Create a new board of the default size. */
    public Board() {
    	size=15;
       this.board=new char[size][size];
        for(int i=0;i<size;i++) {
        	for(int j=0;j<size;j++) {
        		board[i][j]='.';
        	}
        }
    }

    /** Create a new board of the specified size. */
    public Board(int size) {
    	this.size=size;  
        this.board=new char[size][size];
        for(int i=0;i<size;i++) {
        	for(int j=0;j<size;j++) {
        		board[i][j]='.';
        	}
        }
}
    

    /** Return the size of this board. */
    public int size() {
    	return size;
    }
    
    /** Removes all the stones placed on the board, effectively 
     * resetting the board to its original state. 
     */
    public void clear() {
    	for(int i=0;i<board.length;i++) {
    		for(int j=0;j<board[i].length;j++) {
    			board[i][j]='.';
    		}
    		}
    }

    /** Return a boolean value indicating whether all the places
     * on the board are occupied or not.
     */
    public boolean isFull() {
    	for(int i=0;i<board.length;i++) {
    		for(int j=0;j<board[i].length;j++) {
    			if(board[i][j]=='.') {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    /**
     * Place a stone for the specified player at a specified 
     * intersection (x, y) on the board.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     * @param player Player whose stone is to be placed
     */
    public void placeStone(int x, int y, Player player) {
    	char symbol=' ';
    	String user=player.name();
    	if(user.equals("Player 1")) {
    		symbol='X';
    	}else if(user.equals("CPU")||user.equals("Player 2")){
    		symbol='O';
    	}
    	board[x][y]=symbol;	
    }
    
    /**
     * Return a boolean value indicating whether the specified 
     * intersection (x, y) on the board is empty or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isEmpty(int x, int y) {
    	if(board[x][y]=='.') {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Is the specified place on the board occupied?
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupied(int x, int y) {
    	if(board[x][y]=='.') {
    		return false;
    	}
    	return true;
    }

    /**
     * Rreturn a boolean value indicating whether the specified 
     * intersection (x, y) on the board is occupied by the given 
     * player or not.
     *
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public boolean isOccupiedBy(int x, int y, Player player) {
    	String user=player.name();
    	if(user.equals("Player 1") && board[x][y]=='X') {
    		return true;
    	}
    	else if(user.equals("CPU") && board[x][y]=='O') {
    		return true;
    	}
    	else if(user.equals("Player 2") && board[x][y]=='O') {
    		return true;
    	}
    	return false;
    }

    /**
     * Return the player who occupies the specified intersection (x, y) 
     * on the board. If the place is empty, this method returns null.
     * 
     * @param x 0-based column (vertical) index
     * @param y 0-based row (horizontal) index
     */
    public Player playerAt(int x, int y) {
    	if(isOccupiedBy(x,y,player1)) {
    		return player1;
    	}else if(isOccupiedBy(x,y,player2)) {
    		return player2;
    	}
    	return null;
    }

    /** 
     * Return a boolean value indicating whether the given player 
     * has a winning row on the board. A winning row is a consecutive 
     * sequence of five or more stones placed by the same player in 
     * a horizontal, vertical, or diagonal direction.
     */
    public boolean isWonBy(Player player) {
    	if(player.name()=="Player 1") {
    		//Horizontal
    	for(int i=0;i<board.length;i++) {
    		for(int j=0;j<board[i].length-3;j++) {
    			if(board[i][j]=='X'&&board[i][j+1]=='X'&&board[i][j+2]=='X'&&board[i][j+3]=='X'&&board[i][j+4]=='X') {
    				place=new Place(i,j);
    				place1=new Place(i,j+1);
    				place2=new Place(i,j+2);
    				place3=new Place(i,j+3);
    				place4=new Place(i,j+4);
    				return true;
    			}
    		}
    	}
    			//vertical
    		for(int i=0;i<board.length-3;i++) {
    			for(int j=0;j<board[i].length;j++) {
    		 if(board[i][j]=='X'&&board[i+1][j]=='X'&&board[i+2][j]=='X'&&board[i+3][j]=='X'&&board[i+4][j]=='X') {
    			 place=new Place(i,j);
 				place1=new Place(i+1,j);
 				place2=new Place(i+2,j);
 				place3=new Place(i+3,j);
 				place4=new Place(i+4,j);	
    			 return true;
    			}
    			}
    		}
    		//Ascending diagonal
    		for (int i = 4; i < board.length; i++) {
    		    for (int j = 0; j < board[i].length-4; j++) {
    		        if (board[i][j] == 'X' && board[i-1][j+1] == 'X' && board[i-2][j+2] == 'X' && board[i-3][j+3] == 'X' && board[i-4][j+4] == 'X') {
    		        	place=new Place(i,j);
        				place1=new Place(i-1,j+1);
        				place2=new Place(i-2,j+2);
        				place3=new Place(i-3,j+3);
        				place4=new Place(i-4,j+4);
    		        	return true;
    		        }
    		    }
    		}
    		//Descending diagonal
    		for(int i=4;i<board.length;i++) {
    			for(int j=4;j<board[i].length;j++) {
    				if(board[i][j]=='X'&&board[i-1][j-1]=='X'&&board[i-2][j-2]=='X'&&board[i-3][j-3] =='X'&&board[i-4][j-4]=='X') {
    					place=new Place(i,j);
        				place1=new Place(i-1,j-1);
        				place2=new Place(i-2,j-2);
        				place3=new Place(i-2,j-3);
        				place4=new Place(i-2,j-4);
    					return true;
    		}
    			}
    		}
    	}
    	if(player.name()=="CPU"||player.name()=="Player 2") {
    		//Horizontal
    		for(int i=0;i<board.length;i++) {
    			for(int j=0;j<board[i].length-3;j++) {
    				if(board[i][j]=='O'&&board[i][j+1]=='O'&&board[i][j+2]=='O'&&board[i][j+3]=='O'&&board[i][j+4]=='O') {
    					return true;
    				}
    			}
    		}
    				//vertical
    			for(int i=0;i<board.length-3;i++) {
    				for(int j=0;j<board[i].length;j++) {
    			 if(board[i][j]=='O'&&board[i+1][j]=='O'&&board[i+2][j]=='O'&&board[i+3][j]=='O'&&board[i+4][j]=='O') {
    				 place=new Place(i,j);
     				place1=new Place(i,j+1);
     				place2=new Place(i,j+2);
     				place3=new Place(i,j+3);
     				place4=new Place(i,j+4);	
    				 return true;
    				}
    				}
    			}
    			//Ascending diagonal
    			for (int i = 4; i < board.length; i++) {
    			    for (int j = 0; j < board[i].length-4; j++) {
    			        if (board[i][j] == 'O' && board[i-1][j+1] == 'O' && board[i-2][j+2] == 'O' && board[i-3][j+3] == 'O' && board[i-4][j+4] == 'O') {
    			        	place=new Place(i,j);
    	    				place1=new Place(i-1,j+1);
    	    				place2=new Place(i-2,j+2);
    	    				place3=new Place(i-3,j+3);
    	    				place4=new Place(i-4,j+4);
    			        	return true;
    			        }
    			    }
    			}
    			//Descending diagonal
    			for(int i=4;i<board.length;i++) {
    				for(int j=4;j<board[i].length;j++) {
    					if(board[i][j]=='O'&&board[i-1][j-1]=='O'&&board[i-2][j-2]=='O'&&board[i-3][j-3] =='O'&&board[i-4][j-4]=='O') {
    						place=new Place(i,j);
    	    				place1=new Place(i-1,j-1);
    	    				place2=new Place(i-2,j-2);
    	    				place3=new Place(i-3,j-3);
    	    				place4=new Place(i-4,j-4);
    						return true;
    			}
    				}
    			}
    	}
    	return false;
    }

    /** Return the winning row. For those who are not familiar with
     * the Iterable interface, you may return an object of
     * List<Place>. */
    public Iterable<Place> winningRow() {
    	List<Place> winner=new ArrayList<Place>();
    	if(isWonBy(player1)||isWonBy(player2)) {
    		//Horizontal
        	for(int i=0;i<board.length;i++) {
        		for(int j=0;j<board[i].length-3;j++) {
        			if(board[i][j]=='X'&&board[i][j+1]=='X'&&board[i][j+2]=='X'&&board[i][j+3]=='X'&&board[i][j+4]=='X') {
        				Place place=new Place(i,j);
        				Place place1=new Place(i,j+1);
        				Place place2=new Place(i,j+2);
        				Place place3=new Place(i,j+3);
        				Place place4=new Place(i,j+4);
        				winner.add(place);
        				winner.add(place1);
        				winner.add(place2);
        				winner.add(place3);
        				winner.add(place4);
        			}
        		}
        	}
        			//vertical
        		for(int i=0;i<board.length-3;i++) {
        			for(int j=0;j<board[i].length;j++) {
        		 if(board[i][j]=='X'&&board[i+1][j]=='X'&&board[i+2][j]=='X'&&board[i+3][j]=='X'&&board[i+4][j]=='X') {
        			 Place place=new Place(i,j);
     				Place place1=new Place(i+1,j);
     				Place place2=new Place(i+2,j);
     				Place place3=new Place(i+3,j);
     				Place place4=new Place(i+4,j);
     				winner.add(place);
     				winner.add(place1);
     				winner.add(place2);
     				winner.add(place3);
     				winner.add(place4);
        			}
        			}
        		}
        		//Ascending diagonal
        		for (int i = 4; i < board.length; i++) {
        		    for (int j = 0; j < board[i].length-4; j++) {
        		        if (board[i][j] == 'X' && board[i-1][j+1] == 'X' && board[i-2][j+2] == 'X' && board[i-3][j+3] == 'X' && board[i-4][j+4] == 'X') {
        		        	Place place=new Place(i,j);
            				Place place1=new Place(i-1,j+1);
            				Place place2=new Place(i-2,j+2);
            				Place place3=new Place(i-3,j+3);
            				Place place4=new Place(i-4,j+4);
            				winner.add(place);
            				winner.add(place1);
            				winner.add(place2);
            				winner.add(place3);
            				winner.add(place4);
        		        }
        		    }
        		}
        		//Descending diagonal
        		for(int i=4;i<board.length;i++) {
        			for(int j=4;j<board[i].length;j++) {
        				if(board[i][j]=='X'&&board[i-1][j-1]=='X'&&board[i-2][j-2]=='X'&&board[i-3][j-3] =='X'&&board[i-4][j-4]=='X') {
        					Place place=new Place(i,j);
            				Place place1=new Place(i-1,j-1);
            				Place place2=new Place(i-2,j-2);
            				Place place3=new Place(i-3,j-3);
            				Place place4=new Place(i-4,j-4);
            				winner.add(place);
            				winner.add(place1);
            				winner.add(place2);
            				winner.add(place3);
            				winner.add(place4);
        		}
        			}
        		}
        		//Horizontal
        		for(int i=0;i<board.length;i++) {
        			for(int j=0;j<board[i].length-3;j++) {
        				if(board[i][j]=='O'&&board[i][j+1]=='O'&&board[i][j+2]=='O'&&board[i][j+3]=='O'&&board[i][j+4]=='O') {
        					Place place=new Place(i,j);
            				Place place1=new Place(i,j+1);
            				Place place2=new Place(i,j+2);
            				Place place3=new Place(i,j+3);
            				Place place4=new Place(i,j+4);
            				winner.add(place);
            				winner.add(place1);
            				winner.add(place2);
            				winner.add(place3);
            				winner.add(place4);
        				}
        			}
        		}
        				//vertical
        			for(int i=0;i<board.length-3;i++) {
        				for(int j=0;j<board[i].length;j++) {
        			 if(board[i][j]=='O'&&board[i+1][j]=='O'&&board[i+2][j]=='O'&&board[i+3][j]=='O'&&board[i+4][j]=='O') {
        				 Place place=new Place(i,j);
          				Place place1=new Place(i+1,j);
          				Place place2=new Place(i+2,j);
          				Place place3=new Place(i+3,j);
          				Place place4=new Place(i+4,j);
          				winner.add(place);
          				winner.add(place1);
          				winner.add(place2);
          				winner.add(place3);
          				winner.add(place4);
        				}
        				}
        			}
        			//Ascending diagonal
        			for (int i = 4; i < board.length; i++) {
        			    for (int j = 0; j < board[i].length-4; j++) {
        			        if (board[i][j] == 'O' && board[i-1][j+1] == 'O' && board[i-2][j+2] == 'O' && board[i-3][j+3] == 'O' && board[i-4][j+4] == 'O') {
        			        	Place place=new Place(i,j);
                				Place place1=new Place(i-1,j+1);
                				Place place2=new Place(i-2,j+2);
                				Place place3=new Place(i-3,j+3);
                				Place place4=new Place(i-4,j+4);
                				winner.add(place);
                				winner.add(place1);
                				winner.add(place2);
                				winner.add(place3);
                				winner.add(place4);    
        			        }
        			    }
        			}
        			//Descending diagonal
        			for(int i=4;i<board.length;i++) {
        				for(int j=4;j<board[i].length;j++) {
        					if(board[i][j]=='O'&&board[i-1][j-1]=='O'&&board[i-2][j-2]=='O'&&board[i-3][j-3] =='O'&&board[i-4][j-4]=='O') {
        						Place place=new Place(i,j);
                				Place place1=new Place(i-1,j-1);
                				Place place2=new Place(i-2,j-2);
                				Place place3=new Place(i-3,j-3);
                				Place place4=new Place(i-4,j-4);
                				winner.add(place);
                				winner.add(place1);
                				winner.add(place2);
                				winner.add(place3);
                				winner.add(place4);
                				
        			}
        				}
        			}
    	}
    	/*for(int i=0;i<board.length;i++) {
        	for(int j=0;j<board[i].length;j++) {
        		if(isWonBy(player1)||isWonBy(player2)) {
        			Place place=new Place(i,j);
        			winner.add(place);
        		}
        	}
        }*/
       		return winner;
        
    }

    /**
     * An intersection on an Omok board identified by its 0-based column
     * index (x) and row index (y). The indices determine the position 
     * of a place on the board, with (0, 0) denoting the top-left 
     * corner and (n-1, n-1) denoting the bottom-right corner, 
     * where n is the size of the board.
     */
    public static class Place {
        /** 0-based column index of this place. */
        public final int x;

        /** 0-based row index of this place. */
        public final int y;

        /** Create a new place of the given indices. 
         * 
         * @param x 0-based column (vertical) index
         * @param y 0-based row (horizontal) index
         */
        public Place(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // other methods if needed ...
    }
}

