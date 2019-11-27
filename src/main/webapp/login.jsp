<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录</title>
</head>
<body>

<form action="/doLogin" method="post">
    用户名：<input type="text" name="userName"><br/>
    密  码：<input type="password" name="userPassword"><br/>
    <input type="submit" value="提交">
</form>

</body>
</html>
