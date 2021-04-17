<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Product Details</title>
</head>

<body>
<div>

<h1>Product Details</h1>

<form:form modelAttribute="product" action="addToCart.htm">
	<form:hidden path="id" id="productId"/>
	<table>
		<tr>
			<td>Name:</td>
			<td>${product.name}</td>
		</tr>
		<tr>
			<td>Description:</td>
			<td>${product.description}</td>
		</tr>
		<tr>
			<td>Price:</td>
			<td>${product.price}</td>
		</tr>
		<tr>
			<td><input type="submit" value="Add to cart"/></td>
		</tr>
	</table>
</form:form>

</div>
</body>

</html>