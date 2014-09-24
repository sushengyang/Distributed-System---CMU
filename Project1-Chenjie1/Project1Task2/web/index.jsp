<%-- 
    Document   : index
    Created on : Jan 26, 2014, 8:39:28 PM
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
        <h1>Please input data:</h1>
        <form action="BigCalc" method="GET">
            First Input X:  <input type="text" name="inputX"><br>
            Second Input Y: <input type="text" name="inputY"><br>
            Operation:
            <select name="operations">
                <option value="add">Add</option>
                <option value="multiply">Multiply</option>
                <option value="relativelyprime">Relatively Prime</option>
                <option value="mod">Mod</option>
                <option value="modinverse">Mod Inverse</option>
                <option value="power">Power</option>           
            </select><br>
            <input type="submit" value="submit"/>
        </form>

    </body>
</html>
