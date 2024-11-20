<?php 

// Set up associative array
$data = array('success'=> true,'message'=>'Success message: worked!');

// JSON encode and send back to the server
echo json_encode($data);

?>
