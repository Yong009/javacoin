<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>코인토론방</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>

		#wrap-question{

		}

		.button-right{

			float:right;
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
                <h3 class="mt-4">건의하기</h3>
                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>

                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        건의 및 요청 사항 게시판
                    </div>
                    <div class="card-body">
						<button id="writeBtn" type="button" class="btn btn-success button-right" >글쓰기</button>
					    <table class="table table-hover table-bordered text-center">
					        <thead class="table-secondary border">
					        <tr >
					            <th>번호</th>
					            <th>제목</th>
					            <th>글쓴이</th>
					            <th>일시</th>

					        </tr>
					        </thead>
					        <tbody id="Tb" >

					        </tbody>
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

    function question(seq, title, writer, writeDate) {
        this.seq = seq;
        this.title = title;
        this.writer = writer;
        this.writeDate = writeDate;


    }

    var username = '${user}';

    if(username == 'goo'){

        $.ajax({
            url:"/questionAjax",
            Type:"GET",
            dataType:"json",
            contentType:"application/json",
            success: function (result) {

            	console.log(result);

            	$.each(result, function (i, item) {

            		var tbody = $('#Tb');
                    var row = $('<tr>');

                    var td1 = $('<td class="center">').text(result[i].seq);
                    row.append(td1);

                    var td2 = $('<td class="center">');
                    var link = $('<a>').attr('href', '#').text(result[i].title);
                    td2.append(link);
                    row.append(td2);

                    var td3 = $('<td class="center">').text(result[i].writer);
                    row.append(td3);

                    var td4 = $('<td class="center">').text(result[i].writeDate);
                    row.append(td4);


                    tbody.append(row);

                    link.on('click', function (event) {
                        event.preventDefault();

                          // 여기에서 seq 값을 이용하여 상세 페이지로 이동하도록 구현
          				//consolo.log("조회수 증가!!!!")
                          var question = {
          				 	seq : item.seq }

                          console.log(question);
          				 var responseData = {seq: item.seq};

                          localStorage.setItem('seq', JSON.stringify(responseData));

                          window.location.href = "/questionDetail";



                      });

                /*refreshTable();*/
            })
           }
        })

    }else{

    	var question = {
			writer:username
    	}

    	$.ajax({
            url:"/questionAjax2",
            Type:"GET",
            dataType:"json",
            data:JSON.stringify(question),
            contentType:"application/json",
            success: function (result2){


            	$.each(result2, function (i, item) {

            		var tbody = $('#Tb');
                    var row = $('<tr>');

                    var td1 = $('<td class="center">').text(result[i].seq);
                    row.append(td1);

                    var td2 = $('<td class="center">');
                    var link = $('<a>').attr('href', '#').text(result[i].title);
                    td2.append(link);
                    row.append(td2);

                    var td3 = $('<td class="center">').text(result[i].writer);
                    row.append(td3);

                    var td4 = $('<td class="center">').text(result[i].writeDate);
                    row.append(td4);


                    tbody.append(row);

                    link.on('click', function (event) {
                        event.preventDefault();

                          // 여기에서 seq 값을 이용하여 상세 페이지로 이동하도록 구현
          				//consolo.log("조회수 증가!!!!")
                          var question = {
          				 	seq : item.seq }

                          console.log(question);
          				 var responseData = {seq: item.seq};

                          localStorage.setItem('seq', JSON.stringify(responseData));

                          window.location.href = "/questionDetail";



                      });

            })
    	}
    })


    }




    $('#writeBtn').on('click',function(){

    	location.href= "/boardwrite";

    })



</script>
</body>
</html>
