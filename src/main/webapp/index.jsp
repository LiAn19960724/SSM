<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

<form action="/login" method="post">
    用户名：<input type="text" name="userName"><br/>
    密  码：<input type="password" name="userPassword"><br/>
    <input type="submit" value="提交">
</form>

<a href="/Redis">点击获取Redis里面的值</a><br/>

<form action="/Page" method="post">
    当前页：<input type="text" name="pageNum"><br/>
    每页记录数：<input type="text" name="pageSize"><br/>
    <input type="submit" value="提交">
</form>
<img src="image/001.jpg"/>

</body>
</html>
