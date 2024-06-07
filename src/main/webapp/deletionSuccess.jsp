<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deletion Status</title>
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
        .message {
            text-align: center;
        }
        .success {
            color: green;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="message">
            <% 
                String message = (String) request.getAttribute("deletionStatus");
                if (message != null && message.equals("success")) {
                    out.print("Record Deleted Successfully");
                } else {
                    out.print("UniqueID Not Found");
                }
            %>
        </h2>
        <% 
            String uniqueID = (String) request.getAttribute("uniqueID");
            if (uniqueID != null) {
                out.print("<p>UniqueID: " + uniqueID + "</p>");
            }
        %>
    </div>
</body>
</html>