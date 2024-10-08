<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>myPage</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>

        #wrap-mainPage {

        }

        .searchandsaveBtn {
            float: right;
            margin-right: 8px;
            width: 100px;
        }


        .mg-top {
            margin-top: 50px;

        }

        .mg-bottom {
            margin-bottom: 10px;
        }

        .text-white {
            color: white;

        }

        .text-red {
            color: red;

        }

        .text-blue {
            color: blue;

        }

        .text-center {
            text-align: center;

        }

        .text-bold {
            font-weight: bold

        }

        .button-Height {
            height: 35px;
        }



		<!-- 로딩창 -->
		#loader {
            position: fixed;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            border: 16px solid #f3f3f3;
            border-radius: 50%;
            border-top: 16px solid #3498db;
            width: 120px;
            height: 120px;
            -webkit-animation: spin 2s linear infinite;
            animation: spin 2s linear infinite;
            z-index: 9999;
        }

        @-webkit-keyframes spin {
            0% { -webkit-transform: rotate(0deg); }
            100% { -webkit-transform: rotate(360deg); }
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }


		/* body{

			display:none;
		}

		#nav, #sidebar, #footer{

			display:none;
		} */



    </style>

</head>

<body class="sb-nav-fixed noto-sans-kr-font commonBody">


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
                <h3 class="mt-4">나의 잔고</h3>
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

                        <h6 id="userid" class="text-bold">자동매매 : </span><span id="auto"></span><span
                                id="profit2"></span></span><span id="profit"></span></span></h6>
                        <button type="button" id="accountBtn"
                                class="searchandsaveBtn btn btn-info mg-bottom text-white"><span>조회</span></button>
                        <button type="button" id="accountSaveBtn" class="searchandsaveBtn btn btn-success mg-bottom">
                            <span>저장</span></button>
                        <br/>
                        <label for="access" class="text-bold mg-bottom">access키</label><input id="access" type="text"
                                                                                              class="form-control mg-bottom"/>
                        <label for="secret" class="text-bold mg-bottom">secret키</label><input id="secret" type="text"
                                                                                              class="form-control mg-bottom"/>


                    </div>
                </div>

                <div class="card mb-4 col-md-6 mg-top">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        코인 보유 현황 및 매도
                    </div>
                    <div class="card-body">

                        <table class="table table-hover table-bordered text-center">
                            <thead>
                            <tr class="table-secondary border">
                                <th>코인</th>
                                <th>수량</th>
                                <th>매도</th>
                            </tr>
                            </thead>
                            <tbody id="Tb">

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



