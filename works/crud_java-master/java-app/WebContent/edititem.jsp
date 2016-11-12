<head><title>Edit Item Form</title></head>

<a href="./ViewItem">Back to View</a><br/>
<h1>Edit Item  ${id} ${name} </h1>
<form action="./edit" method="post" enctype="multipart/form-data">
<table>
<tr><td>Name:</td><td><input type="text" name="name" value="${name}"/></td></tr>
<tr><td>Select File:</td><td> <input type="file" name="file"/></td></tr>
<input type="hidden" name="id" value="${id}"/>
<tr><td colspan="2"><input type="submit" value="Edit Item"/></td></tr>
</table>
</form>