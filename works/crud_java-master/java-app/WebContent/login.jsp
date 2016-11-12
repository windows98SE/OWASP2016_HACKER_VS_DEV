

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="./login" method="post">
            <table>
            <tr><td>Username:</td><td><input type="text" name="username"/></td></tr>
            <tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
            <tr><td colspan="2"><input type="submit" value="Login"/></td></tr>
            </table>
        </form>
    </body>
</html>
