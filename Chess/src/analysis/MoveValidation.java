package analysis;

import java.util.ArrayList;
import gamefoundation.*;

import static gamefoundation.Piece.blackKing;
import static gamefoundation.Piece.whiteKing;

public class MoveValidation {

    public static ArrayList <Pair> blackPawnDirection = new ArrayList<>();
    public static ArrayList <Pair> whitePawnDirection = new ArrayList<>();
    public static ArrayList <Pair> bishopDirection = new ArrayList<>();
    public static ArrayList <Pair> kingDirection = new ArrayList<>();
    public static ArrayList <Pair> knightDirection = new ArrayList<>();
    public static ArrayList <Pair> rookDirection = new ArrayList<>();
    public static ArrayList <Pair> queenDirection = new ArrayList<>();

    //TODO better solution for this
    //Fills all the direction ArrayLists with all possible Moves for each foundation.Piece
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


    //TODO: Pawn promotion
    /**
     * Confirms if a move is legal and can be played.
     * Also checks for all the special cases.
     * @param move The move that is to be checked for validity
     * @throws IllegalMoveException is thrown if any of the severals checks fail with a description on why the move is illegal
     */
    public boolean isValid(Move move) throws IllegalMoveException {
        // explanation outdated
        /* 0. Check if the correct color is making a move
         * 1. Check if Player is currently in check
         * 2. Check if foundation.Piece is actually on the starting field that is being named
         * 3. Check if foundation.Piece can move that way -> Pawn Capture?
         * 4. Check if Tile is empty or if enemy has piece on it -> own piece cant be captured
         * 5. Check if own king would be in check after move
         */
        Piece p = move.getPiece();
        Board boardInstance = Board.getBoardInstance();
        Piece[][] board = boardInstance.getBoard();


        int currentRow = move.getCurrentPosition().getRow();
        int currentColumn = move.getCurrentPosition().getColumn();
        int targetRow = move.getTargetPosition().getRow();
        int targetColumn = move.getTargetPosition().getColumn();

        //Check if the correct color is making a move :: Done
        ArrayList<Move> moveList = boardInstance.getPlayedMoves();
        if(moveList.size()>0) {
            PlayerColor lastMoveColor = moveList.get(moveList.size()-1).color;
            if(lastMoveColor == move.getColor()) {
                throw new IllegalMoveException("Expected move from different color.\nLast move played by " +
                        moveList.get(moveList.size()-1).color + "\nCurrent foundation.Move played by: "+move.getColor());
            }
        }
        else {
            if(move.color == PlayerColor.BLACK) {
                throw new IllegalMoveException("White starts the game");
            }
        }


        //Check if foundation.Piece is on the named square :: Done
        if(!(p== board[currentRow][currentColumn])) {
            //the piece from the move isnt equal to the piece saved in the board array
            throw new IllegalMoveException("foundation.Piece is not on the named starting piece.\nfoundation.Piece current foundation.Position: "
                    + move.getCurrentPosition() + "\nfoundation.Piece on the supposed foundation.Position: " + board[currentRow][currentColumn]);
        }

        //Check if foundation.Piece can move that way ::Done
        if(correctDirection(move) == false) {
            throw new IllegalMoveException("This foundation.Piece cannot move that way by the rules of chess");
        }

        //check if Tile is empty or if enemy has piece on it ::Done
        if(board[targetRow][targetColumn] != null) {
            if(board[targetRow][targetColumn].getPieceColor()==move.getColor()) {
                throw new IllegalMoveException("Square is blocked by foundation.Piece of the same color");
            }
        }
        //check for capture , pawn blockage, en passant, first move 2 squares
        if(p == Piece.blackPawn || p == Piece.whitePawn) {
            pawnMoveValidity(move,p);
        }
        else if(p== Piece.blackKnight || p == Piece.whiteKnight){
            //no checks neccessary
        }
        else if(p== Piece.whiteKing  || p== Piece.blackKing) {
            if(kingMoveValidity(move,p) == false){
                throw new IllegalMoveException("Castling illegal");
            }
        }
        //bishop queen rook
        else {
            if(checkPathBlocked(move.currentPosition,move.targetPosition,move.piece) == false) {
                throw new IllegalMoveException("Path is blocked");
            }
        }

        //check if King would be in check after move;
        Piece[][] boardSimulation = boardInstance.cloneBoard(board);
        boardInstance.updateBoard(move, boardSimulation);
        if(inCheck(move.getColor(),boardSimulation) == true) {
            throw new IllegalMoveException("King is in check after this move");
        }


        //check if enemy is checkmated after this move

        //PlayerColor enemy = null;
        //if(move.getColor() == PlayerColor.WHITE) enemy = PlayerColor.BLACK;
        //if(move.getColor() == PlayerColor.BLACK) enemy = PlayerColor.WHITE;

        //if(isCheckMated(enemy,boardSimulation)){
          //  System.out.println(enemy + " is checkmated! Game ended");
           //throw new RuntimeException("Game over");
        //}

        return true;
    }



