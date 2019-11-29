<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta charset="utf-8">
<title>stock market</title>

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700"
	rel="stylesheet">
<!-- Bootstrap Styles -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- Template Styles -->
<link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">

<!-- CSS Reset -->
<link rel="stylesheet" type="text/css" href="./css/normalize.css">

<!-- Milligram CSS minified -->
<link rel="stylesheet" type="text/css" href="./css/milligram.min.css">

<!-- Main Styles -->
<link rel="stylesheet" type="text/css" href="./css/main.css">

<link rel="stylesheet" type="text/css" href="./css/tablet.css">


<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="./css/tablet.css">

<!--  pop-up -->
<link rel="stylesheet" type="text/css" href="./css/popup.css">
<script src="./js/popup/popup.js"></script>

<!--[if lt IE 9]>
   <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
   <![endif]-->
<style>
@media only screen and (min-width:800px) and (max-width: 1200px) {
	.row .column.column-33, .row .column.column-34 {
		flex: none;
		max-width: 49.3333%;
	}
}
</style>


</head>

<body>
<!-- TODO 
아이디/비번 잘못입력시 문구
로그인 실패시 문구
무료회원가입
이메일/비밀번호찾기
-->
   <!-- =============================================================================================================== -->
   <header class="navbar">
      <h1 style="display: none;">Stock Market</h1>
      <section class="row">
         <h1 style="display: none;">header</h1>
         <section class="column column-30 col-site-title">
            <h1 style="display: none;">site title</h1>
            <a href="#" class="site-title float-left">Stock Market</a>
            <section class="column column-30">
               <h1 style="display: none;">user section</h1>
               <div class="user-section">
                  <a href="#"> <img src="http://via.placeholder.com/50x50"
                     alt="profile photo" class="circle float-left profile-photo"
                     width="50" height="auto">
                  </a>
                  <div class="personal">
                  	<c:if test="${empty sessionScope.id }">
                  		<input type="button" value="로그인">
                  	</c:if>
                  	<c:if test="${not empty sessionScope.id }">
	                  	<input type="button" value="USERasfsdsd">
	                  	<input type="button" value="로그아웃">
                  	</c:if>
                  </div>
               </div>
            </section>
         </section>
         <!-- <div class="column column-40 col-search"><a href="#" class="search-btn fa fa-search"></a>
                <input type="text" name="" value="" placeholder="Search..." />
            </div> -->

      </section>
   </header>
   <!-- =============================================================================================================== -->
   <main class="row">
   <h1 style="display: none;">cards</h1>
   <section id="main-content" class="column">
      <!-- 첫번째 로우 -->

      <div class="row grid-responsive" id="dragdiv">
         <!-- 검색카드 -->
         <section class="column column-33">
            <div class="card">
               <div class="card-block">
                  <section class="search">

                     <ul class="nav nav-tabs" id="myTab" role="tablist style"
                        style="border: none">
                        <li class="nav-item"><a class="nav-link nav-link-s active"
                           id="search-tab" data-toggle="tab" href="#search" role="tab"
                           aria-controls="index1" aria-selected="true">검 색</a></li>
                     </ul>
                     <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="search"
                           role="tabpanel" aria-labelledby="search-tab">
                           
                           <iframe height="100%" src="./card/search/search" scrolling="no">
                              	<!-- 여기에 html 삽입 -->
                           </iframe>
                        </div>
                     </div>
                  </section>
               </div>

            </div>
         </section>

         <!-- 분석 매매 예측 카드 -->

         <section class="column column-33">
            <div class="card">
               <div class="card-block">
                  <section class="analysis/trading">
                     <ul class="nav nav-tabs" id="myTab" role="tablist style"
                        style="border: none">
                        <li class="nav-item"><a class="nav-link nav-link-s active"
                           id="analysis-tab" data-toggle="tab" href="#analysis" role="tab"
                           aria-controls="index1" aria-selected="true">분 석</a></li>
                        <li class="nav-item"><a class="nav-link nav-link-s "
                           id="trading-tab" data-toggle="tab" href="#trading" role="tab"
                           aria-controls="index2" aria-selected="false">매 매</a></li>
                     </ul>
                     <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="analysis"
                           role="tabpanel" aria-labelledby="analysis-tab">
                           <iframe height="100%" src="./card/trade/analysis"
                              scrolling="no"> </iframe>
                        </div>
                        <div class="tab-pane fade" id="trading" role="tabpanel"
                           aria-labelledby="trading-tab">
                           <iframe height="100%" src="./card/trade/trade" scrolling="no">
                           </iframe>
                        </div>

                     </div>
                  </section>
               </div>
            </div>
         </section>


         <!-- 주식 캡처 카드 -->

         <section class="column column-33">
            <div class="card">
               <div class="card-block">
                  <section class="capture">
                     <ul class="nav nav-tabs" id="myTab" role="tablist style"
                        style="border: none">
                        <li class="nav-item"><a class="nav-link nav-link-s active"
                           id="capture-tab" data-toggle="tab" href="#capture" role="tab"
                           aria-controls="index1" aria-selected="true">캡 쳐</a></li>
                     </ul>
                     <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="capture"
                           role="tabpanel" aria-labelledby="capture-tab"></div>
                            <iframe height="100%" src="/card/capturememo/captureMemo" scrolling="no">
                           </iframe>
                     </div>
                  </section>
               </div>
            </div>

         </section>


         <!-- 두번째 로우 -->

         <!-- 커뮤니티 카드 -->
         <section class="column column-33">
            <div class="card ">
               <div class="card-block">
                  <section class="news/community/eventcommunity">
                     <ul class="nav nav-tabs" id="myTab" role="tablist style"
                        style="border: none">
                        <li class="nav-item"><a class="nav-link active"
                           id="news-tab" data-toggle="tab" href="#news" role="tab"
                           aria-controls="index1" aria-selected="true">뉴스&이슈</a></li>
                        <li class="nav-item"><a class="nav-link" id="community-tab"
                           data-toggle="tab" href="#community" role="tab"
                           aria-controls="index2" aria-selected="false">전체 커뮤니티</a></li>
                        <li class="nav-item"><a class="nav-link"
                           id="eventcommunity-tab" data-toggle="tab"
                           href="#eventcommunity" role="tab" aria-controls="index3"
                           aria-selected="false">종목별 커뮤니티</a></li>
                     </ul>
                     <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="news" role="tabpanel"
                           aria-labelledby="news-tab">
                           <iframe height="100%" src="./card/board/news_board"
                              scrolling="no"> </iframe>
                        </div>
                        <div class="tab-pane fade" id="community" role="tabpanel"
                           aria-labelledby="community-tab">
                           <iframe height="100%" src="./card/board/community_board"
                              scrolling="no"></iframe>
                        </div>
                        <div class="tab-pane fade" id="eventcommunity" role="tabpanel"
                           aria-labelledby="eventcommunity-tab">
                           <iframe height="100%" src="./card/board/stock_board"
                              scrolling="no"></iframe>
                        </div>
                     </div>
                  </section>
               </div>

            </div>
         </section>


         <!-- 보유 목록/관심 목록 카드 -->
         <section class="column column-33">
            <div class="card">
               <div class="card-block">
                  <section class="Purchase list/Interest List">
                     <ul class="nav nav-tabs" id="myTab" role="tablist style"
                        style="border: none">
                        <li class="nav-item"><a class="nav-link active"
                           id="Purchaselist-tab" data-toggle="tab" href="#Purchaselist"
                           role="tab" aria-controls="index1" aria-selected="true">보유목록</a></li>
                        <li class="nav-item"><a class="nav-link" id="Interest-tab"
                           data-toggle="tab" href="#Interest" role="tab"
                           aria-controls="index2" aria-selected="false">관심목록</a></li>
                     </ul>
                     <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="Purchaselist"
                           role="tabpanel" aria-labelledby="Purchaselist-tab">
                           <iframe height="100%" src="./card/managestocks/holdinglist"
                              scrolling="no"> </iframe>
                        </div>
                        <div class="tab-pane fade" id="Interest" role="tabpanel"
                           aria-labelledby="Interest-tab">
                           <iframe height="100%" src="./card/managestocks/interestlist"
                              scrolling="no"> </iframe>
                        </div>
                     </div>
                  </section>
               </div>
            </div>
         </section>

            <!-- 자산추이 카드 -->
            <section class="column column-33">
               <div class="card">
                  <div class="card-block">
                     <section class="asset graph/compare">
                        <ul class="nav nav-tabs" id="myTab" role="tablist style"
                           style="border: none">
                           <li class="nav-item"><a class="nav-link active"
                              id="assetgraph-tab" data-toggle="tab" href="#assetgraph"
                              role="tab" aria-controls="index1" aria-selected="true">MY 자산</a></li>
               
                        </ul>
                        <div class="tab-content" id="myTabContent">
                           <div class="tab-pane fade show active" id="assetgraph"
                              role="tabpanel" aria-labelledby="assetgraph-tab">
                              <iframe height="100%" src="./card/asset/myAsset" scrolling="no">
                              </iframe></div>

                        </div>
                        </section>
                     </div>
               </div>
            </section>


         <!-- 세번째 로우 -->

         <!-- 빈 공간 -->
         <div class="column column-33"></div>

         <div class="column column-33"></div>


         <!-- 랭킹 카드 -->
         <section class="column column-33">
            <div class="card">
               <div class="card-block">
                  <section class="rangking">
                     <ul class="nav nav-tabs" id="myTab" role="tablist style"
                        style="border: none">
                        <li class="nav-item"><a class="nav-link nav-link-s active"
                           id="rangking-tab" data-toggle="tab" href="rangking" role="tab"
                           aria-controls="index1" aria-selected="true">랭킹</a></li>
                     </ul>
                     <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="rangking"
                           role="tabpanel" aria-labelledby="rangking-tab">
                           <iframe height="100%" src="./card/rank/ranking" scrolling="no">
                           </iframe>
                        </div>
                     </div>
                  </section>
               </div>
            </div>
         </section>
         </div>
   </section>
   </main>
   <!-- =============================================================================================================== -->
   <footer>
      <section></section>
   </footer>
   <!-- =============================================================================================================== -->
	<!-- ===== POPUP ========================================================================================================== -->   
	<div class="pop-up-wrapper">
	   <div class="pop-up">
			<div class="pop-up-top">STOCK MARKET<br>로그인</div>
	   		<div class="pop-up-border">
	   			<div class="pop-up-context">
		   				<div class="text">이메일</div>
		   				<input class="box" type="text" name="userEmail" placeholder="Enter the Email..." form="user">
		   				
		   				<div class="text">비밀번호</div>
		   				<input class="box" type="password" name="pwd" placeholder="Enter the Password..." form="user">
	   					
	   					<form class="login-box" action="login" method="post" id="user">
		   					<input type="submit" value="로그인">
	   					</form>
		   				
		   				<hr>
		   				
		   				<form class="check-box">   				
		   					<input class="box" type="submit" value="무료회원가입">
		   					<input class="box" type="submit" value="이메일/비밀번호 찾기">
		   				</form>
	   			</div>
	   		</div>
	   </div>
   </div>
   <!-- =============================================================================================================== -->
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
      integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
      crossorigin="anonymous"></script>
   <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
      integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
      crossorigin="anonymous"></script>
   <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
      integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
      crossorigin="anonymous"></script>
</body>

</html>