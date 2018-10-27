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
function vehicleForm(){
	var linebreak = document.createElement('br');
	var form = document.createElement('form');
	form.setAttribute("action", "");
	form.setAttribute("method", "post");
	var licenselabel = document.createElement('label');
	licenselabel.innerHTML = "License Number: ";
	form.appendChild(licenselabel);

	var licenseelement = document.createElement('input'); // Create Input Field for Name
	inputelement.setAttribute("type", "text");
	inputelement.setAttribute("name", "number");
	form.appendChild(licenseelement);

	var linebreak = document.createElement('br');
	form.appendChild(linebreak);

	var makelabel = document.createElement('label'); // Create Label for E-mail Field
	makelabel.innerHTML = "Make: ";
	form.appendChild(makelabel);

	var makeelement = document.createElement('input'); // Create Input Field for E-mail
	makeelement.setAttribute("type", "text");
	makeelement.setAttribute("name", "make");
	form.appendChild(makeelement);

	var linebreak = document.createElement('br');
	form.appendChild(linebreak);

	var modellabel = document.createElement('label'); // Create Label for E-mail Field
	makelabel.innerHTML = "Model: ";
	form.appendChild(modellabel);

	var modelelement = document.createElement('input'); // Create Input Field for E-mail
	modelelement.setAttribute("type", "text");
	modelelement.setAttribute("name", "model");
	form.appendChild(modelelement);

	var linebreak = document.createElement('br');
	form.appendChild(linebreak);

	var colorlabel = document.createElement('label'); // Create Label for E-mail Field
	colorlabel.innerHTML = "Color: ";
	form.appendChild(colorlabel);

	var colorelement = document.createElement('input'); // Create Input Field for E-mail
	colorelement.setAttribute("type", "text");
	colorelement.setAttribute("name", "color");
	form.appendChild(colorelement);

	var linebreak = document.createElement('br');
	form.appendChild(linebreak);

	var yearlabel = document.createElement('label'); // Create Label for E-mail Field
	makelabel.innerHTML = "Year: ";
	form.appendChild(yearlabel);

	var yearelement = document.createElement('input'); // Create Input Field for E-mail
	yearelement.setAttribute("type", "text");
	yearelement.setAttribute("name", "year");
	form.appendChild(yearelement);

	var linebreak = document.createElement('br');
	form.appendChild(linebreak);

	var buttonelement = document.createElement('input');
	buttonelement.setAttribute("type", "button");
	buttonelement.setAttribute("name", "add another vehicle");
	buttonelement.setAttribute("onclick", "vehicleForm");
	form.appendChild(buttonelement);

}

function Add(){
	var x = document.getElementById("addSpots");
	x.setAttribute("method", "post");
}
