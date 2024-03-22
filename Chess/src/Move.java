import java.util.ArrayList;

/**
 * The Move class represents a Move played in the game.
 * It encapsulates information about the piece being moved, the current and targeted Position and which Color is currently moving.
 * It also provides the function to check if a move is valid and can be legally played in the game.
 * 
 * @author lalbr
 *
 */
//TODO: Outsource Move Validation to new class ? Seperation of concerns?
public class Move {
	private static ArrayList <Pair> blackPawnDirection = new ArrayList<>();
	private static ArrayList <Pair> whitePawnDirection = new ArrayList<>();
	private static ArrayList <Pair> bishopDirection = new ArrayList<>();
	private static ArrayList <Pair> kingDirection = new ArrayList<>();
	private static ArrayList <Pair> knightDirection = new ArrayList<>();
	private static ArrayList <Pair> rookDirection = new ArrayList<>();
	private static ArrayList <Pair> queenDirection = new ArrayList<>();
	
	/**
	 * Fills all the direction ArrayLists with all possible Moves for each Piece
	 */
	//TODO: better solution? maybe use same system as in checkPathBlocked(...)
	static {
		blackPawnDirection.add(new Pair(1,0));				
		blackPawnDirection.add(new Pair(1,1));
		blackPawnDirection.add(new Pair(1,-1));
		//first move only:
		blackPawnDirection.add(new Pair(2,0));
		
		whitePawnDirection.add(new Pair(-1,0));
		whitePawnDirection.add(new Pair(-1,1));
		whitePawnDirection.add(new Pair(-1,-1));
		//first move only:
		whitePawnDirection.add(new Pair(-2,0));
		
		bishopDirection.add(new Pair(1,1));
		bishopDirection.add(new Pair(1,-1));
		bishopDirection.add(new Pair(-1,1));
		bishopDirection.add(new Pair(-1,-1));			
		bishopDirection.add(new Pair(2,2));
		bishopDirection.add(new Pair(2,-2));
		bishopDirection.add(new Pair(-2,2));
		bishopDirection.add(new Pair(-2,-2));
					
		bishopDirection.add(new Pair(3,3));
		bishopDirection.add(new Pair(3,-3));
		bishopDirection.add(new Pair(-3,3));
		bishopDirection.add(new Pair(-3,-3));
					
		bishopDirection.add(new Pair(4,4));
		bishopDirection.add(new Pair(4,-4));
		bishopDirection.add(new Pair(-4,4));
		bishopDirection.add(new Pair(-4,-4));
				
		bishopDirection.add(new Pair(5,5));
		bishopDirection.add(new Pair(5,-5));
		bishopDirection.add(new Pair(-5,5));
		bishopDirection.add(new Pair(-5,-5));
					
		bishopDirection.add(new Pair(5,5));
		bishopDirection.add(new Pair(5,-5));
		bishopDirection.add(new Pair(-5,5));
		bishopDirection.add(new Pair(-5,-5));
					
		bishopDirection.add(new Pair(6,6));
		bishopDirection.add(new Pair(6,-6));
		bishopDirection.add(new Pair(-6,6));
		bishopDirection.add(new Pair(-6,-6));
					
		bishopDirection.add(new Pair(7,7));
		bishopDirection.add(new Pair(7,-7));
		bishopDirection.add(new Pair(-7,7));
		bishopDirection.add(new Pair(-7,-7));
		
		kingDirection.add(new Pair(0,1));
		kingDirection.add(new Pair(0,-1));
		
		kingDirection.add(new Pair(1,0));
		kingDirection.add(new Pair(1,1));
		kingDirection.add(new Pair(1,-1));
		
		kingDirection.add(new Pair(-1,0));
		kingDirection.add(new Pair(-1,1));
		kingDirection.add(new Pair(-1,-1));
		//Castling moves:
		kingDirection.add(new Pair(0,-2));
		kingDirection.add(new Pair(0,2));
		
		knightDirection.add(new Pair(2,1));
		knightDirection.add(new Pair(2,-1));
		
		knightDirection.add(new Pair(-2,1));
		knightDirection.add(new Pair(-2,-1));
		
		knightDirection.add(new Pair(1,2));
		knightDirection.add(new Pair(1,-2));
		
		knightDirection.add(new Pair(-1,2));
		knightDirection.add(new Pair(-1,-2));
		
		rookDirection.add(new Pair(0,1));
		rookDirection.add(new Pair(0,2));
		rookDirection.add(new Pair(0,3));
		rookDirection.add(new Pair(0,4));
		rookDirection.add(new Pair(0,5));
		rookDirection.add(new Pair(0,6));
		rookDirection.add(new Pair(0,7));
		
		rookDirection.add(new Pair(1,0));
		rookDirection.add(new Pair(2,0));
		rookDirection.add(new Pair(3,0));
		rookDirection.add(new Pair(4,0));
		rookDirection.add(new Pair(5,0));
		rookDirection.add(new Pair(6,0));
		rookDirection.add(new Pair(7,0));
		
		rookDirection.add(new Pair(0,-1));
		rookDirection.add(new Pair(0,-2));
		rookDirection.add(new Pair(0,-3));
		rookDirection.add(new Pair(0,-4));
		rookDirection.add(new Pair(0,-5));
		rookDirection.add(new Pair(0,-6));
		rookDirection.add(new Pair(0,-7));
		
		rookDirection.add(new Pair(-1,0));
		rookDirection.add(new Pair(-2,0));
		rookDirection.add(new Pair(-3,0));
		rookDirection.add(new Pair(-4,0));
		rookDirection.add(new Pair(-5,0));
		rookDirection.add(new Pair(-6,0));
		rookDirection.add(new Pair(-7,0));
		
		queenDirection.addAll(bishopDirection);
		queenDirection.addAll(kingDirection);
		queenDirection.addAll(rookDirection);
	}
	
