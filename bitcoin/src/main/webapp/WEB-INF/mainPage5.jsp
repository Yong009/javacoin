<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>myPage</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>

		#wrap-mainPage{

		}


    </style>

</head>
<body id="wrap-mainPage">


<div id="header" ></div>


<table width="100%" >
    <tr>
        <td colspan="2" >

            <h4 id="userid" ><span th:text="${user}"
                                                           ></span>님 환영합니다.&emsp;<span>&emsp;자동매매 : </span><span id="auto" ></span><span id="profit2" ></span></span><span id="profit"></span></span></h4>

            <form id="logoutForm" action="/logout" method="post">
                <button type="submit" ><span >로그아웃</span></button>
            </form>


        </td>
    </tr>
    <tr>
        <td >
            <h2  >업비트 api코드</h2>
            <button type="button" id="accountBtn" ><span >잔고 조회</span>
            </button>
            <button type="button" id="accountSaveBtn"
                    >
              <span >Key 저장</span>
            </button>
            <br/>

            <br/>
            <label for="access" >access키</label><br/><br/>
            <input id="access" type="text" /><br/><br/>
            <label for="secret"  >secret키</label><br/><br/>
            <input id="secret" type="text" />

        </td>
        <td >
            <h4 >매도 예약 주문이 걸려 있으면 수량이 '0'으로 뜹니다!!!</h4>
            <h2 >my 잔고</h2>


            <table >
                <thead>
                <tr >
                    <th scope="row" >코인</th>
                    <th scope="row" >수량</th>
                    <th scope="row" >매도</th>

                </tr>
                </thead>
                <tbody id="Tb">

                </tbody>
            </table>
        </td>
    </tr>

</table>
<div id ="footer" ></div>
<script>

function loadHTML(elementId, url, callback) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById(elementId).innerHTML = xhr.responseText;
            if (callback) callback();
        }
    };
    xhr.send();
}

function onjQueryReady(callback) {
    const checkReady = setInterval(function () {
        if (typeof jQuery !== 'undefined') {
            clearInterval(checkReady);
            callback();
        }
    }, 100);
}

document.addEventListener("DOMContentLoaded", function() {
    loadHTML("header", "header.html", function() {
        onjQueryReady(function() {
            // jQuery is loaded, you can use jQuery dependent code here
            console.log("jQuery is ready!");
        });
    });
    loadHTML("footer", "footer.html");
});

