function getlogin() {
    alert("check")
    sessionStorage.clear();
    login = {
        "email": document.getElementById("uname").value,
        "f_name": document.getElementById("pass").value
    }
    // console.log(document.getElementById("uname").value, document.getElementById("pass").value)
    $.ajax({
        type: "POST",
        url: "http://localhost:9093/login",
        data: JSON.stringify(login),
        success: function (result) {
            console.log(JSON.parse(result));

            JSON.parse(result).forEach(ele => {
                sessionStorage.setItem("uname", ele.f_name);
                sessionStorage.setItem("id", ele.emp_id);
                location.href = ele.r_link;
            });
            optin1(JSON.parse(result));
        },
        error: function (result) {
            console.log(`Error ${error}`)
        },
    });

}
function username() {
    document.getElementById("dpuser").textContent = sessionStorage.getItem("uname");
}

function optin1() {
    // console.log(data)
    alert("option")
    let select = document.getElementById('modeldesignation');
    // data.forEach(aut => {
    var opt = document.createElement('option');
    // opt.value = "mansk";
    opt.innerHTML = "new one";
    select.appendChild(opt);
    // });
    // in1.appendChild(select);
}

function getpunch() {
    // alert("vewpunch");
    $.ajax({
        url: "http://localhost:9093/login",
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            displayData(data);
            document.getElementById("welcome").textContent = "new user"
        },
        error: function (error) {
            console.log(`Error ${error}`)
        },
    });
}
function displayData(data) {
    let tbody = document.getElementById("viewtable");

    data.forEach(element => {
        let tr = tbody.insertRow();

        let td1 = tr.insertCell(0);
        let id = document.createTextNode(element.emp_id);
        td1.appendChild(id);

        let td2 = tr.insertCell(1);
        let title = document.createTextNode(element.date);
        td2.appendChild(title);

        let td3 = tr.insertCell(2);
        let author = document.createTextNode(element.time);
        td3.appendChild(author);

        let td4 = tr.insertCell(3);
        let price = document.createTextNode(element.device);
        td4.appendChild(price);
    });
    // book = data;
}
function getItems() {
    username();
    $('#tableid').DataTable({
        "ajax": {
            "url": "http://localhost:9093/login",
            "dataSrc": ""
        },
        "columns": [
            { "data": "emp_id" },
            { "data": "date" },
            { "data": "time" },
            { "data": "device" }
        ]
    });
}
// --------------------------
function getemp() {
    $('#emptable').DataTable(
        {
            "ajax": {
                "url": "http://localhost:9093/pass",
                "dataSrc": ""
            },
            "columns": [
                { "data": "emp_id" },
                { "data": "f_name" },
                { "data": "l_name" },
                { "data": "email" },
                { "data": "phone_number" },
                { "data": "hire_date" },
                { "data": "desgination" },
                { "data": "salary" },
                { "data": "manager_id" },
                { "data": "department_id" },
                { "data": "location" },
                {
                    data: null,
                    className: "dt-center editor-edit",
                    defaultContent: '<i class="fa fa-pencil" onclick="edititem();" />',
                    orderable: false
                },
                {
                    data: "",
                    // className: "dt-center editor-edit",
                    defaultContent: '<i class="fa-solid fa-trash" onclick="deleteitem();" />',
                    orderable: false
                },
                // { "data": "", "defaultContent": "<button onclick='edititem();'>Edit</button>" }

            ]
        });
    username();

    // $('#example').on('click', 'td.editor-edit', function (e) {
    //     e.preventDefault();

    //     editor.edit($(this).closest('tr'), {
    //         title: 'Edit record',
    //         buttons: 'Update'
    //     });
    // });

}
function edititem() {
    console.log("edit");

}
function deleteitem() {
    console.log("delete");
}
// ---------------------------------
// ------user-----------
function getid() {
    // console.log(sessionStorage.getItem("id"));
    username();
    $('#usertable').DataTable({
        "ajax": {
            type: "POST",
            "url": "http://localhost:9093/get",
            "dataSrc": ""
        },
        "columns": [
            { "data": "emp_id" },
            { "data": "date" },
            { "data": "time" },
            { "data": "device" }
        ]
    });

}

function displaypunch(data) {
    let tbody = document.getElementById("viewtable");

    data.forEach(element => {
        let tr = tbody.insertRow();

        let td1 = tr.insertCell(0);
        let id = document.createTextNode(element.emp_id);
        td1.appendChild(id);

        let td2 = tr.insertCell(1);
        let title = document.createTextNode(element.date);
        td2.appendChild(title);

        let td3 = tr.insertCell(2);
        let author = document.createTextNode(element.time);
        td3.appendChild(author);

        let td4 = tr.insertCell(3);
        let price = document.createTextNode(element.device);
        td4.appendChild(price);
    });

}

