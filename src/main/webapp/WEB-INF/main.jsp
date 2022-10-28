<%--
  Created by IntelliJ IDEA.
  User: ARTA
  Date: 8/10/2021
  Time: 4:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <link rel="stylesheet" href="css/mainCss.css"/>
      <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
      <script src="webjars/jquery/3.4.1/jquery.min.js"></script>
    <title>Title</title>
  </head>
  <body>

  <div class="sidenav">
      <a  class="menu-item" onclick="setHeader('Daily planing')" href="#">Daily planing</a>
      <a  onclick="setHeader('Diaries')" href="#">Diaries</a>
      <a  onclick="setHeader('Gallery')" href="#">Gallery</a>
      <a  onclick="setHeader('about')" href="#">about</a>
  </div>
    <div class="home-header">
        <h2 id=header >daily</h2>
    </div>
  <div class="main">
      <jsp:include page="daily-planning/DailyPlanning.jsp"/>
<%--      <h2>Sidenav Example</h2>--%>
<%--      <input id="datePicker" type="date" class="form-control col-sm-2">--%>
<%--      <p>This sidenav is always shown.</p>--%>
  </div>
  </body>

<script>
    $("#datePicker, .datePickerC").persianDatepicker();
    function setHeader(headerName){
        $('#header').text(headerName);

    }
</script>
</html>
