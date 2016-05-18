<%--
  Created by IntelliJ IDEA.
  User: CuiH
  Date: 2016/5/15
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Actor</title>
</head>
<body>
<form:form modelAttribute="actorEditor" action="/add_actor" method="post">
    <p>
        <label for="name">name: </label>
        <form:input type="text" id="name" path="name"/>
        <form:errors path="name" cssClass="error"/>
    </p>

    <p>
        <label for="imageUrl">image url: </label>
        <form:input type="text" id="imageUrl" path="imageUrl"/>
        <form:errors path="imageUrl" cssClass="error"/>
    </p>

    <p>
        <label for="brief">brief: </label>
        <form:input type="text" id="brief" path="brief"/>
        <form:errors path="brief" cssClass="error"/>
    </p>

    <p>
        <label for="birthday">birthday: </label>
        <form:input type="text" id="birthday" path="birthday"/>
        (XXXX-XX-XX)
        <form:errors path="birthday" cssClass="error"/>
    </p>

    <p>
        <input id="submit" type="submit" value="Submit">
    </p>
</form:form>
</body>
</html>