function getemployee() {
    console.log(sessionStorage.getItem("id"))
    username();
    login = {
        "emp_id": sessionStorage.getItem("id"),
    }
    $.ajax({
        type: "PUT",
        url: "http://localhost:9093/get",
        data: JSON.stringify(login),
        success: function (result) {
            console.log(JSON.parse(result));
            dispalyuserdetail(JSON.parse(result));
        },
        error: function (result) {
            console.log(`Error ${error}`)
        },
    });

}
function dispalyuserdetail(userid) {
    userid.forEach(uid => {
        document.getElementById("empid").value = uid.emp_id;
        document.getElementById("First1").value = uid.f_name;
        document.getElementById("Last").value = uid.l_name
        document.getElementById("Mail").value = uid.email;
        document.getElementById("Phone").value = uid.phone_number;
        document.getElementById("Hire").value = uid.hire_date;
        document.getElementById("Desgination").value = uid.desgination;
        document.getElementById("Salary").value = uid.salary;
        document.getElementById("Manager").value = uid.manager_id;
        document.getElementById("Department").value = uid.department_id;
        document.getElementById("Location").value = uid.location;

    });
}
// ----------------------------------
// google piechart----------

function piechart1() {
    username();
    $.ajax({
        type: "PUT",
        url: "http://localhost:9093",
        data: JSON.stringify(login),
        success: function (result) {
            console.log(JSON.parse(result));
        },
        error: function (result) {
            console.log(`Error ${error}`)
        },
    });
    var data = [{
        data: [],
        labels: ["Total emp", "Punched", "unpunch",],
        backgroundColor: [
            "#4b77a9",
            "#5f255f",
            "#d21243",
            "#B27200"
        ],
        borderColor: "#fff"
    }];
    var options = {
        tooltips: {
            enabled: false
        },
        plugins:
        {
            datalabels:
            {
                formatter: (value, ctx) => {
                    let datasets = ctx.chart.data.datasets;
                    if (datasets.indexOf(ctx.dataset) === datasets.length - 1) {
                        let sum = datasets[0].data.reduce((a, b) => a + b, 0);
                        let percentage = Math.round((value / sum) * 100) + '%';
                        return percentage;
                    } else {
                        return percentage;
                    }
                },
                color: '#fff',
            }
        }
    };
    var ctx = document.getElementById("piechart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'pie',
        data: {
            datasets: data
        },
        options: options
    });


}


function validateForm() {
    alert();
    // var id = document.getElementById("emp_id").value;
    var f_name = document.getElementById("modelFirst").value;
    var l_name = document.getElementById("modelLast").value;
    var email = document.getElementById("modelMail").value;
    var phone_number = document.getElementById("modelphone").value;
    var hire_date = document.getElementById("modelhire").value;
    var desgination = document.getElementById("modeldesignation").value;
    var salary = document.getElementById("modelsalary").value;
    var manager_id = document.getElementById("modelmanager").value;
    var department_id = document.getElementById("modeldept").value;
    var location_id = document.getElementById("modellocationa").value

    var nameErr = emailErr = phoneErr = hireErr = salaryErr = manErr = deptErr = locationErr = true;

    if (f_name == " ") {
        printError("please enter a  name :");
    }
    else {

        var regex = /^[a-zA-Z\s]+$/;

        if (regex.test(f_name) === false) {
            printError("please enter a valid name");
        } else {
            printError("nameErr", "");
            nameErr = false;
        }
    }

    if (l_name == "") {
        printError("please enter a Last_name");

    }
    else {

        var regex = /^[a-zA-Z\s]+$/;
        if (regex.test(l_name) === false) {
            printError("Please enter a valid name");
        }
        else {

            printError("nameErr", "");
            nameErr = false;
        }
    }
    if (email==="data from DB") {
        printError("Email already exist:");
    }
    else {
        var regex = /^\S+@\S+\.\S+$/;
        if (regex.test(email) === false) {
            printError("please enter a valid email");
        }
        else {
            printError("emailErr", "");
            emailErr = false;
        }
    }
    if (phone_number === "data from Db") {
        printError("phone number already exists");
    }
    else {

        var regex = /^[1-9]\d{9}$/;
        if (regex.test(phone_number) === false) {
            printError("please enter a valid phone number");
        }
        else {
            printError("emailErr", "");
            phoneErr = false;
        }
    }

    //  if(hire_date =="")
    //  {
    //     printError("please enter a Hiring date !!!");
    //  }
    //  else{

    //         var regex = (0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((?:19|20)[0-9][0-9]);
    //  }

    if (salary == "") {
        printError("please enter a salary !!");
    }
    else {

        var regex = [0 - 9][1 - 9.] * [0 - 9] + [1 - 9];
        if (regex.test(salary) === false) {
            printError("please enter a valid salary !!");
        }
        else {
            printError("emailErr", "");
            salaryErr = false;
        }
    }


    if((nameErr || emailErr || phoneErr || hireErr || salaryErr || manErr || deptErr || locationErr) == true) {
        return false;
     } else {
         // Creating a string from input data for preview
         var dataPreview = "You've entered the following details: \n" +
                           "First Name: " + f_name + "\n" +
                           "Last Name: " + l_name + "\n" +
                           "Email Address: " + email + "\n" +
                           "phone number " + phone_number + "\n" +
                           "Hiring date: " + hire_date + "\n" +
                           "Designation: " + desgination + "\n" +
                           "salary: " + salary + "\n";
                           "managerid: " + manager_id + "\n" +
                           "department_id: " + department_id + "\n" +
                           "location: " + location_id + "\n";
         if(hobbies.length) {
             dataPreview += "Hobbies: " + hobbies.join(", ");
         }
         alert(dataPreview);
     }


}
function printError(elemId, hintMsg) {
    document.getElementById(elemId).innerHTML = hintMsg;
}


