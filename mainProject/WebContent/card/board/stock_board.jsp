<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="kr">

<head>
<meta charset="UTF-8">

<title>Stock</title>
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700"
	rel="stylesheet">

<!-- Template Styles -->
<link rel="stylesheet" type="text/css"
	href="../../css/font-awesome.min.css">

<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">

<link href="../../css/board/stock_board.css" type="text/css"
	rel="stylesheet">


</head>
<body>
	<div id="stockScroll">
		<c:if test="${empty selectStockName}">
			<div style="color: cyan; font-weight: bold; font-size: 30px">
				종목을 선택하면 나타납니다.</div>

		</c:if>
		<!-- ${not empty selectStockName}"  이거쓸꺼야 -->
		<c:if test="${empty selectStockName}">
			<!-- 이거는 볼라고 임시로 냅둘꺼야 -->

			<div style="color: black; font-weight: bold; font-size: 20px">
				기현 알고리즘</div>
			<div>
				<input type="button" name="my_board_view" value="My"> <input
					type="button" name="favo_board_view" value="관심"> <input
					type="button" name="reg_board" value="글쓰기">
			</div>
			<br>
			<table border=1>
				<thead align="center">
					<tr>
						<th></th>
						<th width="200">제목</th>
						<th>작성일</th>
						<th>조회</th>
						<th></th>
					</tr>
				</thead>
				<tbody align="center">
					<c:forEach var="n" items="${CommunityBoard}">
						<tr>
							<td rowspan="2">${n.id}</td>
							<td rowspan="2">[${n.stockName}] ${n.title}</td>
							<td rowspan="1"><fmt:formatDate value="${n.regdate}"
									pattern="yy/MM/dd hh:mm" /></td>
							<td>${n.hit}</td>
							<td rowspan="2"><input type="button" name="Favo"
								value="Favo"></td>
						</tr>
						<tr>
							<td colspan="2">${n.writerId}</td>
						</tr>
						<tr>
							<td colspan="5">
								<div align="left">${n.content}</div>
								<div align="right">
									<input type="button" name="modify" value="수정">
								</div>
								<div align="left">댓글목록</div>
								<hr>
								<div>
									<textarea class="message_area" style="width: 80%;"
										placeholder="주제와 무관한 댓글이나 악플은 경고 조치 없이 삭제되며 징계 대상이 될 수 있습니다."></textarea>
									<input type="button" name="reg" value="등록">
								</div>
								<div style="text-align: left">
									갓광동 2019-10-31 10:30 <input type="button" name="modify_reply"
										value="댓글수정"> <input type="button" name="delete_reply"
										value="댓글삭제">
									<div>잘 확인했습니다.</div>
								</div>
								<hr>
								<div style="text-align: left">
									디비신명훈 2019-10-31 10:28 <input type="button" name="modify_reply"
										value="댓글수정"> <input type="button" name="delete_reply"
										value="댓글삭제">
									<div>넵! ㅎㅎ</div>
								</div>
								<hr>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>