<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
    <title>title</title>
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

		.img-size{

			width:35px;
			height:35px;
			margin-right:10px;
		}

		.text-white{

			color:white;
		}
    </style>

</head>
<body>
<!--<div th:fragment="nav">-->
  <!--  <div class="nav">-->

        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark noto-sans-kr-font">

            <a class="navbar-brand ps-3" href="/mainPage"><img src="Bitcoin_Cash.png" class="img-size" alt="예제 이미지" >Coin Sabu</a>

            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!" ><i
                    class="fas fa-bars"></i></button>

            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
					  <div class="text-white" id="headerId"></div>
                </div>
            </form>

            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">

                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/myPage">마이페이지</a></li>
                        <li><hr class="dropdown-divider"/></li>
                        <li><a class="dropdown-item" href="#!" id="" onclick="submitForm()">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
        </nav>




<script>

    function submitForm() {
        // 폼 생성
        var form = document.createElement("form");
        form.setAttribute("method", "post"); // 혹은 "get"
        form.setAttribute("action", "/logout"); // 서버 엔드포인트

        // 폼 데이터 추가 (필요에 따라 입력 필드 추가)
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "fieldName");
        hiddenField.setAttribute("value", "fieldValue");
        form.appendChild(hiddenField);

        // 폼을 body에 추가하고 제출
        document.body.appendChild(form);
        form.submit();
    }

    var username = '${user}';

    $('#headerId').append(username+'님');
    //console.log(username);

    /* if(username == 'goo'){
         $('#manager').append('<li><a href="/monitoring">모니터링</a></li>')
         $('#memberManage').append('<li><a href="/memberManage">회원 관리</a></li>')
         $('#chartSearch').append('<li><a href="/index">국내 주식 검색</a></li>')

         /*$('#question').append('<li><a href="/question">건의함</a></li>');*
     }*/


</script>


</body>

</html>


