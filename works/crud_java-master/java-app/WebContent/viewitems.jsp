<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Item</title>
</head>
<body>

<%@page import="com.item.Items,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h1>Item List</h1>
<a href="./AddItem">Create</a> <a href ="./Search">Search</a>
<br><br>


<%
List<Items> list = (List<Items>)session.getAttribute("list");

//request.setAttribute("list",list);

%>

<table border="1" width="90%">
    <tr><th>ID</th><th>Name</th><th>File</th><th>Created Time</th><th>Updated Time</th><th>Update</th><th>Delete</th></tr>

<c:forEach items="${list}" var="u">
        <%
            String b64 ="";
            try{
            Items item = (Items)pageContext.getAttribute("u");
        BufferedImage bImage = item.getB();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( bImage, "jpg", baos );
        baos.flush();
        byte[] imageInByteArray = baos.toByteArray();
        baos.close();
         b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
            }
            catch(IllegalArgumentException e){
            }
        %>
	<tr><td>${u.getId()}</td><td>${u.getName()}</td><td> <img src="data:image/jpg;base64, <%=b64%>" width="300"/></td><td>${u.getTs()}</td><td>${u.getEditTs()}</td>
            <td>
                <form action="./editItem" method="post">
                    <input type="submit" value="Update" name="edit<%=((Items)pageContext.getAttribute("u")).getId()%>"/>
                    <input type="hidden" name="id" value="<%=((Items)pageContext.getAttribute("u")).getId()%>"/>
                </form>
            </td>
            <td>
                <form action="./deleteItem" method="post">
                    <input type="submit" value="Delete" name="delete<%=((Items)pageContext.getAttribute("u")).getId()%>"/>
                    <input type="hidden" name="id" value="<%=((Items)pageContext.getAttribute("u")).getId()%>"/>
                </form>
            </td>
        </tr>
</c:forEach>
</table>
<br/>

</body>
</html>