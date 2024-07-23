<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>주문하기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>

		#wrap-order{

		}

		.button-right{

			float:right;
			margin-top:10px;
		}

		.text-bold{

			font-weight:bold;
		}


    </style>
</head>
<body class="sb-nav-fixed noto-sans-kr-font">
<div id="nav" >
	<%@include file="/WEB-INF/views/header.jsp" %>
</div>

<div id="layoutSidenav">
    <div id="sidebar">
    	<%@include file="/WEB-INF/views/sidebar.jsp" %>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h3 class="mt-4">주문하기</h3>
                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>

                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        코인 선택 및 매수하기
                    </div>
                    <div class="card-body">

					    <label for="access" class="text-bold" >access키</label><br/><br/>
					    <input id="access" type="text" class="form-control" /><br/><br/>
					    <label for="secret" class="text-bold">secret키</label><br/><br/>
					    <input id="secret" type="text" class="form-control" /><br/>

  						 <button id="orderBtn" type="button" class="btn btn-success button-right">매수</span></button><br/><br/>

						<label for="coins" class="text-bold" >코인</label><br/><br/>
    					<select id="coins" class="form-select form-select-sm" aria-label=".form-select-sm example"></select><br/><br/>
    					<label for="price" id="account3" class="text-bold">금액(원)</label><br/><br/>
    					<input id="price" type="text" class="form-control"/>
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
            $('#access').val(result[0].accessCode);
            var a = $('#access').val();
            $('#secret').val(result[0].secretCode)
            var b = $('#secret').val();


            if ($('#access').val() != '' && $('#secret').val() != '') {

                var tbody = $('#Tb');


                var acc = $('#access').val();
                var sec = $('#secret').val();
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
                    success: function (result) {

                        $.each(result, function (i, item) {
                            if (item.currency == 'KRW') {
                                $('#account3').text("금액 " + "(잔액 : " + Math.floor(item.balance) + "원)")
                            }

                        })

                    }
                })


                var accessCode = $('#access').val();
                var secretCode = $('#secret').val();
                var account = {
                    accessCode: accessCode,
                    secretCode: secretCode
                }


                if (accessCode == '') {
                    alert('엑세스키를 입력해주세요')
                    return
                }

                if (secretCode == '') {
                    alert('시크릿키를 입력해주세요')
                    return
                }
            }
        }
    })


    $.ajax({
        url: "/market7",
        type: "Get",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {

            $.each(result, function (i, item) {

                if (result[i].market.includes('KRW')) {

                    var option = $('<option style="height:50px; font-size:15px;" class="noto-sans-kr-font">');
                    var text = result[i].korean_name;

                    option.text(text);
                    $('#coins').append(option);


                }


            })

        }
    })


    $('#orderBtn').on('click', function () {

        //var coin = $('#coin').val();
        var coin = $('#coins').val();

        var coin2;
        $.ajax({
            url: "/market7",
            type: "Get",
            dataType: "json",
            contentType: "application/json",
            success: function (result2) {

                $.each(result2, function (i, item) {

                    if (result2[i].korean_name == coin && result2[i].market.includes('KRW-')) {

                        coin2 = result2[i].market;


                    }


                })

                var type = 'bid';
                var price = $('#price').val();

                if (price < 5000) {
                    alert('최소주문금액은 5000원 이상입니다!!!')
                }
                var accessCode = $('#access').val();
                var secretCode = $('#secret').val();
                var order = {
                    coin: coin2,
                    orderType: type,
                    price: price,
                    accessCode: accessCode,
                    secretCode: secretCode
                }


                    $.ajax({

                        url: "/order7",
                        type: "POST",
                        dataType: "json",
                        data: JSON.stringify(order),
                        contentType: "application/json",
                        success: function (result) {

                            // if(result == true ){
                            //     alert('매수 성공!!!');
                            // else{
                            //     alert('매수 실패!!!');
                            //     }
                            // }
                           // alert(coin + "을" + price + "원치" + "매수하였습니다.");


                        }


                    })




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
			  title: "매수하였습니다."
			});
    })

</script>
</body>
</html>