<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>모니터링</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <style>

	#wrap-monitoring{

		}

	.text-red{

		color:red;

	}

	.text-blue{

		color:blue;
	}


	 .pageing-btn{
            position: relative;
            left:50%;
            right:50%;
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
                <h3 class="mt-4">자동매매 사용 여부</h3>
                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>

                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        자동매매 사용 회원
                    </div>
                    <div class="card-body">
					  <table class="table table-hover table-bordered text-center">
				          <thead>
				          <tr class="table-secondary border">
				              <th>id</th>
				              <th>자동매매</th>
				          </tr>
				          </thead>
				          <tbody id="Tb" >

				          </tbody>
				      </table>
				      <div id="pagination" class="pageing-btn"></div>
    				 </div>
  		 		  </div>

  		   	 <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        자동매매 미사용 회원
                    </div>
                    <div class="card-body">
		     		 <table class="table table-hover table-bordered text-center">
			          <thead>
			          <tr class="table-secondary border">
			              <th>id</th>
			              <th>자동매매</th>
			          </tr>
			          </thead>
			          <tbody id="Tb2" >

			          </tbody>
			      </table>
			      <div id="pagination2" class="pageing-btn"></div>
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

	  function moniter(id, auto) {
	      this.id = id;
	      this.auto = auto;

	  }

	var username = '${user}'
	refreshTable();
	refreshTable2();



	function refreshTable(){


        var auto = 'Y'
        var member = {
            auto : auto
        }

    $.ajax({
        url:"/memberAuto",
        type:"POST",
        data:JSON.stringify(member),
        contentType:"application/json",
        dataType:"json",
        success:function(result){

            var tbody = $('#Tb');
            tbody.empty();
            $.each(result,function(i,item){
                var row= $('<tr>');
                var td1 = $('<td class="center">').text(result[i].id);
                row.append(td1);
                var td2 = $('<td class="center text-red">').text("사용")
                row.append(td2);
                tbody.append(row);
            })
        }

    })

   var max;

    $.ajax({
        url: "/autoOnMax",
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {

            if (result <= 10 || result != 0) {
                max = 1;
            } else {
                max = Math.floor((result / 10) + 1);
            }

            $('#pagination').empty();

            for (var i = 1; i <= max; i++) {

                var pageButton = $('<button class="btn btn-secondary sm" id=' + i + '>').text(i);

                pageButton.click(function () {
                	 var tbody = $('#Tb');
                     tbody.empty();

                    var selectedPage = $(this).text();

                    var vo = {
                        page: selectedPage
                    }

                    pageButton.css('font-weight', 'bold');

                    $.ajax({
                        url: "/moniterAjax",
                        type: "post",
                        dataType: "json",
                        data: JSON.stringify(vo),
                        contentType: "application/json",
                        success: function (result3) {

                        	auto = result3;

                            refreshTable();
                        }
                    })

                })
                $('#pagination').append(pageButton);
            }
        }
    })

}

	function refreshTable2(){



        var auto2 = 'N'
        var member2 = {
            auto : auto2
        }


    $.ajax({
        url:"/memberAuto",
        type:"POST",
        data:JSON.stringify(member2),
        contentType:"application/json",
        dataType:"json",
        success:function(result){

            var tbody2 = $('#Tb2');
            tbody2.empty();

            $.each(result,function(i,item){
                var row2= $('<tr>');
                var td1 = $('<td class="center">').text(result[i].id);
                row2.append(td1);
                var td2 = $('<td class="center text-blue">').text("미사용")
                row2.append(td2);
                tbody2.append(row2);
            })
        }
    })

	var max;

    $.ajax({
        url: "/autoOffMax",
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {

            if (result <= 10 || result != 0) {
                max = 1;
            } else {
                max = Math.floor((result / 10) + 1);
            }

            $('#pagination2').empty();

            for (var i = 1; i <= max; i++) {

                var pageButton = $('<button class="btn btn-secondary sm" id=' + i + '>').text(i);

                pageButton.click(function () {

                	var tbody2 = $('#Tb2');
                    tbody2.empty();
                    var selectedPage = $(this).text();

                    var vo = {
                        page: selectedPage
                    }

                    pageButton.css('font-weight', 'bold');

                    $.ajax({
                        url: "/moniterAjax2",
                        type: "post",
                        dataType: "json",
                        data: JSON.stringify(vo),
                        contentType: "application/json",
                        success: function (result3) {

                            auto = result3;

                            refreshTable2();
                        }
                    })

                })
                $('#pagination2').append(pageButton);
            }
        }
    })
}
  </script>
</body>
</html>