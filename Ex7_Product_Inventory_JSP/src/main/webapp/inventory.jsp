<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // 1. Retrieve the product details
    String productId = request.getParameter("productId");
    String productName = request.getParameter("productName");
    String supplierName = request.getParameter("supplierName");

    // 2. Retrieve and convert numeric values
    double unitPrice = 0.0;
    int quantity = 0;

    try {
        if (request.getParameter("unitPrice") != null) {
            unitPrice = Double.parseDouble(request.getParameter("unitPrice"));
        }

        if (request.getParameter("quantity") != null) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }
    } catch (Exception e) {
        System.out.println("Form parsing error prevented: " + e.getMessage());
    }

    // 3. Calculate Total Inventory Value
    double totalValue = unitPrice * quantity;

    // 4. Determine Stock Status
    String stockStatus;

    if (quantity == 0) {
        stockStatus = "Out of Stock";
    } else if (quantity < 10) {
        stockStatus = "Low Stock";
    } else {
        stockStatus = "In Stock";
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory Report</title>
</head>

<body>

    <h2>Product Inventory Report</h2>

    <table border="1" cellpadding="10">

        <tr>
            <th>Product ID</th>
            <td><%= (productId != null) ? productId : "Not Provided" %></td>
        </tr>

        <tr>
            <th>Product Name</th>
            <td><%= (productName != null) ? productName : "Not Provided" %></td>
        </tr>

        <tr>
            <th>Unit Price</th>
            <td>₹<%= unitPrice %></td>
        </tr>

        <tr>
            <th>Quantity</th>
            <td><%= quantity %></td>
        </tr>

        <tr>
            <th>Supplier Name</th>
            <td><%= (supplierName != null) ? supplierName : "Not Provided" %></td>
        </tr>

        <tr>
            <th>Total Inventory Value</th>
            <td>₹<%= totalValue %></td>
        </tr>

        <tr>
            <th>Stock Status</th>
            <td><%= stockStatus %></td>
        </tr>

    </table>

    <br>

    <a href="product.jsp">Add Another Product</a>

</body>
</html>