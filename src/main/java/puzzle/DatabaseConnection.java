package puzzle;

import utility.LoggingUtility;

import java.sql.*;
import java.util.Random;

public class DatabaseConnection {
    private Connection connection;
    private int puzzleAmount = -1;

    /**
     * Default Constructor that establishes a connection and retrieves the amount of entries in the database and stores it in a variable
     */
    public DatabaseConnection() {
        establishConnection();
        puzzleAmount = getPuzzleAmount();
    }

    /**
     * Connect to the database
     */
    private void establishConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chess","postgres","root");
            if(connection != null) {
                System.out.println("connected");
                LoggingUtility.getLogger().info("Database Connection Established");
            }
            else {
                LoggingUtility.getLogger().info("Database Connection failed");
                System.out.println("not connected");
            }
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the amount of puzzle Objects that are stored in the database
     * @return int amount
     */
    private int getPuzzleAmount(){
        String query = "select count(id) from puzzle;";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet set = st.executeQuery();
            if(set.next()) {
                LoggingUtility.getLogger().info("Fetching puzzle amount");
                return set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LoggingUtility.getLogger().info("Failed to fetch puzzle amount");
        return -1;
    }

    /**
     * Retrieves the Puzzle with the according index from the Database.
     * If index >= amount of puzzles it retrieves a random puzzle with a different index
     * @param id the id of the puzzle to be retrieves
     * @return String array that has to be converted to a puzzle object
     */
    public String[] retrievePuzzle(int id){
        if(id==puzzleAmount || id > puzzleAmount) {
            Random random = new Random();
            int idbefore = id;
            id = random.nextInt(puzzleAmount);
            while(idbefore == id){
                id = random.nextInt(puzzleAmount);
            }
        }
            String query = "select board,solution from puzzle where id = ?;";
            try {
                PreparedStatement st = connection.prepareStatement(query);
                st.setInt(1, id);
                ResultSet set = st.executeQuery();
                if(set.next()) {
                    String board = set.getString("board");
                    String solution = set.getString("solution");

                    LoggingUtility.getLogger().info("Fetched puzzle with index " +id);
                    return new String[] {board, solution};
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        LoggingUtility.getLogger().info("Failed to fetch Puzzle with index " +id);

        return null;
    }

    /**
     * get the puzzle that was last stored in the database
     * @return the last added puzzle
     */
    public int getLatestPuzzle(){
        String query = "select max(id) from puzzle";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet set = st.executeQuery();
            if(set.next()) {
                LoggingUtility.getLogger().info("Fetching latest puzzle id");
                return set.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LoggingUtility.getLogger().info("Failed to fetch latest puzzle");
       return -1;
    }

    /**
     * Insert a puzzle into the database
     * @param board the board represented as as 128 length String
     * @param solution the solution move represented as a 6 length String
     * @return boolean value if the operation was successful or not
     */
    public boolean insertPuzzle(String board, String solution) {
        if(board.length() != 128 ||solution.length() != 6){
            LoggingUtility.getLogger().severe("Failed to insert puzzle due to wrong string formatting");
            return false;
        }
        int id = getLatestPuzzle() +1;
        String query = "insert into puzzle values('"+board+"','"+solution+"',"+id+");";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LoggingUtility.getLogger().info("Inserted puzzle with id " +id);
        return true;
    }


}
