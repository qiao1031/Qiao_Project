<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>修改商品信息</h1>
   <form action="product" method="post">
   <input type="hidden" name="operation" value="3"/>
     <input type="hidden" name="id" value="${product.id}"/>
	 <table align="center">
	   <tr>
      <td>商品名称</td>
      <td><input type="text" name="pname" value="${product.name}"/></td>
     </tr>
      <tr>
        <td>商品描述</td>
      <td><input type="text" name="pdesc" value="${product.pdesc}"/></td>
      </tr>
       <tr>
        <td>商品价格</td>
      <td><input type="text" name="price" value="${product.price }"/></td>
      </tr>
       <tr>
        <td>商品规格</td>
      <td><input type="text" name="rule" value="${product.rule }"/></td>
      </tr>
       <tr>
        <td>商品图片</td>
      <td><input type="text" name="pimage" value="${product.image }"/></td>
      </tr>
        <tr>
        <td>商品库存</td>
      <td><input type="text" name="stock" value="${product.stock }"/></td>
      </tr>
	    <tr>
	   	    <td><input type="submit" value="确认修改"/></td>
	   </tr>
	 </table>

	</form>
    
</body>
</html>