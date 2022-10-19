<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Donation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 <script src="admin-header.js"></script>
  <link rel="stylesheet" href="../../css/body-content.css">
</head>

<body>
   <jsp:include page="../layout/admin-header.jsp"></jsp:include>
    <div id="content">
    <div id="content-header">
    <h4>Quản Lý Luợt Quyên Góp</h4>
    <div id="table-filter" class="d-flex py-2">
    <input type="text" placeholder="ID" class="form-control">
    <input type="text" placeholder="Username" class="form-control">
    <input type="text" placeholder="Fund" class="form-control">
    <button type="button" class="btn btn-success btn-sm mx-1"><i class="fa-solid fa-magnifying-glass"></i> Tìm Kiếm</button>
    <button type="button" class="btn btn-primary btn-sm mx-1"><i class="fa-solid fa-plus"></i> Thêm Mới</button>
    </div>
    </div>
 <table class="table table-bordered table-sm">
          <thead class="table-primary">
          <th>ID</th>
          <th>Username</th>
          <th>Fund</th>
          <th>Donation Amount</th>
         <th>Donation Message</th>
         <th>Created Date</th>
          <th style="width:150px">Function</th>
          </thead>
         <tbody>
         <tr class="">
                <td>A</td>
                <td>B</td>
                <td>C</td>
                <td>D</td>
                <td>E</td>
                <td>F</td>
                <td>
                <button type="button" class="btn btn-warning btn-sm">Edit</button>
                 <button type="button" class="btn btn-danger btn-sm">Delete</button>
                </td>
            </tr>
         </tbody>
        </table>
    </div>
   <jsp:include page="../layout/admin-footer.jsp"></jsp:include>
</body>
</html>