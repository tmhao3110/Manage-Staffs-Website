<%-- 
    Document   : viewStaff
    Created on : Oct 13, 2020, 8:05:01 AM
    Author     : Minh Hao
--%>

<%@page import="hao.model.Record"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách đánh giá</title>
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
                    <span class="navbar-brand">Danh sách đánh giá</span>
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
                    <table class="table table-bordered" >
                        <tr>
                            <th>Mã đánh giá</th>
                            <th>Loại đánh giá</th>
                            <th>Ngày đánh giá</th>
                            <th>Lý do</th>
                            <th>Mã nhân viên</th>
                            <th>Cập nhật</th>
                            <th>Xóa</th>
                            <th>Gửi mail</th>
                        </tr>
                        <c:forEach var="c" items="${listRecord}">
                            
                            <tr>
                                <td>${c.recordid}</td>
                                <td>${c.getGetNameThanhtich()}</td>
                                <td>${c.date1}</td>
                                <td>${c.recordreason}</td>
                                <td>${c.staffid}</td>
                                <td><a class="btn btn-outline-success" href="laythongtinRecord.htm?maRecord=${c.recordid}">Cập nhật</a></td>
                                <td><a class="btn btn-outline-danger" href="xoandanhgia.htm?madanhgia=${c.recordid}">Xóa</a></td>
                                <td><a class="btn btn-outline-dark" href="requestEmail.htm?id=${c.recordid}">Gửi Mail</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a class="btn btn-outline-warning" href="returnInsertRecord.htm">Thêm</a>
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
