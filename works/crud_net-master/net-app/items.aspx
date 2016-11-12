<%@ Page Language="C#" AutoEventWireup="true" CodeFile="items.aspx.cs" Inherits="items" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
    <style type="text/css">
        body
        {
            font-family: Arial;
            font-size: 10pt;
        }
        table
        {
            border: 1px solid #ccc;
            width: 450px;
            margin-bottom: -1px;
        }
        table th
        {
            background-color: #F7F7F7;
            color: #333;
            font-weight: bold;
        }
        table th, table td
        {
            padding: 5px;
            border-color: #ccc;
        }
    </style>
<body>
    <form id="form1" runat="server">
        <asp:GridView ID="GVItems" runat="server" AutoGenerateColumns="false" DataKeyNames="id"
        EmptyDataText="No records has been added." OnRowCancelingEdit="OnRowCancelingEdit" OnRowDataBound="OnRowDataBound" OnRowDeleted="GVItems_RowDeleted" OnRowEditing="OnRowEditing" OnRowUpdating="OnRowUpdating">
        <Columns>
            <asp:TemplateField HeaderText="ID" ItemStyle-Width="150">
                <ItemTemplate>
                    <asp:Label ID="lblId" runat="server" Text='<%# Eval("id") %>'></asp:Label>
                </ItemTemplate>
                <EditItemTemplate>
                    <asp:Label ID="lblId" runat="server" Text='<%# Eval("id") %>'></asp:Label>
                </EditItemTemplate>
            </asp:TemplateField>
            <asp:TemplateField HeaderText="Name" ItemStyle-Width="150">
                <ItemTemplate>
                    <asp:Label ID="lblName" runat="server" Text='<%# Eval("name") %>'></asp:Label>
                </ItemTemplate>
                <EditItemTemplate>
                    <asp:TextBox ID="txtName" runat="server" Text='<%# Eval("name") %>'></asp:TextBox>
                </EditItemTemplate>
            </asp:TemplateField>

              <asp:TemplateField HeaderText="File">
                <ItemTemplate>
                    <asp:Image Width="300" runat="server" ID="image" /> 
                </ItemTemplate>
                <EditItemTemplate>
                    <asp:FileUpload ID="FileUploadFileAttachment" runat="server" />
                </EditItemTemplate>
                </asp:TemplateField>
              <asp:TemplateField HeaderText="Created Time" ItemStyle-Width="150">
                <ItemTemplate>
                    <asp:Label ID="lblCreated" runat="server" Text='<%# Eval("created_time") %>'></asp:Label>
                </ItemTemplate>
                <EditItemTemplate>
                    <asp:Label ID="lblCreated" runat="server" Text='<%# Eval("created_time") %>'></asp:Label>
                </EditItemTemplate>
            </asp:TemplateField>
              <asp:TemplateField HeaderText="Updated Time" ItemStyle-Width="150">
                <ItemTemplate>
                    <asp:Label ID="lblUpdate" runat="server" Text='<%# Eval("updated_time") %>'></asp:Label>
                </ItemTemplate>
                <EditItemTemplate>
                    <asp:Label ID="lblUpdate" runat="server" Text='<%# Eval("updated_time") %>'></asp:Label>
                </EditItemTemplate>
            </asp:TemplateField>

          
            <asp:CommandField ButtonType="Link" ShowEditButton="true" ShowDeleteButton="true"
                ItemStyle-Width="150" />
        </Columns>
    </asp:GridView>
    <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse">
        <tr>
            <td style="width: 150px">
                Name:<br />
                <asp:TextBox ID="txtName" runat="server" Width="140" />
            </td>
            <td style="width: 300px">
                File:<br />
                <asp:FileUpload ID="FileUploadFileAttachment" runat="server" />
            </td>
            <td style="width: 100px">
                <asp:Button ID="btnAdd" runat="server" Text="Add" OnClick="Insert" />
            </td>
        </tr>
    </table>
    </form>
</body>
</html>
