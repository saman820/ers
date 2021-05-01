
/**
 * 
 */

/*window.onload = function(){
	getSessUser();
}

function getSessUser() {
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){	
		if(xhttp.readyState == 4 && xhttp.status==200){
/*			let user = JSON.parse(xhttp.responseText);
*/			
/*			let user = xhttp.responseText;
			console.log(user);
			document.getElementById("id1").innerText=`Welcome ${user.userName}`;
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ers/serv2");

	xhttp.send();
}*/
console.log("connected");
window.onload = function(){
	getUser();
	getManagers();
}

async function getUser(){
	let ersUser = await fetch("http://localhost:8080/ers/serv2/getUser");
	ersUser = await ersUser.json();
	let newA =document.createElement('a');
	newA.id="newAId";
	newA.setAttribute('href','view-user.ers');
	let aText = document.createTextNode(ersUser.userName);
	newA.appendChild(aText);
	document.getElementById("id2").append(newA);
	console.log(ersUser); 
}

async function getManagers(){
	let managers = await fetch ("http://localhost:8080/ers/serv2/getManagerNames");
	managers = await managers.json();
	console.log(managers);
	for(manager of managers){
		let newOption= document.createElement('option');
		newOption.innerHTML=`${manager}`;
		newOption.setAttribute('value',`${manager}`);
		document.getElementById("managers").append(newOption);
		console.log(newOption);
	}	
}

/*$('.alert').alert()
$(".alert").delay(4000).slideUp(200, function() {
    $(this).alert('close');
});*/