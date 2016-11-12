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

public partial class Login : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

    public void LoginUser(object sender, EventArgs e)
    {
        var constr = ConfigurationManager.ConnectionStrings["constr"].ConnectionString;

        var cn = new MySqlConnection(constr);
        //cn.Open();

        DataTable dt = new DataTable();
        using (var cmd = new MySqlCommand(string.Format("SELECT * FROM app.users WHERE username = '{0}' AND password = '{1}'", this.username.Text, CalculateMD5Hash(this.password.Text))))
        {
            using (var sda = new MySqlDataAdapter())
            {
                cmd.Connection = cn;
                sda.SelectCommand = cmd;
                sda.Fill(dt);


                if (dt.Rows.Count == 0)
                {
                    Response.Redirect("./login.aspx");

                    return;
                }
                Session["login"] = "name";
                Response.Redirect("./items.aspx");
                return;


            }
        }
    }

    public string CalculateMD5Hash(string password)
    {
        // given, a password in a string


        // byte array representation of that string
        byte[] encodedPassword = new UTF8Encoding().GetBytes(password);

        // need MD5 to calculate the hash
        byte[] hash = ((HashAlgorithm)CryptoConfig.CreateFromName("MD5")).ComputeHash(encodedPassword);

        // string representation (similar to UNIX format)
        string encoded = BitConverter.ToString(hash)
            // without dashes
           .Replace("-", string.Empty)
            // make lowercase
           .ToLower();
        return encoded;
    }
}