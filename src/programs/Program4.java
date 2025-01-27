package programs;//SQL DB에 데이터를 수정하는 코드 (DELETE)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program4 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String title =  "너 자신을 알라";
        String writer_id =  "니체";
        String content =  "알아라!";
        String file ="";
        int id =3;

        String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
        String sql = "DELETE NOTICE WHERE ID=?";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url,"NEWLEC","khj0922.");
        //Statement st = con.createStatement();
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1,id);

        int result = st.executeUpdate();//이미 PreparedStatement에서 sql을 가지고 있기에 sql을 다시 넘겨 주지 않는다.
        System.out.println(result);
        st.close();
        con.close();
    }
}
