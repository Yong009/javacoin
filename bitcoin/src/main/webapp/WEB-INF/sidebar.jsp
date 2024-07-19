<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <link href="/css/styles.css" rel="stylesheet"/>
    <script src="/js/scripts.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
            crossorigin="anonymous"></script>
    <script src="/js/datatables-simple-demo.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

    <style>
    	.noto-sans-kr-font {
            font-family: "Noto Sans KR", sans-serif;
            font-optical-sizing: auto;
            font-weight: 400;
            font-style: normal;
        }

    </style>

</head>
<body class="noto-sans-kr-font">
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">나의 페이지</div>
                <a class="nav-link" href="/dashboard">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    대시보드
                </a>
                <a class="nav-link" href="/mainPage">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    나의 잔고 및 매도
                </a>
                <a class="nav-link" href="/myPage">
                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                    마이 페이지
                </a>
                <a class="nav-link" href="/buySellPage">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    거래내역
                </a>

                <div class="sb-sidenav-menu-heading">자동매매</div>
                <a class="nav-link" href="/changeAuto">
                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    변동성 돌파 전략
                </a>
                <a class="nav-link" href="#">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    rsi 전략
                </a>

                <a class="nav-link" href="/orderPage">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    시장가 매수
                </a>

                <div class="sb-sidenav-menu-heading">자동매매</div>
                <a class="nav-link" href="/chart">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    차트 및 검색
                </a>

                <div class="sb-sidenav-menu-heading">게시판</div>
                <a class="nav-link" href="/board">
                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                        코인 토론방
                </a>

                <div class="sb-sidenav-menu-heading">고객센터</div>
                <a class="nav-link" href="/question">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    건의 하기
                </a>

                <div class="sb-sidenav-menu-heading" id="admin"></div>
                <a class="nav-link" href="monitoring" id="moniter">
                <!--     <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    모니터링 -->
                </a>
                <a class="nav-link" href="/memberManage" id="manager">
                  <!--   <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    회원 관리 -->
                </a>


                <!--<div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="layout-static.html">Static Navigation</a>
                        <a class="nav-link" href="layout-sidenav-light.html">Light Sidenav</a>
                    </nav>
                </div>-->
                <!--<a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages"
                   aria-expanded="false" aria-controls="collapsePages">
                    <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                    Pages
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>-->
               <!-- <div class="collapse" id="collapsePages" aria-labelledby="headingTwo"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#pagesCollapseAuth" aria-expanded="false"
                           aria-controls="pagesCollapseAuth">
                            Authentication
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne"
                             data-bs-parent="#sidenavAccordionPages">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="login.html">Login</a>
                                <a class="nav-link" href="register.html">Register</a>
                                <a class="nav-link" href="password.html">Forgot Password</a>
                            </nav>
                        </div>
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                           data-bs-target="#pagesCollapseError" aria-expanded="false"
                           aria-controls="pagesCollapseError">
                            Error
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne"
                             data-bs-parent="#sidenavAccordionPages">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="401.html">401 Page</a>
                                <a class="nav-link" href="404.html">404 Page</a>
                                <a class="nav-link" href="500.html">500 Page</a>
                            </nav>
                        </div>
                    </nav>-->
                </div>
                <!--<div class="sb-sidenav-menu-heading">Addons</div>
                <a class="nav-link" href="charts.html">
                    <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                    Charts
                </a>
                <a class="nav-link" href="tables.html">
                    <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                    Tables
                </a>
            </div>-->
        </div>
        <div class="sb-sidenav-footer">
            <div class="small" id="sidebarId"></div>
            환영합니다.
        </div>
    </nav>
</div>

<script>


	var username = '${user}';

	$('#sidebarId').append(username+"님");



	if(username == 'goo'){
		$('#admin').append('회원 관리');
		$('#moniter').append('<div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>모니터링');
		$('#manager').append(' <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>회원 관리');
	}

</script>
</body>
</html>