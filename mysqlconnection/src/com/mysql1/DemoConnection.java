package com.mysql1;
import java.sql.*;

public class DemoConnection {

public static void main(String[] args) throws Exception {
String url="jdbc:mysql://localhost:3306/students";//jdbc:mysql://localhost:3306/sonoo
String uname="root";
String pass="1234";
String query="select name from trial where age=23";

Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection(url,uname,pass);
Statement st=con.createStatement();
ResultSet rs= st.executeQuery(query);

rs.next();
String name= rs.getString("name");
System.out.println(name);

st.close();
con.close();


}
}