	private Piece piece;
	private Position currentPosition;
	private Position targetPosition;
	private PlayerColor color;
	
	public Move(Piece piece, Position currentPosition, Position targetPosition, PlayerColor color) {
		this.piece = piece;
		this.currentPosition = currentPosition;
		this.targetPosition = targetPosition;
		this.color = color;
		try {
			isValid(this);
		} catch (IllegalMoveException e) {
			e.printStackTrace();
		}
	}
	//private constructor to prevent validity checking for this move if it is only to be simulated for check searching. also prevents unwanted add to playedmove list
	private Move(Piece piece, Position currentPosition,Position targetPosition,boolean checkValidity) {
		this.piece = piece;
		this.currentPosition = currentPosition;
		this.targetPosition = targetPosition;

	}
	
	//TODO: Castling
	//TODO: Pawn promotion
	/**
	 * Confirms if a move is legal and can be played.
	 * Also checks for all the special cases.
	 * @param move The move that is to be checked for validity
	 * @throws IllegalMoveException is thrown if any of the severals checks fail with a description on why the move is illegal 
	 */
	private void isValid(Move move) throws IllegalMoveException{
		/* 0. Check if the correct color is making a move
		 * 1. Check if Player is currently in check
		 * 2. Check if Piece is actually on the starting field that is being named
		 * 3. Check if Piece can move that way -> Pawn Capture?
		 * 4. Check if Tile is empty or if enemy has piece on it -> own piece cant be captured
		 * 5. Check if own king would be in check after move
		 */
		Board boardInstance = Board.getBoardInstance();
		Piece p = move.getPiece();
		Piece[][] board = boardInstance.getBoard();
		int currentRow = move.getCurrentPosition().getRow();
		int currentColumn = move.getCurrentPosition().getColumn();
		int targetRow = move.getTargetPosition().getRow();
		int targetColumn = move.getTargetPosition().getColumn();
	
		//Check if the correct color is making a move :: Done
		ArrayList<Move> moveList = boardInstance.getPlayedMoves();
		if(moveList.size()>0) {
			PlayerColor lastMove = moveList.get(moveList.size()-1).color;
			if(lastMove == move.getColor()) {
				throw new IllegalMoveException("Expected move from different color.\nLast move played by " + 
						moveList.get(moveList.size()-1).color + "\nCurrent Move played by: "+move.getColor());
			}
		}
		else {
			if(move.color == PlayerColor.BLACK) {
				throw new IllegalMoveException("White starts the game");
			}
		}
		
		//Check if Piece is on the named square :: Done
		if(!p.equals(board[currentRow][currentColumn])) {
			//the piece from the move isnt equal to the piece saved in the board array
			throw new IllegalMoveException("Piece is not on the named starting piece.\nPiece current Position: " 
			+ move.getCurrentPosition() + "\nPiece on the supposed Position: " + board[currentRow][currentColumn]);
		}
		
		//Check if Piece can move that way ::Done
		if(correctDirection(move) == false) {
			throw new IllegalMoveException("This Piece cannot move that way by the rules of chess");
		}

		//check if Tile is empty or if enemy has piece on it ::Done
		if(board[targetRow][targetColumn] != null) {	
			if(board[targetRow][targetColumn].getPieceColor() == move.getColor()) {
				throw new IllegalMoveException("Square is blocked by Piece of the same color");
			}
		}
				
				
		//TODO there has to be a better more generic way to do this...
		if(p.equals(Piece.blackPawn) || p.equals(Piece.whitePawn)) {
			//check for capture , pawn blockage, en passant, first move 2 squares
			switch(move.color) {
			case BLACK:	//pawn blockage
				boolean enpassant = false;
				if(currentColumn == targetColumn && board[targetRow][targetColumn] == Piece.whitePawn) throw new IllegalMoveException("Path blocked by enemy pawn. Pawn cannot capture that way"); 
				//first move 2 squares
				if(Math.abs(currentRow - targetRow) == 2 && currentRow != 1) throw new IllegalMoveException("Pawn can only move 2 squares on the first move");
				//target square is empty
				if(board[targetRow][targetColumn] == null) {
					//check en passant 
					ArrayList<Move> playedMoves = boardInstance.getPlayedMoves();


						Move previouslyPlayed = playedMoves.get(playedMoves.size()-1);
						//moved piece has to be pawn
						if(previouslyPlayed.piece == Piece.whitePawn) {
							Position previouslyTargetPosition = previouslyPlayed.getTargetPosition();
							int previouslyTargetRow = previouslyTargetPosition.getRow();
							int previouslyTargetColumn = previouslyTargetPosition.getColumn();
							int previouslyCurrentRow = previouslyPlayed.getCurrentPosition().getRow();
							/*
							 * en passant condition:
							 * current move: one forward one to the side. previous move made by pawn and 2 squares crossed, 
							 * current targetcolumn = previous move target column (no sidewards moves in prev.) and the current target is null
							 */
							if(Math.abs(currentRow-targetRow) == 1 && Math.abs(currentColumn - targetColumn)==1 && !(Math.abs(previouslyTargetRow - previouslyCurrentRow ) == 2  
									&& previouslyTargetRow == currentRow  && previouslyTargetColumn == targetColumn)) {
								throw new IllegalMoveException("en-passant is not possible here / ");
							}
							//rule correct en passant
							else if(Math.abs(currentRow-targetRow) == 1 && Math.abs(currentColumn - targetColumn)==1 && Math.abs(previouslyTargetRow - previouslyCurrentRow ) == 2  
									&& previouslyTargetRow == currentRow  && previouslyTargetColumn == targetColumn) {
								boardInstance.removePiece(previouslyTargetPosition,boardInstance.getBoard());
								enpassant = true;
							}
						}

					//moving to empty diagonal field without en passant possibility
					if(!enpassant && currentColumn != targetColumn) {
						throw new IllegalMoveException("moving to empty diagonal square as pawn without en passant is illegal");
					}
				}
				//targetSquare is not empty and occpuied by same colored pawn
				else if(Math.abs(currentRow-targetRow) == 1 && Math.abs(currentColumn - targetColumn) == 1 && !(board[targetRow][targetColumn].getPieceColor() != PlayerColor.BLACK)){
						throw new IllegalMoveException("The targeted square is filled with a Piece from the same color");
				}
				
				break;
						
			case WHITE:	//pawn blockage
						enpassant = false;
						if(currentColumn == targetColumn && board[targetRow][targetColumn] == Piece.blackPawn) throw new IllegalMoveException("Path blocked by enemy pawn. Pawn cannot capture that way"); 
						//first move 2 squares
						if(Math.abs(currentRow - targetRow) == 2 && currentRow != 6) throw new IllegalMoveException("Pawn can only move 2 squares on the first move");
						//target square is empty
						if(board[targetRow][targetColumn] == null) {
							//check en passant 
							ArrayList<Move> playedMoves = boardInstance.getPlayedMoves();
							//atleast 4 moves must have been made inorder to reach an en passant position
							if(playedMoves.size()>=4) {
								Move previouslyPlayed = playedMoves.get(playedMoves.size()-1);
								//moved piece has to be pawn
								if(previouslyPlayed.piece == Piece.blackPawn) {
									Position previouslyTargetPosition = previouslyPlayed.getTargetPosition();
									int previouslyTargetRow = previouslyTargetPosition.getRow();
									int previouslyTargetColumn = previouslyTargetPosition.getColumn();
									int previouslyCurrentRow = previouslyPlayed.getCurrentPosition().getRow();
									/*
									 * en passant condition:
									 * current move: one forward one to the side. previous move made by pawn and 2 squares crossed, 
									 * current targetcolumn = previous move target column (no sidewards moves in prev.) and the current target is null
									 */
									if(Math.abs(currentRow-targetRow) == 1 && Math.abs(currentColumn - targetColumn)==1 && !(Math.abs(previouslyTargetRow - previouslyCurrentRow ) == 2  
											&& previouslyTargetRow == currentRow  && previouslyTargetColumn == targetColumn)) {
										throw new IllegalMoveException("en-passant is not possible here / ");
									}
									//rule correct en passant
									else if(Math.abs(currentRow-targetRow) == 1 && Math.abs(currentColumn - targetColumn)==1 && Math.abs(previouslyTargetRow - previouslyCurrentRow ) == 2  
											&& previouslyTargetRow == currentRow  && previouslyTargetColumn == targetColumn) {
										boardInstance.removePiece(previouslyTargetPosition,boardInstance.getBoard());
										enpassant = true;
									}
								}
							}	
							//moving to empty diagonal field without en passant possibility
							if(!enpassant && currentColumn != targetColumn) {
								throw new IllegalMoveException("moving to empty diagonal square as pawn without en passant is illegal");
							}
						}
						//targetSquare is not empty and occpuied by same colored pawn
						else if(Math.abs(currentRow-targetRow) == 1 && Math.abs(currentColumn - targetColumn) == 1 && !(board[targetRow][targetColumn].getPieceColor() != PlayerColor.WHITE)){							
								throw new IllegalMoveException("There square is filled with a Piece from the same color");		
						}
						
						break;
			}			
		}
		else if(p.equals(Piece.blackKnight) || p.equals(Piece.whiteKnight)){
			//no checks neccessary
		}
		else if(p.equals(Piece.whiteKing)  || p.equals(Piece.blackKing)) {
			/*
			 * if king and rook have not moved => check playedMoves
			 * if king move => 2 column & 0 row
			 * if not in check(every tile between currentdirection and targetdirection) => else exception
			 * update board
			 */

			boolean blackKingHasMoved = false;
			boolean whiteKingHasMoved = false;
			boolean WhiteArookHasMoved = false;
			boolean WhiteHrookHasMoved = false;
			boolean blackArookHasMoved = false;
			boolean blackHrookHasMoved = false;
			ArrayList<Move> playedMoves = boardInstance.getPlayedMoves();
			//castling move
			if(currentRow-targetRow == 0 && Math.abs(currentColumn-targetColumn) == 2){
				//check if king or rook were moved
				for(Move m : playedMoves){
					if(m.getPiece().equals(Piece.whiteKing)) whiteKingHasMoved = true;
					else if(m.getPiece().equals(Piece.blackKing)) blackKingHasMoved = true;
					else if(m.getPiece().equals(Piece.whiteRook) && m.getCurrentPosition() == Position.A1) WhiteArookHasMoved = true;
					else if(m.getPiece().equals(Piece.whiteRook) && m.getCurrentPosition() == Position.H1) WhiteHrookHasMoved = true;
					else if(m.getPiece().equals(Piece.blackRook) && m.getCurrentPosition() == Position.A8) blackArookHasMoved = true;
					else if(m.getPiece().equals(Piece.blackRook) && m.getCurrentPosition() == Position.H8) blackHrookHasMoved = true;
				}
				//short castling white
				if(targetPosition == Position.G1 && whiteKingHasMoved==false && WhiteHrookHasMoved == false){
					//check if King would be in check after move;
					Piece[][] boardSimulation = board;
					Move moveF1 = new Move(Piece.whiteKing,currentPosition,Position.F1,PlayerColor.WHITE);
					boardInstance.updateBoard(moveF1, boardSimulation);
					//king cannot LEAVE or CROSS OVER or FINISH on a square that is checked
					//currently in check:
					if(inCheck(PlayerColor.WHITE,board)){
						throw new IllegalMoveException("King cannot castle when given a check");
					}
					//cross over:
					if(inCheck(move.getColor(),boardSimulation)) {
						throw new IllegalMoveException("King cannot cross a square that is dangered. F1 dangered");
					}
					//finish: gets checked at the end of the function automatically
					boardInstance.removePiece(Position.H1,board);
					boardInstance.placePiece(Position.F1,board,Piece.whiteRook);
				}
				//long castling white
				else if(targetPosition == Position.C1 && whiteKingHasMoved==false && WhiteArookHasMoved == false){
					//king cannot LEAVE or CROSS OVER or FINISH on a square that is checked
					Piece[][] boardSimulation = board;
					//create move to determine if king would be in check while crossing to new position
					Move moveD1 = new Move(Piece.whiteKing,currentPosition,Position.D1,PlayerColor.WHITE);
					boardInstance.updateBoard(moveD1, boardSimulation);
					//currently in check:
					if(inCheck(PlayerColor.WHITE,board)){
						throw new IllegalMoveException("King cannot castle when given a check");
					}
					//cross over:
					if(inCheck(move.getColor(),boardSimulation)) {
						throw new IllegalMoveException("King cannot cross a square that is dangered. D1 dangered");
					}
					//finish: gets checked at the end of the function automatically
					boardInstance.removePiece(Position.A1,board);
					boardInstance.placePiece(Position.D1,board,Piece.whiteRook);
				}
				//short castling black
				else if(targetPosition == Position.G8 && blackKingHasMoved==false && blackArookHasMoved == false){
					//check if King would be in check after move;
					Piece[][] boardSimulation = board;
					Move moveF8 = new Move(Piece.blackKing,currentPosition,Position.F8,PlayerColor.BLACK);
					boardInstance.updateBoard(moveF8, boardSimulation);
					//king cannot LEAVE or CROSS OVER or FINISH on a square that is checked
					//currently in check:
					if(inCheck(PlayerColor.BLACK,board)){
						throw new IllegalMoveException("King cannot castle when given a check");
					}
					//cross over:
					if(inCheck(move.getColor(),boardSimulation)) {
						throw new IllegalMoveException("King cannot cross a square that is dangered. F1 dangered");
					}
					//finish: gets checked at the end of the function automatically
					boardInstance.removePiece(Position.H8,board);
					boardInstance.placePiece(Position.F8,board,Piece.blackRook);
				}
				//long castling black
				else if(targetPosition == Position.C8&&blackKingHasMoved==false && blackHrookHasMoved == false){
					//king cannot LEAVE or CROSS OVER or FINISH on a square that is checked
					Piece[][] boardSimulation = board;
					//create move to determine if king would be in check while crossing to new position
					Move moveD8 = new Move(Piece.blackKing,currentPosition,Position.D8,PlayerColor.BLACK);
					boardInstance.updateBoard(moveD8, boardSimulation);
					//currently in check:
					if(inCheck(PlayerColor.BLACK,board)){
						throw new IllegalMoveException("King cannot castle when given a check");
					}
					//cross over:
					if(inCheck(move.getColor(),boardSimulation)) {
						throw new IllegalMoveException("King cannot cross a square that is dangered. D1 dangered");
					}
					//finish: gets checked at the end of the function automatically
					boardInstance.removePiece(Position.A8,board);
					boardInstance.placePiece(Position.D8,board,Piece.blackRook);
				}
				// 2 column difference but no castling allowed => illegal
				else{
					throw new IllegalMoveException("Castling is not allowed in this situation\nBlackKing has moved " + blackKingHasMoved
					+ "\nBlack A Rook has moved: " + blackArookHasMoved + "\nBlack H Rook has Moved: " + blackHrookHasMoved
					+ "\nWhite King has moved " + whiteKingHasMoved + "\nWhite A Rook has moved " +WhiteArookHasMoved +
							"\nWhite H Rook has moved " + WhiteHrookHasMoved);
				}

			}


		}
		else {
			if(checkPathBlocked(currentPosition,targetPosition,move.piece) == false) {
				throw new IllegalMoveException("Path is blocked");				
			}
		}
		
		//check if King would be in check after move;
		Piece[][] boardSimulation = board;
		boardInstance.updateBoard(move, boardSimulation);
		if(inCheck(move.getColor(),boardSimulation)) {
			throw new IllegalMoveException("King is in check after this move");
		}
		//After every check is successfully passed
		boardInstance.updateBoard(move, board);
		boardInstance.playedMoves.add(move);
	}
	
	
	
	
	
