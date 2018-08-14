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
<table>

    <tr>
        <th>订单明细ID </th>
        <td>${items.id}  </td>
    </tr>
    <tr>
        <th>订单明细编号 </th>
        <td> ${items.order_no}  </td>
    </tr>
    <tr>
        <th>用户id </th>
        <td> ${items.user_id }  </td>
    </tr>
    <tr>
        <th>商品名字 </th>
        <td>${items.product_id}</td>
    </tr>
    <tr>
        <th>商品图片 </th>
        <td>${items.product_image}  </td>
    </tr>
    <tr>
        <th>生成订单时的价格 </th>
        <td> ${items.current_unit_price} </td>
    </tr>
    <tr>
        <th>商品数量 </th>
        <td> ${items.quantity} </td>
    </tr>
    <tr>
        <th>总价 </th>
        <td>${items.total_price} </td>
    </tr>
    <tr>
        <th>创建时间 </th>
        <td> ${items.create_time}  </td>
    </tr>
    <tr>
         <th>更新时间 </th>
         <td>${items.update_time} </td>
    </tr>
    <tr>
        <th>操作 </th>
        <td><a href="#">删除</a></td>
        <td><a href="#">修改</a></td>
    </tr>

</table>
</body>
</html>

