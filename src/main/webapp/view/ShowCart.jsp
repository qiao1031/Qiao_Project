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

    <c:forEach items="${carts}" var="cart">

        <tr>
            <td>${cart.id}</td>
            <td>${cart.product.id}</td>
            <td>${cart.product.name}</td>
            <td>${cart.product.price}</td>
            <td>${cart.product.image}</td>
            <td>${cart.product.rule}</td>
            <td>${cart.product.stock}</td>
            <td>${cart.product.pdesc}</td>
            <td>${cart.productNum}</td>
            <td>
                <a href="cart?operation=5&id=${cart.id}">修改</a>
                <a href="cart?operation=4&id=${cart.id}">删除</a>
            </td>

        </tr>

    </c:forEach>

</table>
<a href="OrderServlet?operation=1">立即下单</a>

</body>
</html>