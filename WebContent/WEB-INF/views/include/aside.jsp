<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String servletPath = request.getServletPath();
System.out.println(servletPath);
%>

<div id="aside">
	<%
	if (servletPath.contains("user")) {
	%>
	<h2>회원</h2>
	<ul>
		<li>회원정보</li>
		<li><a href="/mysite/user?action=loginForm">로그인</a></li>
		<li><a href="/mysite/user?action=joinForm">회원가입</a></li>
	</ul>
	<%
	} else if (servletPath.contains("guestbook")) {
	%>
	<div id="aside">
		<h2>방명록</h2>
		<ul>
			<li><a href="/mysite/guestbook?action=addList">일반방명록</a></li>
			<li>ajax방명록</li>
		</ul>
	</div>
	<%
	}
	%>
</div>