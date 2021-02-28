<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.Item"%>

<%
	//Retrieving the values from items.jsp----------------------------------

	String itemID = request.getParameter("itemID");
	String itemCode = request.getParameter("itemCode");
	String itemName = request.getParameter("itemName");
	String itemPrice = request.getParameter("itemPrice");
	String itemDesc = request.getParameter("itemDesc");	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update item</title>
</head>
<body>
	<h1>Update item details</h1>
		
	<form method="post" action="updated.jsp">		
				   <input name="itemID" type="hidden" value="<%=itemID%>"> 
		Item code: <input name="itemCode" type="text" value="<%=itemCode%>"><br> 
		Item name: <input name="itemName" type="text" value="<%=itemName%>"><br>
		Item price: <input name="itemPrice" type="text" value="<%=itemPrice%>"><br> 
		Item description: <input name="itemDesc" type="text" value="<%=itemDesc%>"><br>
						  <input name="btnSubmit" type="submit" value="Update">
						  <input name='itemID' type='hidden' value="itemID">
						  <input name='itemCode' type='hidden' value="itemCode"> 
						  <input name='itemName' type='hidden' value="itemName"> 
						  <input name='itemPrice' type='hidden' value="itemPrice"> 
						  <input name='itemDesc' type='hidden' value="itemDesc">
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