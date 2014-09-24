<%-- 
    Document   : result
    Created on : Jan 26, 2014, 9:37:24 PM
    Author     : chenjie zhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>X=<%=request.getAttribute("inputX")%></h2>
        <h2>Operation: <%=request.getAttribute("operation")%></h2>
        <h2>Y=<%=request.getAttribute("inputY")%></h2>
        <h2>Result is: <%=request.getAttribute("output")%></h2>
    </body>
</html>
