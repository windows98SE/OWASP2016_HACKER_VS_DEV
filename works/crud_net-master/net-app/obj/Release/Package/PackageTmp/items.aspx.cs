using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
public partial class items : System.Web.UI.Page
{
    private string constr = ConfigurationManager.ConnectionStrings["constr"].ConnectionString;
    protected void Page_Load(object sender, EventArgs e)
    {
        LoadItems();
    }

    private void LoadItems()
    {
        using (var cn = new MySqlConnection(constr))
        {
            using (var dt = new DataTable())
            {
                using (var cmd = new MySqlCommand("SELECT * FROM mysql.items where is_deleted=false"))
                {
                    using (var sda = new MySqlDataAdapter())
                    {
                        cmd.Connection = cn;
                        sda.SelectCommand = cmd;
                        sda.Fill(dt);
                        GVItems.DataSource = dt;
                        GVItems.DataBind();
                    }
                }
            }
        }
    }

    protected void Insert(object sender, EventArgs e)
    {
        if (Session["login"] == null) Response.Redirect("./login.aspx");
        var name = txtName.Text;
        var fFileAttachment = (FileUpload)FileUploadFileAttachment;
        //var bytes = File.ReadAllBytes(fFileAttachment.FileName);
        //var bytes = br.ReadBytes((long)fs.Length);
         var file = ReadAllBytes(fFileAttachment.PostedFile.InputStream);
        using (var cn = new MySqlConnection(constr))
        {
            using (var cmd = new MySqlCommand("INSERT INTO mysql.items (name,file,is_deleted,created_time,updated_time) VALUES (@Name,@File,false,current_timestamp,current_timestamp)"))
            {
                //cmd.Parameters.AddWithValue("@id", id);
                cmd.Parameters.AddWithValue("@Name", name);
                cmd.Parameters.AddWithValue("@File", file);
                cmd.Connection = cn;

                cn.Open();
                cmd.ExecuteNonQuery();
                cn.Close();
            }
        }
        LoadItems();
    }
    protected void OnRowEditing(object sender, GridViewEditEventArgs e)
    {
        if (Session["login"] == null) Response.Redirect("./login.aspx");

        GVItems.EditIndex = e.NewEditIndex;
        LoadItems();
    }
    protected void OnRowCancelingEdit(object sender, GridViewCancelEditEventArgs e)
    {
        GVItems.EditIndex = -1;
        LoadItems();
    }
    protected void GVItems_RowDeleted(object sender, GridViewDeletedEventArgs e)
    {
        if (Session["login"] == null) Response.Redirect("./login.aspx");
        else Response.Redirect(Request.RawUrl);
    }
    protected void OnRowDataBound(object sender, GridViewRowEventArgs e)
    {
        if(e.Row.RowType==DataControlRowType.DataRow && e.Row.RowIndex != GVItems.EditIndex){
            var name = ((Label)(e.Row.FindControl("lblName"))).Text;
            if (e.Row.RowType == DataControlRowType.DataRow) {
                var data = (DataRowView)e.Row.DataItem;
                var bytes = (byte[])data.Row.ItemArray[2];
                string base64String=Convert.ToBase64String(bytes, 0, bytes.Length);
                ((Image)e.Row.FindControl("image")).ImageUrl = Convert.ToString("data:image/jpeg;base64,") + base64String;
            }
        }
    }
    protected void OnRowUpdating(object sender, GridViewUpdateEventArgs e)
    {

        var row = GVItems.Rows[e.RowIndex];
        var id=Convert.ToInt32(GVItems.DataKeys[e.RowIndex].Values[0]);
        var name = ((TextBox)row.FindControl("txtName")).Text;
        var fileUpload = (FileUpload)row.FindControl("FileUploadFileAttachment");
        var file = ReadAllBytes(fileUpload.PostedFile.InputStream);

            using (var cn = new MySqlConnection(constr))
            {
                using(var cmd = new MySqlCommand("UPDATE mysql.items SET Name = @Name, File=@File,created_time=created_time,updated_time=DEFAULT WHERE id = @id"))
                {
                    cmd.Parameters.AddWithValue("@id", id);
                    cmd.Parameters.AddWithValue("@Name", name);
                    cmd.Parameters.AddWithValue("@File", file);
                    cmd.Connection = cn;

                    cn.Open();
                    cmd.ExecuteNonQuery();
                    cn.Close();
                }
        }
        GVItems.EditIndex = -1;
        LoadItems();
    }

    public byte[] ReadAllBytes(Stream stream)
    {
        using (var ms = new MemoryStream())
        {
            stream.CopyTo(ms);
            return ms.ToArray();
        }
        
        
    }
}