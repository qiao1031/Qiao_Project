<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="CategoryServlet" method="get">
	<input type="hidden" name = "operation"  value="1" />
	<table>
		<tr>
			<td>父类ID</td>
			<td>

				<select  name="parent_id" >
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
				</select>

			</td>
		</tr>
		<tr>
			<td>类别名称</td>
			<td><input type="text" name="name" /></td>	
		</tr>
		<tr>
			<td>类别状态</td>
			<td><input type="text" name="status" /></td>	
		</tr>		
		<tr>
			<td>排序编号</td>
			<td><input type="text" name="sort_order" /></td>	
		</tr>	
		<%--<tr>
			<td>创建时间</td>
			<td><input type="text" name="create_time" /></td>	
		</tr>
		<tr>
			<td>更新时间</td>
			<td><input type="text" name="update_time" /></td>	
		</tr>		--%>
							
		<tr>
			<td><input type="submit" value="提交"/></td>
		</tr>
	
	</table>
	</form>
</body>
</html>