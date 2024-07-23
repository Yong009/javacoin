<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body class="sb-nav-fixed noto-sans-kr-font">

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
										<td><c:out value="${result.seq}"/></td>
										<td><c:out value="${result.title}"/></td>
										<td><c:out value="${result.writeDate}"/></td>
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


<div id="footer">
	<%@include file="/WEB-INF/views/footer.jsp" %>
</div>

		<script>

		var username = "${user}"






		</script>


</body>
</html>