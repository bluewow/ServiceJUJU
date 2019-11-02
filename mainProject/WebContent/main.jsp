<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<html>

<head>
    <meta charset="utf-8">
    <title>stock market</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700"
        rel="stylesheet">

    <!-- Template Styles -->
    <link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css">

    <!-- CSS Reset -->
    <link rel="stylesheet" type="text/css" href="./css/normalize.css">

    <!-- Milligram CSS minified -->
    <link rel="stylesheet" type="text/css" href="./css/milligram.min.css">

    <!-- Main Styles -->
    <link rel="stylesheet" type="text/css" href="./css/main.css">

    <!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>

<body>
    <!-- =============================================================================================================== -->
    <header class="navbar">
        <h1 style="display: none;">Stock Market</h1>
        <section class="row">
            <h1 style="display: none;">hedaer</h1>
            <section class="column column-30 col-site-title">
                <h1 style="display: none;">site title</h1>
                <a href="#" class="site-title float-left">Stock Market</a>
            </section>
            <!-- <div class="column column-40 col-search"><a href="#" class="search-btn fa fa-search"></a>
                <input type="text" name="" value="" placeholder="Search..." />
            </div> -->
            <section class="column column-30">
                <h1 style="display: none;">user section</h1>
                <div class="user-section"><a href="#">
                        <img src="http://via.placeholder.com/50x50" alt="profile photo"
                            class="circle float-left profile-photo" width="50" height="auto">
                        <div class="username">
                            <h4>bluewow</h4>
                            <p>Administrator</p>
                        </div>
                    </a></div>
            </section>
        </section>
    </header>
    <!-- =============================================================================================================== -->
    <main class="row">
        <h1 style="display: none;">cards</h1>
        <section id="main-content" class="column">
            <!-- 첫번째 로우 -->
            <div class="row grid-responsive">
                <!-- 검색카드 -->
                <section class="column column-33">
                    <div class="card">
                        <div class="card-block">
                            <iframe height="500" src="search.html" scrolling="no"></iframe>
                        </div>
                    </div>
                </section>

                <!-- 분석 매매 예측 카드 -->
                <section class="column column-33">
                    <div class="card">
                        <div class="card-block">
                            <iframe height="500" src="#" scrolling="no"></iframe>
                        </div>
                    </div>
                </section>

                <!-- 주식 캡처 카드 -->
                <section class="column column-33">
                    <div class="card">
                        <div class="card-block">
                            <iframe height="500" src="#" scrolling="no"></iframe>
                        </div>
                    </div>
                </section>
            </div>

            <!-- 두번째 로우 -->
            <div class="row grid-responsive">
                <!-- 커뮤니티 카드 -->
                <section class="column column-33">
                    <div class="card">
                        <div class="card-block">
                        </div>
                    </div>
                </section>

                <!-- 구매 / 관심 목록 카드 -->
                <section class="column column-33">
                    <div class="card">
                        <div class="card-block">
                        </div>
                    </div>
                </section>

                <!-- 자산추이 카드 -->
                <section class="column column-33">
                    <div class="card">
                        <div class="card-block">
                        </div>
                    </div>
                </section>
            </div>

            <!-- 세번째 로우 -->
            <div class="row grid-responsive">
                <!-- 빈 공간 -->
                <div class="column column-33"></div>

                <!-- 빈 공간 -->
                <div class="column column-33"></div>

                <!-- 랭킹 카드 -->
                <section class="column column-33">
                    <div class="card">
                        <div class="card-block">
                        </div>
                    </div>
                </section>
            </div>
        </section>
    </main>
    <!-- =============================================================================================================== -->
    <footer>
        <section>

        </section>
    </footer>
    <!-- =============================================================================================================== -->
</body>

</html>