<%@ page import="com.company.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 3/24/2022
  Time: 10:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

    <%
        User user=(User) session.getAttribute("loggedInUser");
//        throw new IllegalArgumentException("hey");
    %>
    <%="Welcome, "+user.getName()+"!!!"%>
