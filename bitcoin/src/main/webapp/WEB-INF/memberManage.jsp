<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 관리</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>

		#wrap-memberManage{

		}

		.text-red{

			color:red;

		}

		.text-blue{

			color:blue;
		}

		  .button-center{

        	position:relative;
        	left:50%;
        	right:50%;
        	margin-right:5px;
        	margin-top:10px;

        }

        .text-bold{

        	font-weight:bold;

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
                <h3 class="mt-4">회원 관리</h3>
                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>

                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        API 키 및 자동매매 상황
                    </div>
                    <div class="card-body">

				          <table class="table table-hover table-bordered text-center">
				              <thead >
				              <tr class="table-secondary border">
				                  <th>id</th>
				                  <th>자동매매</th>
				                  <th>off</th>
				                  <th>회원탈퇴</th>
				              </tr>
				              </thead>
				              <tbody id="Tb" >

				              </tbody>
				          </table>
				          <div id="pagination" ></div>
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

    $.ajax({
        url:"/memberAll",
        type:"POST",
        contentType:"application/json",
        dataType:"json",
        success:function(result){
           // console.log(result);
            var tbody = $('#Tb');
            $.each(result,function(i,item){
                var row= $('<tr>');
                var td1 = $('<td class="center">').text(result[i].id);
                row.append(td1);
                if(result[i].auto=='Y'){
                    var td2 = $('<td class="center text-red ">').text("사용")

                }else{
                    var td2 = $('<td class="center text-blue ">').text("미사용")
                }
                row.append(td2);

                var stopBtn = $('<buttonn type="button" id="stopBtn" class="btn btn-success">').text('끄기')
                var td3 = $('<td class="center">').append(stopBtn);
                row.append(td3);

				stopBtn.on('click',function(){
					var id2 = result[i].id
                    var member2 = {
                        id : id2
                    }

					   $.ajax({
	                        url:"/manageAuto",
	                        type:"POST",
	                        data:JSON.stringify(member2),
	                        dataType: "json",
	                        contentType:"application/json",
	                        success: function(result){



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
						  title: "자동매매를 종료하였습니다."
						});


				})


                var deleteBtn = $('<buttonn type="button" id="deleteBtn" class="btn btn-danger">').text('탈퇴')
                var td4 = $('<td class="center">').append(deleteBtn);
                row.append(td4);

                deleteBtn.on('click',function(){

                    var id2 = result[i].id
                    var member2 = {
                        id : id2
                    }

                    $.ajax({
                        url:"/deleteMember",
                        type:"POST",
                        data:JSON.stringify(member2),
                        dataType: "json",
                        contentType:"application/json",
                        success: function(result){



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
						  title: "탈퇴처리하였습니다."
						});

                })


                tbody.append(row);
            })
        }
    })


	var max;

    $.ajax({
		url:"/memberMax",
		type:"GET",
		dataType:"json",
		contentType:"application/json",
		success:function(result){

			if(result <= 10 || result != 0){
				max = 1;
			}else{
				max = Math.floor((result/10)+1);
			}

			$('#pagination').empty();

				for(var i = 1; i<= max; i++){
					var pageButton = $('<button class="btn btn-secondary sm button-center" id='+i+'>').text(i);

					pageButton.click(function(){

						var selectedPage = $(this).text();

						var vo = {
							page : selectedPage
						}

						pageButton.css('font-weight','bold');

						$.ajax({
							url:"/memberListAjax2",
							type:"POST",
							dataType:"json",
							data:JSON.stringify(vo),
							contentType:"application/json",
							success:function(result2){

								   var tbody = $('#Tb');
								   tbody.empty();
						            $.each(result2,function(i,item){
						                var row= $('<tr>');
						                var td1 = $('<td class="center">').text(result2[i].id);
						                row.append(td1);
						                if(result2[i].auto=='Y'){
						                    var td2 = $('<td class="center text-red ">').text("사용")

						                }else{
						                    var td2 = $('<td class="center text-blue ">').text("미사용")
						                }
						                row.append(td2);

						                var stopBtn = $('<buttonn type="button" id="stopBtn" class="btn btn-success">').text('끄기')
						                var td3 = $('<td class="center">').append(stopBtn);
						                row.append(td3);

										stopBtn.on('click',function(){
											var id2 = result2[i].id
						                    var member2 = {
						                        id : id2
						                    }

											   $.ajax({
							                        url:"/manageAuto",
							                        type:"POST",
							                        data:JSON.stringify(member2),
							                        dataType: "json",
							                        contentType:"application/json",
							                        success: function(result3){



							                        }
							                    })

							                     alert('자동매매 끄기 성공!!!')
										})


						                var deleteBtn = $('<buttonn type="button" id="deleteBtn" class="btn btn-danger">').text('탈퇴')
						                var td4 = $('<td class="center">').append(deleteBtn);
						                row.append(td4);

						                deleteBtn.on('click',function(){

						                    var id2 = result2[i].id
						                    var member2 = {
						                        id : id2
						                    }

						                    $.ajax({
						                        url:"/deleteMember",
						                        type:"POST",
						                        data:JSON.stringify(member2),
						                        dataType: "json",
						                        contentType:"application/json",
						                        success: function(result4){


						                        }
						                    })

						                    alert('탈퇴 성공!!!')

						                })
						                tbody.append(row);
						            })

							}
						})

					})
					$('#pagination').append(pageButton);
				}
		}


    })
  </script>
</body>
</html>