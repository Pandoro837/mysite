<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String result = request.getParameter("result"); %>
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
					<h3>로그인</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">로그인</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="user">
					<div id="loginForm">
						<form action="/mysite/user" method="get">
							<input type="hidden" name="action" value="login">
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
							</div>

							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">비밀번호</label> 
								<input type="password" id="input-pass" name="pw" value="" placeholder="비밀번호를 입력하세요">
							</div>
							
							<% if("fail".equals(result)) {%>
								<p>로그인에 실패했습니다. 다시 로그인 해주세요</p>
							<%} %>
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">로그인</button>
							</div>

						</form>
					</div>
					<!-- //loginForm -->
				</div>
				<!-- //user -->
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