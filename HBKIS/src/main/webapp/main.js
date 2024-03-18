/*// This function handles the logic of login
function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    alert("Login button clicked." + "\nUsername Entered: " + username + "\nPassword Entered: " + password);
    $.ajax({
        url: "http://localhost:8080/mars/login",
        type: "POST",
        data: {
            username: username,
            password: password
        },
        success: function(response) {
            if(response === "success") {
                // alert("Login Successfully!");
                document.getElementById("status").innerHTML = "Login Successfully!";
                window.location.href = "home.html"
            } else if (response === "failed") {
                // alert("Login failed. Please check your username and password.");
                document.getElementById("status").innerHTML = "Login Failed!";
            }
        },
        error: function() {
            alert("Error occurred during login.");
        }
    });
}

// This function handles the logic of logout
function logout() {
    alert("You have been logout.");
    window.location.href = "login.html"
}*/

// This function is to fetch the saved lab test data
function fetchData() {
    $.ajax({
        url: "http://localhost:8080/HBKIS/fetchAllLabTest",
        type: "GET",
        datatype: "json",
        success: function(response) {
             // Create a table
             var table = "<table border='1'>";
            
             // Add table headers
             table += "<thead><tr>";
             table += "<th>Patient ID</th>";
             table += "<th>Patient Name</th>";
             table += "<th>Test Date</th>";
             table += "<th>Lab Test</th>";
             table += "<th>Test Value</th>";
             table += "</tr></thead>";
 
             // Add table body with data
             table += "<tbody>";
             for (var i = 0; i < response.length; i++) {
                 table += "<tr>";
                 table += "<td>" + response[i].patient_id + "</td>";
                 table += "<td>" + response[i].patient_name + "</td>";
                 table += "<td>" + response[i].test_date + "</td>";
                 table += "<td>" + response[i].lab_test + "</td>";
                 table += "<td>" + response[i].test_value + "</td>";
                 table += "</tr>";
             }
             table += "</tbody>";
 
             // Close the table
             table += "</table>";
 
             // Append the table to a div or any other container in your HTML
             $("#dataDisplay").html(table);
        },
        error: function() {
            alert("Error fetching data");
        }
    });
}