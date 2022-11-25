<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <!-- font awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
        integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- own -->
    <script src="punch.js"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body class="bgimg">
    <div class="container col">
    <div class="container row "></div>
    <div class="container row  marg ">
    <div class="container col-md-6  bg1img" >
        <div class="scaleimg"></div>
    </div>
    <div class="col-md-2 "></div>    
    <div class="col-md-4 card1">
        <form action="javascript:void(0);" method="post">
            <h5 class="loghead m-2">Login</h5>
            <select name="" id="loginselect" class="form-select">
                <option value="1">KGISL</option>
                <option value="1">KGISL TRUST</option>
                <option value="1">KGDESIGN</option>
                <option value="1">KGISTL</option>
                <option value="1">KGISL INFRASTRUCTURES</option>
            </select><br>
            <input type="text" class="form-control" name="uname" id="uname" placeholder="username"></br>
            <input type="password" class="form-control" name="pass" id="pass" placeholder="password"></br>
            <button  id ="sigin" onclick="getlogin()" class="btn btn-primary mb-2">sign in</button>
        </form>
    </div>
    <!-- <div class="col-md-1"></div> -->
</div>
</div>
</body>
</html>