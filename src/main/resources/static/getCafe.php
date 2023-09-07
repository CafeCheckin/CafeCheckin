<?php
    header("Content-Type:application/json");
    include "db.php";
    $db = new MysqliDb ('localhost', 'root', 'wnstjr6522', 'cafetour');
    $list = $db->get('cafe_information',2);
    print_r($list);
    echo json_encode($list);
