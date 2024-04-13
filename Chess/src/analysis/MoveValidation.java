package analysis;

import java.util.ArrayList;

import gamefoundation.*;

import static gamefoundation.Piece.*;

public class MoveValidation {

    public static ArrayList <Pair> blackPawnDirection = new ArrayList<>();
    public static ArrayList <Pair> whitePawnDirection = new ArrayList<>();
    public static ArrayList <Pair> bishopDirection = new ArrayList<>();
    public static ArrayList <Pair> kingDirection = new ArrayList<>();
    public static ArrayList <Pair> knightDirection = new ArrayList<>();
    public static ArrayList <Pair> rookDirection = new ArrayList<>();
    public static ArrayList <Pair> queenDirection = new ArrayList<>();

    //TODO better solution for this
    //Fills all the direction ArrayLists with all possible Moves for each Piece
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



    /**
     * Confirms if a move is legal and can be played be testing for the following cases:
     * Check if the correct color is making a move
     * Check if piece is on the named starting square
     * Check if piece can theoretically move that way
     * Ceck if targetsquare is empty or has enemy piece on it
     * Check for pawn and king special cases
     * Check if path is blocked between currentposition and targetposition
     * Check if king would be in check after this move
     * @param move The move that is to be checked for validity
     * @throws IllegalMoveException is thrown if any of the severals checks fail with a description on why the move is illegal
     */
    public boolean isValid(Move move) {
        Piece p = move.getPiece();
        Board boardInstance = Board.getBoardInstance();
        Piece[][] board = boardInstance.getBoard();
        int currentRow = move.getCurrentPosition().getRow();
        int currentColumn = move.getCurrentPosition().getColumn();
        int targetRow = move.getTargetPosition().getRow();
        int targetColumn = move.getTargetPosition().getColumn();

        //Check if the correct color is making a move
        ArrayList<Move> moveList = boardInstance.getPlayedMoves();
        if(!moveList.isEmpty()) {
            PlayerColor lastMoveColor = moveList.get(moveList.size() - 1).getColor();
            if(lastMoveColor == move.getColor()) {
                System.out.println("Expected move from different color.\nLast move played by " +
                        moveList.get(moveList.size() - 1).getColor() + "\nCurrent Move played by: "+move.getColor());
                return false;
//                throw new IllegalMoveException("Expected move from different color.\nLast move played by " +
//                        moveList.get(moveList.size() - 1).getColor() + "\nCurrent Move played by: "+move.getColor());
            }
        }
        else {
            if(move.getColor() == PlayerColor.BLACK) {
                System.out.println("White starts the game");
                return false;
                //throw new IllegalMoveException("White starts the game");
            }
        }

        //Check if Piece is on the named square
        if(p!= board[currentRow][currentColumn]) {
            System.out.println("Piece is not on the named starting piece.\nPiece current Position: "
                    + move.getCurrentPosition() + "\nPiece on the supposed Position: " + board[currentRow][currentColumn]);
            return false;
//            throw new IllegalMoveException("Piece is not on the named starting piece.\nPiece current Position: "
//                    + move.getCurrentPosition() + "\nPiece on the supposed Position: " + board[currentRow][currentColumn]);
        }

        //Check if Piece can move that way
        if(correctDirection(move) == false) {
            System.out.println("This Piece cannot move that way by the rules of chess");
            return false;
//            throw new IllegalMoveException("This Piece cannot move that way by the rules of chess");
        }

        //check if Tile is empty or if enemy has piece on it
        if(board[targetRow][targetColumn] != null) {
            if(board[targetRow][targetColumn].getPieceColor()==move.getColor()) {
                System.out.println("Square is blocked by Piece of the same color");
                return false;
//                throw new IllegalMoveException("Square is blocked by Piece of the same color");
            }
        }
        //check for capture , pawn blockage, en passant, first move 2 squares
        if(p == Piece.blackPawn || p == whitePawn) {
            pawnMoveValidity(move,p);
        }
        else if(p== Piece.blackKnight || p == Piece.whiteKnight){
            //no checks neccessary
        }
        else if(p== Piece.whiteKing  || p== Piece.blackKing) {
            if(kingMoveValidity(move,p) == false){
                System.out.println("Castling illegal in this case");
                return false;
//                throw new IllegalMoveException("Castling illegal");
            }
        }
        //bishop queen rook
        else {
            if(checkPathBlocked(move.getCurrentPosition(), move.getTargetPosition(), move.getPiece(),board) == false) {
                System.out.println("Path is blocked");
                return false;
//                throw new IllegalMoveException("Path is blocked");
            }
        }

        //check if King would be in check after move;
        Piece[][] boardSimulation = boardInstance.cloneBoard(board);
        boardInstance.updateBoard(move, boardSimulation);
        if(inCheck(move.getColor(),boardSimulation) == true) {
            System.out.println("King is in check after this move: " + move);
            return false;
//            throw new IllegalMoveException("King is in check after this move: " + move);
        }
        return true;
    }

