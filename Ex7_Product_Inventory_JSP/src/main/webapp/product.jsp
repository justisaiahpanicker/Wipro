<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Inventory</title>

    <style>
        label, input {
            display: block;
            margin: 12px;
        }
    </style>
</head>

<body>

    <h1>Product Inventory Management</h1>

    <form action="inventory.jsp" method="post">

        <label>Product ID</label>
        <input type="text" name="productId" required>

        <label>Product Name</label>
        <input type="text" name="productName" required>

        <label>Unit Price</label>
        <input type="number" name="unitPrice" step="0.01" required>

        <label>Quantity</label>
        <input type="number" name="quantity" required>

        <label>Supplier Name</label>
        <input type="text" name="supplierName" required>

        <input type="submit" value="Generate Inventory Report">

    </form>

</body>
</html>