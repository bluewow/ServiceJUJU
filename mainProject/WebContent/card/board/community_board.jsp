<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="kr">

<head>
<meta charset="UTF-8">

<title>Community</title>
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700"
	rel="stylesheet">

<!-- Template Styles -->
<link rel="stylesheet" type="text/css"
	href="../../css/font-awesome.min.css">

<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">

<link href="../../css/board/community_board.css" type="text/css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="../../js/board/community_board.js"></script>


</head>
<body>
	<section id="communityScroll">
		<br>
		<nav id="my-menu">
			<input type="button" id="my-button" name="my_board_view" value="My">
			<input type="button" id="favo-button" name="favo_board_view"
				value="관심">
		</nav>
		<table id="communityTable">
			<thead class="subject">
				<tr>
					<th></th>
					<th>제목</th>
					<th>작성일</th>
					<th>조회</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody class="content">
				<tr>
                   <td colspan="5" style="text-align:center; height:50px; line-height:50px;">게시된 글이 없습니다.</td>
               </tr>
			</tbody>
		</table>
		
		<div class="pager">
			<a href="?p=1">1</a> <a href="?p=2">2</a> <a href="?p=3">3</a>
		</div>
		
		<template class="tr-template">
			<tr>
			<td></td>
			<td rowspan="2" class="border_bottom" id="board-title"><a href="detail.html"></a></td>
			<td rowspan="1"></td>
			<td></td>

			<td rowspan="2" class="border_bottom" id="favo-add"><a href="detail.html"></td>
		</tr>
		<tr>
			<td class="border_bottom" id="del-button"><a href="detail.html"></td>
			<td colspan="2" class="border_bottom"></td>
		</tr>
		</template>
		
	</section>
</body>
</html>