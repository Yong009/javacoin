<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>


</head>
<body>

	<nav id="nav"></nav>

	<div id="layoutSidenav">
	<div id="sidebar"></div>

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h3 class="mt-4">대시보드</h3>
                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>

                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        공지사항 게시판
                    </div>
                    <div class="card-body">
						 <table class="table table-hover table-bordered text-center">
                            <thead>
                            <tr class="table-secondary border">
                                <th>순번</th>
                                <th>제목</th>
                                <th>일시</th>
                            </tr>
                            </thead>
                            <tbody id="Tb">

								<c:forEach var="result" items="${vo}">
									<tr>
										<td><c:out value="${result.seq }"/></td>
										<td><c:out value="${result.title }"/></td>
										<td><c:out value="${result.writeDate }"/></td>
									</tr>
								</c:forEach>


                            </tbody>
                        </table>
			 </div>
           </div>


           </div>
        </main>

    </div>
</div>


		<footer id="footer"></footer>

		<script>

		var username = "${user}"
		console.log(username);

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



		</script>


</body>
</html>