	/**
	 * Checks if the path between the current location and the target location is blocked by a piece and the move therefore illegal.
	 * Calculates the difference between current and targetposition and checks each position by using the Integer.compare(a,b) function to increment with 1 or -1 until the position is reached.
	 * @param piece The piece that is moving
	 * @param targetPosition the targeted position
	 * @param currentPosition the current position from which the piece wants to move away from
	 * @return boolean false if path is blocked; boolean true if path is not blocked 
	 */
	private boolean checkPathBlocked(Position currentPosition, Position targetPosition, Piece piece) {
		int currentRow = currentPosition.getRow();
		int targetRow = targetPosition.getRow();
		int currentColumn = currentPosition.getColumn();
		int targetColumn = targetPosition.getColumn();
		Board boardInstance = Board.getBoardInstance(); 
		int rowIncrement = Integer.compare(targetRow, currentRow);
		int columnIncrement = Integer.compare(targetColumn, currentColumn);
		//start increment 1 before so it doesnt detect the currentPosition as Piece that is blocking the path:
		currentRow += rowIncrement;
		currentColumn += columnIncrement;
		if(piece.equals(Piece.blackBishop) || piece.equals(Piece.whiteBishop) || piece.equals(Piece.whiteRook) || piece.equals(Piece.blackRook) || piece.equals(Piece.blackQueen) || piece.equals(Piece.whiteQueen) ) {
			while(currentRow!= targetRow || currentColumn != targetColumn) {
				if(boardInstance.getBoard()[currentRow][currentColumn]!=null) { 
					return false;
				}
				currentRow += rowIncrement;
				currentColumn += columnIncrement;
			}
		}
		return true;
	}
	
