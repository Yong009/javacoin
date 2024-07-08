<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>코인사부(aks)</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>

		#wrap-login{

		}



    </style>
</head>

<body id ="wrap-login">
<div id="container" >
    <!-- FORM SECTION -->
    <div >
        <!-- SIGN UP -->
        <div >
            <img src="Bitcoin_Cash.png" alt="예제 이미지" width="300" height="250">
            <div >

                <div >
                    <div >
                        <i class='bx bxs-user'></i>
                        <img src="bitcoin.jpg" alt="예제 이미지" width="75" height="75">
                        <input type="text" id="newId" placeholder="아이디">

                    </div>

                  <!--  <div class="input-group">
                        <i class='bx bxs-lock-alt'></i>
                        <input type="password" id="newPw" placeholder="비밀번호">
                    </div> -->
                    <div >
                        <i ></i>
                        <!--<button type="submit" id="checkId">중복체크</button>-->
                        </div>
                        <button type="submit" id="newMemberBtn">회원가입</button>
                        <p>
                  <span>
                    이미 회원이 있으시다구요?
                  </span>
                            <b onclick="toggle()" >
                                로그인
                            </b>
                        </p>
                    </div>
                </div>

            </div>
            <!-- END SIGN UP -->
        <!-- SIGN IN -->
        <div >
            <img src="Bitcoin_Cash.png" alt="예제 이미지" width="300" height="250">
            <div >
                <div >
                    <div>
                        <i></i>
                        <img src="bitcoin.jpg" alt="예제 이미지" width="75" height="75">
                        <form  method="POST" action="/login-process">
                            <input type="text" placeholder="아이디" id="username" name="userid">
                    </div>
                    <div >
                        <i ></i>
                        <input type="password" placeholder="비밀번호" id="password" name="pw">
                    </div>

                    <button type="submit" id="login_btn">
                        로그인
                    </button>
                    </form>
                    <p>
                        <b>
                            <a href="/chart2">비회원용 코인 차트 보기</a>
                        </b>
                        <b>
                            <a href="/board2">게시판 보러 가기</a>
                        </b>

                    </p>
                    <p>
              <span>
                계정이 없으신가요?
              </span>
                     <!--   <b onclick="toggle()" class="pointer">
                            회원가입
                        </b>-->
                    </p>
                </div>
            </div>
            <div >

            </div>
        </div>
        <!-- END SIGN IN -->
    </div>
    <!-- END FORM SECTION -->
    <!-- CONTENT SECTION -->
    <div >
        <!-- SIGN IN CONTENT -->
        <div >
            <div>
                <h2>
                    환영합니다.
                </h2>

            </div>
            <div>

            </div>
        </div>
        <!-- END SIGN IN CONTENT -->
        <!-- SIGN UP CONTENT -->
        <div >
            <div>

            </div>
            <div>
                <h2>
                    가입을 환영합니다.
                </h2>

            </div>
        </div>
        <!-- END SIGN UP CONTENT -->
    </div>
    <!-- END CONTENT SECTION -->
</div>

	<div id="footer"></div>
</body>

<script>
    let container = document.getElementById('container')

     $('#footer').load('footer.html');

    localStorage.setItem('id', $('#username').val())
    toggle = () => {
       //window.location.href = "/memberJoin";

        container.classList.toggle('sign-in')
        container.classList.toggle('sign-up')
    }

    setTimeout(() => {
        container.classList.add('sign-in')
    }, 200)

    $('#newMemberBtn').on('click', function () {

        var id = $('#newId').val();
        //var password = $('#newPw').val();



        if (id == '') {
            alert('아이디를 입력해주세요');
            return
        }

        if (password = '') {
            alert('비밀번호를 입력해주세요');
            return
        }


        var member = {
            id: id
        }


        $.ajax({
            url: "/join",
            type: "GET",
            data: member,
            contentType: "application/json",
            dataType: "json",
            success: function (result) {


            },
            error: function (xhr, status, error) {

            }


        })

    })

    $('#checkId').on('click',function(){
        var id2 = $('#newId').val();
        var member2 = {
            id : id2
        }

        if (id2 == '') {
            alert('아이디를 입력해주세요');
            return
        }

        $.ajax({
            url:"/check",
            type:"GET",
            data:member2,
            dataType: "json",
            contentType: "application/json",
            success:function (result){

                if(result ==0){
                    alert('사용 가능한 아이디입니다.')
                }else{
                    alert('이미 존재하는 아이디 입니다.')
                }
            }
        })

    })


</script>

</html>