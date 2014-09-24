<%-- 
    Document   : result
    Created on : Jan 31, 2014, 7:29:21 AM
    Author     : chenjie zhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
    </head>
    <body>
        <h2>Input is: <%=request.getAttribute("output")%></h2>
        <h2>Is input a palindrome: <%=request.getAttribute("isPalin")%></h2>  
    </body>
</html>
