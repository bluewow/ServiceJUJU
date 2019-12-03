<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="kr">

<head>
	<meta charset="UTF-8">

	<title>Detail</title>
	<!-- Google Fonts -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700"
		rel="stylesheet">

	<!-- Template Styles -->
	<link rel="stylesheet" type="text/css" href="../../css/font-awesome.min.css">

	<!-- CSS Reset -->
	<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
	<link href="../../css/board/detail.css" type="text/css" rel="stylesheet">
	<script src="../../js/board/detail.js"></script>
</head>

<body>
	<section id="detail">
		<br>

		<div id=content-row>
			<div>게시글 내용</div>
			<div>${detail.content}</div>
		</div>

		<div id=modify-row>
			<input type="button" id="modify-button" name="modify-button" value="수정">
		</div>

		<div id=reply-row>
			댓글 목록
		</div>

		<hr>

		<table>
			<colgroup>
				<col width="90%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<td>
						<textarea id="reply-content" maxlength="200"
							placeholder="주제와 무관한 댓글이나 악플은 경고조치없이 삭제되며 징계 대상일 될 수 있습니다."></textarea>
					</td>
					<td> <input type="button" id="reg-reply-button" name="reg-reply-button" value="등록">
					</td>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td colspan="2" style="text-align:center; height:50px; line-height:50px;">게시된 글이 없습니다.</td>
				</tr>
			</tbody>
		</table>


		<template class="tr-template">
			<tr>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</template>

	</section>
</body>

</html>