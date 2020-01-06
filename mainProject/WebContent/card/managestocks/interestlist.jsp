<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<link rel="stylesheet" type="text/css" href="../../css/holding.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function goDelete() {
		var form = document.getElementById("deleteForm");
		form.submit();
	}	
</script>


</head>

<body class="scrollbar custom-scrollbar-style">
	<div class="btposition">
		<table>
			<thead>
				<tr>
					<th style="width: 45%">종목명</th>
					<th style="width: 35%">현재가</th>
					<th style="width: 20%">편집</th>
				</tr>
			</thead>
			<tbody>


				<c:forEach var="n" items="${list}">

					<tr>
						<td style="text-align: center">${n.stockname}</td>
						<td class="up">${n.price}<span class="fa fa-caret-up"></span> <span>${n.percent}%</span></td>
						<td style="text-align: center"><button style="border:none; background:white" class="fa fa-trash-o" onclick="goDelete()"></button>
						</td>
					</tr>
					
					        <form id="deleteForm" action="interestlist" method="get">
                            <input type="hidden" name="delStockName" value="${n.stockname}" />
					         </form>
				</c:forEach>

				<!--        <tr>
         <td style="text-align:center;">네오위즈 홀딩스</td>
         <td class="down">24,150   <span class="fa fa-caret-down"></span><br>
         <span>4.17%</span></td>
         <td style="text-align:center;">16주</td>
         <td class="down"><span>4,418</span><br>
         <span>1.15%</span></td>
      </tr>  -->
			</tbody>
		</table>
		</div>
</body>
</html>