    /**
     * This method analyzes a given board and examines it if movecolor king is currently in check
     * @param moveColor the playercolor that is making the move
     * @param boardParam the board which has to be examined
     * @return boolean true if the moveColor-king is currently in check
     **/
    public boolean inCheck(PlayerColor moveColor, Piece[][] boardParam) {
        /*
         * Check each square next to the king for pieces of the same color => if they are all from the same color then there can only be a check by knight
         * 	=> for each square where there is no piece of the same color next to the King, iterate over all the square horizontal and diagonal from it and look for an enemy piece
         * Check for Knight checks
         * 	=> King position plus all possible knight moves from knightDirection ArrayList
         */
        //determines current king position :: DONE and works
        Position kingPosition = null;
        Piece king = null;
        Piece knight = null;
        if(moveColor == PlayerColor.WHITE){
            king = whiteKing;
            knight = Piece.blackKnight;
        }
        else if(moveColor== PlayerColor.BLACK){
            king = Piece.blackKing;
            knight = Piece.whiteKnight;
        }

        //get kingPosition
        for(int i = boardParam.length-1;i>=0;i--) {
            for(int j = boardParam[i].length-1;j>=0;j--) {
                if(boardParam[i][j]==king) {
                    kingPosition = Position.getPositionFromValue(i,j);
                }
            }
        }

        //check for knight checks => take king position and add all possible knight moves to it and check if there is an enemy knight on it; returns true if there is
        for(Pair p : knightDirection) {
            int iterateRow = kingPosition.getRow() + p.row;
            int iterateColumn = kingPosition.getColumn() + p.column;
            //prevent out of bounds exception
            if (iterateRow >= 0 && iterateRow < boardParam.length && iterateColumn >= 0 && iterateColumn < boardParam[iterateRow].length) {
                if(boardParam[iterateRow][iterateColumn] != null && boardParam[iterateRow][iterateColumn]==knight) {
                    //enemy Knight can jump to the king from his current position and the king is therefore in check
                    return true;
                }
            }
        }


        //iterate over possibleChecks list and determine if the piece can theoreticall move to the king => can capture him and therefore give him a check
        //ArrayList<Position> possibleChecks = findPossibleChecks(moveColor,kingPosition,boardParam);
        //determine squares that need to be checked => either enemy piece or empty square through which a distant piece can give a check
        ArrayList<Position> possibleChecks = getPossibleChecks(kingPosition,boardParam,moveColor);

        //MoveValidation m = new MoveValidation();
        if(getCheckSourcePosition(possibleChecks,kingPosition,boardParam) != null){
            return true;
        }
        return false;
    }

