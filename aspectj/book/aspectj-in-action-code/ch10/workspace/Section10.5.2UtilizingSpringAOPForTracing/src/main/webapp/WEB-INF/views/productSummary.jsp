<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<title>Product Summary</title>
</head>

<body>
<div>

<h1>Product Summary</h1>

<table>
	<c:forEach items="${products}" var="product">
		<tr>
			<td><a href="productDetails.htm?productId=${product.id}">${product.name}</a></td>
		</tr>
	</c:forEach>
</table>

<div>
	<a href="viewCart.htm">View Cart</a>
</div>
</div>
</body>

</html>