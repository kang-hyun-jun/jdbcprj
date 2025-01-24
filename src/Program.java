import java.sql.*;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String sql = "SELECT * FROM NOTICE";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url,"NEWLEC","khj0922.");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if(rs.next()) {
            String title = rs.getString("TITLE");
            System.out.println(rs.getString(title));
        }

        rs.close();
        st.close();
        con.close();
    }
}
