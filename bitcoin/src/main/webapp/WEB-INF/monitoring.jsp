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


  </script>
</body>
</html>