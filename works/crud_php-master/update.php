<?php
    require 'database.php';

    //check login
    session_start();
    if(!isset($_SESSION["user"]))
    {
        header("Location: login.php");
    exit();
    }
 
    $id = null;
    if ( !empty($_GET['id'])) {
        $id = $_REQUEST['id'];
    }
     
    if ( $id==null ) {
        header("Location: index.php");
    }
     
    if ( !empty($_POST) && !empty($_FILES) ) {
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
        
        $pdo = Database::connect();
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $sql = "UPDATE items  set name = ?, file = ?, updated_time=now() WHERE id = ?";
        $q = $pdo->prepare($sql);
        $q->execute(array($name,$file,$id));
        Database::disconnect();
        header("Location: index.php");

    } else {
        $pdo = Database::connect();
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $sql = "SELECT * FROM items where id = ?";
        $q = $pdo->prepare($sql);
        $q->execute(array($id));
        $data = $q->fetch(PDO::FETCH_ASSOC);
        $name = $data['name'];
        $file = $data['file'];
        Database::disconnect();
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
                        <h3>Update a Customer</h3>
                    </div>
             
                    <form class="form-horizontal" action="update.php?id=<?php echo $id?>" method="post" enctype="multipart/form-data">
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
                          <button type="submit" class="btn btn-success">Update</button>
                          <a class="btn" href="index.php">Back</a>
                        </div>
                    </form>
                </div>
                 
    </div> <!-- /container -->
  </body>
</html>