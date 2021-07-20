<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="servletPath" value="${pageContext.request.servletPath }"/>
<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<c:choose>
	<c:when test="${fn:contains(servletPath, 'main')}">				<!-- 서블릿 패스 내부에 main이 있을 때 -->
		<link href="/mysite/assets/css/main.css" rel="stylesheet" type="text/css">
	</c:when>													
	<c:when test="${fn:contains(servletPath, 'user')}">				<!-- 서블릿 패스 내부에 user가 있을 때 -->
		<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
	</c:when>
	<c:when test="${fn:contains(servletPath, 'guestbook')}">		<!-- 서블릿 패스 내부에 guestbook이 있을 때 -->
		<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	</c:when>
	<c:when test="${fn:contains(servletPath, 'board')}">		<!-- 서블릿 패스 내부에 board이 있을 때 -->
		<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
	</c:when>
</c:choose>	