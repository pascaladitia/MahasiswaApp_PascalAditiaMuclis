<?php

include_once('koneksi.php');

if (!empty($_POST['mahasiswa_nama']) && !empty($_POST['mahasiswa_nohp']) 
    && !empty($_POST['mahasiswa_alamat'])) {

    $mahasiswa_nama = $_POST['mahasiswa_nama'];
    $mahasiswa_nohp = $_POST['mahasiswa_nohp'];
    $mahasiswa_alamat = $_POST['mahasiswa_alamat'];

    $query = "INSERT INTO mahasiswa(mahasiswa_nama, mahasiswa_nohp, mahasiswa_alamat)
        VALUES ('$mahasiswa_nama', '$mahasiswa_nohp', '$mahasiswa_alamat')";

    $insert = mysqli_query($connect, $query);

    if($insert) {
        set_response(true, "Success insert data");
    } else {
        set_reponse(false, "False insert data");
    }
} else {
    set_response(false, "nama, nohp, alamat, harus diisi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );
    echo json_encode($result);
}

?>