    /**
     * This method analyzes a given board and examines if the movecolor king is currently in check. Uses the functions getPossibleChecks and getCheckSourcePosition.
     * @param moveColor the playercolor that is making the move
     * @param boardParam the board which has to be examined
     * @return boolean true if the moveColor-king is currently in check
     **/
    public boolean inCheck(PlayerColor moveColor, Piece[][] boardParam) {
        /*
        determines king position on the board
        test for knight checks
        iterate over possibleChecks list obtained by external function and determine if the piece can theoreticall move to the king using external function
         */

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
        for(int i = 0; i< boardParam.length;i++){
            for(int j = 0; j<boardParam[i].length;j++){
                if(boardParam[i][j] == king) {
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
        //determine squares that need to be checked => either enemy piece or empty square through which a distant piece can give a check
       ArrayList<Position> possibleChecks = getPossibleChecks(kingPosition,boardParam,moveColor);
        if(possibleChecks.size() >0){
            Position p = getCheckSourcePosition(possibleChecks,kingPosition,boardParam);
            if(p!=null){
                return true;
            }
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
        /*
            iterate over squares that touch the king
                enemy piece next to king
                    add to possibleChecks
                empty square next to king
                    iterate from kingposition in the direction of empty square until end of board, own piece or enemy piece is hit
                        enemy piece => add to possibleChecks
            kingposition + knightdirections = enemyknight => add to possibleChecks
         */
        ArrayList<Position> possibleChecks = new ArrayList<>();
        for(Pair p : kingDirection) {
            // castling moves are not allowed, only the squares the king can move to (right next to him) are relevant in this function
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
                    //one iteration so the current king position does not get checked
                    iterateRow += rowIncrement;
                    int iterateColumn = kingPosition.getColumn();
                    iterateColumn += columnIncrement;
                    //Iterate from current king position in the direction of the empty square until an own piece, enemy piece or the end of the board is reached
                    while(iterateRow>=0 && iterateColumn >= 0 && iterateRow < boardParam.length && iterateColumn < boardParam[iterateRow].length ) {
                        if(boardParam[iterateRow][iterateColumn] != null) {
                            if(boardParam[iterateRow][iterateColumn].getPieceColor()==moveColor) {
                                //piece of the same color blocks the way => no check possible
                                break;
                            }
                            //enemy piece found => add to possibleChecks list
                            else if(boardParam[iterateRow][iterateColumn].getPieceColor() != moveColor) {
                                //TODO this can be done more generic
                                if(boardParam[iterateRow][iterateColumn] == whitePawn ||boardParam[iterateRow][iterateColumn] == Piece.blackPawn){
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
        Piece knight = null;
        if(moveColor == PlayerColor.WHITE){
            knight = blackKnight;
        }
        else if(moveColor == PlayerColor.BLACK){
            knight = whiteKnight;
        }
        for(Pair p : knightDirection) {
            int possibleKnightCheckRow = kingPosition.getRow() + p.row;
            int possibleKnightCheckColumn = kingPosition.getColumn() + p.column;
            //prevent out of bounds
            if(possibleKnightCheckRow < boardParam.length && possibleKnightCheckRow >= 0 && possibleKnightCheckColumn < boardParam.length && possibleKnightCheckColumn >= 0){
                if(boardParam[possibleKnightCheckRow][possibleKnightCheckColumn] == knight){
                    possibleChecks.add(Position.getPositionFromValue(possibleKnightCheckRow,possibleKnightCheckColumn));
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
        /*
            iterates over possiblechecks list
                test if the pieces could "travel" to the kingposition and therefore capture him
                    true => return the position
        */
        for(Position p : possibleChecks) {
            if(boardParam[p.getRow()][p.getColumn()] != null) {
                //use private move constructor to prevent each move getting checked for validity which is not needed in this case. move only gets created to re-use correctDirection method
                Move simulationMove = new Move(boardParam[p.getRow()][p.getColumn()],p,kingPosition,false);
                if(correctDirection(simulationMove)) {
                    Piece[][] simulationBoard = Board.getBoardInstance().cloneBoard(boardParam);
                    Board.getBoardInstance().updateBoard(simulationMove,simulationBoard);
                    //path not blocked
                    if(checkPathBlocked(simulationMove.getCurrentPosition(), simulationMove.getTargetPosition(), simulationMove.getPiece(),simulationBoard) == true){
                        return p;
                    }

                }
            }
        }
        return null;
    }

    /**
     * Checks if the king is currently in a checkmate position on the given board
     * @param moveColor the color that is to be tested if it is currently in a checkmated position
     * @param boardParam the board containing the current chess position that is to be checked
     * @return true if the king is currently checkmated on the given board
     */
    public boolean isCheckMated(PlayerColor moveColor, Piece[][] boardParam){
        /**
            if king in check
            if king can escape checkmate by running away
            if checkmate can be prevented by blocking the check
            test for possible moves that can capture the checksource
            test for blocking check
         */
        Piece king = null;
        if(moveColor == PlayerColor.WHITE) king = whiteKing;
        else if(moveColor == PlayerColor.BLACK) king = blackKing;
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
            return false;
        }
        //if king can escape checkmate by running away
        for(Pair p : kingDirection){
            Piece[][] simulationBoard = Board.getBoardInstance().cloneBoard(Board.getBoardInstance().getBoard());

            //king can only move 2 squares when castling
            if(p.column == 2 || p.column == -2){
                continue;
            }
            int testRow = kingPosition.getRow() + p.row;
            int testColumn = kingPosition.getColumn() + p.column;

            //prevent out of bounds
            if(testRow < 0 ||testRow >= boardParam.length ||testColumn < 0 || testColumn >= boardParam.length){
                continue;
            }

            Move simulateMove = new Move(king,kingPosition,Position.getPositionFromValue(testRow,testColumn),false);
            //piece can theoretically move there
            if(correctDirection(simulateMove)){
                //target square is not empty
                if(simulationBoard[simulateMove.getTargetPosition().getRow()][simulateMove.getTargetPosition().getColumn()] != null) {
                    if(simulationBoard[simulateMove.getTargetPosition().getRow()][simulateMove.getTargetPosition().getColumn()].getPieceColor()==simulateMove.getPiece().getPieceColor()) {
                        //DO NOTHING THIS IS NOT A LEGAL MOVE - occupied by the same color
                        continue;
                    }
                    //occupied by the opponent color
                    else if (simulationBoard[simulateMove.getTargetPosition().getRow()][simulateMove.getTargetPosition().getColumn()].getPieceColor()!=simulateMove.getPiece().getPieceColor()){
                        //enemy piece blocking

                        Board.getBoardInstance().updateBoard(simulateMove,simulationBoard);
                        if(inCheck(king.getPieceColor(),simulationBoard) == false){
                            //king kann zur targetPosition gehen ohne dass er dort gechecked wird, also kein checkmate
                            return false;
                        }
                    }
                }
                //square is empty
                else if(simulationBoard[simulateMove.getTargetPosition().getRow()][simulateMove.getTargetPosition().getColumn()] == null){
                    Board.getBoardInstance().updateBoard(simulateMove,simulationBoard);
                    if(inCheck(king.getPieceColor(),simulationBoard) == false){
                        //king has an empty square it can reach and not be checked on it => no checkmate
                        return false;
                    }
                }


            }

        }
        //if checkmate can be prevented by blocking the check
        //if no piece from the checked king color can move to any of the positions between king and checkgiver then its checkmate (this includes capturing)
        ArrayList<Position> possibleChecks = getPossibleChecks(kingPosition,boardParam,king.getPieceColor());
        Position checkSource = getCheckSourcePosition(possibleChecks,kingPosition,boardParam);
        int kingRow = kingPosition.getRow();
        int kingColumn = kingPosition.getColumn();
        int checkSourceRow = checkSource.getRow();
        int checkSourceColumn = checkSource.getColumn();
        int rowIncrement = Integer.compare(checkSourceRow,kingRow);
        int columnIncrement = Integer.compare(checkSourceColumn,kingColumn);
        //TODO this can be done better so it is also checked inside the while loop later on.
        //checks for captures
            for(int k = 0; k< boardParam.length;k++) {
                for (int l = 0; l < boardParam[k].length; l++) {
                    Piece p = boardParam[k][l];
                    if(p != null && p.getPieceColor() == king.getPieceColor() && p != king){

                        Move simulationMove = new Move(p,Position.getPositionFromValue(k,l),checkSource,false);
                        //System.out.println(simulationMove);
                        if(correctDirection(simulationMove)){
                            Piece[][] simulationBoard = Board.getBoardInstance().cloneBoard(Board.getBoardInstance().getBoard());
                            if(checkPathBlocked(Position.getPositionFromValue(k,l),checkSource,p,simulationBoard) == true){
                                if(inCheck(p.getPieceColor(),simulationBoard)){
                                    if(p == Piece.whitePawn|| p == blackPawn){
                                        int rowDiff = Math.abs(k-checkSourceRow);
                                        int columnDiff = Math.abs(l-checkSourceColumn);
                                        //capturing possible and therefore not checkmate TODO this has to be properly tested
                                        if(rowDiff == 1 && columnDiff == 1){
                                            //check if it would be check after the capturing
                                            Board.getBoardInstance().updateBoard(simulationMove,simulationBoard);
                                            if(inCheck(simulationMove.getPiece().getPieceColor(),simulationBoard) == false){
                                                return false;
                                            }
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        //search for pieces that can block the check
        for(int i = 0; i< boardParam.length;i++){
            for(int j = 0 ; j<boardParam[i].length;j++){
                Piece p = boardParam[i][j];
                //we only need to iterate over our own pieces and can skip enemy and null squares
                if(p == null || p.getPieceColor() != king.getPieceColor()){
                    continue;
                }
                //same color as the king getting checked -> test if it can stop the check somehow
                if(p.getPieceColor() == king.getPieceColor()){
                    int rowIterator = kingPosition.getRow() + rowIncrement;
                    int columnIterator = kingPosition.getColumn() + columnIncrement;
                    //iterate over all the squares between the king and the checksource
                    while(rowIterator != checkSourceRow || columnIterator != checkSourceColumn){
                        //if its a pawn it can only move diagonal if it captures a piece
                        if(p == Piece.whitePawn|| p == blackPawn){
                            int rowDiff = Math.abs(i - rowIterator);
                            int columnDiff = Math.abs(j - columnIterator);
                            //diagonal moves are not allowed. diagonal moves are only possible when capturing and if I try to block the check there is no piece between it
                            if(rowDiff == 1 && columnDiff == 1){
                                rowIterator+= rowIncrement;
                                columnIterator += columnIncrement;
                                continue;
                            }
                            else{
                                //check if move would be valid
                                Move toCheck = new Move(p,Position.getPositionFromValue(i,j),Position.getPositionFromValue(rowIterator,columnIterator),false);
                                Piece[][] simulationBoard = Board.getBoardInstance().cloneBoard(Board.getBoardInstance().getBoard());
                                if(correctDirection(toCheck)){
                                    if(checkPathBlocked(toCheck.getCurrentPosition(), toCheck.getTargetPosition(), toCheck.getPiece(),simulationBoard)){
                                        Board.getBoardInstance().updateBoard(toCheck,simulationBoard);
                                        if(inCheck(king.getPieceColor(),simulationBoard) == false){
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                        else if( p != king){
                            Move toCheck = new Move(p,Position.getPositionFromValue(i,j),Position.getPositionFromValue(rowIterator,columnIterator),false);
                            //if it can reach the position theoretically it can block the check and therefore it is not checkmated
                            Piece[][] simulationBoard = Board.getBoardInstance().cloneBoard(Board.getBoardInstance().getBoard());
                            if(correctDirection(toCheck)){
                                if(checkPathBlocked(toCheck.getCurrentPosition(), toCheck.getTargetPosition(), toCheck.getPiece(),simulationBoard)){
                                    Board.getBoardInstance().updateBoard(toCheck,simulationBoard);
                                    if(inCheck(king.getPieceColor(),simulationBoard) == false){
                                        //System.out.println(toCheck + "This move prevents the checkmate");
                                        return false;
                                    }
                                }
                            }
                        }
                        rowIterator+= rowIncrement;
                        columnIterator += columnIncrement;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Tests if the playerColor of the given Board is currently in a Stalemate positionn
     * @param playerColor the color to be checked for stalemate
     * @param boardParam the board to be checked for stalemate
     * @return true if the board is currently in a stalemate position
     */
    public boolean isStaleMated(PlayerColor playerColor, Piece[][] boardParam){
        /*
            iterate over all pieces
                for each piece iterate over all the possible directions and test if the move would be legal
         */
        for(int i = 0; i<boardParam.length;i++){
            for(int j = 0; j<boardParam[i].length;j++){
                if(boardParam[i][j] != null && boardParam[i][j].getPieceColor() == playerColor){
                    Piece p = boardParam[i][j];
                    if(hasPossibleMoves(p,Position.getPositionFromValue(i,j),boardParam) == true){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Tests if the piece has a legal chess move
     * @param piece the piece to be testes
     * @param currentPosition the current position of the piece
     * @param boardParam the board to be tested
     * @return true if the piece has a legal move
     */
    private boolean hasPossibleMoves(Piece piece, Position currentPosition,Piece[][] boardParam){
        /*
            get the correct directionarray for the according piece
            iterate over all possible pairs of arraylist and add them to the position and determine if they are legal
         */
        ArrayList<Pair> direction = null;
        if(piece == whitePawn){
            direction = whitePawnDirection;
        }
        else if(piece == blackPawn){
            direction = blackPawnDirection;
        }
        else if(piece == whiteBishop ||piece == blackBishop){
            direction = bishopDirection;
        }
        else if(piece==blackKnight ||piece == whiteKnight){
            direction = knightDirection;
        }
        else if(piece == blackRook || piece == whiteRook){
            direction = rookDirection;
        }
        else if(piece == whiteKing || piece == blackKing){
            direction = kingDirection;
        }
        else if(piece == whiteQueen || piece == blackQueen){
            direction = queenDirection;
        }
        //iterate over all possible pairs and add them to the position and determine if they are legal
        for(Pair p : direction){
            int rowAfterMove = currentPosition.getRow() + p.row;
            int columnAfterMove = currentPosition.getColumn() + p.column;
            //prevent out of bounds
            if(rowAfterMove >= boardParam.length ||rowAfterMove<0 || columnAfterMove >= boardParam.length ||columnAfterMove<0){
                continue;
            }
            Move simulationMove = new Move(piece,currentPosition,Position.getPositionFromValue(rowAfterMove,columnAfterMove),false);
            //prevents moves to be checked that capture own pieces or are just illegal in general. This caused the king to get captured and threw a nullpointer when searching for the kingposition
            if(boardParam[simulationMove.getTargetPosition().getRow()][simulationMove.getTargetPosition().getColumn()] != null && boardParam[simulationMove.getTargetPosition().getRow()][simulationMove.getTargetPosition().getColumn()].getPieceColor() == simulationMove.getPiece().getPieceColor()){
                continue;
            }
            Piece[][] simulationBoard = Board.getBoardInstance().cloneBoard(boardParam);
            /* if correct direction
                if not path blocked
                    if not in check afterwards */
            if(correctDirection(simulationMove)){
                if(checkPathBlocked(currentPosition, simulationMove.getTargetPosition(), simulationMove.getPiece(),simulationBoard) == true){
                    Board.updateBoard(simulationMove,simulationBoard);
                    if(inCheck(simulationMove.getPiece().getPieceColor(), simulationBoard) == false){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //FIXME switching parameters of this function to Move and Board would probably be better
    /**
     * Checks if the path between the current location and the target location is blocked by a piece and the move therefore illegal.
     * Calculates the difference between current and targetposition and checks each position by using the Integer.compare(a,b) function to increment with 1 or -1 until the position is reached.
     * @param piece The piece that is moving
     * @param targetPosition the targeted position
     * @param currentPosition the current position from which the piece wants to move away from
     * @return boolean false if path is blocked; boolean true if path is not blocked
     */
    private boolean checkPathBlocked(Position currentPosition, Position targetPosition, Piece piece,Piece[][] boardParam) {
        int currentRow = currentPosition.getRow();
        int targetRow = targetPosition.getRow();
        int currentColumn = currentPosition.getColumn();
        int targetColumn = targetPosition.getColumn();
        int rowIncrement = Integer.compare(targetRow, currentRow);
        int columnIncrement = Integer.compare(targetColumn, currentColumn);
        //start increment 1 before so it doesnt detect the currentPosition as Piece that is blocking the path:
        currentRow += rowIncrement;
        currentColumn += columnIncrement;
        if(piece == Piece.blackBishop || piece == Piece.whiteBishop || piece==Piece.whiteRook || piece==Piece.blackRook || piece==Piece.blackQueen || piece==Piece.whiteQueen ) {
            while(currentRow!= targetRow || currentColumn != targetColumn) {
                if(boardParam[currentRow][currentColumn]!=null) {
                    return false;
                }
                currentRow += rowIncrement;
                currentColumn += columnIncrement;
            }
        }
        return true;
    }


    /**
     * Checks for an incoming move if the Position can be legally reached
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
        else if(p == whitePawn){
            return positionMatches(whitePawnDirection,currentPosition,targetPosition);
        }
        else{
            return false;
        }
    }

    /**
     * This function Takes the currentPosition, adds all possible direction changes to it and checks if the targetPosition equals a Position that gets calculated when adding the move coordinates to the currentPosition
     * @param list List of Pairs that represent all the possible moves the piece can theoretically execute
     * @param currentPosition The current Position of the Piece
     * @param targetPosition The in the move targeted Position of the move
     * @return Returns boolean value stating if the Piece can theoretically move to the given Position; True if it can move there; false if it cannot
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
    private boolean pawnMoveValidity(Move move, Piece pawn) {
        /*
            pawn blockage by enemy pawn
            tests for the following cases:
            first pawn move 2 squares
            en passant
            no diagonal pawn moves without captures
            diagonal pawn captures
         */
        Board boardInstance = Board.getBoardInstance();
        Piece[][] board = boardInstance.getBoard();
        int currentRow = move.getCurrentPosition().getRow();
        int currentColumn = move.getCurrentPosition().getColumn();
        int targetRow = move.getTargetPosition().getRow();
        int targetColumn = move.getTargetPosition().getColumn();
        int startingRow = -1;
        boolean enpassant = false;
        Piece enemyPawn = null;
        if(pawn.getPieceColor() == PlayerColor.BLACK){
            startingRow = 1;
            enemyPawn = whitePawn;
        }
        else if(pawn.getPieceColor() == PlayerColor.WHITE){
            startingRow = 6;
            enemyPawn = Piece.blackPawn;
        }
        //enemy pawn blocks path
//       if(currentColumn == targetColumn && board[targetRow][targetColumn] == enemyPawn) throw new IllegalMoveException("Path blocked by enemy pawn. Pawn cannot capture that way");
        if(currentColumn == targetColumn && board[targetRow][targetColumn] == enemyPawn){
            System.out.println("Path blocked by enemy pawn. Pawn cannot capture that way");
            return false;
        }

        //first move 2 squares
//        if(Math.abs(currentRow - targetRow) == 2 && currentRow != startingRow) throw new IllegalMoveException("Pawn can only move 2 squares on the first move");
        if(Math.abs(currentRow - targetRow) == 2 && currentRow != startingRow){
            System.out.println("Pawn can only move 2 squares on the first move");
            return false;
        }
        //target square is empty
        if(board[targetRow][targetColumn] == null) {
            //check en passant
            ArrayList<Move> playedMoves = boardInstance.getPlayedMoves();
            if(playedMoves.size()>0){
                Move previouslyPlayed = playedMoves.get(playedMoves.size()-1);
                //moved piece has to be enemy pawn
                if(previouslyPlayed.getPiece() == enemyPawn) {
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
                        System.out.println("en-passant is not possible here");
                        return false;
//                        throw new IllegalMoveException("en-passant is not possible here / ");
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
                    System.out.println("moving to empty diagonal square as pawn without en passant is illegal");
                    return false;
//                    throw new IllegalMoveException("moving to empty diagonal square as pawn without en passant is illegal");
                }
            }
            //targetSquare is not empty and occpuied by same colored pawn
            else if(Math.abs(currentRow-targetRow) == 1 && Math.abs(currentColumn - targetColumn) == 1 && board[targetRow][targetColumn].getPieceColor() == pawn.getPieceColor()){
                System.out.println("The targeted square is filled with a Piece from the same color");
                return false;
//                throw new IllegalMoveException("The targeted square is filled with a Piece from the same color");
            }
        }
        return true;
    }

    /**
     * checks the validity of a king move, including castling
     * @param move the king move to be checked
     * @param king either whiteKing or blackKing enum values
     * @return true if the king move is valid
     * @throws IllegalMoveException gets thrown if the move is illegal
     */
    private boolean kingMoveValidity(Move move, Piece king)  {
        //test if castling is legal => else simulateKingMove(king,rook,move,crossingSquare,rookPosition);
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
            if(inCheck(move.getColor(),board)){
                System.out.println("King cannot castle when given a check");
                return false;
//                throw new IllegalMoveException("King cannot castle when given a check");
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
            if(move.getTargetPosition() == Position.G1){
                whiteShortCastle = true;
                crossingSquare = Position.F1;
                rookPosition = Position.H1;
                rook = Piece.whiteRook;
            }
            else if(move.getTargetPosition() == Position.C1){
                whiteLongCastle = true;
                crossingSquare = Position.D1;
                rookPosition = Position.A1;
                rook = Piece.whiteRook;
            }
            else if(move.getTargetPosition() == Position.G8){
                blackShortCastle = true;
                crossingSquare = Position.F8;
                rookPosition = Position.H8;
                rook = Piece.blackRook;
            }
            else if(move.getTargetPosition() == Position.C8){
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
                System.out.println("Pieces have moved and therefore cannot castle");
                return false;
//                throw new IllegalMoveException("Pieces have moved and therefore cannot castle");
            }
            else{
                return simulateKingMove(king,rook,move,crossingSquare,rookPosition);
            }
        }
        return true;
    }
    //TODO this and kingMoveValidity could be merged?
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
    private boolean simulateKingMove(Piece king, Piece rook , Move move, Position crossingSquare, Position rookPosition) {
        Board boardInstance = Board.getBoardInstance();
        Piece[][] board = boardInstance.getBoard();
        Piece[][] boardSimulation = boardInstance.cloneBoard(board);
        Move moveToCrossingSquare = new Move(king, move.getCurrentPosition(),crossingSquare,false);
        boardInstance.updateBoard(moveToCrossingSquare, boardSimulation);
        //cross over square that is checked by enemy piece:
        if(inCheck(king.getPieceColor(),boardSimulation)) {
            System.out.println("King cannot cross a square that is dangered."+ crossingSquare +" dangered");
            return false;
//            throw new IllegalMoveException("King cannot cross a square that is dangered. F1 dangered");
        }
        Piece[][] boardSimulationDefault = boardInstance.cloneBoard(board);
        boardInstance.updateBoard(move, boardSimulationDefault);
        if(inCheck(king.getPieceColor(),boardSimulationDefault)) {
            System.out.println("King is in check after this move");
            return false;
//            throw new IllegalMoveException("King is in check after this move");
        }
        boardInstance.removePiece(rookPosition, Board.getBoardInstance().getBoard());
        boardInstance.placePiece(crossingSquare, Board.getBoardInstance().getBoard(),rook);
        return true;
    }

    /**
     * checks if the given move includes a pawn prmotion move
     * @param move the move to be tested
     * @return true if a pawn is promoted in this move
     */
    public boolean pawnPromotes(Move move){
        // only pawn moves relevant
        if(move.getPiece() != whitePawn && move.getPiece() != blackPawn){
            return false;
        }
        //pawn reaches baseline => promotion
        if(move.getColor() == PlayerColor.WHITE){
            if(move.getTargetPosition().getRow() == 0){
                return true;
            }
        }
        else if(move.getColor() == PlayerColor.BLACK){
            if(move.getTargetPosition().getRow() == 7){
                return true;
            }
        }
        return false;
    }

    /**
     * checks if there is still sufficient material on the board to deliver a checkmate on any side
     * @param boardParam the board to be checked
     * @return true if there is still enough material on the board
     */
    public boolean sufficientMaterialOnBoard(Piece[][] boardParam){
        /*
        insufficient material: king alone || king + knight or bishop
        => sufficient material: king + pawn/rook/queen ||king + 2 pieces
        iterate over board add all the pieces to the according arraylists
         */
        ArrayList<Piece> blackPieces = new ArrayList<>();
        ArrayList<Piece> whitePieces = new ArrayList<>();
        //fill array with all pieces
        for(int i = 0; i< boardParam.length;i++){
            for(int j = 0; j<boardParam[i].length;j++){
                if(boardParam[i][j] == null){
                    continue;
                } else if (boardParam[i][j].getPieceColor() == PlayerColor.WHITE) {
                    whitePieces.add(boardParam[i][j]);
                } else if (boardParam[i][j].getPieceColor() == PlayerColor.BLACK) {
                    blackPieces.add(boardParam[i][j]);
                }
            }
        }
        //atleast one player has enough material on the board
        if(blackPieces.size() >2 || whitePieces.size()>2){
            return true;
        }
        else{
            //piece that can give a checkmate by itself (queen rook pawn->promotion)
            for(Piece p : blackPieces){
                if(p == blackPawn ||p == blackRook || p == blackQueen){
                    return true;
                }
            }
            for(Piece p : whitePieces){
                if(p == whitePawn || p == whiteRook || p == whiteQueen){
                    return true;
                }
            }
        }
        return false;
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
