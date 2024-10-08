<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<style>
	  /*    //<uniquifier>: Use a unique and descriptive class name
        // <weight>: Use a value from 100 to 900*/

        .noto-sans-kr-font {
                           font-family: "Noto Sans KR", sans-serif;
                           font-optical-sizing: auto;
                           font-weight: 400;
                           font-style: normal;
                       }
        body {
            background-color: #3e94ec;
            font-family: "Roboto", helvetica, arial, sans-serif;
            font-size: 16px;
            font-weight: 400;
            text-rendering: optimizeLegibility;
        }

        div.table-title {
            display: block;
            margin: auto;
            max-width: 600px;
            padding: 5px;
            width: 100%;
        }

        .table-title h3 {
            color: #fafafa;
            font-size: 30px;
            font-weight: 400;
            font-style: normal;
            font-family: "Roboto", helvetica, arial, sans-serif;
            text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
            text-transform: uppercase;
        }


        /*** Table Styles **/

        .table-fill {
            background: white;
            border-radius: 3px;
            border-collapse: collapse;
            height: 320px;
            margin: auto;
            max-width: 600px;
            padding: 5px;
            width: 100%;
            box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
            animation: float 5s infinite;
        }

        th {
            color: #D5DDE5;;
            background: #1b1e24;
            border-bottom: 4px solid #9ea7af;
            border-right: 1px solid #343a45;
            font-size: 23px;
            font-weight: 100;
            padding: 24px;
            text-align: left;
            text-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
            vertical-align: middle;
        }

        th:first-child {
            border-top-left-radius: 3px;
        }

        th:last-child {
            border-top-right-radius: 3px;
            border-right: none;
        }

        tr {
            border-top: 1px solid #C1C3D1;
            border-bottom-: 1px solid #C1C3D1;
            color: #666B85;
            font-size: 16px;
            font-weight: normal;
            text-shadow: 0 1px 1px rgba(256, 256, 256, 0.1);
        }


        tr:first-child {
            border-top: none;
        }

        tr:last-child {
            border-bottom: none;
        }

        tr:nth-child(odd) td {
            background: #EBEBEB;
        }


        tr:last-child td:first-child {
            border-bottom-left-radius: 3px;
        }

        tr:last-child td:last-child {
            border-bottom-right-radius: 3px;
        }

        td {
            background: #FFFFFF;
            padding: 20px;
            text-align: left;
            vertical-align: middle;
            font-weight: 300;
            font-size: 18px;
            text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
            border-right: 1px solid #C1C3D1;
            color: black;
        }

        td:last-child {
            border-right: 0px;
        }

        th.text-left {
            text-align: left;
        }

        th.text-center {
            text-align: center;
        }

        th.text-right {
            text-align: right;
        }

        td.text-left {
            text-align: left;
        }

        td.text-center {
            text-align: center;
            font-weight: bold;
            color: black;
        }

        td.text-right {
            text-align: right;
        }

        input {
            background-color: #fff;

            padding: 0.9rem 0.9rem;
            margin: 0.5rem 0;
            width: 90%;
        }


        .color .red {
            background: #fa5a5a;
        }

        .color .yellow {
            background: #f0d264;
        }

        .color .green {
            background: #82c8a0;
        }

        .color .cyan {
            background: #7fccde;
        }

        .color .blue {
            background: #6698cb;
        }

        .color .purple {
            background: #cb99c5;
        }

        .content,
        .content-gradient,
        .content-3d {
            margin: 40px auto;
        }

        .content {
            width: 80%;
            max-width: 700px;
        }

        .content-3d {
            width: 50%;
            max-width: 300px;
        }

        pre {
            width: 100%;
            padding: 30px;
            background-color: rgba(0, 0, 0, 0.72);
            color: #f8f8f2;
            border-radius: 0 0 4px 4px;
            margin-top: 20px;
            white-space: pre-wrap; /* css-3 */
            white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
            white-space: -pre-wrap; /* Opera 4-6 */
            white-space: -o-pre-wrap; /* Opera 7 */
            word-wrap: break-word; /* Internet Explorer 5.5+ */
        }

        pre .bt {
            color: #f8f8f2;
        }

        /* <> */
        pre .anc {
            color: #f92672;
        }

        /* anchor tag */
        pre .att {
            color: #a6a926;
        }

        /* attribute */
        pre .val {
            color: #e6db74;
        }

        /* value */

        .btn-container, .container {
            background-color: white;
            border-radius: 4px;
            text-align: center;
            margin-bottom: 40px;
        }

        .container h2 {
            padding-top: 30px;
            font-weight: 300;
        }

        .btn, .btn-two {
            margin: 9px;
        }

        .btn-gradient {
            margin: 5px;
        }

        a[class*="btn"] {
            text-decoration: none;
        }

        input[class*="btn"],
        button[class*="btn"] {
            border: 0;
        }

        /* Here you can change the button sizes */
        .btn.large,
        .btn-two.large,
        .btn-effect.large {
            padding: 20px 40px;
            font-size: 22px;
        }

        .btn.small,
        .btn-two.small,
        .btn-gradient.small,
        .btn-effect.small {
            padding: 8px 18px;
            font-size: 14px;
        }

        .btn.mini,
        .btn-two.mini,
        .btn-gradient.mini,
        .btn-effect.mini {
            padding: 4px 12px;
            font-size: 12px;
        }

        .btn.block,
        .btn-two.block,
        .btn-gradient.block,
        .btn-effect.block {
            display: block;
            width: 60%;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }

        .btn-gradient.large {
            padding: 15px 45px;
            font-size: 22px;
        }

        /* Colors for .btn and .btn-two */
        .btn.blue, .btn-two.blue {
            background-color: #7fb1bf;
        }

        .btn.green, .btn-two.green {
            background-color: #9abf7f;
        }

        .btn.red, .btn-two.red {
            background-color: #fa5a5a;
        }

        .btn.purple, .btn-two.purple {
            background-color: #cb99c5;
        }

        .btn.cyan, .btn-two.cyan {
            background-color: #7fccde;
        }

        .btn.yellow, .btn-two.yellow {
            background-color: #f0d264;
        }

        .rounded {
            border-radius: 10px;
        }

        /* default button style */
        .btn {
            position: relative;
            border: 0;
            padding: 15px 25px;
            display: inline-block;
            text-align: center;
            color: white;
        }

        .btn:active {
            top: 4px;
        }

        /* color classes for .btn */
        .btn.blue {
            box-shadow: 0px 4px #74a3b0;
        }

        .btn.blue:active {
            box-shadow: 0 0 #74a3b0;
            background-color: #709CA8;
        }

        .btn.green {
            box-shadow: 0px 4px 0px #87a86f;
        }

        .btn.green:active {
            box-shadow: 0 0 #87a86f;
            background-color: #87a86f;
        }

        .btn.red {
            box-shadow: 0px 4px 0px #E04342;
        }

        .btn.red:active {
            box-shadow: 0 0 #ff4c4b;
            background-color: #ff4c4b;
        }

        .btn.purple {
            box-shadow: 0px 4px 0px #AD83A8;
        }

        .btn.purple:active {
            box-shadow: 0 0 #BA8CB5;
            background-color: #BA8CB5;
        }

        .btn.cyan {
            box-shadow: 0px 4px 0px #73B9C9;
        }

        .btn.cyan:active {
            box-shadow: 0 0 #73B9C9;
            background-color: #70B4C4;
        }

        .btn.yellow {
            box-shadow: 0px 4px 0px #D1B757;
        }

        .btn.yellow:active {
            box-shadow: 0 0 #ff4c4b;
            background-color: #D6BB59;
        }

        /* Button two - I have no creativity for names */
        .btn-two {
            color: white;
            padding: 15px 25px;
            display: inline-block;
            border: 1px solid rgba(0, 0, 0, 0.21);
            border-bottom-color: rgba(0, 0, 0, 0.34);
            text-shadow: 0 1px 0 rgba(0, 0, 0, 0.15);
            box-shadow: 0 1px 0 rgba(255, 255, 255, 0.34) inset,
            0 2px 0 -1px rgba(0, 0, 0, 0.13),
            0 3px 0 -1px rgba(0, 0, 0, 0.08),
            0 3px 13px -1px rgba(0, 0, 0, 0.21);
        }

        .btn-two:active {
            top: 1px;
            border-color: rgba(0, 0, 0, 0.34) rgba(0, 0, 0, 0.21) rgba(0, 0, 0, 0.21);
            box-shadow: 0 1px 0 rgba(255, 255, 255, 0.89), 0 1px rgba(0, 0, 0, 0.05) inset;
            position: relative;
        }

        /* 3D Button */
        .btn-3d {
            position: relative;
            display: inline-block;
            font-size: 22px;
            padding: 20px 60px;
            color: white;
            margin: 20px 10px 10px;
            border-radius: 6px;
            text-align: center;
            transition: top .01s linear;
            text-shadow: 0 1px 0 rgba(0, 0, 0, 0.15);
        }

        .btn-3d.red:hover {
            background-color: #e74c3c;
        }

        .btn-3d.blue:hover {
            background-color: #699DD1;
        }

        .btn-3d.green:hover {
            background-color: #80C49D;
        }

        .btn-3d.purple:hover {
            background-color: #D19ECB;
        }

        .btn-3d.yellow:hover {
            background-color: #F0D264;
        }

        .btn-3d.cyan:hover {
            background-color: #82D1E3;
        }

        .btn-3d:active {
            top: 9px;
        }

        /* 3D button colors */
        .btn-3d.red {
            background-color: #e74c3c;
            box-shadow: 0 0 0 1px #c63702 inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 8px 0 0 #C24032,
            0 8px 0 1px rgba(0, 0, 0, 0.4),
            0 8px 8px 1px rgba(0, 0, 0, 0.5);
        }

        .btn-3d.red:active {
            box-shadow: 0 0 0 1px #c63702 inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 0 0 1px rgba(0, 0, 0, 0.4);
        }

        .btn-3d.blue {
            background-color: #6DA2D9;
            box-shadow: 0 0 0 1px #6698cb inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 8px 0 0 rgba(110, 164, 219, .7),
            0 8px 0 1px rgba(0, 0, 0, .4),
            0 8px 8px 1px rgba(0, 0, 0, 0.5);
        }

        .btn-3d.blue:active {
            box-shadow: 0 0 0 1px #6191C2 inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 0 0 1px rgba(0, 0, 0, 0.4);
        }

        .btn-3d.green {
            background-color: #82c8a0;
            box-shadow: 0 0 0 1px #82c8a0 inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 8px 0 0 rgba(126, 194, 155, .7),
            0 8px 0 1px rgba(0, 0, 0, .4),
            0 8px 8px 1px rgba(0, 0, 0, 0.5);
        }

        .btn-3d.green:active {
            box-shadow: 0 0 0 1px #82c8a0 inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 0 0 1px rgba(0, 0, 0, 0.4);
        }

        .btn-3d.purple {
            background-color: #cb99c5;
            box-shadow: 0 0 0 1px #cb99c5 inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 8px 0 0 rgba(189, 142, 183, .7),
            0 8px 0 1px rgba(0, 0, 0, .4),
            0 8px 8px 1px rgba(0, 0, 0, 0.5);
        }

        .btn-3d.purple:active {
            box-shadow: 0 0 0 1px #cb99c5 inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 0 0 1px rgba(0, 0, 0, 0.4);
        }

        .btn-3d.cyan {
            background-color: #7fccde;
            box-shadow: 0 0 0 1px #7fccde inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 8px 0 0 rgba(102, 164, 178, .6),
            0 8px 0 1px rgba(0, 0, 0, .4),
            0 8px 8px 1px rgba(0, 0, 0, 0.5);
        }

        .btn-3d.cyan:active {
            box-shadow: 0 0 0 1px #7fccde inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 0 0 1px rgba(0, 0, 0, 0.4);
        }

        .btn-3d.yellow {
            background-color: #F0D264;
            box-shadow: 0 0 0 1px #F0D264 inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 8px 0 0 rgba(196, 172, 83, .7),
            0 8px 0 1px rgba(0, 0, 0, .4),
            0 8px 8px 1px rgba(0, 0, 0, 0.5);
        }

        .btn-3d.yellow:active {
            box-shadow: 0 0 0 1px #F0D264 inset,
            0 0 0 2px rgba(255, 255, 255, 0.15) inset,
            0 0 0 1px rgba(0, 0, 0, 0.4);
        }

        /* Gradient buttons */
        .btn-gradient {
            text-decoration: none;
            color: white;
            padding: 10px 30px;
            display: inline-block;
            position: relative;
            border: 1px solid rgba(0, 0, 0, 0.21);
            border-bottom: 4px solid rgba(0, 0, 0, 0.21);
            border-radius: 4px;
            text-shadow: 0 1px 0 rgba(0, 0, 0, 0.15);
        }

        /* Gradient - ugly css is ugly */
        .btn-gradient.cyan {
            background: rgba(27, 188, 194, 1);
            background: -webkit-gradient(linear, 0 0, 0 100%, from(rgba(27, 188, 194, 1)), to(rgba(24, 163, 168, 1)));
            background: -webkit-linear-gradient(rgba(27, 188, 194, 1) 0%, rgba(24, 163, 168, 1) 100%);
            background: -moz-linear-gradient(rgba(27, 188, 194, 1) 0%, rgba(24, 163, 168, 1) 100%);
            background: -o-linear-gradient(rgba(27, 188, 194, 1) 0%, rgba(24, 163, 168, 1) 100%);
            background: linear-gradient(rgba(27, 188, 194, 1) 0%, rgba(24, 163, 168, 1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#1bbcc2', endColorstr='#18a3a8', GradientType=0);
        }

        .btn-gradient.red {
            background: rgba(250, 90, 90, 1);
            background: -webkit-gradient(linear, 0 0, 0 100%, from(rgba(250, 90, 90, 1)), to(rgba(232, 81, 81, 1)));
            background: -webkit-linear-gradient(rgba(250, 90, 90, 1) 0%, rgba(232, 81, 81, 1) 100%);
            background: -moz-linear-gradient(rgba(250, 90, 90, 1) 0%, rgba(232, 81, 81, 1) 100%);
            background: -o-linear-gradient(rgba(250, 90, 90, 1) 0%, rgba(232, 81, 81, 1) 100%);
            background: linear-gradient(rgba(250, 90, 90, 1) 0%, rgba(232, 81, 81, 1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fa5a5a', endColorstr='#e85151', GradientType=0);
        }

        .btn-gradient.orange {
            background: rgba(255, 105, 30, 1);
            background: -webkit-gradient(linear, 0 0, 0 100%, from(rgba(255, 105, 30, 1)), to(rgba(230, 95, 28, 1)));
            background: -webkit-linear-gradient(rgba(255, 105, 30, 1) 0%, rgba(230, 95, 28, 1) 100%);
            background: -moz-linear-gradient(rgba(255, 105, 30, 1) 0%, rgba(230, 95, 28, 1) 100%);
            background: -o-linear-gradient(rgba(255, 105, 30, 1) 0%, rgba(230, 95, 28, 1) 100%);
            background: linear-gradient(rgba(255, 105, 30, 1) 0%, rgba(230, 95, 28, 1) 100%);
        }

        .btn-gradient.blue {
            background: rgba(102, 152, 203, 1);
            background: -moz-linear-gradient(top, rgba(102, 152, 203, 1) 0%, rgba(92, 138, 184, 1) 100%);
            background: -webkit-gradient(left top, left bottom, color-stop(0%, rgba(102, 152, 203, 1)), color-stop(100%, rgba(92, 138, 184, 1)));
            background: -webkit-linear-gradient(top, rgba(102, 152, 203, 1) 0%, rgba(92, 138, 184, 1) 100%);
            background: -o-linear-gradient(top, rgba(102, 152, 203, 1) 0%, rgba(92, 138, 184, 1) 100%);
            background: -ms-linear-gradient(top, rgba(102, 152, 203, 1) 0%, rgba(92, 138, 184, 1) 100%);
            background: linear-gradient(to bottom, rgba(102, 152, 203, 1) 0%, rgba(92, 138, 184, 1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#6698cb', endColorstr='#5c8ab8', GradientType=0);
        }

        .btn-gradient.purple {
            background: rgba(203, 153, 197, 1);
            background: -moz-linear-gradient(top, rgba(203, 153, 197, 1) 0%, rgba(181, 134, 176, 1) 100%);
            background: -webkit-gradient(left top, left bottom, color-stop(0%, rgba(203, 153, 197, 1)), color-stop(100%, rgba(181, 134, 176, 1)));
            background: -webkit-linear-gradient(top, rgba(203, 153, 197, 1) 0%, rgba(181, 134, 176, 1) 100%);
            background: -o-linear-gradient(top, rgba(203, 153, 197, 1) 0%, rgba(181, 134, 176, 1) 100%);
            background: -ms-linear-gradient(top, rgba(203, 153, 197, 1) 0%, rgba(181, 134, 176, 1) 100%);
            background: linear-gradient(to bottom, rgba(203, 153, 197, 1) 0%, rgba(181, 134, 176, 1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#cb99c5', endColorstr='#b586b0', GradientType=0);
        }

        .btn-gradient.yellow {
            background: rgba(240, 210, 100, 1);
            background: -webkit-gradient(linear, 0 0, 0 100%, from(rgba(240, 210, 100, 1)), to(rgba(229, 201, 96, 1)));
            background: -webkit-linear-gradient(rgba(240, 210, 100, 1) 0%, rgba(229, 201, 96, 1) 100%);
            background: -moz-linear-gradient(rgba(240, 210, 100, 1) 0%, rgba(229, 201, 96, 1) 100%);
            background: -o-linear-gradient(rgba(240, 210, 100, 1) 0%, rgba(229, 201, 96, 1) 100%);
            background: linear-gradient(rgba(240, 210, 100, 1) 0%, rgba(229, 201, 96, 1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f0d264', endColorstr='#e5c960', GradientType=0);
        }

        .btn-gradient.green {
            background: rgba(130, 200, 160, 1);
            background: -moz-linear-gradient(top, rgba(130, 200, 160, 1) 0%, rgba(130, 199, 158, 1) 100%);
            background: -webkit-gradient(left top, left bottom, color-stop(0%, rgba(130, 200, 160, 1)), color-stop(100%, rgba(130, 199, 158, 1)));
            background: -webkit-linear-gradient(top, rgba(130, 200, 160, 1) 0%, rgba(130, 199, 158, 1) 100%);
            background: -o-linear-gradient(top, rgba(130, 200, 160, 1) 0%, rgba(130, 199, 158, 1) 100%);
            background: -ms-linear-gradient(top, rgba(130, 200, 160, 1) 0%, rgba(130, 199, 158, 1) 100%);
            background: linear-gradient(to bottom, rgba(130, 200, 160, 1) 0%, rgba(124, 185, 149, 1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#82c8a0', endColorstr='#82c79e', GradientType=0);
        }

        .btn-gradient.red:active {
            background: #E35252;
        }

        .btn-gradient.orange:active {
            background: #E8601B;
        }

        .btn-gradient.cyan:active {
            background: #169499;
        }

        .btn-gradient.blue:active {
            background: #608FBF;
        }

        .btn-gradient.purple:active {
            background: #BD8EB7;
        }

        .btn-gradient.yellow:active {
            background: #DBC05B;
        }

        .btn-gradient.green:active {
            background: #72B08E;
        }
</style>

<div class="noto-sans-kr-font" style="background-color:white;"></div>
<div th:fragment="fragment-nav">

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="/mainPage">코인사부의 주식 - 이전</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/indices">지수(이거 누르시면 지수가 보입니다.)</a>
        </li>
      </ul>
      <form action="/equities/" onsubmit="this.action = this.action + this.stockCode.value; this.submit();" class="d-flex" role="search">
        <input class="form-control me-2" type="search" name="stockCode" placeholder="종목코드" aria-label="Search">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>
</div>
</div>
</html>