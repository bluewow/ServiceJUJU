<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="n" value="${captureList}" /> -->
<html>
	<head>
		
		<meta charset="UTF-8">
		
		<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/normalize.css">
<link rel="stylesheet" type="text/css" href="../../css/captureMemo.css">
<script type="text/javascript" src="../../js/capture/capture.js"></script>

</head>
<!-- style="overflow:scroll" -->
<body class="scrollbar custom-scrollbar-style">
	<section id="captureScroll">
		<table id="captureTable" >
			<thead class="thead">
				<tr>
					<th>종목명</th>
					<th>제목</th>
					<th>캡쳐날짜</th>
					
					
				</tr>
			</thead>
			<tr>
				<td class="border-bottom">네오위즈</td>
				<td class="border-bottom"id="capt-title">10자이내로 작성</td>
				<td class="border-bottom"id="del-button">2019.12.05<span><a href=""></a></span></td>
				
			</tr>
			<tbody class="content">
				<!-- <tr>
					<td colspan="3"	style="text-align: center; height: 50px; line-height: 50px;">캡쳐된
					내용이 없습니다.</td>
				</tr> -->
				<tr>
					<td class="border-bottom" style="background-color: #F2F2F2;"  colspan="2">
					캡쳐차트1
					</td>
					<td class="border-bottom" style ="background-color: #F2F2F2;" >
						<div class="memo">
							<form class="memobox">
								<div><input type="text" class="memo-title" name="title" maxlength="40" placeholder="제목을 입력하세요"></div>
								<div>
										<textarea class="memo-content" maxlength="800" placeholder="내용을 입력하세요"></textarea>
								
								</div>
								<div>
										<input type="button" class="reg-submit" name="submit" value="등록">
										<input type="button" class="reg-cancel" name="cancel" value="수정"> 
								</div>
							</form>
						</div>
					</td>
					
				</tr>
				<tr>
					<td class="border-bottom">test</td>
					<td class="border-bottom"id="capt-title">test</td>
					<td class="border-bottom"id="del-button">test<span><a href=""></a></span></td>
				</tr>
				<tr>
					<td class="border-bottom">test</td>
					<td class="border-bottom"id="capt-title">test</td>
					<td class="border-bottom"id="del-button">test<span><a href=""></a></span></td>
				</tr>
				<tr>
						<td class="border-bottom">test</td>
						<td class="border-bottom"id="capt-title">test</td>
						<td class="border-bottom"id="del-button">test<span><a href=""></a></span></td>
					</tr>
					<tr>
						<td class="border-bottom">test</td>
						<td class="border-bottom"id="capt-title">test</td>
						<td class="border-bottom"id="del-button">test<span><a href=""></a></span></td>
					</tr>	
			</tbody>
		</table>
		<!-- <template class="tr-template">
	
		</template> -->
	</section>
</body>
</html>