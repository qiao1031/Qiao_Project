<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>展示商品</h1>
  <h2><a href="AddProduct.jsp">添加商品</a></h2>
 <table border="1px" cellpadding="5" cellspacing="0">
 <tr>
   <th>商品id</th>
   <th>商品名称</th>
   <th>商品价格</th>
   <th>商品图片</th>
   <th>商品规格</th>
   <th>商品库存</th>
   <th>商品描述</th>
   <th>商品操作</th>
 </tr>

 <c:forEach items="${pageModel.data}" var="product">
   
 <tr>
    <td>${product.id}</td>
	<td>${product.name}</td>
	<td>${product.price}</td>
	<td>${product.image}</td>
	<td>${product.rule}</td>
	<td>${product.stock}</td>
	<td>${product.pdesc}</td>
	<td>
	<a href="product?operation=5&id=${product.id}">修改</a>
	<a href="product?operation=4&id=${product.id}">删除</a>             
	</td>
 </tr>
</c:forEach>
 </table>


  <c:forEach var="pageNoe" begin="1" end="${pageModel.totalPage}"  step="1">
      <c:choose>
          <c:when test="${pageModel.currentPage==pageNoe}">
              <a style="color:red" href="product?pageNo=${pageNoe}&operation=2">${pageNoe }</a>
          </c:when>
          <c:when test="${pageModel.currentPage!= pageNoe}">
              <a href="product?pageNo=${pageNoe}&operation=2">${pageNoe }</a>
          </c:when>
      </c:choose>
  </c:forEach>


<form action="product" method="get">

    <input type="hidden" name = "operation"  value="6" />
    <table>
        <tr>
            <td>请输入想查看的商品ID</td>
            <td><input type="text" name="id" /></td>
        </tr>

        <tr>
            <td><input style="width: 150px; height: 25px;" type="submit" value="提交"/></td>
        </tr>

    </table>

</form>

</body>
</html>

