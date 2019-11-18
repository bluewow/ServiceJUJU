<%@ page language="java" contentType="text/html; charset=EUC-KR"
pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="../../css/holding.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>

<body>
<div class="btposition" >
<button type="button" class="btn btn-secondary btn-sm" >편집</button>
</div>
   <table >
   <thead>
   <tr>
      <th>종목명</th>
      <th>현재가</th>
      <th>보유수량</th>
      <th>수익금</th>
   </tr>
   </thead>
   <tbody>
<%--     <c:forEach var="n" items="${list}">
       <tr>
         <td >${n.stockName}</td>
         <td class="up">24,896    <span class="fa fa-caret-up"></span><br>
         <span>1.46%</span></td>
          <td class="up">16주</td>
           <td class="up"><span>4,418</span><br>
           <span>1.15%</span></td>
           </td>
      </tr>
    </c:forEach>  --%>
       <tr>
         <td style="text-align:center;">네오위즈 홀딩스</td>
         <td class="down">24,150   <span class="fa fa-caret-down"></span><br>
         <span>4.17%</span></td>
         <td style="text-align:center;">16주</td>
         <td class="down"><span>4,418</span><br>
         <span>1.15%</span></td>
      </tr> 
      </tbody>
   </table>
</body>
</html>