<%-- 
    Document   : dangnhap
    Created on : Oct 8, 2020, 7:22:58 PM
    Author     : Minh Hao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>

        <div class="header">
            <a href="#"><img src="images/logo-removebg-preview.png"></a>
        </div>

        <div id="formlogin" style="position: relative; left: 60%;">
            <form action="login.htm" modelAttribute="user" method="post">
                <p style="font-size:36px; text-align:center; padding-top:40px">Đăng Nhập</p>
                <p class="rowedit">
                    <input style="font-weight:bold; margin-top: 10px;" type="text" name="username" value=""
                           placeholder="Email/Số điện thoại/Tên đăng nhập" />
                </p>
                <p style="color: red; font-style: italic">${saiTK}</p>
                <p class="rowedit">
                    <input style="font-weight:bold; margin-top: 20px;" type="password" name="password" placeholder="Mật khẩu" />
                </p>
                <p style="color: red; font-style: italic">${saiMK}</p>
                <p style="text-align:center">
                    <input id="login" type="submit" name="action" value="Đăng nhập" />
                </p>
            </form>
        </div>
    </body>
</html>

<style>
    * {
        padding: 0;
        margin: 0;
    }

    .header {
        height: 80px;
        text-align: center;
        background: white;
    }

    .header img {
        position: relative;
        padding-top: 15px;
        float: left;
        left: 100px;
    }

    body {
        background-image: url(images/work-731198_1920.jpg);
        background-size: 1920px 1080px;
        background-repeat: no-repeat;
    }

    #formlogin {
        width: 500px;
        height: 500px;
        margin-top: 6%;
        border-radius: 10px;
        background-color: #FFF;
    }

    input {
        height: 30px;
        border: thin solid #CCC;
        border-radius: 5px;
        font-family: Monaco;
    }

    p {
        text-align: left;
        font-family: Monaco;
    }

    .rowedit {
        margin-left: 4%;
    }

    .rowedit input {
        width: 460px;
        height: 50px;
    }

    .rowedit input:hover {
        border: thin solid #900;
    }

    #login {
        margin-top: 30px;
        width: 460px;
        background-color: #06F;
        border-radius: 5px;
        height: 50px;
        color: #FFF;
        font-size: larger;
    }

    #login:hover {
        background-color: #09F;
    }

    .hoac {
        text-align: center;
        color: rgb(185, 183, 183);
        margin-top: 10px;
    }

    .taikhoan {
        text-align: center;
        margin-top: 15px;
    }
</style>
