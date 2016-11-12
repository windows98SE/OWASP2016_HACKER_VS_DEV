<?php
     
    require 'database.php';

    //check login
    session_start();
    if(!isset($_SESSION["user"]))
    {
        header("Location: login.php");
        exit();
    }

    if ( !empty($_POST) && !empty($_FILES)) {
        // keep track post values
        $name = $_POST['name'];

        $fileName = $_FILES['fileToUpload']['name'];
        $tmpName  = $_FILES['fileToUpload']['tmp_name'];
        $fileSize = $_FILES['fileToUpload']['size'];
        $fileType = $_FILES['fileToUpload']['type'];

        $fp      = fopen($tmpName, 'r');
        $content = fread($fp, filesize($tmpName));
        $file = $content;
        fclose($fp);
      
         
        // insert data
        
        $pdo = Database::connect();
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $sql = "INSERT INTO items (name,file) values(?, ?)";
        $q = $pdo->prepare($sql);
        $q->execute(array($name,$file));
        Database::disconnect();
        header("Location: index.php");
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link   href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
 
<body>
    <div class="container">
     
      <div class="span10 offset1">
          <div class="row">
              <h3>Create item</h3>
          </div>
   
          <form class="form-horizontal" action="create.php" method="post" enctype="multipart/form-data">
            <div class="control-group">
              <label class="control-label">Name</label>
              <div class="controls">
                  <input name="name" type="text"  placeholder="Name" value="<?php echo !empty($name)?$name:'';?>">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label">Upload</label>
              <div class="controls">
                  <input type="file" name="fileToUpload" id="fileToUpload">
              </div>
            </div>
            <div class="form-actions">
                <input type="submit" name="submit" class="btn btn-success" value="Create">
                <a class="btn btn-default" href="index.php">Back</a>
              </div>
          </form>
      </div>
                 
    </div> <!-- /container -->
  </body>
</html>