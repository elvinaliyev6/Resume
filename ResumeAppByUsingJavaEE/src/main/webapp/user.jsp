<%-- 
   Document   : user
   Created on : Jan 6, 2022, 4:44:19 PM
   Author     : Hp
--%>

<%@page import="com.company.dao.inter.UserDaoInter" %>
<%@page import="com.company.main.Context" %>
<%@page import="com.company.entity.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<%
    UserDaoInter userDao = Context.instanceUserDao();

    User u = userDao.getById(6);
%>

<div>
    <form action="UserController" method="POST">
        <input type="hidden" name="id" value="<%=u.getId()%>"/>
        <label> name: </label>
        <input type="text" name="name" value="<%=u.getName()%>"/>
        <br/>
        <label> surname: </label>
        <input type="text" name="surname" value="<%=u.getSurname()%>"/>

        <input type="submit" name="save" value="Save"/>
        <input type="submit" name="save2" value="Save2"/>


    </form>
</div>

</body>
</html>
