<%-- 
    Document   : index
    Created on : Jan 26, 2014, 4:22:57 PM
    Author     : Chenjie Zhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Text to Hash</title>
    </head>
    <body>
        <form action="ComputeHashes" method="GET">
            <h1><label for="letter">Please Input Text:</label></h1>
            <input type="text" name="hashText" value="" /><br>
            <input type="radio" name="Hash" value="MD5" checked="1">MD5<br>
            <input type="radio" name="Hash" value="SHA-1">SHA-1<br><br>
            <input type="submit" value="submit" /><br>
        </form>
    </body>
</html>
