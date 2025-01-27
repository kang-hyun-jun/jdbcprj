package programs;//SQL DB에 데이터를 삽입하는 코드 (INSERT)
import java.sql.*;

public class Program2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String title =  "르상티망이란 무엇인가?";
        String writer_id =  "니체";
        String content =  "르상티망은 르상티망이다.";
        String file ="";

        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String sql = "INSERT INTO notice (" +
                "    title," +
                "    writer_id," +
                "    content," +
                "    files" +
                ") VALUES (?,?,?,?)";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url,"NEWLEC","khj0922.");
        //Statement st = con.createStatement();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,title);
        st.setString(2,writer_id);
        st.setString(3,content);
        st.setString(4,file);

        int result = st.executeUpdate();//이미 PreparedStatement에서 sql을 가지고 있기에 sql을 다시 넘겨 주지 않는다.
        System.out.println(result);





        st.close();
        con.close();
    }
}
