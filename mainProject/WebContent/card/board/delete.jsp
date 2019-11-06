<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 - 게시글 삭제</title> 
</head>
<%
 
    String idx = request.getParameter("idx");
 
    try {
 
    	String driverName = "oracle.jdbc.driver.OracleDriver"; 
 
    	String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
 
      
 
        Class.forName(driverName);
 
        Connection con = DriverManager.getConnection(url,"ACORNGROUP1","month100man");
 
        out.println("Oracle Database Connection Success.");
 
      
 
        Statement stmt = con.createStatement();        
 
        String sql = "delete from board where idx = " + idx ;   // 삭제 쿼리입니다.
 
        stmt.executeQuery(sql);
 
        con.close();
 
    }catch (Exception e) {
 
        out.println("Oracle Database Connection Something Problem. <hr>");
 
        out.println(e.getMessage());
 
        e.printStackTrace();
 
    }
 
%>
 
<script>
 
    alert("게시글이 삭제되었습니다");  // 게시글이 삭제되었다는 경고창을 띄워주고
 
    location.href="index.jsp";          // 리스트페이지로 이동합니다.
 
</script>
 
<body>                                           
 
</body>
 
</html>