/*function loadHTML(elementId, url) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById(elementId).innerHTML = xhr.responseText;
        }
    };
    xhr.send();
}

document.addEventListener("DOMContentLoaded", function() {
    loadHTML("header", "header.html");
    loadHTML("footer", "footer.html");
});*/


   // $('#header').load('header.html')
   // $('#footer').load('footer.html')


    $('a').click(function (event) {
        event.preventDefault();
    });



    var username = '[[${user}]]';
    //console.log(username);

    var userid = {
        id: username
    }

  /*   $.ajax({
        url:"/orderList",
        type:"POST",
        data: JSON.stringify(userid),
        dataType:"json",
        contentType:"application/json",
        success:function(result){
            //console.log(result);
        }
    }) */



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

            if(result[0].auto =='Y'){
                $('#auto').text('on    ')
            }else{
                $('#auto').text('off    ')
            }

            if($('#auto').text()=='on    '){
                $('#auto').css("color","red")
            }else{
                $('#auto').css("color","blue")
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
                             	url:"/currentPrice7",
                             	type:"Get",
                             	dataType:"json",
                             	contentType:"application/json",
                             	success: function(result5){

                            $.each(result0, function (i, item) {


                                $.each(result1, function (j, item) {

                                    if (result1[j].market.includes('KRW')) {

                                        if (('KRW-' + result0[i].currency) == 'KRW-BTC') {


                                        	var profit
                                            var nowPrice
                                			var orderPrice   = result[0].orderPrice;
                                			var autoPrice    = result[0].autoPrice;


                                			$.each(result5,function(p,item){

                                     			if(result5[p].market == 'KRW-BTC'){

                                     				nowPrice = result5[p].trade_price;
                                     				$('#profit2').text('수익률 : ')
                                     			}
                                     		})


                                     		if(orderPrice <= nowPrice){
                                				$('#profit').text((((nowPrice - orderPrice) / orderPrice) * 100).toFixed(2)+"%");
                                				$('#profit').css("color","red");
                                			}else{
                                				$('#profit').text((((orderPrice - nowPrice) / orderPrice) * 100).toFixed(2)+"%");
                                				$('#profit').css("color","blue");
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
                                    var td1 = $('<td class="center" style="font-weight: bold;" >').text(item.currency);
                                    row.append(td1);
                                    var td2 = $('<td class="center">').text(item.balance);
                                    row.append(td2);

                                    if (item.currency == '원화') {
                                        var sell2 = $('<td class="center">').val('');
                                        var td3 = $('<td class="center">').append(sell2);
                                        row.append(td3);

                                    } else if (check.includes(item.currency) == true) {
                                        var sell = $('<button type="button" id="sellBtn" class="btn-gradient blue" style="height:30px; width:50px; text-align: center;">').text('매도');
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
                        var td1 = $('<td class="center" style="font-weight: bold;">').text(item.currency);
                        row.append(td1);
                        var td2 = $('<td class="center">').text(item.balance);
                        row.append(td2);
                        var sell = $('<input type="button" id="sellBtn" class="btn-gradient blue" style="width:50px;" text-align: center;>').text('매도');
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

            if(result[0].auto =='Y'){
                $('#auto').text('on    ')
            }else{
                $('#auto').text('off    ')
            }

            if($('#auto').text()=='on    '){
                $('#auto').css("color","red")
            }else{
                $('#auto').css("color","blue")
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
                             	url:"/currentPrice7",
                             	type:"Get",
                             	dataType:"json",
                             	contentType:"application/json",
                             	success: function(result5){

	                            $.each(result0, function (i, item) {


	                                $.each(result1, function (j, item) {

	                                    if (result1[j].market.includes('KRW')) {

	                                        if (('KRW-' + result0[i].currency) == 'KRW-BTC') {



	                                        	var profit
	                                            var nowPrice
	                                			var orderPrice   = result[0].orderPrice;
	                                			var autoPrice    = result[0].autoPrice;

	                                			$.each(result5,function(p,item){

                                         			if(result5[p].market == 'KRW-BTC'){

                                         				nowPrice = result5[p].trade_price;
                                         				$('#profit2').text('수익률 : ')
                                         			}
                                         		})


                                         		if(orderPrice <= nowPrice){
                                    				$('#profit').text((((nowPrice - orderPrice) / orderPrice) * 100).toFixed(2)+"%");
                                    				$('#profit').css("color","red");
                                    			}else{
                                    				$('#profit').text((((orderPrice - nowPrice) / orderPrice) * 100).toFixed(2)+"%");
                                    				$('#profit').css("color","blue");
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

                  /*console.log(result)*/

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
                                var sell = $('<button type="button" id="sellBtn" class="btn-gradient blue" style="height:30px; width:25px;">').text('매도');
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


            }


        })
		alert("잔고를 조회하였습니다.")
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

                /* if (result) {
                    alert('저장 성공!!')
                } else {
                    alert('저장 실패!!')
                } */
            }

        })
        alert("코드를 저장하였습니다.")
    })


  /*    $.ajax({
		url:"/upbitRsi",
		type:"GET",
		dataType:"json",
		contentType:"application/json",
		success:function(result){
			console.log(result);
		}

    })

	$.ajax({
		url:"/rsi",
		type:"GET",
		dataType:"json",
		contentType:"application/json",
		success:function(result){
			console.log(result);
		}

    })
 */

 /*    $.ajax({
		url:"/koreaPrice",
		type:"GET",
		dataType:"json",
		contentType:"application/json",
		success:function(result){

			console.log(JSON.parse(result));
			console.log(JSON.Stringify(result));
		}
    }) */

    // $('#orderBtn').on('click', function () {
    //
    //     $.ajax({
    //
    //         url: "/order",
    //         type: "POST",
    //         dataType: "json",
    //         contentType: "applcation/json",
    //         success: function (result) {
    //
    //
    //         }
    //
    //
    //     })
    //
    //
    // })

/*
    $.ajax({
    	url:"/market7",
     	type:"Get",
     	dataType:"json",
     	contentType:"application/json",
     	success: function(result){

     		console.log(result);

     	}
     }) */


/*      $.ajax({
     	url:"/currentPrice7",
     	type:"Get",
     	dataType:"json",
     	contentType:"application/json",
     	success: function(result){

     		console.log(result);
     	}
     }) */

/*     $.ajax({
      	url:"/rsiSearch",
      	type:"Get",
      	dataType:"json",
      	contentType:"application/json",
      	success: function(result){

      		console.log(result);
      	}
      })*/


 /*     $.ajax({
        	url:"/rsi14",
        	type:"Get",
        	dataType:"json",
        	contentType:"application/json",
        	success: function(result){

        		console.log(result);
        	}

      })*/


</script>

</body>
</html>