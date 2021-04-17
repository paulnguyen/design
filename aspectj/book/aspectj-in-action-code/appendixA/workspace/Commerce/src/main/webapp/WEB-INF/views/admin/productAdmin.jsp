<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<table>
	<tr>
		<td>Product:</td>
		<td>${product.id}</td>
	</tr>
	<tr>
		<td>Name:</td>
		<td>${product.name}</td>
	</tr>
	<tr>
		<td><a href="productEdit.htm?productId=${product.id}">Edit</a></td>
		<td><a href="productDelete.htm?productId=${product.id}">Delete</a></td>
	</tr>
</table>

</div>
</body>

</html>