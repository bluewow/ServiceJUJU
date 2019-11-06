<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 만들자</title>
<style type="text/css">
 
    table, td, th   {
 
    border:1px solid green;
 
    }
 
    th{
 
    background-color:green;
 
    color:white;
 
    }
 
</style>
</head>
<%
	try {

		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		String sql = "select * from board order by idx desc";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ACORNGROUP1", "month100man");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		out.println("Oracle Database Connection Success.");
%>




<body>
	<!-- HTML문서의 주 내용이 들어가는 부분입니다. -->

	<h1>게시글 리스트</h1>
	<!-- 헤드라인 글씨를 표현하는 태그입니다. -->

	<table>
		<!-- 표 형식의 데이터를 표현하는 태그입니다. -->

		<tr>
			<!-- table태그 내에서 행을 정의할때 쓰는 태그입니다. -->

			<th>번호</th>
			<!-- Table Header의 약자로 table태그 내에서 -->

			<th>제목</th>
			<!-- 강조하고싶은 컬럼을 나타낼때 쓰는 태그입니다. -->

			<th>작성자</th>

			<th>날짜</th>

			<th>조회수</th>

			<th>종목</th>

		</tr>

		<%
			while (rs.next()) {

					out.print("<tr>");

					
					out.print("<td>" + rs.getString("idx") + "</td>");
					
					out.print("<td> <a href='content.jsp?idx="+ rs.getString("idx") +"'>" + rs.getString("title") + " </a></td>");

					out.print("<td>" + rs.getString("writer") + "</td>");

					out.print("<td>" + rs.getString("regdate") + "</td>");

					out.print("<td>" + rs.getString("count") + "</td>");
					
					out.print("<td>" + rs.getString("stockcode") + "</td>");

					out.print("</tr>");

				}
		%>

	</table>

	<a href="write.jsp">글쓰기</a>

	<%
		con.close();

		} catch (Exception e) {

			out.println("Oracle Database Connection Something Problem. <hr>");

			out.println(e.getMessage());

			e.printStackTrace();

		}
	%>

</body>

</html>
