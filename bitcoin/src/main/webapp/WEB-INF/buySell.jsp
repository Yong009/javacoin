<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>거래 내역</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>

		#wrap-buySell{

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

	<nav id="nav" ></nav>
	<div id="layoutSidenav">
    <div id="sidebar"></div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h3 class="mt-4">나의 잔고</h3>
                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>

                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        거래 내역
                    </div>
                    <div class="card-body">

						 <table class="table table-hover table-bordered text-center">
					          <thead >
					          <tr class="table-secondary border">
					              <th>코인</th>
					              <th>수량</th>
					              <th>매수/매도</th>
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

	<div id ="footer" ></div>

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
    //console.log(username);

    var userid = {
        id: username
    }

    function formatDate(dateStr) {
        // 날짜 문자열을 Date 객체로 변환
        var date = new Date(dateStr);

        // 년, 월, 일을 추출
        var year = date.getFullYear();
        var month = ('0' + (date.getMonth() + 1)).slice(-2); // 월은 0부터 시작하므로 1을 더함
        var day = ('0' + date.getDate()).slice(-2);

        // 시, 분을 추출
        var hours = ('0' + date.getHours()).slice(-2);
        var minutes = ('0' + date.getMinutes()).slice(-2);

        // "년-월-일 시:분" 형식으로 반환
        return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes;
    }

    $.ajax({
        url:"/orderList",
        type:"POST",
        data: JSON.stringify(userid),
        dataType:"json",
        contentType:"application/json",
        success:function(result){
        	var tbody = $('#Tb');
        	$.each(result,function(i,item){
				var row = $('<tr>');
				var td1 = $('<td class="center">').text(result[i].market);
				row.append(td1);
				var td2 = $('<td class="center">').text(result[i].volume);
				row.append(td2);
				var td3 = $('<td class="center">').text(result[i].side);
				 if(result[i].side =='bid'){
					var td3 = $('<td class="center">').text('매수');
					td3.css("color","red");
				}else if(result[i].side =='ask'){
					var td3 = $('<td class="center">').text('매도');
					td3.css("color","blue");
				}
				row.append(td3);

				var td4 = $('<td class="center">').text(formatDate(result[i].created_at));
				row.append(td4);
				tbody.append(row);
        	})
        }
    })

	</script>

</body>

</html>
