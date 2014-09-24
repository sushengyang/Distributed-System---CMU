
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Give me an artist last name, I will give you a painting from United States National Gallery of Arts:</p>
        <form action="getNGAPicture" method="GET">
            <label for="letter">Type the word.</label>
            <input type="text" name="searchWord" value="" /><br>
            <input type="submit" value="Click Here" />
        </form>
    </body>
</html>