    /**
     * Creates arraylist including all possible checks to the current movecolor
     * @param kingPosition
     * @param boardParam
     * @param moveColor
     * @return arraylist including all possible positions with enemy pieces on them that can give a check to the movecolor king
     */
    private ArrayList<Position> getPossibleChecks(Position kingPosition,Piece[][] boardParam,PlayerColor moveColor){
        ArrayList<Position> possibleChecks = new ArrayList<>();
        for(Pair p : kingDirection) {
            // CASTLING POSSIBILITY MOVE BREAKS IT
            if(p.column == 2 || p.column == -2){
                continue;
            }

            //iterate over the squares that touch the king
            int rowAfterMovement = kingPosition.getRow() + p.row;
            int columnAfterMovement = kingPosition.getColumn() + p.column;
            //prevents out of bounds
            if(rowAfterMovement >= 0 && columnAfterMovement >=0 && rowAfterMovement < boardParam.length && columnAfterMovement < boardParam[rowAfterMovement].length ) {
                //enemy piece stands next to king
                if(boardParam[rowAfterMovement][columnAfterMovement] != null && boardParam[rowAfterMovement][columnAfterMovement].getPieceColor()!=moveColor) {
                    //add to possibleChecks
                    possibleChecks.add(Position.getPositionFromValue(rowAfterMovement, columnAfterMovement));
                }
                //empty square next to king
                else if(boardParam[rowAfterMovement][columnAfterMovement] == null) {
                    int rowIncrement = Integer.compare(rowAfterMovement, kingPosition.getRow());
                    int columnIncrement = Integer.compare(columnAfterMovement, kingPosition.getColumn());
                    int iterateRow = kingPosition.getRow();
                    iterateRow += rowIncrement;
                    int iterateColumn = kingPosition.getColumn();
                    iterateColumn += columnIncrement;
                    // Iterate from current king position in the direction of the empty square until an own piece, enemy piece or the end of the board is reached
                    while(iterateRow>=0 && iterateColumn >= 0 && iterateRow < boardParam.length && iterateColumn < boardParam[iterateRow].length && iterateColumn>=0) {
                        if(boardParam[iterateRow][iterateColumn] != null) {
                            if(boardParam[iterateRow][iterateColumn].getPieceColor()==moveColor) {
                                //piece of the same color blocks the way => no check possible
                                break;
                            }
                            //enemy piece found => add to possibleChecks list
                            else if(boardParam[iterateRow][iterateColumn].getPieceColor() != moveColor) {
                                //TODO this can be done more generic
                                if(boardParam[iterateRow][iterateColumn] == Piece.whitePawn ||boardParam[iterateRow][iterateColumn] == Piece.blackPawn){
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
        return possibleChecks;
    }

    /**
     * get the correct source of the given check from the possibleChecks arraylist
     * @param possibleChecks
     * @param kingPosition
     * @param boardParam
     * @return position from which the check is given; null if there is no check given
     */
    private Position getCheckSourcePosition(ArrayList<Position> possibleChecks, Position kingPosition, Piece[][] boardParam){
        for(Position p : possibleChecks) {
            if(boardParam[p.getRow()][p.getColumn()] != null) {
                //use private move constructor to prevent each move getting checked for validity which is not needed in this case. move only gets created to re-use correctDirection method
                if(correctDirection(new Move(boardParam[p.getRow()][p.getColumn()],p,kingPosition,false))) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Checks if the moveColor is checkmated
     * @return true if the movecolor is checkmated
     */
    public boolean isCheckMated(Piece king, Piece[][] boardParam){
         /*
        checkmate:  if in check == true
                    if king has no safe square it can go to
                        if check path cannot be blocked
                            if check giving piece cannot be captured
                                => Checkmate
     */

        /*
        check all possible king moves (except castling) and test if king would be inCheck after them
         */
        //get king enum value

        //get king position
        Position kingPosition = null;
        for(int i = 0 ; i< boardParam.length;i++){
            for(int j = 0; j<boardParam[i].length;j++){
                if(boardParam[i][j] == king){
                    kingPosition = Position.getPositionFromValue(i,j);
                }
            }
        }

        //if king is not in check, no checkmate testing needed
        if(inCheck(king.getPieceColor(),boardParam) == false){
            // TODO ELSE IF CHECK FOR STALEMATE?
            System.out.println("false!");
            return false;
        }

        //Piece[][] backupboard = Board.getBoardInstance().getBoard();
        //if king can escape checkmate by running away
        for(Pair p : kingDirection){
            Piece[][] simulationBoard = Board.getBoardInstance().cloneBoard(Board.getBoardInstance().getBoard());

            if(p.column == 2 || p.column == -2){
                continue;
            }
            int testRow = kingPosition.getRow() + p.row;
            int testColumn = kingPosition.getColumn() + p.column;


            if(testRow < 0 ||testRow >= boardParam.length ||testColumn < 0 || testColumn >= boardParam.length){
                continue;
            }




            Move simulateMove = new Move(king,kingPosition,Position.getPositionFromValue(testRow,testColumn),false);



            //piece can theoretically move there
            if(correctDirection(simulateMove)){


                Board.getBoardInstance().updateBoard(simulateMove,simulationBoard);


                //target square is not empty
                if(simulationBoard[simulateMove.targetPosition.getRow()][simulateMove.targetPosition.getColumn()] != null) {
                    //occupied by the same color
                    if(simulationBoard[simulateMove.targetPosition.getRow()][simulateMove.targetPosition.getColumn()].getPieceColor()==simulateMove.getPiece().getPieceColor()) {
                        //DO NOTHING THIS IS NOT A LEGAL MOVE

                    }
                    //occupied by the opponent color
                    else if (simulationBoard[simulateMove.targetPosition.getRow()][simulateMove.targetPosition.getColumn()].getPieceColor()!=simulateMove.getPiece().getPieceColor()){
                        //enemy piece blocking
                        System.out.println(simulationBoard[simulateMove.targetPosition.getRow()][simulateMove.targetPosition.getColumn()].getPieceColor()!=simulateMove.getColor());


                        if(inCheck(king.getPieceColor(),simulationBoard) == false){
                            //king kann zur targetPosition gehen ohne dass er dort gechecked wird, also kein checkmate
                            System.out.println("king kann aber nicht weglaufen!" + simulateMove.targetPosition + " " + king.getPieceColor() + " " + simulationBoard[Position.F8.getRow()][Position.F8.getColumn()].getPieceColor());
                            return false;
                        }
                    }
                }
                //square is empty
                else if(simulationBoard[simulateMove.targetPosition.getRow()][simulateMove.targetPosition.getColumn()] == null){
                    Board.getBoardInstance().updateBoard(simulateMove,simulationBoard);
                    if(inCheck(king.getPieceColor(),simulationBoard) == false){
                        //king kann weglaufen also kein checkmate
                        System.out.println("king kann aber nicht weglaufen!" + simulateMove.targetPosition);
                        //FIXME problem: this move has to be checked for validity, this is probably the error source (the move constructor above)

                        return false;
                    }
                }


            }

        }
        //knight checks? multiple checks at the same time (abzugsdoppelschach)?
        //if checkmate can be prevented by blocking the check
        // -> int compare checkgiving position, kingposition to get the squares between the check giver and the king
        //      if no piece from the checked king color can move to any of the positions between king and checkgiver then its checkmate (this includes capturing)

        //-> get source of the check(s?)

        ArrayList<Position> possibleChecks = getPossibleChecks(kingPosition,boardParam,king.getPieceColor());
        Position checkSource = getCheckSourcePosition(possibleChecks,kingPosition,boardParam);
        if(checkSource == null){

            System.out.println("this should never be possible because inCheck gets called above and there therefore has to be a checkSource" + possibleChecks.size());
        }

        int kingRow = kingPosition.getRow();
        int kingColumn = kingPosition.getColumn();

        int checkSourceRow = checkSource.getRow();
        int checkSourceColumn = checkSource.getColumn();

        //reihenfolge beachten und wenn bugs auftreten umdrehen FIXME
        int rowIncrement = Integer.compare(kingRow,checkSourceRow);
        int columnIncrement = Integer.compare(kingColumn,checkSourceColumn);

        int rowDifference = Math.abs(kingRow-checkSourceRow);
        int columnDifference = Math.abs(kingColumn-checkSourceColumn);

        //this should be 3 for knight checks
        int cumulatedDifference = Math.abs(rowDifference+columnDifference);
        if(cumulatedDifference == 3){
            //it is given by a knight and can only be stopped by capturing the knight
        }
        else{
            //blocking or capturing
            /*
             der diagonale entlang gehen bis zur source und für jedes feld prüfen ob es von einem movecolor piece erreicht werden kann
             woher bekomm ich alle pieces? über das ganze feld iterieren und die Pieces in einem array speichern?
             */

            ArrayList<Piece> moveColorPieces = new ArrayList<>();

            for(int i = 0; i< boardParam.length;i++){
                for(int j = 0 ; j<boardParam[i].length;j++){
                    if(boardParam[i][j].getPieceColor() == king.getPieceColor()){

                        int rowIterator = kingPosition.getRow() + rowIncrement;
                        int columnIterator = kingPosition.getColumn() + columnIncrement;
                        //it should be impossible for them to not be both true or both false
                        while(rowIterator != checkSourceRow && columnIterator != checkSourceColumn){

                            //moveColorPieces.add(boardParam[i][j]);
                            Piece p = boardParam[i][j];
                            //i can just try here if it can reach it??
                            //not king position! the targetposition
                            if(correctDirection(new Move(p,Position.getPositionFromValue(i,j),Position.getPositionFromValue(rowIterator,columnIterator),false))){
                                return false;
                            }

                            rowIterator+= rowIncrement;
                            columnIterator += columnIncrement;
                        }



                    }
                }
            }
        }


        return true;
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
        //start increment 1 before so it doesnt detect the currentPosition as foundation.Piece that is blocking the path:
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


    /**
     * Checks for an incoming move if the foundation.Position can be legally reached
     * @param move The move that is to be checked
     * @return returns true if the piece can theoretically move to the targeted position
     */
    public boolean correctDirection(Move move) {
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
        else if(p == Piece.whiteQueen || p== Piece.blackQueen){
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
     * This function Takes the currentPosition, adds all possible direction changes to it and checks if the targetPosition equals a foundation.Position that gets calculated when adding the move coordinates to the currentPosition
     *
     * @param list List of Pairs that represent all the possible moves the piece can theoretically execute
     * @param currentPosition The current foundation.Position of the foundation.Piece
     * @param targetPosition The in the move targeted foundation.Position of the move
     * @return Returns boolean value stating if the foundation.Piece can theoretically move to the given foundation.Position; True if it can move there; false if it cannot
     */
    private boolean positionMatches(ArrayList<Pair> list, Position currentPosition, Position targetPosition) {
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

    /**
     * Checks if a pawn move is valid according to the rules of chess
     * @param move the move that is to be checked for validity
     * @param pawn either whitePawn or blackPawn according to the move
     * @return true if the move is valid; if it is not valid analysis.IllegalMoveException gets thrown
     * @throws IllegalMoveException
     */
    private boolean pawnMoveValidity(Move move, Piece pawn) throws IllegalMoveException {
        Board boardInstance = Board.getBoardInstance();
        Piece[][] board = boardInstance.getBoard();
        int currentRow = move.getCurrentPosition().getRow();
        int currentColumn = move.getCurrentPosition().getColumn();
        int targetRow = move.getTargetPosition().getRow();
        int targetColumn = move.getTargetPosition().getColumn();

        int startingRow = -1;
        Piece enemyPawn = null;
        if(pawn.getPieceColor() == PlayerColor.BLACK){
            startingRow = 1;
            enemyPawn = Piece.whitePawn;
        }
        else if(pawn.getPieceColor() == PlayerColor.WHITE){
            startingRow = 6;
            enemyPawn = Piece.blackPawn;
        }

        boolean enpassant = false;
        if(currentColumn == targetColumn && board[targetRow][targetColumn] == enemyPawn) throw new IllegalMoveException("Path blocked by enemy pawn. Pawn cannot capture that way");
        //first move 2 squares
        if(Math.abs(currentRow - targetRow) == 2 && currentRow != startingRow) throw new IllegalMoveException("Pawn can only move 2 squares on the first move");

        //target square is empty
        if(board[targetRow][targetColumn] == null) {
            //check en passant
            ArrayList<Move> playedMoves = boardInstance.getPlayedMoves();
            if(playedMoves.size()>0){
                Move previouslyPlayed = playedMoves.get(playedMoves.size()-1);
                //moved piece has to be enemy pawn
                if(previouslyPlayed.piece == enemyPawn) {
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
            else if(Math.abs(currentRow-targetRow) == 1 && Math.abs(currentColumn - targetColumn) == 1 && board[targetRow][targetColumn].getPieceColor() == pawn.getPieceColor()){
                throw new IllegalMoveException("The targeted square is filled with a foundation.Piece from the same color");
            }
        }
        return true;
    }

    /**
     * This method checks the validity of a king move, including casling
     * @param move the king move to be checked
     * @param king either whiteKing or blackKing enum values
     * @return true if the king move is valid
     * @throws IllegalMoveException gets thrown if the move is illegal
     */
    private boolean kingMoveValidity(Move move, Piece king) throws IllegalMoveException {


        //king cannot LEAVE or CROSS OVER or FINISH on a square that is checked
        // LEAVE => this function
        // cross over => simulateKingMove
        // FINISH => isValid at the End
        Board boardInstance = Board.getBoardInstance();
        Piece[][] board = boardInstance.getBoard();
        int currentRow = move.getCurrentPosition().getRow();
        int currentColumn = move.getCurrentPosition().getColumn();
        int targetRow = move.getTargetPosition().getRow();
        int targetColumn = move.getTargetPosition().getColumn();

        boolean blackKingHasMoved = false;
        boolean whiteKingHasMoved = false;
        boolean whiteArookHasMoved = false;
        boolean whiteHrookHasMoved = false;
        boolean blackArookHasMoved = false;
        boolean blackHrookHasMoved = false;

        boolean whiteShortCastle = false;
        boolean whiteLongCastle = false;
        boolean blackShortCastle = false;
        boolean blackLongCastle = false;

        Position crossingSquare = null;
        Position rookPosition = null;
        Piece rook = null;

        boolean piecesHaveMoved = false;

        ArrayList<Move> playedMoves = boardInstance.getPlayedMoves();
        //castling move
        if(currentRow-targetRow == 0 && Math.abs(currentColumn-targetColumn) == 2){
            //currently in check: LEAVE
            if(inCheck(move.color,board)){
                throw new IllegalMoveException("King cannot castle when given a check");
            }
            //check if king or rook were moved
            for(Move m : playedMoves){
                if(m.getPiece().equals(Piece.whiteKing)) whiteKingHasMoved = true;
                else if(m.getPiece().equals(Piece.blackKing)) blackKingHasMoved = true;
                else if(m.getPiece().equals(Piece.whiteRook) && m.getCurrentPosition() == Position.A1)whiteArookHasMoved = true;
                else if(m.getPiece().equals(Piece.whiteRook) && m.getCurrentPosition() == Position.H1)whiteHrookHasMoved = true;
                else if(m.getPiece().equals(Piece.blackRook) && m.getCurrentPosition() == Position.A8)blackArookHasMoved = true;
                else if(m.getPiece().equals(Piece.blackRook) && m.getCurrentPosition() == Position.H8)blackHrookHasMoved = true;
            }
            if(move.targetPosition == Position.G1){
                whiteShortCastle = true;
                crossingSquare = Position.F1;
                rookPosition = Position.H1;
                rook = Piece.whiteRook;
            }
            else if(move.targetPosition == Position.C1){
                whiteLongCastle = true;
                crossingSquare = Position.D1;
                rookPosition = Position.A1;
                rook = Piece.whiteRook;
            }
            else if(move.targetPosition == Position.G8){
                blackShortCastle = true;
                crossingSquare = Position.F8;
                rookPosition = Position.H8;
                rook = Piece.blackRook;
            }
            else if(move.targetPosition == Position.C8){
                blackLongCastle = true;
                crossingSquare = Position.D8;
                rookPosition = Position.A8;
                rook = Piece.blackRook;
            }

            if(whiteShortCastle && (whiteKingHasMoved || whiteHrookHasMoved)) piecesHaveMoved = true;
            if(whiteLongCastle && (whiteKingHasMoved || whiteArookHasMoved)) piecesHaveMoved = true;
            if(blackShortCastle && (blackKingHasMoved || blackHrookHasMoved)) piecesHaveMoved = true;
            if(blackLongCastle && (blackKingHasMoved || blackArookHasMoved)) piecesHaveMoved = true;
            if(piecesHaveMoved){
                throw new IllegalMoveException("Pieces have moved and therefore cannot castle");
            }
            else{
                return simulateKingMove(king,rook,move,crossingSquare,rookPosition);
            }
        }
        return true;
    }

    /**
     * Simulates a king move and determines if it is legal or not
     * @param king either blackking or whiteking
     * @param rook either blackrook or whiterook
     * @param move the move that is to be checked
     * @param crossingSquare the square that gets crossed when castling
     * @param rookPosition the position of the rook that is involved when castling
     * @return true if move is possible
     * @throws IllegalMoveException
     */
    private boolean simulateKingMove(Piece king, Piece rook , Move move, Position crossingSquare, Position rookPosition) throws IllegalMoveException {
        Board boardInstance = Board.getBoardInstance();
        Piece[][] board = boardInstance.getBoard();
        Piece[][] boardSimulation = boardInstance.cloneBoard(board);

        Move moveToCrossingSquare = new Move(king,move.currentPosition,crossingSquare,false);
        boardInstance.updateBoard(moveToCrossingSquare, boardSimulation);
        //cross over square that is checked by enemy piece:
        if(inCheck(king.getPieceColor(),boardSimulation)) {
            throw new IllegalMoveException("King cannot cross a square that is dangered. F1 dangered");
        }
        Piece[][] boardSimulationDefault = boardInstance.cloneBoard(board);
        boardInstance.updateBoard(move, boardSimulationDefault);
        if(inCheck(king.getPieceColor(),boardSimulationDefault)) {
            throw new IllegalMoveException("King is in check after this move");
        }
        // is this correct?
        boardInstance.removePiece(rookPosition, Board.getBoardInstance().getBoard());
        boardInstance.placePiece(crossingSquare, Board.getBoardInstance().getBoard(),rook);
        return true;
    }


    static class Pair{
        public int row;
        public int column;
        public Pair(int row,int column) {
            this.row = row;
            this.column = column;
        }
    }
}