/* $(document).ready(function () {


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

 });*/

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

            if (result[0].auto == 'Y') {
                $('#auto').text('on    ')
            } else {
                $('#auto').text('off    ')
            }

            if ($('#auto').text() == 'on    ') {

                 //$('#auto').css("color","red")
                $('#auto').removeClass("text-red")
                $('#auto').removeClass("text-blue")
                $('#auto').addClass("text-red")
            } else {
                //$('#auto').css("color","blue")
                $('#auto').removeClass("text-red")
                $('#auto').removeClass("text-blue")
                $('#auto').addClass("text-blue")
            }

            var accessCode = $('#access').val();
            var secretCode = $('#secret').val();
            var account = {
                accessCode: accessCode,
                secretCode: secretCode
            }

            $.ajax({
                url: "/account7",
                type: "POST",
                data: JSON.stringify(account),
                contentType: "application/json",
                dataType: "json",
                success: function (result0) {


                    $.ajax({
                        url: "/market7",
                        type: "Get",
                        dataType: "json",
                        contentType: "application/json",
                        success: function (result1) {

                            if (result.error && result.error.name === 'invalid_access_key') {
                                //alert('실패했습니다. 유효하지 않은 접근 키')
                                return false;
                            }


                            $.ajax({
                                url: "/currentPrice7",
                                type: "Get",
                                dataType: "json",
                                contentType: "application/json",
                                success: function (result5) {

                                    $.each(result0, function (i, item) {


                                        $.each(result1, function (j, item) {

                                            if (result1[j].market.includes('KRW')) {

                                                if (('KRW-' + result0[i].currency) == 'KRW-BTC') {


                                                    var profit
                                                    var nowPrice
                                                    var orderPrice = result[0].orderPrice;
                                                    var autoPrice = result[0].autoPrice;


                                                    $.each(result5, function (p, item) {

                                                        if (result5[p].market == 'KRW-BTC') {

                                                            nowPrice = result5[p].trade_price;
                                                            $('#profit2').text('수익률 : ')
                                                        }
                                                    })


                                                    if (orderPrice <= nowPrice) {
                                                        $('#profit').text((((nowPrice - orderPrice) / orderPrice) * 100).toFixed(2) + "%");
                                                        $('#profit').css("color", "red");
                                                    } else {
                                                        $('#profit').text((((orderPrice - nowPrice) / orderPrice) * 100).toFixed(2) + "%");
                                                        $('#profit').css("color", "blue");
                                                    }


                                                } else {


                                                }


                                            }

                                        })

                                    })
                                }
                            })
                        }
                    })

                }
            })
            if ($('#access').val() != '' && $('#secret').val() != '') {

                var tbody = $('#Tb');


                $(document).ready(function () {

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

                $.ajax({
                    url: "/account7",
                    type: "POST",
                    data: JSON.stringify(account),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (result) {

                        // console.log(result)

                        $.ajax({
                            url: "/market7",
                            type: "Get",
                            dataType: "json",
                            contentType: "application/json",
                            success: function (result2) {

                                if (result.error && result.error.name === 'invalid_access_key') {
                                    //alert('실패했습니다. 유효하지 않은 접근 키')
                                    return false;
                                }

                                var check = [];
                                $.each(result, function (i, item) {

                                    if (item.currency == 'KRW') {
                                        item.currency = '원화';
                                        item.balance = Math.floor(item.balance) + '원';
                                    }

                                    $.each(result2, function (j, item) {

                                        if (result2[j].market.includes('KRW')) {

                                            if (('KRW-' + result[i].currency) == result2[j].market) {


                                                result[i].currency = result2[j].korean_name;
                                                check.push(result2[j].korean_name);
                                            } else {


                                            }


                                        }


                                    })

                                    var row = $('<tr class="noto-sans-kr-font">');
                                    var td1 = $('<td class="center"  >').text(item.currency);
                                    row.append(td1);
                                    var td2 = $('<td class="center">').text(item.balance);
                                    row.append(td2);

                                    if (item.currency == '원화') {
                                        var sell2 = $('<td class="center">').val('');
                                        var td3 = $('<td class="center">').append(sell2);
                                        row.append(td3);

                                    } else if (check.includes(item.currency) == true) {
                                        var sell = $('<button type="button" id="sellBtn" class="btn btn-primary button-Height" >').text('매도');
                                        var td3 = $('<td class="center">').append(sell);
                                        row.append(td3);

                                        sell.on('click', function () {

                                            var accessCode = $('#access').val();
                                            var secretCode = $('#secret').val();
                                            var korean_name = item.currency;
                                            //console.log(korean_name)
                                            var volume = item.balance;
                                            var orderType = "ask";
                                            var name;

                                            $.ajax({
                                                url: "/market7",
                                                type: "Get",
                                                dataType: "json",
                                                contentType: "application/json",
                                                success: function (result3) {

                                                    $.each(result3, function (m, item) {

                                                        if (korean_name == result3[m].korean_name && result3[m].market.includes('KRW-')) {
                                                            name = result3[m].market;
                                                            var order = {
                                                                coin: name,
                                                                volume: volume,
                                                                accessCode: accessCode,
                                                                secretCode: secretCode,
                                                                orderType: orderType

                                                            }
                                                            $.ajax({
                                                                url: "/sell7",
                                                                type: "POST",
                                                                data: JSON.stringify(order),
                                                                dataType: "json",
                                                                contentType: "application/json",
                                                                success: function (result) {
                                                                    location.href = "/mainPage";
                                                                }


                                                            })
                                                        }
                                                    })
                                                }
                                            })
                                            alert('매도 성공!!!')

                                            // sell ajax 구현 ( 밑에 버튼에도 매도 버튼이랑 sell ajax 구현 )
                                        })
                                    } else {
                                        var sell3 = $('<td class="center">').val('');
                                        var td3 = $('<td class="center">').append(sell3);
                                        row.append(td3);
                                    }


                                    //console.log(item.currency)
                                    tbody.append(row);


                                })

                            }
                        })


                    }


                })


                function refreshTable(account2) {
                    tbody.empty();
                    $.each(account2, function (i, item) {
                        var row = $('<tr>');
                        var td1 = $('<td class="center" >').text(item.currency);
                        row.append(td1);
                        var td2 = $('<td class="center">').text(item.balance);
                        row.append(td2);
                        var sell = $('<input type="button" id="sellBtn" class="btn btn-primary" style="width:50px;" text-align: center;>').text('매도');
                        var td3 = $('<td class="center">').append(sell);
                        row.append(td3);
                        tbody.append(row);
                    })

                }


            }


        }

    })


    $(document).ready(function () {

        setInterval(function () {

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

                    if (result[0].auto == 'Y') {
                        $('#auto').text('on    ')
                    } else {
                        $('#auto').text('off    ')
                    }

                    if ($('#auto').text() == 'on    ') {
                        //$('#auto').css("color","red")
                        $('#auto').removeClass("text-blue")
                        $('#auto').removeClass("text-red")
                        $('#auto').addClass("text-red")
                    } else {
                        //$('#auto').css("color","blue")
                        $('#auto').removeClass("text-blue")
                        $('#auto').removeClass("text-red")
                        $('#auto').addClass("text-blue")
                    }

                    var accessCode = $('#access').val();
                    var secretCode = $('#secret').val();
                    var account = {
                        accessCode: accessCode,
                        secretCode: secretCode
                    }

                    $.ajax({
                        url: "/account7",
                        type: "POST",
                        data: JSON.stringify(account),
                        contentType: "application/json",
                        dataType: "json",
                        success: function (result0) {



                            $.ajax({
                                url: "/market7",
                                type: "Get",
                                dataType: "json",
                                contentType: "application/json",
                                success: function (result1) {

                                    if (result.error && result.error.name === 'invalid_access_key') {
                                        //alert('실패했습니다. 유효하지 않은 접근 키')
                                        return false;
                                    }


                                    $.ajax({
                                        url: "/currentPrice7",
                                        type: "Get",
                                        dataType: "json",
                                        contentType: "application/json",
                                        success: function (result5) {

                                            $.each(result0, function (i, item) {


                                                $.each(result1, function (j, item) {

                                                    if (result1[j].market.includes('KRW')) {

                                                        if (('KRW-' + result0[i].currency) == 'KRW-BTC') {


                                                            var profit
                                                            var nowPrice
                                                            var orderPrice = result[0].orderPrice;
                                                            var autoPrice = result[0].autoPrice;

                                                            $.each(result5, function (p, item) {

                                                                if (result5[p].market == 'KRW-BTC') {

                                                                    nowPrice = result5[p].trade_price;
                                                                    $('#profit2').text('수익률 : ')
                                                                }
                                                            })


                                                            if (orderPrice <= nowPrice) {
                                                                $('#profit').text((((nowPrice - orderPrice) / orderPrice) * 100).toFixed(2) + "%");
                                                                //$('#profit').css("color","red");

                                                                $('#profit').removeClass("text-blue")
                                                                $('#profit').removeClass("text-red")
                                                                $('#profit').addClass("text-red")
                                                            } else {
                                                                $('#profit').text((((orderPrice - nowPrice) / orderPrice) * 100).toFixed(2) + "%");
                                                                //$('#profit').css("color","blue");
                                                                $('#profit').removeClass("text-blue")
                                                                $('#profit').removeClass("text-red")
                                                                $('#profit').addClass("text-blue")
                                                            }


                                                        } else {


                                                        }


                                                    }

                                                })

                                            })

                                        }
                                    })
                                }
                            })

                        }
                    })


                }

            })

        }, 5000);
    })


    var tbody = $('#Tb');

    function account(currency, balance) {
        this.currency = currency;
        this.balance = balance;
    }

    $('#accountBtn').on('click', function () {

        var tbody = $('#Tb');
        tbody.empty();

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

        $.ajax({
            url: "/account7",
            type: "POST",
            data: JSON.stringify(account),
            contentType: "application/json",
            dataType: "json",
            success: function (result) {



                $.ajax({
                    url: "/market7",
                    type: "Get",
                    dataType: "json",
                    contentType: "application/json",
                    success: function (result2) {

                        if (result.error && result.error.name === 'invalid_access_key') {


                            alert('실패했습니다. 유효하지 않은 접근 키')
                            return false;
                        }
                        var check = [];
                        $.each(result, function (i, item) {

                            if (item.currency == 'KRW') {
                                item.currency = '원화';
                                item.balance = Math.floor(item.balance) + '원';
                            }

                            $.each(result2, function (j, item) {

                                if (result2[j].market.includes('KRW')) {

                                    if (('KRW-' + result[i].currency) == result2[j].market) {
                                        //console.log('KRW-' + result[i].currency);
                                        // console.log(result2[j].market);
                                        result[i].currency = result2[j].korean_name;
                                        check.push(result2[j].korean_name);

                                    }
                                }
                            })

                            var row = $('<tr>');
                            var td1 = $('<td class="center" style="font-weight: bold;">').text(item.currency);
                            row.append(td1);
                            var td2 = $('<td class="center">').text(item.balance);
                            row.append(td2);
                            if (item.currency == '원화') {
                                var sell2 = $('<td class="center">').val('');
                                var td3 = $('<td class="center">').append(sell2);
                                row.append(td3);
                            } else if (check.includes(item.currency) == true) {
                                var sell = $('<button type="button" id="sellBtn" class="btn btn-primary" >').text('매도');
                                var td3 = $('<td class="center">').append(sell);
                                row.append(td3);
                            } else {
                                var sell3 = $('<td class="center">').val('');
                                var td3 = $('<td class="center">').append(sell3);
                                row.append(td3);
                            }

                            tbody.append(row);


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
							  title: "잔고가 조회되었습니다."
							});


            }


        })
        //alert("잔고를 조회하였습니다.")
    })

    function refreshTable(account2) {
        tbody.empty();
        $.each(account2, function (i, item) {
            var row = $('<tr>');
            var td1 = $('<td class="center" style="font-weight: bold;">').text(item.currency);
            row.append(td1);
            var td2 = $('<td class="center">').text(item.balance);
            row.append(td2);
            tbody.append(row);
        })

    }

    $('#accountSaveBtn').on('click', function () {

        var id = '[[${user}]]'
        var accessCode = $('#access').val();
        var secretCode = $('#secret').val();

        var member = {
            id: id,
            accessCode: accessCode,
            secretCode: secretCode

        }

        $.ajax({
            url: "/saveCode",
            type: "post",
            data: JSON.stringify(member),
            dataType: "json",
            contentType: "application/json",
            success: function (result) {

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
						  title: "코드가 저장되었습니다."
						});

                /* if (result) {
                    alert('저장 성공!!')
                } else {
                    alert('저장 실패!!')
                } */
            }

        })
        //alert("코드를 저장하였습니다.")
    })


	/*	$.ajax({
			url:"/rsiSearch",
			type:"GET",
			dataType:"json",
			contentType:"application/json",
			success: function(result9){

				}

				})*/



       /*  $.ajax({
             url:"/rsi14",
             type:"Get",
             dataType:"json",
             contentType:"application/json",
             success: function(result){

                 console.log(result);
             }

       })*/



/*         var accessCode = $('#access').text();
        var secretCode = $('#secret').text();

        var account = {
              accessCode: accessCode,
              secretCode: secretCode
          }


        console.log(account);

		   $.ajax({
                  url: "/account7",
                  type: "POST",
                  data: JSON.stringify(account),
                  contentType: "application/json",
                  dataType: "json",
                  success: function (result2) {


                  	console.log(result2);
                  }

		   }) */


</script>

</body>
</html>