	//TODO: Is this neccessary? Can this be done using the function above?
	
	/**
	 * Checks for an incoming move if the Position can be legally reached  
	 * @param move The move that is to be checked
	 * @return returns true if the piece can theoretically move to the targeted position
	 */
	private boolean correctDirection(Move move) {
		Piece p = move.getPiece();
		Position currentPosition = move.getCurrentPosition();
		Position targetPosition = move.getTargetPosition();
		if(p == Piece.blackBishop ||p == Piece.whiteBishop){
			return positionMatches(bishopDirection,currentPosition,targetPosition);
		}
		else if(p == Piece.blackKing || p == Piece.whiteKing){
			return positionMatches(kingDirection,currentPosition,targetPosition);
		}
		else if(p == Piece.blackKnight || p == Piece.whiteKnight){
			return positionMatches(knightDirection,currentPosition,targetPosition);
		}
		else if(p == Piece.whiteQueen || p==Piece.blackQueen){
			return positionMatches(queenDirection,currentPosition,targetPosition);
		}
		else if(p == Piece.blackRook || p == Piece.whiteRook) {
			return positionMatches(rookDirection,currentPosition,targetPosition);
		}
		else if(p == Piece.blackPawn){
			return positionMatches(blackPawnDirection,currentPosition,targetPosition);
		}
		else if(p == Piece.whitePawn){
			return positionMatches(whitePawnDirection,currentPosition,targetPosition);
		}
		else{
			return false;
		}

	}
	
