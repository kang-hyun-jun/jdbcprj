package com.doje.app.service;

import com.doje.app.entity.Notice;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeService {
    private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String my_id = "NEWLEC";
    private String my_pwd = "khj0922.";

    public List<Notice> getlist(int page,String search_method,String search_string) throws ClassNotFoundException, SQLException {
        int start = 1+(page-1)*10;
        int end = start + 9;
        String sql = "SELECT * FROM (SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICE WHERE "+ search_method+" LIKE ? ORDER BY REGDATE DESC) N) WHERE NUM BETWEEN ? AND ?";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,my_id,my_pwd);
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,"%"+search_string+"%");
        st.setInt(2, start);
        st.setInt(3, end);
        ResultSet rs = st.executeQuery();

        List<Notice> list = new ArrayList<Notice>();

        while(rs.next()) {
            int id = rs.getInt("ID");
            String title = rs.getString("TITLE");
            String writer_id = rs.getString("WRITER_ID");
            String content = rs.getString("CONTENT");
            Date date = rs.getDate("REGDATE");
            int hit = rs.getInt("HIT");
            String files = rs.getString("FILES");
            Notice notice = new Notice(id,title,writer_id,content,date,hit,files);
            list.add(notice);
        }

        rs.close();
        st.close();
        con.close();
        return list;
    }
    public int getCount(int page,String search_method,String search_string) throws ClassNotFoundException, SQLException {
        String sql = "SELECT COUNT(ID) COUNT FROM(SELECT * FROM NOTICE_VIEW WHERE "+search_method+" LIKE ?)";
        int count = 0;

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,my_id,my_pwd);
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,"%"+search_string+"%");
        ResultSet rs = st.executeQuery();

        if(rs.next())
        {
            count = rs.getInt("COUNT");
        }


        rs.close();
        st.close();
        con.close();
        return count;
    }
    public int insert(Notice notice) throws ClassNotFoundException, SQLException
    {
        String title =  notice.getTitle();
        String writer_id =  notice.getWriter_id();
        String content =  notice.getContent();
        String file =notice.getFiles();

        String sql = "INSERT INTO notice (" +
                "    title," +
                "    writer_id," +
                "    content," +
                "    files" +
                ") VALUES (?,?,?,?)";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,my_id,my_pwd);
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,title);
        st.setString(2,writer_id);
        st.setString(3,content);
        st.setString(4,file);

        int result = st.executeUpdate();//이미 PreparedStatement에서 sql을 가지고 있기에 sql을 다시 넘겨 주지 않는다.
        st.close();
        con.close();
        return result;
    }
    public int update(Notice notice) throws ClassNotFoundException, SQLException
    {
        String title =  notice.getTitle();
        String writer_id =  notice.getWriter_id();
        String content =  notice.getContent();
        String file =notice.getFiles();
        int id =notice.getId();

        String sql = "UPDATE NOTICE " +
                "SET " +
                "    TITLE =?," +
                "    CONTENT=?," +
                "    FILES=?" +
                "WHERE ID=?";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,my_id,my_pwd);
        //Statement st = con.createStatement();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,title);
        st.setString(2,content);
        st.setString(3,file);
        st.setInt(4,id);

        int result = st.executeUpdate();//이미 PreparedStatement에서 sql을 가지고 있기에 sql을 다시 넘겨 주지 않는다.
        st.close();
        con.close();
        return result;
    }
    public int delete(Notice notice) throws ClassNotFoundException, SQLException
    {
        int id =notice.getId();

        String sql = "DELETE NOTICE WHERE ID=?";

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url,my_id,my_pwd);
        //Statement st = con.createStatement();
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1,id);

        int result = st.executeUpdate();//이미 PreparedStatement에서 sql을 가지고 있기에 sql을 다시 넘겨 주지 않는다.
        st.close();
        con.close();
        return result;
    }

}
