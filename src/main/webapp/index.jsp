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
        <script>
            function selectUser() {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function () {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        document.getElementById("test").innerHTML = xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST", "user/show.do", true);
                xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xmlhttp.send("id=1");
            }
            function requestJson(){

                var jsonData = {
                    "username" : "手机",
                    "role" : "root"
                };
                console.log('${pageContext.request.contextPath}');
                $.ajax({
                    type:'post',
                    url:'${pageContext.request.contextPath}/user/requestJson.do',
                    crossDomain:true,
                    contentType:'application/json;charset=utf-8',//指定为json类型
                    //数据格式是json串，商品信息
                    data:JSON.stringify(jsonData),
                    success:function(data){//返回json结果
                        alert(data.role);
                    }
                });
            }
        </script>
        <p id="test">Hello World!</p>
        <button type="button" onclick="selectUser()">onclick test</button>
        <button type="button" onclick="requestJson()">json test</button>
    </body>

</html>
