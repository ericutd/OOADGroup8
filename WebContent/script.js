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

	var password = document.forms["regform"]["password"].value;
	var rpassword = document.forms["regform"]["retry-password"].value;
	var email = document.forms["regform"]["email"].value;
	var dropdown = document.forms["regform"]["dropdown"].value;
	
	if (password== "") {
        alert("password must be filled out");
        document.forms["regform"]["password"].focus();
        return false;
    }else if (email== "") {
        alert("Email must be filled out");
        document.forms["regform"]["email"].focus();
        return false;
    }else if (dropdown== "") {
        alert("Account Type must be filled out");
        document.forms["regform"]["dropdown"].focus();
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
	var email = document.forms["loginform"]["email"].value;
	var password = document.forms["loginform"]["password"].value;
	
	if (email == "") {
        alert("username must be filled out");
        document.forms["loginform"]["email"].focus();
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
