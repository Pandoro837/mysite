<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
	String no = request.getParameter("no"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/include/css.jsp"></jsp:include>
<!--//css  -->

</head>

<body>
	<div id="wrap">

		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<!-- //header -->

		<jsp:include page="/WEB-INF/views/include/navigator.jsp"></jsp:include>
		<!-- //nav -->

		<div id="container" class="clearfix">
		
			<jsp:include page="/WEB-INF/views/include/aside.jsp"></jsp:include>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="/mysite/guestbook" method="post">
						<input type = "hidden" name = "action" value = "delete">
						<input type = "hidden" name = "no" value = "<%=no%>">
						<table id="guestDelete">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 25%;">
								<col style="width: 25%;">
							</colgroup>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="password" value = ""></td>
								<td class="text-left"><button type="submit">삭제</button></td>
								<td><a href="/mysite/guestbook?action=addList">[메인으로 돌아가기]</a></td>
							</tr>
						</table>
						<input type='hidden' name="" value=""> <input type='hidden' name="" value="">
					</form>

				</div>
				<!-- //guestbook -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>
