<%--
  Created by IntelliJ IDEA.
  User: nicholas.chi
  Date: 2018/1/11
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HelloWorld</title>
</head>
<body>
    <h1>Hello World!</h1>

    <form action="/v17/testUpload" method="post" enctype="multipart/form-data">
        Img : <input type="file" name="image" >
        <input type="submit" value="submit">
    </form>


</body>
</html>
