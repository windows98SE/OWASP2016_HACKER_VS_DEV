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
            <div class="row">
                <h3>Item List</h3>
            </div>
            <div class="row">
            	<p>
                    <a href="create.php" class="btn btn-success">Create</a>
                    <a href="search.php" class="btn btn-success">Search</a>
                </p>
                <table class="table table-striped table-bordered">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                      <th>File</th>
                      <th>Created Time</th>
                      <th>Updated Time</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                  <?php
                   include 'database.php';
                   $pdo = Database::connect();
                   $sql = 'SELECT * FROM items ORDER BY id ASC';
                   foreach ($pdo->query($sql) as $row) {
                            echo '<tr>';
                            echo '<td>'. $row['id'] . '</td>';
                            echo '<td>'. $row['name'] . '</td>';
                            // echo '<td>'. $row['file'] . '</td>';

                            // $i = mysql_fetch_array($row['file']);
                            echo '<td><img src="data:image/jpeg;base64,'.base64_encode( $row['file'] ).'"/ width="100"></td>';
                            // echo '<td><img src="data:image/jpg;base64,'. $row['file'] .'"/></td>';
                            echo '<td>'. $row['created_time'] . '</td>';
                            echo '<td>'. $row['updated_time'] . '</td>';
                            echo '<td width=250>';
                            echo '<a class="btn btn-success" href="update.php?id='.$row['id'].'">Update</a>';
                            echo ' ';
                            echo '<a class="btn btn-danger" href="delete.php?id='.$row['id'].'">Delete</a>';
                            echo '</td>';
                            echo '</tr>';
                   }
                   Database::disconnect();
                  ?>
                  </tbody>
            </table>
        </div>
    </div> <!-- /container -->
  </body>
</html>