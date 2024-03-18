// This function handles the logic of login
function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    
    $.ajax({
        url: "http://localhost:8080/HBKIS/login",
        type: "POST",
        data: {
            username: username,
            password: password
        },
        dataType: "json", // Specify that you expect JSON
        success: function(response) {
            console.log(response);
            if(response) {
                // No need to parse, as jQuery does this automatically
                localStorage.setItem("name", response.name);
                localStorage.setItem("username", response.username);
                localStorage.setItem("userId", response.userId);
                localStorage.setItem("role", response.role);
                window.location.href = "2FA.html";
            } else {
                document.getElementById("status").innerHTML = "Login Failed!";
            }
        },
        error: function() {
            alert("Error occurred during login.");
        }
    });
}

// 2nd Factor Authentication
function SecFAlogin() {
    var userID = document.getElementById("2FAID").value;
    // Retrieve the username from local storage
    var username = localStorage.getItem("username");

    if (!username) {
        document.getElementById("status").innerHTML = "No username found. Please login again.";
        return;
    }

    $.ajax({
        url: "http://localhost:8080/HBKIS/login", 
        type: "POST",
        data: {
            username: username,
            userID: userID
        },
        success: function(response) {

            if(response.status === "success") {
                document.getElementById("status").innerHTML = "2FA Verification Successful!";

                // Redirect based on the user's role stored in localStorage
                var userRole = localStorage.getItem("role");
                console.log(userRole);
                if (userRole === "Physician") {
                    window.location.href = "PhysicianHome.html";
                } else if (userRole === "Nurse") {
                    window.location.href = "NurseHome.html";
                } else if (userRole === "Receptionist") {
                    window.location.href = "ReceptionistHome.html";
                }
            } else {
                document.getElementById("status").innerHTML = "2FA Verification Failed!";
            }
        },
        error: function() {
            alert("Error occurred during 2FA verification.");
        }
    });
}

