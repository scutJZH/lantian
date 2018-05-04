<%--
  Created by IntelliJ IDEA.
  User: YeZhilie
  Date: 2018/3/7
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

    <head>
        <base href="<%=basePath%>">

        <title>HomePage</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <%--引入jquery--%>
        <script  src="jquery/jquery-3.3.1.min.js"></script>
    </head>


    <body>
        HelloWord!
    </body>

</html>
