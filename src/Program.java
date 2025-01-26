//SQL DB에서 저장되어있는 데이터를 가져오는 코드(SELECT)
import java.sql.*;
import java.util.Date;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String sql = "SELECT * FROM NOTICE";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url,"NEWLEC","khj0922.");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);


        while(rs.next()) {
            int id = rs.getInt("ID");
            String title = rs.getString("TITLE");
            String writer_id = rs.getString("WRITER_ID");
            String content = rs.getString("CONTENT");
            Date date = rs.getDate("REGATE");
            int hit = rs.getInt("HIT");
            String files = rs.getString("FILES");
            System.out.print("id : "+id);
            System.out.print(" title : "+title);
            System.out.print(" writer_id : "+writer_id);
            System.out.print(" content : "+content);
            System.out.print(" date : "+date);
            System.out.print(" hit : "+hit);
            System.out.println(" files : "+files);
        }

        rs.close();
        st.close();
        con.close();
    }
}
