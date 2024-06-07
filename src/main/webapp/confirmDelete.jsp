
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Deletion</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f1f1f1;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
        }
        p {
            margin-bottom: 10px;
        }
        form {
            text-align: center;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Confirm Deletion</h2>
        <p>Are you sure you want to delete the following record?</p>
        <p>Name: <%= request.getAttribute("name") %></p>
        <p>UniqueID: <%= request.getAttribute("uniqueID") %></p>
        <p>Qualification: <%= request.getAttribute("qualification") %></p>
        <p>DOB: <%= request.getAttribute("dob") %></p>
        <p>Experience: <%= request.getAttribute("experience") %> years</p>
        <p>Subjects: <%= request.getAttribute("subjects") %></p>
        <p>Address: <%= request.getAttribute("address") %></p>
        <p>Phone: <%= request.getAttribute("phone") %></p>
        <p>Email: <%= request.getAttribute("email") %></p>
        <p>Organization Email: <%= request.getAttribute("orgEmail") %></p>

        <form action="DeleteConfirmedServlet" method="post">
            <input type="hidden" name="uniqueID" value="<%= request.getAttribute("uniqueID") %>">
            <input type="submit" value="Yes, Delete">
        </form>
    </div>
</body>
</html>
