<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>변동성 돌파 자동매매</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>

		#wrap-changeAuto{

		}

		.text-red{

			color:red

		}

		.text-bold{

			font-weight:bold
		}


		.mg-top{

			margin-top:15px;
		}

    </style>
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

                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>
                <h3 class="mt-4">변동성 돌파 전략 이란?&nbsp;&nbsp;&nbsp;</h3>
                <div class="card mb-4 mg-top">
                    <div class="card-body">
               			<p>전날 종가, 최고가, 저가를 이용해서 변동성을 확인 후 그 적정 가격에 구매를 한뒤, 변동이 큰 아침 9시에 파는 전략입니다. </p>
    					<p>리스트를 최소화한 대신 리턴도 적은 전략입니다.</p>
                    </div>
                  </div>



                 <div class="card mb-4">
                    <div class="card-body">
               		 <p class="text-red">※access키와 secret키를 잔고 조회 페이지에서 저장 시 자동매매 기능을 사용하실 수 있습니다.※</p>
               		 <p class="text-red">※만원 단위로 기입하셔야 하며 0도 다 적어주셔야합니다.!!!!! 아닐 시 자동매매가 안될 수도 있습니다.※</p>
                    </div>
                  </div>

				 <div class="card mb-4">
                    <div class="card-body">
               		 <h6><span>자동매매 : </span><span id="auto" ></span>&nbsp;&nbsp;&nbsp;<span>오늘의 목표가(매수가) : &nbsp;</span><span id="todayPrice"></span></h6>
                    </div>
                  </div>


				  <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
						  자동매매
                    </div>
                    <div class="card-body">

						<label for="price" class="text-bold" id="account3" >금액(원)</label><br/><br/>
					    <input id="price"  class="form-control" type="text" /><br/>
					    <button id="startTrading" class="btn btn-success" type="button">자동 매매 시작</button>
					    <button id="stopTrading"  class="btn btn-danger" type="button">자동 매매 중지</button>
					</div>

                  </div>
			</div>
        </main>
    </div>
</div>
<div id="footer" >
	<%@include file="/WEB-INF/views/footer.jsp" %>
</div>


<script>


 	var username = '${user}';


    var userid = {
        id: username
    }

    $.ajax({
        url: "/getCode",
        type: "POST",
        data: JSON.stringify(userid),
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            $('#access2').val(result[0].accessCode);
            var a = $('#access').val();
            $('#secret2').val(result[0].secretCode)
            var b = $('#secret').val();

            if (result[0].auto == 'Y') {
                $('#auto').text('on');
            } else {
                $('#auto').text('off');
            }

            if ($('#auto').text() == 'on') {
                $('#auto').css("color", "red");
                $('#auto').css("font-weight", "bold");
            } else {
                $('#auto').css("color", "blue");
                $('#auto').css("font-weight", "bold");
            }


        }
    })

    /*setInterval(function () {

        $.ajax({
            url: "/getCode",
            type: "POST",
            data: JSON.stringify(userid),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                $('#access2').val(result[0].accessCode);
                var a = $('#access').val();
                $('#secret2').val(result[0].secretCode)
                var b = $('#secret').val();

                if (result[0].auto == 'Y') {
                    $('#auto').text('on');
                } else {
                    $('#auto').text('off');
                }

                if ($('#auto').text() == 'on') {
                    $('#auto').css("color", "red");
                    $('#auto').css("font-weight", "bold");
                } else {
                    $('#auto').css("color", "blue");
                    $('#auto').css("font-weight", "bold");
                }


            }
        })

    },3000);*/

    $('#startTrading').on('click', function () {



        $.ajax({
            url: "/getCode",
            type: "POST",
            data: JSON.stringify(userid),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {

                if (result[0].auto == 'Y') {
                    $('#auto').text('on');
                } else {
                    $('#auto').text('off');
                }

                if ($('#auto').text() == 'on') {
                    $('#auto').css("color", "red");
                    $('#auto').css("font-weight", "bold");
                } else {
                    $('#auto').css("color", "blue");
                    $('#auto').css("font-weight", "bold");
                }


            }
        })


        var acc = $('#access2').val();
        var sec = $('#secret2').val();
        var price = $('#price').val();
        var username = '[[${user}]]';
        var member = {
            accessCode: acc,
            secretCode: sec,
            id: username
        }
        var member2 = {
            autoPrice: price,
            id: username
        }

        $.ajax({
            url: "/autoTrade2",
            type: "POST",
            dataType: "json",
            data: JSON.stringify(member),
            contentType: "application/json",
            success: function (result) {
                alert("자동매매 실행 성공!!!")
            }
        })

        $.ajax({
            url: "/saveAutoPrice",
            type: "POST",
            dataType: "json",
            data: JSON.stringify(member2),
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
			  title: "자동매매를 시작하였습니다."
			});
    })

    $('#stopTrading').on('click', function () {

        $.ajax({
            url: "/getCode",
            type: "POST",
            data: JSON.stringify(userid),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {

                if (result[0].auto == 'Y') {
                    $('#auto').text('on');
                } else {
                    $('#auto').text('off');
                }

                if ($('#auto').text() == 'on') {
                    $('#auto').css("color", "red");
                    $('#auto').css("font-weight", "bold");
                } else {
                    $('#auto').css("color", "blue");
                    $('#auto').css("font-weight", "bold");
                }


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
					  title: "자동매매를 정지하였습니다."
					});

            }
        })


        var username = '[[${user}]]';
        var member = {
            id: username
        }


        $.ajax({
            url: "/autoStop",
            type: "POST",
            dataType: "json",
            data: JSON.stringify(member),
            contentType: "application/json",
            success: function (userid) {

            }
        })


    })

    $.ajax({
        url: "/getCode",
        type: "POST",
        data: JSON.stringify(userid),
        dataType: "json",
        contentType: "application/json",
        success: function (result) {

            var acc = result[0].accessCode
            var sec = result[0].secretCode

            var account2 = {
                accessCode: acc,
                secretCode: sec
            }

            $.ajax({
                url: "/account7",
                type: "POST",
                dataType: "json",
                data: JSON.stringify(account2),
                contentType: "application/json",
                success: function (result2) {

                    $.each(result2, function (i, item) {
                        if (item.currency == 'KRW') {
                        	let balance = Math.floor(item.balance / 10000) * 10000;
                            $('#account3').text("금액 " + "(잔액 : " + Math.floor(item.balance) + "원)");
                            $('#price').val(Math.floor(balance));
                        }

                    })

                }
            })


        }
    })

	var seq = {
		seq : "1"
    }


    $.ajax({
		url:"/yesterdayPrice",
		type:"GET",
		dataType:"json",
		contentType:"application/json",
		success:function(result){

			var targetPrice;

			  $.ajax({
			        url: "/currentPrice7",
			        type: "Get",
			        dataType: "json",
			        contentType: "application/json",
			        success: function (result2) {

			      		$.each(result2,function(i,item){

			      			if(result2[i].market == 'KRW-BTC'){
								targetPrice = result2[i].prev_closing_price + ((result[0].highPrice - result[0].lowPrice) * 0.5);
								$('#todayPrice').text(targetPrice);

			      			}

			      		})

			        }
			    })
		}
	})



    // $.ajax({
    // 	url:"/market7",
    // 	type:"Get",
    // 	dataType:"json",
    // 	contentType:"application/json",
    // 	success: function(result){
    //
    // 		console.log(result);
    //
    // 	}
    // })


   /*   */

</script>
</body>
</html>