<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
    String dd = (String) request.getAttribute("serverUrl");

%>
<title>登录</title>
<head></head>
<script type="text/javascript">
    function login() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        if (username == ''){
            alert('用户名不能为空!');
            return;
        }
        if (password == ''){
            alert('密码不能为空!');
            return;
        }
        document.getElementById("frm").submit();
    }
</script>
<body>
<form action="${serverUrl}" id="frm" method="post">
    <label>用户名：</label><input type="text" id="username" name="username" style="width: 200px;height: 30px"/><br><br>
    <label>密&nbsp;&nbsp;&nbsp;&nbsp;码：</label><input type="password" id="password" name="password" style="width: 200px;height: 30px"/><br><br>
    <input type="button" value="登录" style="height: 30px; width: 100px;" onclick="login()">
</form>
</body>
</html>
