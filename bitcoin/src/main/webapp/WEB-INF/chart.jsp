<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>차트</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>

		#wrap-chart{

		}

		 .text-center {
            text-align: center;

        }

        .button-right{

        	float:right;
			margin-right:10px;
        }

        .text-bold{

        	font-weight:bold;

        }

        .text-red{

        	color:red;

        }

        .text-blue{

        	color:blue;

        }

		.text-black{

			color:black;

		}

		.back-color-white{

			background-color:white;
		}

		.back-color-yellow{

			background-color:yellow;

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
                <h3 class="mt-4">차트(업비트) 및 검색</h3>
                <ol class="breadcrumb mb-4">
                    <!-- <li class="breadcrumb-item"><a href="index.html">Dashboard</a></li>
                   <li class="breadcrumb-item active">Tables</li> -->
                </ol>
                <div class="card mb-4 col-md-6">
                    <div class="card-header">
                        <i class="fas fa-table me-1"></i>
                        차트(업비트)
                    </div>
                    <div class="card-body">

					    <label for="coins" >코인 검색 <span >(검색시 은색으로 표시됩니다.)</span> </label>
					    <button type="button" id="reset" class="btn btn-secondary button-right" >초기화</button>
					    <button type="button" id="seachCoin" class="btn btn-success button-right"  >검색</button><br/><br/>

					    <select id="coins" class="form-select form-select-sm" aria-label=".form-select-sm example"></select>

					    <table class="table table-hover table-bordered text-center">
					        <thead>
					        <tr id="Tr" class="table-secondary border">
					            <th >코인</th>
					            <th >현재가</th>
					            <th >변화</th>
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
        url: "/market7",
        type: "Get",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            var tablecheck2 = 1;
            $.each(result, function (i, item) {

                if (result[i].market.includes('KRW')) {

                    var option = $('<option style="height:50px; font-size:15px;" id=' + 'seach' + tablecheck2 + '>');
                    var text = result[i].korean_name;

                    option.text(text);
                    tablecheck2++;
                    $('#coins').append(option);

                }


            })

        }
    })


    $.ajax({
        url: "/market7",
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (result) {


            $.ajax({
                url: "/currentPrice7",
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (result2) {


                    var tablecheck = 1;

                    var tbody = $('#Tb');

                    $.each(result, function (index, item) {

                        var market = result[index].market


                        $.each(result2, function (index2, item2) {


                            if (market == result2[index2].market) {

                                var row = $('<tr>');

                                var td1 = $('<td id=' + result[index].korean_name + ' >').text(result[index].korean_name + "[" + result[index].market + "]"); //+ "[" + result[index].market + "]"
                                tablecheck++;
                                row.append(td1);
                                var td2 = $('<td id=' + tablecheck + '>').text(result2[index2].trade_price + "원");
                                tablecheck++;
                                row.append(td2);


                                if (result2[index2].opening_price > result2[index2].trade_price) {

                                    var td3 = $('<td  class="text-blue text-bold"  id=' + tablecheck + '>').text("-" + ((result2[index2].opening_price - result2[index2].trade_price) / result2[index2].opening_price * 100).toFixed(2) + "%");
                                    row.append(td3);
                                    tbody.append(row);


                                } else if (result2[index2].trade_price > result2[index2].opening_price) {


                                    var td3 = $('<td class="text-red text-bold"  id=' + tablecheck + '>').text("+" + ((result2[index2].trade_price - result2[index2].opening_price) / result2[index2].opening_price * 100).toFixed(2) + "%");
                                    row.append(td3);
                                    tbody.append(row);


                                } else if (result2[index2].opening_price = result2[index2].trade_price) {


                                    var td3 = $('<td class="table-primary text-bold text-black"  id=' + tablecheck + '>').text("0.0%");
                                    row.append(td3);
                                    tbody.append(row);

                                }

                                tablecheck++;

                            }

                        })

                    })
                }

            })

        }

    })


    $(document).ready(function () {

        setInterval(function () {

            var tbody = $('#Tb');

            //tbody.empty();

            tablecheck = 0;
            $.ajax({
                url: "/market7",
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (result) {

                    var tablecheck = 0;


                    $.ajax({
                        url: "/currentPrice7",
                        type: "GET",
                        dataType: "json",
                        contentType: "application/json",
                        success: function (result2) {

                            tablecheck = 1;

                            $.each(result, function (index, item) {

                                var market = result[index].market

                                $.each(result2, function (index2, item2) {

                                    if (market == result2[index2].market) {


                                        var tr2 = $('#' + tablecheck)

                                        $('#' + result[index].korean_name).text(result[index].korean_name+ "[" + result[index].market + "]"); //+ "[" + result[index].market + "]"

                                        tablecheck++;
                                        $('#' + tablecheck).text(result2[index2].trade_price + "원");

                                        tablecheck++;

                                        if (result2[index2].opening_price > result2[index2].trade_price) {

                                            if ($('#' + tablecheck).text() != "-" + ((result2[index2].opening_price - result2[index2].trade_price) / result2[index2].opening_price * 100).toFixed(2) + "%") {

                                                $('#' + tablecheck).text("-" + ((result2[index2].opening_price - result2[index2].trade_price) / result2[index2].opening_price * 100).toFixed(2) + "%");

                                                $('#' + tablecheck).css("background-color", "yellow");

                                                /* if($('#' + tablecheck).hasClass('back-color-white')){
	                                              	$('#' + tablecheck).removeClass('back-color-white')

                                                }else if($('#' + tablecheck).hasClass('back-color-yellow')){
                                                	$('#' + tablecheck).removeClass('back-color-yellow')
                                                }else{
												} */

                                                //$('#' + tablecheck).addClass("back-color-yellow");

                                            } else {
                                                $('#' + tablecheck).text("-" + ((result2[index2].opening_price - result2[index2].trade_price) / result2[index2].opening_price * 100).toFixed(2) + "%");

                                                $('#' + tablecheck).css("background-color", "white");


                                               /*  if($('#' + tablecheck).hasClass('back-color-white')){
	                                              	$('#' + tablecheck).removeClass('back-color-white')

                                                }else if($('#' + tablecheck).hasClass('back-color-yellow')){
                                                	$('#' + tablecheck).removeClass('back-color-yellow')
                                                }else{
												}
 */
                                                //$('#' + tablecheck).addClass("back-color-white");

                                            }


                                        } else if (result2[index2].trade_price > result2[index2].opening_price) {


                                            if ($('#' + tablecheck).text() != "+" + ((result2[index2].trade_price - result2[index2].opening_price) / result2[index2].opening_price * 100).toFixed(2) + "%") {

                                                $('#' + tablecheck).text("+" + ((result2[index2].trade_price - result2[index2].opening_price) / result2[index2].opening_price * 100).toFixed(2) + "%");

                                                $('#' + tablecheck).css("background-color", "yellow");
                                               /*  if($('#' + tablecheck).hasClass('back-color-white')){
	                                              	$('#' + tablecheck).removeClass('back-color-white')

                                                }else if($('#' + tablecheck).hasClass('back-color-yellow')){
                                                	$('#' + tablecheck).removeClass('back-color-yellow')
                                                }else{
												} */

                                                //$('#' + tablecheck).addClass("back-color-yellow");

                                            } else {
                                                $('#' + tablecheck).text("+" + ((result2[index2].trade_price - result2[index2].opening_price) / result2[index2].opening_price * 100).toFixed(2) + "%");

                                                $('#' + tablecheck).css("background-color", "white");
                                             /*    if($('#' + tablecheck).hasClass('back-color-white')){
	                                              	$('#' + tablecheck).removeClass('back-color-white')

                                                }else if($('#' + tablecheck).hasClass('back-color-yellow')){
                                                	$('#' + tablecheck).removeClass('back-color-yellow')
                                                }else{
												} */

                                                //$('#' + tablecheck).addClass("back-color-white");

                                            }


                                        } else if (result2[index2].opening_price = result2[index2].trade_price) {


                                            $('#' + tablecheck).text("0.0%");


                                        }
                                        tablecheck++;

                                    }

                                })

                            })


                        }

                    })

                }

            })
        }, 5000);
    })

    $('#seachCoin').on('click', function () {

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
				  title: "조회하였습니다.(회색으로 표시됩니다.)"
				});



        var coinName = $('#coins').val();

        $.ajax({
            url: "/market7",
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function (result) {

                $.each(result, function (i, item) {
                    $('#' + result[i].korean_name).css("background-color", "white");
                })

                $.each(result, function (j, item) {

                    if (coinName == result[j].korean_name) {

                        $('#' + coinName).css("background-color", "silver");

                    }

                })
            }
        })
    })

    $('#reset').on('click', function () {

        $.ajax({
            url: "/market7",
            type: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function (result) {

                $.each(result, function (i, item) {
                    $('#' + result[i].korean_name).css("background-color", "white");
                })

            }
        })
    })

</script>
</body>

</html>