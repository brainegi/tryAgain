<!-- 管理者用お問い合わせ一覧画面 -->
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Question"%>

<%
	ArrayList<Question> list = (ArrayList<Question>) request.getAttribute("list");
%>

<html>
<head>
<title>管理者メニュー</title>
</head>


<body>
	<!--ヘッダー部分  -->
	<header>
		<h1 style="text-align: center;">管理者画面</h1>
		<hr style="height: 4px;; background-color: black">
	</header>


	<table>
		<tr>
			<td><a href="<%=request.getContextPath()%>/view/menu.jsp">[メニュー]</a></td>
			<td></td>
			<td><form action="<%=request.getContextPath()%>/logout"
					method=""></form> <input type="submit" value="ログアウト">
				</form></td>
		</tr>
	</table>

	<br>
	<h2 align="center">お問い合わせ一覧</h2>

	<%--メイン --%>
	<table align="center">
		<tr>
			<th style="width: 100px;">No.</th>
			<th style="width: 200px;">名前</th>
			<th style="width: 100px;">性別</th>
			<th style="width: 150px;">お問い合わせ日</th>
			<th style="text-align: center; width: 300px;">お問い合わせ内容</th>
		</tr>

		<%
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Question qObj = list.get(i);
		%>
		<tr style="text-align: center;">
			<td style="width: 100px;"><%=qObj.getNum()%></td>
			<td style="width: 200px;"><%=qObj.getName()%></td>
			<td style="width: 100px;"><%=qObj.getSex()%></td>
			<td style="width: 150px;"><%=qObj.getDate()%></td>
			<td style="width: 300px;"><a
				href="<%=request.getContextPath()%>>/detail"><%=qObj.getText()%></a></td>
		</tr>
		<%
			}
			}
		%>


	</table>
</body>

</html>