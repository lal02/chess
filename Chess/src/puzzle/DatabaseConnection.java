package puzzle;

import gamefoundation.Move;
import gamefoundation.Piece;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
        establishConnection();
    }

    private void establishConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chess","postgres","root");
            if(connection != null) {
                System.out.println("connected");
            }
            else {
                System.out.println("not connected");
            }
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public String[] requestPuzzle(int id){
            String query = "select board,solution from puzzle where id = ?;";
            try {
                PreparedStatement st = connection.prepareStatement(query);
                st.setInt(1, id);
                ResultSet set = st.executeQuery();
                if(set.next()) {
                    String board = set.getString("board");
                    String solution = set.getString("solution");

                    return new String[] {board, solution};
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;

    }

    public int getLatestPuzzle(){
        String query = "select max(id) from puzzle";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet set = st.executeQuery();
            if(set.next()) {
                return set.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return -1;
    }

    public boolean insertPuzzle(String board, String solution) {
        if(board.length() != 128 ||solution.length() != 6){
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
        return true;
    }


}
