/**
 *
 */
const edit = document.querySelector('.edit');
const modal = document.querySelector('.modall');
const overlay = document.querySelector('.overlay');

 edit.addEventListener('click',()=>{
	console.log('Event fired')
	modal.classList.remove('hidden');
	overlay.classList.remove('hidden');
});



 const closer = function () {
  modal.classList.add('hidden');
  overlay.classList.add('hidden');
};
overlay.addEventListener('click', closer);


let updateStatus = document.getElementById("upload-status").value;

 if(updateStatus=='justdetails')

       swal("Success!", "Successfully profile updated without profile pic!", "success");

       else if(updateStatus=='passerror')

           swal("Error!", "Incorrect password", "error");

            else if(updateStatus=='alldetails')

                   swal("Success!", "Successfully profile updated with profile pic!", "success");

