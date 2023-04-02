

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
  const colorChange = document.querySelector('.flex-container');
  let colorchange = document.getElementById("main");
  if (userName == "" || userName == null || userName.length == 0) {
    colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/red.gif");`;
    swal("Error!", "Username field cannot be empty", "error");
    return false;
  } else if (email == "" || email == null || email.length == 0) {
    colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/red.gif");`;
    swal("Error!", "Email field cannot be empty", "error");
    return false;
    }
    else if(repass == "" || repass == null || repass.length == 0){
    colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/red.gif");`;
    swal("Error!", "Password fields cannot be empty", "error")
    return false;
}
   else if (password !== repass) {
    colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/red.gif");`;
    swal("Error!", "Password fields dont match", "error");
    return false;
  } else if (password == "" || password == null || password.length == 0) {
    colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/red.gif");`;
    swal("Error!", "Password field cannot be empty", "error");
    return false;
  } else {

//    colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/green.gif");`;
    return true;
  }
}
//common for login and register
let input = document.getElementsByTagName("input");
    console.log(input)
    for (var i = 1; i <=6; i++) {
 	   input[i].addEventListener('click',()=>{
 		   console.log('1')
 	       const colorChange = document.querySelector('.main');
 	       colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/blue.gif");`;
 	   })
 }


 let status = document.getElementById("status").value;
 const colorChange = document.querySelector('.flex-container');
 if(status==='true'){
   colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/green.gif");`;
       swal("Success!", "Successfully registered", "success");
       }
       else if(status==='username'){
       colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/red.gif");`;
           swal("Error!", "Username already registered!", "error");
       }
        else if(status==='email'){
              colorChange.style=`transition: all 1s ease-in-out 0s; background-image: url("/assets/videos/red.gif");`;
                  swal("Error!", "Email already registered!", "error");
              }


