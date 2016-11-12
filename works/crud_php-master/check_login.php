<?php
	session_start();
	mysql_connect("localhost","root","");
	mysql_select_db("app");

	$username = mysql_real_escape_string($_POST['username']);
	$password_unhash = mysql_real_escape_string($_POST['password']);
	$password = md5($password_unhash);
	$strSQL = "SELECT * FROM users WHERE Username = '".$username."' 
	and Password = '".$password."'";
	$objQuery = mysql_query($strSQL);

	if($objQuery === FALSE) { 
	    die(mysql_error()); // TODO: better error handling
	}

	$objResult = mysql_fetch_array($objQuery);
	if(!$objResult)
	{
			echo "Username and Password Incorrect!";
	}
	else
	{

			$_SESSION["user"] = $objResult["id"];

			session_write_close();
			
			// var_dump($_SESSION["user"]);
			// exit();
			header("Location: index.php");
			exit();
			
	}
	mysql_close();
?>
