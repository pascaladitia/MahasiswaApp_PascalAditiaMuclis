<?php

include_once('koneksi.php');

if (!empty($_POST['id_mahasiswa']) && !empty($_POST['mahasiswa_nama']) 
&& !empty($_POST['mahasiswa_nohp']) && !empty($_POST['mahasiswa_alamat'])) {

    $id_mahasiswa = $_POST['id_mahasiswa'];
    $mahasiswa_nama = $_POST['mahasiswa_nama'];
    $mahasiswa_nohp = $_POST['mahasiswa_nohp'];
    $mahasiswa_alamat = $_POST['mahasiswa_alamat'];

    $query = "UPDATE mahasiswa set mahasiswa_nama = '$mahasiswa_nama', mahasiswa_nohp = '$mahasiswa_nohp',
     mahasiswa_alamat = '$mahasiswa_alamat' WHERE id_mahasiswa = '$id_mahasiswa'";

    $update = mysqli_query($connect, $query);

    if($update) {
        set_response(true, "Success update data");
    } else {
        set_reponse(false, "False update data");
    }
} else {
    set_response(false, "id, nama, nohp, alamat, harus diisi");
}

function set_response($isSuccess, $message) {
    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message
    );
    echo json_encode($result);
}

?>