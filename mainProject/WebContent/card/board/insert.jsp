<%@page import="java.util.regex.Pattern"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판  - 게시글 입력</title>
</head>
<%
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");

	try {

		String driverName = "oracle.jdbc.driver.OracleDriver"; //데이터베이스에 접속하기위한 드라이버를 로드합니다.

		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1"; //접속 URL정보와 포트번호(oracle포트), sid(oracle버전)
		String sql = "INSERT INTO BOARD(IDX, TITLE, WRITER, REGDATE, COUNT, CONTENT, STOCKCODE) VALUES (board_seq.nextval, '"
				+ title + "', '" + writer + "', sysdate, 12, '" + content + "',123456)";
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection con = DriverManager.getConnection(url, "ACORNGROUP1", "month100man");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		out.println("Oracle 데이터베이스 db에 성공적으로 접속했습니다");

		con.close();

	} catch (Exception e) {

		out.println("Oracle 데이터베이스 db 접속에 문제가 있습니다. <hr>");

		out.println(e.getMessage());

		e.printStackTrace();

	} finally {

		out.print("<script>location.href='index.jsp';</script>");
	}
%>
<body>

</body>
</html>