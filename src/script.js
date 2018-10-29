function validateReserve(){
    var lotId = document.forms["reservationForm"]["lotId"].value;
    var spotId = document.forms["reservationForm"]["spotId"].value;
    
    if (lotId == "") {
        alert("lotId must be filled out");
        document.forms["reservationForm"]["lotId"].focus();
        return false;
    }
    if (spotId== "") {
        alert("spotId must be filled out");
        document.forms["reservationForm"]["spotId"].focus();
        return false;
    }
}

function regValidate() {

	var username = document.forms["regform"]["username"].value;
	var password = document.forms["regform"]["password"].value;
	var rpassword = document.forms["regform"]["retry-password"].value;
	var email = document.forms["regform"]["email"].value;
	var accttype = document.forms["regform"]["accttype"].value;
	
	if (username == "") {
        alert("username must be filled out");
        document.forms["regform"]["username"].focus();
        return false;
    }else if (password== "") {
        alert("password must be filled out");
        document.forms["regform"]["password"].focus();
        return false;
    }else if (email== "") {
        alert("Email must be filled out");
        document.forms["regform"]["email"].focus();
        return false;
    }else if (accttype== "") {
        alert("Account Type must be filled out");
        document.forms["regform"]["accttype"].focus();
        return false;
    }    
    else if (rpassword == "") {
        alert("retry-password must be filled out");
        document.forms["regform"]["retry-password"].focus();
        return false;
    }else if(password != rpassword){
    	alert("password doesnt match");
        document.forms["regform"]["password"].focus();
        return false;
    }
}
function loginValidate(){
	var username = document.forms["loginform"]["username"].value;
	var password = document.forms["loginform"]["password"].value;
	
	if (username == "") {
        alert("username must be filled out");
        document.forms["loginform"]["username"].focus();
        return false;
    }else if (password== "") {
        alert("password must be filled out");
        document.forms["loginform"]["password"].focus();
        return false;
    }else if (rpassword == "") {
        alert("retry-password must be filled out");
        document.forms["loginform"]["retry-password"].focus();
        return false;
    }else if(password != rpassword){
    	alert("password doesnt match");
        document.forms["loginform"]["password"].focus();
        return false;
    }
}