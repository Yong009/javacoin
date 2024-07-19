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

        #wrap-boardDetail {

        }

        .text-bold {
            font-weight: bold;

        }

        .text-center {
            text-align: center;

        }

        .input-size {

        	width:50px;

        }

        .button-right{

        	float:right;
        	margin-bottom:10px;
        	margin-top:10px;

        }

        .button-center{

        	position:relative;
        	left:50%;
        	right:50%;
        	margin-right:5px;
        	margin-top:10px;

        }

        .button-center2{

        	position:relative;
        	left:40%;
        	right:60%;
        	margin-right:5px;
        	margin-top:10px;

        }


        .button-center3{

        	position:relative;
        	left:50%;
        	right:50%;
        	margin-right:5px;
        	margin-top:10px;

        }

        .button-center4{

        	position:relative;
        	left:30%;
        	right:70%;
        	margin-right:5px;
        	margin-top:10px;

        }

		.margin-top10{

			margin-top:10px;

		}

		.margin-bottom10{

			margin-bottom:10px;

		}

		body{

			display:none;
		}

		#nav, #sidebar, #footer{

			display:none;
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
                        자유게시판 글쓰기
                    </div>
                    <div class="card-body">
                        <label for="title" class="text-bold">제목</label><br/><br/>
                        <input id="title" class="form-control" type="text"><br/><br/>
                        <label for="id" class="text-bold">작성자</label><br/><br/>
                        <input id="id" class="form-control" type="text" readonly><br/><br/>
                        <label for="content" class="text-bold">내용</label><br/><br/>
                        <textarea id="content" class="form-control" cols="60" rows="10"
                                  placeholder="여기에다가 글 남겨주세요!!"></textarea>
                        <div id="update" >
                            <button id="prev" type="button" class="btn btn-secondary button-center4">이전</button>
                        </div>
                    </div>
                        </div>


                        <div class="card mb-4 col-md-6">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                댓글
                            </div>
                            <div class="card-body">

                                <label for="commentText" class="text-bold margin-bottom10">댓글 내용</label>
                                <input id="commentText" class="form-control text-bold input-size" type="text">
								<button id="insertBtn" class="btn btn-success button-right" type="button">등록</button>

                                <table class="table table-hover text-center">
                                    <thead>
                                    <th>번호</th>
                                    <th>작성자</th>
                                    <th>댓글 내용</th>
                                    <th>날짜</th>
                                    <th>좋아요</th>
                                    <th>댓글삭제</th>
                                    </thead>
                                    <tbody id="Tb"></tbody>

                                </table>
                            </div>
                        </div>

                       </div>
        </main>
    </div>
</div>


