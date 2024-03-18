document.addEventListener('DOMContentLoaded', function () {
    // update welcome message
    updateWelcomeMsg();
});

// Update the welcome message 
function updateWelcomeMsg() {
    var welcomeElement = document.getElementById('welcome');
    if (welcomeElement) {
        welcomeElement.innerHTML = "Welcome, " + localStorage.getItem('name');
    } else {
        console.error("Welcome element not found");
    }
}
$('#searchNurses').click(function() {
    var physicianName = $('#physicianDropdown').val(); 

    $.ajax({
        url: 'http://localhost:8080/HBKIS/GetNursesOfPhysician', 
        type: 'GET',
        data: { physicianName: physicianName },
        dataType: 'json',
        success: function(response) {
            console.log(response);
            updateResultTable('Nurses', response.nurses);
        },
        error: function(xhr, status, error) {
            console.error('Error fetching nurse data:', xhr.responseText);
        }
    });
});

// Function to search for Physician 
$('#searchPhysicians').click(function() {
    var nurseName = $('#nurseDropdown').val(); 

    $.ajax({
        url: 'http://localhost:8080/HBKIS/GetPhysiciansOfNurse',
        type: 'GET',
        data: { nurseName: nurseName },
        dataType: 'json',
        success: function(response) {
            console.log(response);
            updateResultTable('Physicians', response.physicians);
        },
        error: function(xhr, status, error) {
            console.error('Error fetching physician data:', xhr.responseText);
        }
    });
});

function updateResultTable(title, data) {
    var resultContainer = $('.result-container').first();
    if (resultContainer.length === 0) {
        console.error('No result container found');
        return;
    }
    resultContainer.empty();

    var table = $('<table>');
    table.append('<tr><th>User ID</th><th>Name</th><th>Role</th></tr>');

    data.forEach(function(item) {
        var row = $('<tr>');
        row.append('<td>' + item.userId + '</td>');
        row.append('<td>' + item.name + '</td>');
        row.append('<td>' + item.role + '</td>');
        table.append(row);
    });

    resultContainer.append('<h2>' + title + '</h2>');
    resultContainer.append(table);
}

// This function handles the logic of logout
function logout() {
    alert("You have been logout.");
    localStorage.clear();
    window.location.href = "login.html"
}