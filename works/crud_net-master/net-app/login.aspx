<%@ Page Language="C#" AutoEventWireup="true" CodeFile="login.aspx.cs" Inherits="Login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        Username : <asp:TextBox ID="username" runat="server" />
        <br />
         Password : <asp:TextBox ID="password" runat="server" TextMode="Password" />
        <br />
        <asp:Button ID="login" Text="Login" OnClick="LoginUser" runat="server" />
    </div>
    </form>
</body>
</html>
