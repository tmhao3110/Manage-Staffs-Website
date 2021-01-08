<%-- 
    Document   : viewStaff
    Created on : Oct 13, 2020, 8:05:01 AM
    Author     : Minh Hao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách nhân viên</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        ${loi}
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
                    <span class="navbar-brand">Danh sách nhân viên</span>
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
                <div style="padding-left: 15px">Tìm kiếm:<input type="search"> <a class="btn btn-outline-success" style="width: 70px; " >Tìm</a></div><br>
                <div class="col-9" style="left: 25%; position: absolute; width: 60%; height: 50%; padding-top: 50px" enctype="multipart/form-data">
                    <table class="table table-bordered" style="text-align: center" >
                        <tr>
                            <th>Mã nhân viên</th>
                            <th>Tên nhân viên</th>
                            <th>Giới tính</th>
                            <th>Ngày sinh</th>
                            <th>Hình</th>
                            <th>Email</th>
                            <th>Lương</th>
                            <th>Ghi chú</th>
                            <th>Mã phòng ban</th>
                            <th>Trạng thái</th>
                            <th>Cập nhật</th>
                            <th>Xóa</th>
                        </tr>
                        <c:forEach var="b" items="${listStaff}">
                            <tr>
                                <td>${b.staffid}</td>
                                <td>${b.staffname}</td>
                                <td>${b.getNameGioitinh()}</td>
                                <td>${b.getDate1()}</td>
                                <td><img src="images/${b.photo} " width="100px" height="100px"></td>
                                <td>${b.email}</td>
                                <td>${b.salary}</td>
                                <td>${b.notes}</td>
                                <td>${b.departid}</td>
                                <td>${b.getNameTrangthai()}</td>
                                <td><a class="btn btn-outline-success" href="laythongtinStaff.htm?thongtinStaff=${b.staffid}">Cập nhật</a></td>
                                <td><a class="btn btn-outline-danger" href="xoanhanvien.htm?deletenhanvien=${b.staffid}">Xóa</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a class="btn btn-outline-warning" href="returnInsertStaff.htm">Thêm</a>
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
        width: 93%;
        margin-left: 28px;
        margin-top: 50px;
    }

    .footer {
        position: relative;
        background-color: black;
        margin-top: 50%;
        height: 100px;
        width: 100%;
        display: block;
    }
</style>
