<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=0.3">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/favicon.ico" type=image/x-icon>
<title>主页</title>
<%@include file="../common/import.jsp"%>
</head>
<body>
	<div class="container" style="box-shadow: 0px 0px 1px #666;">
		<%@include file="../common/head.jsp"%>
		<c:if test="${!empty user }">
			<div class="search" style="float: right; padding-right: 35px;">
				<form action="${pageContext.request.contextPath}/article/search" method="post" class="navbar-form navbar-left" role="search" style="float: right;">
					<input type="text" name="key" class="form-control" placeholder="Search">
					<button type="submit" class="btn btn-default">
						<span class="glyphicon glyphicon-search"></span> 搜索
					</button>
					<button type="button" class="btn btn-default" onclick="$('#sendContentArea').toggle()">
						<span class="glyphicon glyphicon-pencil"></span> 发帖
					</button>
				</form>
			</div>
		</c:if>
		<div class="row">
			<div class="col-md-8">
				<c:choose>
					<c:when test="${!empty user }">
						<c:forEach var="t" items="${articlePageBean.list }">
							<div class="page-header">
								<h4>
									<c:choose>
										<c:when test="${t.status eq 3}">
											<span class="label label-primary">置顶</span>
											<span class="label label-danger">精</span>
										</c:when>
										<c:when test="${t.status eq 2}">
											<span class="label label-primary">置顶</span>
										</c:when>
										<c:when test="${t.status eq 1}">
											<span class="label label-danger">精</span>
										</c:when>
									</c:choose>
									<%@include file="../common/articleData.jsp"%>
							</div>
						</c:forEach>
						<%-- 分页  --%>
						<div style="float: right;">
							<a href="${pageContext.request.contextPath}/article/list/${articlePageBean.currentPage==1?1:articlePageBean.currentPage-1 }"> 上一页 </a> 第${articlePageBean.currentPage }页/共${articlePageBean.totalPage }页 <a href="${pageContext.request.contextPath}/article/list/${articlePageBean.currentPage==articlePageBean.totalPage?articlePageBean.totalPage:articlePageBean.currentPage+1 }"> 下一页 </a>
						</div>
						<%-- 发帖框 --%>

						<div style="position: absolute; top: 100px; left: 50%; display: none; padding: 10px; background-color: darkgrey;" id="sendContentArea">
							<input type="text" class="form-control" maxlength="50" id="title" placeholder="输入帖子标题"><br />
							<div id="content" style="height: 240px;"></div>
							<br>
							<button type="button" class="btn btn-primary" id="addbtn" onclick="doClick()" style="float: right;">发布</button>
							<select id="lable" class="form-control" style="width: 200px; float: right;">
								<option value="随便说说">随便说说</option>
								<option value="大美汉服">大美汉服</option>
								<option value="娱乐明星">娱乐明星</option>
								<option value="爱综艺">爱综艺</option>
								<option value="追剧狂">追剧狂</option>
								<option value="看电影">看电影</option>
								<option value="体育">体育</option>
								<option value="小说">小说</option>
								<option value="生活家">生活家</option>
								<option value="闲趣">闲趣</option>
								<option value="游戏">游戏</option>
								<option value="动漫宅">动漫宅</option>
								<option value="高校">高校</option>
								<option value="地区">地区</option>
								<option value="人文自然">人文自然</option>
							</select>
						</div>
					</c:when>
					<c:otherwise>
						<center>
							<h3>登陆后才可进行发帖！</h3>
						</center>
					</c:otherwise>
				</c:choose>
				<%@include file="../common/foot.jsp"%>
			</div>
			<div class="col-md-4">
				<%-- 用户头像昵称 --%>
				<div style="padding-top: 120px;">
					<center>
						<c:choose>
							<c:when test="${empty user }">
								<img src="<c:url value="/resources/imgs/head.png"/>" class="img-circle" data-toggle="modal" data-target="#myModal">
								<br>
								<br>用户名：
								<a href="javascript:void(0);" data-toggle="modal" data-target="#myModal">未登录</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath}/user/manager/${user.uid }" target="_blank"> <img alt="${user.username }" src="<c:url value="${user.headimg }"/>" class="img-circle" style="width: 224px; height: 224px;">
								</a>
								<br>
								<br>用户名：
								${user.username }
								<br>
								<a href="<c:url value="/user/exit"/>">安全退出</a>
							</c:otherwise>
						</c:choose>
					</center>
				</div>
				<!-- 弹出对话框 -->
				<%@include file="../common/login.jsp"%>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/*发帖*/
		function doClick() {
			var markupStr = editor.$txt.html();
			var url = "${pageContext.request.contextPath}/article/add";
			var params = {
				title : $('#title').val(),
				content : markupStr,
				uid : '${user.uid}',
				lable : $('#lable').val()
			};
			$
					.ajax({
						'url' : url,
						'data' : params,
						'contentType' : "application/x-www-form-urlencoded; charset=utf-8",
						'type' : 'POST',
						'success' : function(data) {
							location.href = "${pageContext.request.contextPath}/article/list/1";
						},
						'error' : function() {
							alert("发帖失败！");
						}
					});
		}

		/* 注册 */
		function regist() {
			var url = "${pageContext.request.contextPath}/user/regist";
			var params = {
				username : $('#username').val(),
				password : $('#password').val()
			};
			$.post(url, params, function(data) {
				login();
			}, "json");
		}

		/* 登录 */
		function login() {
			var url = "${pageContext.request.contextPath}/user/login";
			var params = {
				username : $('#username').val(),
				password : $('#password').val()
			};
			$
					.post(
							url,
							params,
							function(data) {
								location.href = "${pageContext.request.contextPath}/article/list/1";
							}, "json");
		}
	</script>
	<input id="context" type="hidden" value="${pageContext.request.contextPath}">
	<%@include file="../common/editor.jsp"%>
</body>
</html>
