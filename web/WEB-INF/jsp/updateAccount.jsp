<%-- 
    Document   : home
    Created on : Oct 9, 2020, 8:46:24 AM
    Author     : Minh Hao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật tài khoản</title>
        <base href="${pageContext.servletContext.contextPath}/">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <header>
            <div class="header">
                <a href="trangchu.htm"><img src="images/logo-removebg-preview.png"></a>
                <div class="login"><span>Xin chào, ${tenUser}</span><a href="index.htm">Thoát</a></div>
            </div>
        </header>
        <div >
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
                        aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <button class="list-group-item list-group-item-action"
                            style="width: 386px; text-align: center; border-radius: 10px;" disabled>Danh Mục</button>
                    <span class="navbar-brand" href="#">Cập nhật tài khoản</span>
                </div>
            </nav>
        </div>
        <section>
            <div class="row">
                <div class="col-3">
                    <div class="list-group" style="text-align: center;">
                        <a href="danhsachtaikhoan.htm" class="list-group-item list-group-item-action list-group-item-warning">Danh sách tài khoản</a>
                        <a href="danhsachphongban.htm" class="list-group-item list-group-item-action list-group-item-primary">Phòng Ban</a>
                        <a href="danhsachnhanvien.htm" class="list-group-item list-group-item-action list-group-item-secondary">Nhân
                            Viên</a>
                        <a href="danhsachdanhgia.htm" class="list-group-item list-group-item-action list-group-item-success">Đánh Giá</a>
                        <!--<a href="tongket.htm" class="list-group-item list-group-item-action list-group-item-danger">Tổng Kết</a>-->
                    </div>
                </div>

                <div class="col-9" style="left: 30%; position: absolute; width: 60%; height: 50%;">
                    <div class="col-9" style="left: 5%; position: absolute; width: 80%; height: 50%;">
                        <form action="capnhattaikhoan.htm" modelAttribute="user" method="post" enctype="multipart/form-data">
                            <div style="margin-left: 200px; margin-right: 200px; width: 400px">
                                <div class="form-group">
                                    <label for="text">Tài khoản:</label>
                                    <input type="text" name="username" class="form-control" readonly="${RO}" value="${user1.username}" placeholder="Nhập tên đăng nhập" id="email">
                                </div>
                                <div class="form-group">
                                    <label for="pwd">Mật khẩu:</label>
                                    <input type="password" class="form-control" name="password" value="${user1.password}" placeholder="Nhập mật khẩu" id="pwd">
                                </div>
                                <div class="form-group">
                                    <label for="text">Họ tên:</label>
                                    <input type="text" class="form-control" name="fullname" value="${user1.fullname}" placeholder="Nhập họ tên" id="email">
                                </div>
                                <div class="form-group">
                                    <label for="text">Hình:</label>
                                    <input class="btn btn-outline-warning form-control" type="file" name="hinh"  />
                                    <img src="images/${user1.img} " width="100px" height="100px">
                                </div>
                            </div>
                            <input style="position: absolute; left: 33.7%; margin-top: 3%" class="btn btn-outline-danger" type="submit" name="act" value="Cập nhật tài khoản">
                        </form>
                    </div>
                    </section>
                </div>
        </section>
       
    </body>
</html>

<style>
    /* Header/logo Title */
    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }

    body {
        font-family: Arial, Helvetica, sans-serif;
        background-color: #FFF;

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

    .header .login {
        position: relative;
        float: right;
        right: 100px;
        top: 30px;
    }

    .navbar-brand {
        position: relative;
        left: 3%;
    }

    .row {
        position: absolute;
        /* border: thin solid red; */
        width: 93%;
        height: 100%;
        margin-left: 28px;
        margin-top: 50px;
        /* background-color: white; */
        /* box-shadow: 0 0 1px gray; */
    }

    .footer {
        position: relative;
        background-color: black;
        margin-top: 50%;
        height: 100px;
        width: 100%;
    }
</style>