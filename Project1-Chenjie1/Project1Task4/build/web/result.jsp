<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>
<!-- notice that you do not put a semicolon at the end of this special
form of print-->


<html>
    <head>
        <title>NGA Picture</title>
    </head>
    <body>
        <h1><%= request.getAttribute("pictureTitle")%></h1><br>
        <img <%= request.getAttribute("pictureURL")%>><br><br>
         <form action="getNGAPicture" method="GET">
             <h2>Search for painting by another artist.</h2>
            <input type="text" name="searchWord" value="" /><br>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>

