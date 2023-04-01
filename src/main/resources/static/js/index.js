function LoginValidate() {
  const email = document.getElementById("email").value;
  const password = document.getElementById("pass").value;
	const colorChange = document.querySelector('.flex-container');

  if (email == "" || email == null || email.length == 0) {
    colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/red.gif");`;
    swal("Error!", "Email field cannot be empty", "error");
    return false;
  } else if (password == "" || password == null || password == 0) {
    colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/red.gif");`;
    swal("Error!", "Password field cannot be empty", "error");
    return false;
  } else {
    return true;
  }
}

function registerValidate() {
  const userName = document.getElementById("username").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("pass").value;
  const repass = document.getElementById("repass").value;
  let colorchange = document.getElementById("main");
  if (userName == "" || userName == null || userName.length == 0) {
    swal("Error!", "Username field cannot be empty", "error");
    return false;
  } else if (email == "" || email == null || email.length == 0) {
    swal("Error!", "Email field cannot be empty", "error");
    return false;
    }
    else if(repass == "" || repass == null || repass.length == 0){
    swal("Error!", "Password fields cannot be empty", "error")
    return false;
}
   else if (password !== repass) {
    swal("Error!", "Password fields dont match", "error");
    return false;
  } else if (password == "" || password == null || password.length == 0) {
    swal("Error!", "Password field cannot be empty", "error");
    return false;
  } else {


    return true;
  }
}