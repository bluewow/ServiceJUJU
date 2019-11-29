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
<script src="../../js/board/community_board.js"></script>
</head>
<body>
	<div id="stockScroll">
		<c:if test="${not empty selectStockName}">
			<div style="color: cyan; font-weight: bold; font-size: 30px">
				종목을 선택하면 나타납니다.</div>

		</c:if>
		<!-- ${not empty selectStockName}"  이거쓸꺼야 -->
		<c:if test="${empty selectStockName}">
			<!-- 이거는 볼라고 임시로 냅둘꺼야 -->

			<div style="color: black; font-weight: bold; font-size: 20px">
				기현 알고리즘</div>
			<br>
			<nav id="my-menu">
				<input type="button" id="my-button" name="my_board_view" value="My">
				<input type="button" id="favo-button" name="favo_board_view" value="관심">
				<input type="button" id="reg-button" name="reg_board" value="글쓰기">
			</nav>
					

		<table id="stockTable">
			<thead class="content">
				<tr>
					<th></th>
					<th>제목</th>
					<th>작성일</th>
					<th>조회</th>
					<th></th>
				</tr>
			</thead>
	  
			<tbody>
				  <c:forEach var="n" items="${CommunityBoard}">
					<tr>
							<td>${n.id}</td>
							<td rowspan="2" class="border_bottom" id="board-title">[${n.stockName}]
								${n.title} (${n.replyCnt})</td>
							<td rowspan="1"><fmt:formatDate value="${n.regdate}"
									pattern="yy/MM/dd" /></td>
							<td>${n.hit}</td>
                        
							<td rowspan="2" class="border_bottom">
							<label class="checkbox-wrap">
								<input type="checkbox" name="" value= "" id="favo-check"><i class="check-icon"></i></label></td>             
					</tr>
					<tr>
						<td class="border_bottom"><input type="button"
							id="del-button" name="del" value="del"></td>
						<td colspan="2" class="border_bottom">${n.writerId}</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>