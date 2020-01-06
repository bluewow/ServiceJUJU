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
<script src="../../js/managestocks/interest_list.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function goDelete() {
		var form = document.getElementById("deleteForm");
		form.submit();
	}
</script>
</head>

<body class="scrollbar custom-scrollbar-style">
	<div class="interestList">
		<table>
			<thead>
				<tr>
					<th style="width: 45%">종목명</th>
					<th style="width: 35%">현재가</th>
					<th style="width: 20%">편집</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5">관심종목이 없습니다</td>
				</tr>

			</tbody>
		</table>

		<form id="deleteForm" action="interestlist" method="get">
			<input id="deleteInput" type="hidden" name="delStockName" value="" />
		</form>

		<template class="template">
		
	    <td style="text-align: center">
         <span>stockName</span>
        </td>
		
		<td class="up"><span></span> <span class="fa fa-caret-up"></span><br>
			<span></span>%</td>
		<td class="down"><span></span> <span class="fa fa-caret-down"></span><br>
			-<span></span>%</td>
		<td><span></span> <span></span><br> <span></span>%</td>
		<td style="text-align: center"><button
				style="border: none; background: white" class="fa fa-trash-o"
				onclick="goDelete()"></button>
		</td>
		</template>
	</div>
</body>
</html>