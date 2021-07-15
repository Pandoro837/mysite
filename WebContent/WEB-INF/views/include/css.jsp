<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String servletPath = request.getServletPath();
if (servletPath.contains("main")) {
	servletPath = "main";
} else if (servletPath.contains("user")) {
	servletPath = "user";
} else if (servletPath.contains("guestbook")) {
	servletPath = "guestbook";
}
%>

<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/<%=servletPath%>.css" rel="stylesheet" type="text/css">