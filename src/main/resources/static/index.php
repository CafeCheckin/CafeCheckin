<?php
    require_once('db.php');
    $db = new MysqliDb ('localhost', 'root', 'wnstjr6522', 'cafetour');
print_r($db);
echo json_encode($db);