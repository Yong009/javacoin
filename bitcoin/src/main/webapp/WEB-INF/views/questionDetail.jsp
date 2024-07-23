<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	 <meta charset="UTF-8">
	<title>글쓰기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>

		#wrap-questionDetail{

		}

    </style>
</head>



<body class="sb-nav-fixed noto-sans-kr-font">
	<div id="header"></div>

	<div id="nav">
		<%@include file="/WEB-INF/views/header.jsp" %>
	</div>

	<div id="layoutSidenav">
		<div id="sidebar">
			<%@include file="/WEB-INF/views/sidebar.jsp" %>
		</div>

	<div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h3 class="mt-4">대시보드</h3>
                <ol class="breadcrumb mb-4">

                </ol>

                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        공지사항 게시판
                    </div>
                    <div class="card-body">


	  <h3 >건의하기</h3>
	  <input id="content2" type="text" hidden><br/><br/>
	  <label for="title" >제목</label><br/><br/>
      <input id="title" type="text"  ><br/><br/>
      <label for="id" >작성자</label><br/><br/>
      <input id="id" type="text" readonly ><br/><br/>
      <label id="content2" >내용</label><br/><br/>
      <textarea id="content"  cols="60" rows="10" placeholder="여기에다가 글 남겨주세요!!" ></textarea>
	  <div id="update" >
	  	<button id="prev" type="button" >이전</button>
	  </div>
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

		 var seq2 = localStorage.getItem('seq');

	     var seq = JSON.parse(seq2);

		 var question = {
			seq : seq.seq
		 }

		 console.log(question);

		 $.ajax({
			url:"/questionDetailAjax",
			type:"POST",
			data:JSON.stringify(question),
			dataType:"json",
			contentType:"application/json",
			success:function(result){

				console.log(result);

				$('#title').val(result[0].title)
				$('#id').val(result[0].writer)
				$('#content').val(result[0].textWrite)

				if(username == result[0].writer){

					console.log(username)
					console.log(result[0].writer)

					var btn = $('<button type="button" id="updateBtn" class="btn-gradient green" style="margin-left:15px; font-weight: bold;">').text('수정하기')
 					var btn2 = $('<button type="button" id="deleteBtn" class="btn-gradient blue" style="margin-left:15px; font-weight: bold;">').text('삭제하기')
				 	$('#update').append(btn);
					$('#update').append(btn2);

					 $('#updateBtn').on('click',function(){

							 var title2 = $('#title').val()
							 var content2 = $('#content').val()

							 var board2 = {
									 seq : seq.seq,
								 	 title : title2,
									 textWrite : content2
							 	}

							 console.log(board2)

							 $.ajax({
								url:"/updateBoard",
								type:"POST",
								data:JSON.stringify(board2),
								dataType:"json",
								contentType:"application/json",
								success:function(result){

									}
								})

					 })

						  $('#deleteBtn').on('click',function(){


							 var board3 = {
									 seq : seq.seq,

							 	}



							 $.ajax({
								url:"/deleteBoard",
								type:"POST",
								data:JSON.stringify(board3),
								dataType:"json",
								contentType:"application/json",
								success:function(result){



									}
								})
						 })


				}

			}
		 })










			$('#prev').on('click',function(){

				window.location.href = "/writeQuestion";
			})


    </script>
</body>

</html>>