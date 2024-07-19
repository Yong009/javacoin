<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>코인사부(CSB)</title>
  <script type="text/javascript" src="/js/scripts.js"></script>

  <link type="text/css" href="/css/styles.css" rel="stylesheet" />
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

  <style>

	 .noto-sans-kr-font {
            font-family: "Noto Sans KR", sans-serif;
            font-optical-sizing: auto;
            font-weight: 400;
            font-style: normal;
        }

  	.div-center-outer{

  		position: relative;
  	}

	.div-center-inner{

		position: relative;
		  	 top: 50%;
		  	left: 50%;

	}

	.div-center- innner2{

		position: absolute;
			top:100%;
			left:50%;
			margin :-50px 0 0 -50px;
	}

	.back-color{

		background-color: #f6f7fb;
	}

	.img-size{

		width:50px;
		height:50px;
	}

	.button-center{

		position: relative;
		  	 top: 50%;
		  	left: 40%;
		  	right: 60%;

	}

	.text-bold{

		font-weight:bold;
	}



  </style>
</head>
<body class="bg-dark noto-sans-kr-font">
<!-- bg-dark -->
<div id="layoutAuthentication">
  <div id="layoutAuthentication_content">
    <main>
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-5">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
              <div class="card-header">

              	<h3 class="text-center font-weight-light my-4"><img src="Bitcoin_Cash.png" class="img-size" alt="예제 이미지" >  Coin SaBu(CSB)  <img src="bitcoin.jpg" alt="예제 이미지" class="img-size"></h3>
              	</div>
              <div class="card-body">
                <form method="POST" action="/login-process">
                  <div class="form-floating mb-3">

                    <input class="form-control" id="username" name="userid" type="text" placeholder="아이디" />
                    <label for="username">아이디</label>

                  </div>
                  <div class="form-floating mb-3">

                    <input class="form-control" id="password" name="pw" type="password" placeholder="비밀번호" />
                    <label for="password">비밀번호</label>

                  </div>
                  <div class="form-check mb-3">
                    <!--<input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" />-->
                    <!--<label class="form-check-label" for="inputRememberPassword">Remember Password</label>-->
                  </div>
                  <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                    <!--<a class="small" href="password.html">Forgot Password?</a>-->

                    <button type="submit" id="login_btn" class="btn btn-success button-center">로그인</button>

                    <!--<a class="btn btn-primary"  href="index.html">Login</a>-->
                  </div>
                </form>
              </div>
              <div class="card-footer text-center py-3">
                <!-- <div class="small"><a href="register.html">Need an account? Sign up!</a></div> -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
  <div id="layoutAuthentication_footer">
    <footer class="py-4 bg-light mt-auto">
      <div class="container-fluid px-4">
        <div class="d-flex align-items-center justify-content-between small">

          <div >
            <a href="https://www.instagram.com/codesabu?igsh=MjJxNHlsYW9yYmZj&utm_source=qr">인스타&emsp;</a>
            <a href="https://kmong.com/gig/554066">크몽</a>
            <p>홈페이지를 사용하고 싶으시거나, 회원가입하고 싶으신 분은 관리자( 인스타 : codesabu / 크몽 : 코딩사랑 )  요쪽으로 문의 주시면 됩니다.<p>

          </div>
        </div>
      </div>
    </footer>
  </div>
</div>

</body>
</html>