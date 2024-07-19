<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
	 <meta charset="UTF-8">
	<title>글쓰기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>

		#wrap-write{

		}

		body{

			display:none;
		}

		#nav, #sidebar, #footer {

			display:none;
		}

		.text-bold{

			font-weight:bold;
		}

    </style>
</head>




<body class="sb-nav-fixed noto-sans-kr-font">

	<nav id="nav"></nav>

	<div id="layoutSidenav">
    <div id="sidebar"></div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h3 class="mt-4">글쓰기</h3>
                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>

                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        게시판 글쓰기
                    </div>
                    <div class="card-body">

					  <label for="title" class="text-bold">제목</label><br/><br/>
				      <input class="form-control"  id="title" type="text" ><br/><br/>
				      <label for="id" class="text-bold">작성자</label><br/><br/>
				      <input class="form-control" id="id" type="text" readonly ><br/><br/>
				      <label for="content" class="text-bold">내용</label><br/><br/>
				      <input class="form-control" id="content" type="text" ><br/><br/>
				       <button class="btn btn-secondary" id="prev" type="button">이전</button>
				      <button class="btn btn-success" type="button" id="saveBtn" >저장</button>
				 </div>
                </div>
			 </div>
        </main>

    </div>
</div>

	<footer id="footer"></footer>



	<script>

	 $(document).ready(function () {


		   $("#nav").load("header.html", function() {
		        $("#nav").fadeIn();
		    });
		    $("#sidebar").load("sidebar.html", function() {
		        $("#sidebar").fadeIn();
		    });
		    $("#footer").load("footer.html", function() {
		        $("#footer").fadeIn();
		        $('#loader').css("display","none");
				$('body').fadeIn();
		    });

	 });


	 	var username = '${user}';

		 $('#id').val(username);



		 $('#saveBtn').on('click',function(){

			 var id = $('#id').val();
			 var title = $('#title').val()
			 var content = $('#content').val()

			 var board = {
				 	writer : id,
					title : title,
					textWrite : content
			 	}

			 $.ajax({
				url:"/insertBoard",
				type:"POST",
				data:JSON.stringify(board),
				dataType:"json",
				contentType:"application/json",
				success:function(result){


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
							  title: "게시판에 글을 등록하였습니다."
							});

				location.href="/board";
			 })


			 	$('#prev').on('click',function(){

				window.location.href = "/board";
			})


    </script>
</body>

</html>