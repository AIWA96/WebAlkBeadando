var user;
var passw;
var attempt = 3;

//Validate email and psw
function validatePassword(psw) {
    var res = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}/;
    return res.test(psw);
}

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
//End

//Login Validation
function validate() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (username == user.value && password == passw.value) {
        alert("Login successfully");
        window.location = "Success.html";
        return false;
    } else {
        attempt--;
        alert("You have left " + attempt + " attempt;");
        if (attempt == 0) {
            document.getElementById("username").disabled = true;
            document.getElementById("password").disabled = true;
            document.getElementById("submit").disabled = true;
            return false;
        }
    }
}
//Validation End

//Load New Content
$(document).ready(function () {
    $("a").click(function () {
        var ButtonText = $(this).text();
        $.get(ButtonText + ".html", function (data) {
            document.getElementById('content').innerHTML = data;
        });
    });
});
//End

//JSON
jQuery(function () {
    readJSON = function () {
        $.ajax({
                url: "/css/probaJSON",
                type: "GET",
                dataType: "JSON",
                context: this,
                success: function (data) {

                    $("#content").empty();
                    var innertable = $("<div class='mid'><table style= 'border:solid'> </table> </div>").attr('id', 'innertable');
                    innertable.append("<tr><th>IdNum</th><th>Name</th><th>Gender</th><th>Salary</th>"
                        + "<th>Post</th><th>ShopName</th></tr>");
                    for (i in data) {
                        innertable.append("<tr> <td>" + data[i].idNum + "</td> <td>"
                            + data[i].name + "</td> <td>"
                            + data[i].gender + "</td> <td>"
                            + data[i].salary + "</td> <td>"
                            + data[i].post + "</td> <td>"
                            + data[i].shopName + "</td> </tr>");
                    }
                    $("#content").append(innertable)

                }
            }
        )
    }
})
//End

//Validate Form
jQuery(function () {
    ValidateForm = function () {
        var fname = document.getElementById("fname");
        var sname = document.getElementById("sname");
        var birth = document.getElementById("birth");
        var usern = document.getElementById("usern");
        var psw = document.getElementById("psw");
        var email = document.getElementById("email");

        user = usern;
        passw = psw;
        console.log(user.value);
        console.log(passw.value);

        if (fname.value == null) {
            fname.style.backgroundColor = "red";
            document.getElementById("fname").placeholder = "Please give your first name.";
            fname.style.color = "snow";
            return false;
        } else {
            fname.style.backgroundColor = "green";
            fname.style.color = "snow";
        }
        if (sname.value == null) {
            sname.style.backgroundColor = "red";
            document.getElementById("sname").placeholder = "Please give your second name.";
            sname.style.color = "snow";
            return false;
        } else {
            sname.style.backgroundColor = "green";
            sname.style.color = "snow";
        }
        if (birth.value == null) {
            birth.style.backgroundColor = "red";
            document.getElementById("gym").placeholder = "Please give your birth date.";
            birth.style.color = "snow";
            return false;
        } else {
            birth.style.backgroundColor = "green";
            birth.style.color = "snow";
        }
        if (usern.value == null) {
            usern.style.backgroundColor = "red";
            document.getElementById("usern").placeholder = "Kérlek add meg a felhasználó neved.";
            usern.style.color = "snow";
            return false;
        } else {
            usern.style.backgroundColor = "green";
            usern.style.color = "snow";
        }
        if (email.value == null) {
            email.style.backgroundColor = "red";
            document.getElementById("email").placeholder = "Please give your e-mail address.";
            email.style.color = "snow";
            return false;
        } else {
            if (validateEmail(email.value)) {
                email.style.backgroundColor = "green";
                email.style.color = "snow";
            }
            else {
                email.style.backgroundColor = "red";
                alert("The e-mail you just given is invalid.\nPlease give a valid one");
                email.style.color = "snow";
                return false;
            }
        }
        if (psw.value == null) {
            psw.style.backgroundColor = "red";
            document.getElementById("gym").placeholder = "Please give a password.";
            psw.style.color = "snow";
            return false;
        } else {
            if (validatePassword(psw.value)) {
                psw.style.backgroundColor = "green";
                psw.style.color = "snow";
            } else {
                psw.style.backgroundColor = "red";
                alert("Must contain at least one number and one uppercase and lowercase letter," +
                    "and at least 8 or more characters");
                psw.style.color = "snow";
                return false;
            }
        }
        $.get("Login.html", function (data) {
            document.getElementById('content').innerHTML = data;
        });
    }
});
//End

//Get Accessories
jQuery(function () {
    readJSON = function () {
        $.ajax({
            type: "GET",
            url: "accessories/getaccessories",
            dataType: "JSON",
            context: this,
            success: function (data) {
                console.log(data);
            },
            failure: function (errMsg) {
                alert(errMsg);
            }
        });

    }
});
//End