	/**
	 * This function Takes the currentPosition, adds all possible direction changes to it and checks if the targetPosition equals a Position that gets calculated when adding the move coordinates to the currentPosition
	 *
	 * @param list List of Pairs that represent all the possible moves the piece can theoretically execute
	 * @param currentPosition The current Position of the Piece
	 * @param targetPosition The in the move targeted Position of the move
	 * @return Returns boolean value stating if the Piece can theoretically move to the given Position; True if it can move there; false if it cannot
	 */
	private boolean positionMatches(ArrayList<Pair> list,Position currentPosition,Position targetPosition) {	
		int currentRow = currentPosition.getRow();
		int currentColumn = currentPosition.getColumn();
		for(Pair pair : list) {
			int newRow = currentRow + pair.row;
			int newColumn = currentColumn + pair.column;
			if(newRow == targetPosition.getRow() && newColumn == targetPosition.getColumn()) {
				return true;
			}
		}
		return false;
	}
	
	//TODO add checkmate analyzis? => all possible moves to remove the check
	//FIXME Pawn checks are not calculated correct => they can only move 1 position but get calculated like bishops (probably)
	/**
	 * This method analyzes a given board and examines it if movecolor king is currently in check
	 * @param moveColor the playercolor that is making the move
	 * @param board the board which has to be examined
	 * @return boolean true if the moveColor-king is currently in check
	 */
	private boolean inCheck(PlayerColor moveColor, Piece[][] board) {	
		/*
		 * Check each square next to the king for pieces of the same color => if they are all from the same color then there can only be a check by knight
		 * 	=> for each square where there is no piece of the same color next to the King, iterate over all the square horizontal and diagonal from it and look for an enemy piece
		 * Check for Knight checks 
		 * 	=> King position plus all possible knight moves from knightDirection ArrayList
		 */
		
		//determines current king position :: DONE and works
		Position kingPosition = null;
		if(moveColor == PlayerColor.WHITE) {
			for(int i = board.length-1;i>=0;i--) {
				for(int j = board[i].length-1;j>=0;j--) {
					if(board[i][j] == Piece.whiteKing) {
						kingPosition = Position.getPositionFromValue(i, j);
						//check for knight checks => take king position and add all possible knight moves to it and check if there is an enemy knight on it; returns true if there is
						for(Pair p : knightDirection) {
							int iterateRow = kingPosition.getRow() + p.row;
							int iterateColumn = kingPosition.getColumn() + p.column;
							//prevent out of bounds exception
							if (iterateRow >= 0 && iterateRow < board.length && iterateColumn >= 0 && iterateColumn < board[iterateRow].length) {
								if(board[iterateRow][iterateColumn] != null && board[iterateRow][iterateColumn] == Piece.blackKnight) {
									//enemy Knight can jump to the king from his current position and the king is therefore in check
									return true;
								}
							}
							
						}
					}
				}
			}
		}
		else if(moveColor == PlayerColor.BLACK) {
			for(int i = 0;i<board.length;i++) {
				for(int j=0;j<board[i].length;j++) {
					if(board[i][j] == Piece.blackKing) {
						kingPosition = Position.getPositionFromValue(i, j);
						//check for knight checks => take king position and add all possible knight moves to it and check if there is an enemy knight on it; returns true if there is
						for(Pair p : knightDirection) {
							int iterateRow = kingPosition.getRow() + p.row;
							int iterateColumn = kingPosition.getColumn() + p.column;
							//prevent out of bounds exception
							if (iterateRow >= 0 && iterateRow < board.length && iterateColumn >= 0 && iterateColumn < board[iterateRow].length) {
								if(board[iterateRow][iterateColumn] != null && board[iterateRow][iterateColumn] == Piece.whiteKnight) {
									//enemy Knight can jump to the king from his current position and the king is therefore in check
									return true;
								}
							}
							
						}
						
					}
					
				}
			}
		}
		
		
		
		//determine squares that need to be checked => either enemy piece or empty square through which a distant piece can give a check
		ArrayList<Position> possibleChecks = new ArrayList<Position>();
		for(Pair p : kingDirection) {
			//iterate over the squares that touch the king
			int rowAfterMovement = kingPosition.getRow() + p.row;
			int columnAfterMovement = kingPosition.getColumn() + p.column;
			//prevents out of bounds
			if(rowAfterMovement >= 0 && columnAfterMovement >=0 && rowAfterMovement < board.length && columnAfterMovement < board[rowAfterMovement].length ) {
				//enemy piece stands next to king
				if(board[rowAfterMovement][columnAfterMovement] != null && board[rowAfterMovement][columnAfterMovement].getPieceColor() != moveColor) {
					//add to possibleChecks
					possibleChecks.add(Position.getPositionFromValue(rowAfterMovement, columnAfterMovement));
				}
				//empty square next to king
				else if(board[rowAfterMovement][columnAfterMovement] == null) {		
					int rowIncrement = Integer.compare(rowAfterMovement, kingPosition.getRow());
					int columnIncrement = Integer.compare(columnAfterMovement, kingPosition.getColumn());
					int iterateRow = kingPosition.getRow(); 
							iterateRow += rowIncrement;	
					int iterateColumn = kingPosition.getColumn();
							iterateColumn += columnIncrement;					
					// Iterate from current king position in the direction of the empty square until an own piece, enemy piece or the end of the board is reached
					while(iterateRow>=0 && iterateColumn >= 0 && iterateRow < board.length && iterateColumn < board[iterateRow].length) {
						if(board[iterateRow][iterateColumn] != null) {
							if(board[iterateRow][iterateColumn].getPieceColor() == moveColor) {
								//piece of the same color blocks the way => no check possible
								break;
							}
							//enemy piece found => add to possibleChecks list
							else if(board[iterateRow][iterateColumn].getPieceColor() != moveColor) {
								if(board[iterateRow][iterateColumn] == Piece.whitePawn ||board[iterateRow][iterateColumn] == Piece.blackPawn){
									//pawn has to be one row away from the king inorder to give him a check
									if(Math.abs(iterateRow- kingPosition.getRow())== 1) {
										possibleChecks.add(Position.getPositionFromValue(iterateRow, iterateColumn));
									}
								}
								else{
									possibleChecks.add(Position.getPositionFromValue(iterateRow, iterateColumn));
								}

							}	
						}
							//increment the iteration by the direction of the empty square => Integer.compare gives the direction
							iterateRow +=rowIncrement;
							iterateColumn+=columnIncrement;	
					}
				}
			}
		}
		//iterate over possibleChecks list and determine if the piece can theoreticall move to the king => can capture him and therefore give him a check
		for(Position p : possibleChecks) {
			if(board[p.getRow()][p.getColumn()] != null) {
				//use private move constructor to prevent each move getting checked for validity which is not needed in this case. move only gets created to re-use correctDirection method
				if(correctDirection(new Move(board[p.getRow()][p.getColumn()],p,kingPosition,false))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Piece getPiece() {
		return piece;
	}


	public Position getCurrentPosition() {
		return currentPosition;
	}


	public Position getTargetPosition() {
		return targetPosition;
	}


	public PlayerColor getColor() {
		return color;
	}
	
	/**
	 * Used to generate all possible move directions for a Piece by using a pair that indicates movement in the vertical and horizontal direction
	 * @author lalbr
	 *
	 */
	static class Pair{
		private int row;
		private int column;
		public Pair(int row,int column) {
			this.row = row;
			this.column = column;
		}
	}
	
}
