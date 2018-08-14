<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>修改购物车信息</h1>
   <form action="cart" method="post">
   <input type="hidden" name="operation" value="3"/>
     <input type="hidden" name="id" value="${cart.id}"/>
	 <table align="center">
	   <tr>
      <td>商品id</td>
      <td><input type="text" name="id" value="${cart.product.id}"/></td>
     </tr>
      <tr>
        <td>商品数量</td>
      <td><input type="text" name="num" value="${cart.productNum}"/></td>
      </tr>
	    <tr>
	   	    <td><input type="submit" value="确认修改"/></td>
	   </tr>
	 </table>

	</form>
    
</body>
</html>