<div id="footer"></div>

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
			$('body').fadeIn();
	    });

 });




	 var username = '${user}';

    var seq2 = localStorage.getItem('seq');

    var seq = JSON.parse(seq2);

    var board = {
        seq: seq.seq
    }

    $.ajax({
        url: "/boardDetailAjax",
        type: "POST",
        data: JSON.stringify(board),
        dataType: "json",
        contentType: "application/json",
        success: function (result) {



            $('#title').val(result[0].title)
            $('#id').val(result[0].writer)
            $('#content').val(result[0].textWrite)

            if (username == result[0].writer || username == 'goo') {



                var btn = $('<button type="button" id="updateBtn" class="btn btn-success button-center2">').text('수정')
                var btn2 = $('<button type="button" id="deleteBtn" class="btn btn-danger button-center3">').text('삭제')
                $('#update').append(btn);
                $('#update').append(btn2);


                $('#updateBtn').on('click', function () {


                    var title2 = $('#title').val()
                    var content2 = $('#content').val()

                    var board2 = {
                        seq: seq.seq,
                        title: title2,
                        textWrite: content2
                    }



                    $.ajax({
                        url: "/updateBoard",
                        type: "POST",
                        data: JSON.stringify(board2),
                        dataType: "json",
                        contentType: "application/json",
                        success: function (result) {


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
						  title: "게시글을 수정하였습니다."
						});

                })

                $('#deleteBtn').on('click', function () {


                    var board3 = {
                        seq: seq.seq,

                    }


                    $.ajax({
                        url: "/deleteBoard",
                        type: "POST",
                        data: JSON.stringify(board3),
                        dataType: "json",
                        contentType: "application/json",
                        success: function (result) {


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
						  icon: "warning",
						  title: "게시글을 삭제하였습니다."
						});
						window.location.href = "/board";

                })


            }

        }
    })


    var board4 = {

        boardSeq: seq.seq
    }

    $.ajax({
        url: "/comment",
        type: "POST",
        data: JSON.stringify(board4),
        dataType: "json",
        contentType: "application/json",
        success: function (result) {

            var tbody = $('#Tb');
            tbody.empty();

            $.each(result, function (i, item) {

                var row = $('<tr style="border-bottom:1px solid;">');

                var td1 = $('<td class="center" ">').text(item.seq);
                row.append(td1);

                var td2 = $('<td class="center"">').text(item.writer);
                row.append(td2);

                var td3 = $('<td class="center"">').text(item.textWrite);
                row.append(td3);


                var td6 = $('<td class="center"">').text(item.writeDate);
                row.append(td6);

                var td4 = $('<td class="center" ">').text(item.heart);
                row.append(td4);
                if (item.writer == username || username == 'goo') {
                    var td5 = $('<td class="center" ">');
                    var link = $('<a style="color:red">').attr('href', '#').text("X");
                    td5.append(link);
                    row.append(td5);

                    link.on('click', function (event) {
                        event.preventDefault();

                        var board5 = {
                            seq: item.seq
                        }

                        $.ajax({
                            url: "/deleteComment",
                            type: "POST",
                            data: JSON.stringify(board5),
                            dataType: "json",
                            contentType: "application/json",
                            success: function (result) {

                            }


                        })


						location.reload();



                    })
                } else {
                    var td5 = $('<td class="center">').text('');
                    row.append(td5);
                }


                tbody.append(row);


            })
        }

    })

    $('#insertBtn').on('click', function () {

        var commentText = $('#commentText').val()

        var comment3 = {

            textWrite: commentText,
            writer: username,
            boardSeq: seq.seq,
        }

        $.ajax({
            url: "/insertComment",
            type: "POST",
            data: JSON.stringify(comment3),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {


                var board4 = {

                    boardSeq: seq.seq
                }

                $.ajax({
                    url: "/comment",
                    type: "POST",
                    data: JSON.stringify(board4),
                    dataType: "json",
                    contentType: "application/json",
                    success: function (result) {

                        var tbody = $('#Tb');

                        $.each(result, function (i, item) {

                            var row = $('<tr style="border-bottom:1px solid;">');

                            var td1 = $('<td class="center">').text(item.seq);
                            row.append(td1);

                            var td2 = $('<td class="center">').text(item.writer);
                            row.append(td2);

                            var td3 = $('<td class="center">').text(item.textWrite);
                            row.append(td3);

                            var td6 = $('<td class="center">').text(item.writeDate);
                            row.append(td6);

                            var td4 = $('<td class="center">').text(item.heart);
                            row.append(td4);
                            if (item.writer == username || username == 'goo') {
                                var td5 = $('<td class="center">');
                                var link = $('<a style="color:red">').attr('href', '#').text("X");
                                td5.append(link);
                                row.append(td5);

                                link.on('click', function (event) {
                                    event.preventDefault();

                                    var board5 = {
                                        seq: item.seq
                                    }

                                    $.ajax({
                                        url: "/deleteComment",
                                        type: "POST",
                                        data: JSON.stringify(board5),
                                        dataType: "json",
                                        contentType: "application/json",
                                        success: function (result) {

                                        }


                                    })


                                })
                            }


                            tbody.append(row);


                        })
                    }

                })

            }

        })


		location.reload();
    })


    $('#prev').on('click', function () {

        window.location.href = "/board";
    })


</script>
</body>

</html>