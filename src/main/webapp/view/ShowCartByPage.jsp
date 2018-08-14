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
   <h1>展示购物车</h1>
 <table>
   <tr>
      <th>购物车id</th>
      <th>商品id</th>
      <th>商品名称</th>
      <th>商品价格</th>
      <th>商品图片</th>
      <th>商品规格</th>
      <th>商品库存</th>
      <th>商品描述</th>
      <th>商品数量</th>
      <th>商品操作</th>
   </tr>

  <c:forEach items="${pageModel.data}" var="cart">
   
   <tr>
    <td>${cart.id}</td>
    <td>${cart.product.id}</td>
	<td>${cart.product.name}</td>
	<td>${cart.product.price}</td>
	<td>${cart.product.image}</td>
	<td>${cart.product.rule}</td>
	<td>${cart.product.stock}</td>
	<td>${cart.product.desc}</td>
	<td>${cart.productNum}</td>
	<td>
	 <a href="cart?operation=5&id=${cart.id}">修改</a>
	 <a href="cart?operation=4&id=${cart.id}">删除</a>           
	</td>
   </tr>
   
  </c:forEach>
  
 </table>  
  
 <c:forEach begin="1" end="${pageModel.totalPage}" step="1" var="pageNo_q">
  <c:choose>
    <c:when  test="${pageModel.currentPage==pageNo_q }">
        <a style="color:red" href="cart?pageNo=${pageNo_q}&operation=2">${pageNo_q}</a>
    </c:when>
    <c:when  test="${pageModel.currentPage!=pageNo_q }">
        <a href="cart?pageNo=${pageNo_q}&operation=2&pageSize=2">${pageNo_q}</a>
    </c:when>  
  </c:choose>
 </c:forEach> 
   
   
   <%-- <a href="product?pageNo=      &operation=2">${pageNo}</a> 这句话中pagNo=应该为什么呢，因为有几页就会有几个a标签，所以要遍历totalPage总共有多少页 
   var当前循环的循环项           写pageNo是第几页的意思    step每次的步数  
   <c:forEach var="pageNo_q" begin="1" end="${pageModel.totalPage}"  step="1">
            <a href="product?pageNo=${pageNo_q}&operation=2">${pageNo_q}</a>
   </c:forEach> --%> 
   
   
</body>
</html>