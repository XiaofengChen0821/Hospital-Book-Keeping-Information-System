document.addEventListener('DOMContentLoaded', function () {
    // update welcome message
    updateWelcomeMsg();
    
    // Attach event listeners to search buttons
    attachSearchEventListeners();
});

// Funtion update the welcome message 
function updateWelcomeMsg() {
    var welcomeElement = document.getElementById('welcome');
    if (welcomeElement) {
        welcomeElement.innerHTML = "Welcome, " + localStorage.getItem('name');
    } else {
        console.error("Welcome element not found");
    }
}

// Function to attach search event listeners to buttons
function attachSearchEventListeners() {
    $('#labtest-search').on('click', function () {
        performLabTestSearch();
    });

    $('#visitation-search').on('click', function () {
        fetchVisitationRecords();
    });
}

// Function to handle lab test search
function performLabTestSearch() {
    var patient = $('#patientDropdown').val();
    var labTest = $('#labTestDropdown').val();
    var date = $('#dateInput').val();

    // Prepare the URL with query parameters
    var url = 'http://localhost:8080/HBKIS/GetLabTest';
    var data = {
        patientName: patient,
        labTestType: labTest,
        date: date
    };

    $.ajax({
        url: url,
        data: data,
        type: 'GET',
        success: function (response) {
            console.log(response);
            updateResultContainer('Lab tests', response);
        },
        error: function (xhr, status, error) {
            console.error('Error fetching lab test data:', xhr.responseText);
        }
    });
}

// Function to fetch visitation records
function fetchVisitationRecords() {
    var patientName = $('#patientDropdown').val();
    var visitDate = $('#visit-dateInput').val();

    $.ajax({
        url: 'http://localhost:8080/HBKIS/getVisitationRecord',
        data: {
            patientName: patientName,
            date: visitDate
        },
        success: function (response) {
            console.log(response);
            displayVisitationRecords('Visitation Record', response);
        },
        error: function (xhr, status, error) {
            console.error('Error fetching visitation records:', xhr.responseText);
        }
    });
}

// Function to update the result container with lab test search results
function updateResultContainer(title, results) {
    var resultContainers = document.getElementsByClassName('result-container');
    if (resultContainers.length === 0) {
        console.error('No result container found');
        return;
    }

    var resultContainer = resultContainers[0];
    resultContainer.innerHTML = `<h2>${title}</h2>`;

    // Check for "no result" message
    if (results.message) {
        resultContainer.innerHTML += `<p>${results.message}</p>`;
        return;
    }

    // Create a table element
    var table = document.createElement('table');
    table.style.width = '100%';
    table.setAttribute('border', '1');

    // Create the header row
    var thead = document.createElement('thead');
    var headerRow = document.createElement('tr');
    var headers = ["Patient Name", "Lab Test", "Test Value", "Date"];
    headers.forEach(function (header) {
        var th = document.createElement('th');
        th.textContent = header;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);

    // Create the body of the table
    var tbody = document.createElement('tbody');

    // Function to create a table row for a lab test result
    function createTableRow(result) {
        var row = document.createElement('tr');

        var cellPatientName = document.createElement('td');
        cellPatientName.textContent = result.patientName;
        row.appendChild(cellPatientName);

        var cellLabTest = document.createElement('td');
        cellLabTest.textContent = result.labTest;
        row.appendChild(cellLabTest);

        var cellTestValue = document.createElement('td');
        cellTestValue.textContent = result.testValue;
        row.appendChild(cellTestValue);

        var cellDate = document.createElement('td');
        cellDate.textContent = result.testDate;
        row.appendChild(cellDate);

        return row;
    }

    // Check if results is an array (multiple lab tests)
    if (Array.isArray(results)) {
        results.forEach(function (result) {
            tbody.appendChild(createTableRow(result));
        });
    } else if (results.labTest) {
        // Single lab test
        tbody.appendChild(createTableRow(results.labTest));
    } else {
        console.error('Unexpected results format:', results);
    }

    table.appendChild(tbody);
    resultContainer.appendChild(table);
}

// Function to update the result container with visitation record search results
function displayVisitationRecords(title, response) {
    var resultsContainer = document.querySelector('.result-container');
    // Clear previous results
    resultsContainer.innerHTML = ''; 

    resultsContainer.innerHTML = `<h2>${title}</h2>`;

    // Handle no record found or other messages
    if (!response || response.message) {
        var message = response && response.message ? response.message : "No data received.";
        resultsContainer.innerHTML = `<p>${message}</p>`;
        return;
    }

    var visitationDiv = document.createElement('div');
    visitationDiv.className = 'visitation-record';

    // Dynamically add fields from the response
    Object.keys(response).forEach(function (key) {
        if (key !== "notes") {
            var value = response[key];
            var para = document.createElement('p');
            para.textContent = `${key.charAt(0).toUpperCase() + key.slice(1).replace(/([A-Z])/g, ' $1')}: ${value}`;
            visitationDiv.appendChild(para);
        }
    });

    // Handle notes at the end
    if (response.notes) {
        var notesTitle = document.createElement('p');
        notesTitle.textContent = 'Notes:';
        visitationDiv.appendChild(notesTitle);
        var singleNote = document.createElement('p');
        singleNote.textContent = response.notes;
        visitationDiv.appendChild(singleNote);
    }

    resultsContainer.appendChild(visitationDiv);
}

// This function handles the logic of logout
function logout() {
    alert("You have been logout.");
    localStorage.clear();
    window.location.href = "login.html"
}