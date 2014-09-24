<%-- 
    Document   : result
    Created on : Jan 26, 2014, 4:34:05 PM
    Author     : chenjie zhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hash Result</title>
    </head>
    <body>
        <h1> Hash of String "<%= request.getAttribute("hashText")%>":</h1>
        <h2> Hash Style: <%=request.getAttribute("hashStyle") %> </h2>
        <h2>Hex: <%=request.getAttribute("hex")%></h2> 
        <h2>Base 64: <%=request.getAttribute("base")%></h2>        
           
    </body>
</html>
