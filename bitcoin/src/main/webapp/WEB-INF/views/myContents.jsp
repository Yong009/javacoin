<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>나의 정보</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>

        #wrap-myContents {

        }

        .text-bold {

            font-weight: bold;

        }

		.button-right{

			float: right;

		}




    </style>
</head>
<body class="sb-nav-fixed noto-sans-kr-font">

<nav id="nav">
	<%@include file="/WEB-INF/views/header.jsp" %>
</nav>
<div id="layoutSidenav">
    <div id="sidebar">
    	<%@include file="/WEB-INF/views/sidebar.jsp" %>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h3 class="mt-4">마이 페이지</h3>
                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>

                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        회원 정보 및 API 수정 및 등록
                    </div>
                    <div class="card-body">

                        <label for="id" class="text-bold">아이디</label><br/><br/>

                        <input id="id" type="text" class="form-control" readonly><br/><br/>
                        <label for="pw" class="text-bold">비밀번호</label><br/><br/>
                        <input id="pw" type="text" class="form-control"><br/><br/>
                        <label for="access" class="text-bold">엑세스 코드</label><br/><br/>
                        <input id="access" type="text" class="form-control"><br/><br/>
                        <label for="secret" class="text-bold">시크릿 코드</label><br/><br/>
                        <input id="secret" type="text" class="form-control"><br/><br/>
						<button type="button" class="btn btn-success text-bold" id="saveBtn">수정</button>

                    </div>
                </div>

            </div>
        </main>
    </div>
</div>
    <div id="footer">
    	<%@include file="/WEB-INF/views/footer.jsp" %>
    </div>
    <script>



     	var username = '${user}';

        var userid = {
            id: username
        }

        $.ajax({
            url: "/getCode",
            type: "POST",
            data: JSON.stringify(userid),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {

                $('#id').val(result[0].id)
                $('#pw').val(result[0].password)
                $('#access').val(result[0].accessCode)
                $('#secret').val(result[0].secretCode)
            }
        })

        $('#saveBtn').on('click', function () {

            var password = $('#pw').val()
            var access = $('#access').val()
            var secret = $('#secret').val()

            var member = {
                id: username,
                password: password,
                accessCode: access,
                secretCode: secret

            }



            $.ajax({
                url: "/updateMember",
                type: "POST",
                data: JSON.stringify(member),
                dataType: "json",
                contentType: "application/json",
                success: function (result2) {




                    $.ajax({
                        url: "/getCode",
                        type: "POST",
                        data: JSON.stringify(userid),
                        dataType: "json",
                        contentType: "application/json",
                        success: function (result) {

                            $('#id').val(result[0].id)
                            $('#pw').val(result[0].password)
                            $('#access').val(result[0].accessCode)
                            $('#secret').val(result[0].secretCode)
                        }
                    })



                }





            })

			    const Toast = Swal.mixin({
						  toast: true,
						  position: "top-end",
						  showConfirmButton: false,
						  timer: 3000,
						  timerProgressBar: true,
						  didOpen: (toast) => {
						    toast.onmouseenter = Swal.stopTimer;
						    toast.onmouseleave = Swal.resumeTimer;
						  }
						});
						Toast.fire({
						  icon: "success",
						  title: "회원정보가 수정되었습니다."
						});


           // alert('수정 성공!!!')
        })


    </script>
</body>
</html>