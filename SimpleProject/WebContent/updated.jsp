<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.Item"%>
<%
	//Update item details----------------------------------
	if (request.getParameter("itemID") != null && request.getParameter("itemCode") != null && request.getParameter("itemName") != null &&
			request.getParameter("itemPrice") != null && request.getParameter("itemDesc") != null) {
		
		Item itemObj = new Item();
		
		String stsMsg = itemObj.updateItem(request.getParameter("itemID"), request.getParameter("itemCode"), 
					                           request.getParameter("itemName"), request.getParameter("itemPrice"), request.getParameter("itemDesc") );
		
		session.setAttribute("statusMsg", stsMsg);		
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
</head>
<body>
	<h1>Items Management</h1>
		
	<form method="post" action="items.jsp">		
		Item code: <input name="itemCode" type="text" required><br> 
		Item name: <input name="itemName" type="text" required><br>
		Item price: <input name="itemPrice" type="text" required><br> 
		Item description: <input name="itemDesc" type="text" required><br>
		<input name="btnSubmit" type="submit" value="Save">
	</form>
		
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
	<br>
	<%
		Item itemObj = new Item();
		out.print(itemObj.readItems());
	%>
</body>
</html>