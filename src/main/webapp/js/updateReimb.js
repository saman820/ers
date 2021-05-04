window.onload = function(){
	getMessage();
	getUser();
	getManagers();
}
async function getMessage(){
	let mes= await fetch("http://localhost:8080/ers/serv2/getMessage");
	mes = await mes.json();
	let mesCla= await fetch("http://localhost:8080/ers/serv2/getMessageClass");
	mesCla = await mesCla.json();
	if(mes!=null && mes!="" && mesCla!=null && mesCla!=""){
		document.getElementById("alertMessage").innerHTML=  `<div class="alert ${mesCla} alert-dismissible fade show">${mes }<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`;
		setTimeout(function(){ document.getElementById("alertMessage").style.display="none"; }, 3000);
	}
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
}

async function getManagers(){
	let managers = await fetch ("http://localhost:8080/ers/serv2/getManagerNames");
	managers = await managers.json();
	for(manager of managers){
		let newOption= document.createElement('option');
		newOption.innerHTML=`${manager}`;
		newOption.setAttribute('value',`${manager}`);
		document.getElementById("managers").append(newOption);
	}
	let reimb = await fetch("http://localhost:8080/ers/serv2/getCurrentreimb");
	reimb = await reimb.json();
	document.getElementById('amount').setAttribute('value',reimb.amount);	
	selectItemByValue(document.getElementById('currency'),reimb.ersCurrency);	
	if(reimb.resolverUserName!=null){selectItemByValue(document.getElementById('managers'),reimb.resolverUserName);}	
	if(reimb.authorUserName!=null){selectItemByValue(document.getElementById('author'),reimb.authorUserName);}
	if(reimb.description!=null){document.getElementById('description').value= reimb.description;}
	if(reimb.typeId!=null){selectItemByValue(document.getElementById('typee'),reimb.typeId);}
	if(reimb.statusId!=null){selectItemByValue(document.getElementById('status'),reimb.statusId);}
	if(reimb.receipt!=""){
		document.getElementById("rec").setAttribute('src',`data:image/jpeg;base64,${reimb.receipt}`);
		document.getElementById("rec2").setAttribute('src',`data:image/jpeg;base64,${reimb.receipt}`);
		}
}

async function getUser(){
	let authors = await fetch ("http://localhost:8080/ers/serv2/getAuthorNames");
	authors = await authors.json();
	for(author of authors){
		let newOption= document.createElement('option');
		newOption.innerHTML=`${author}`;
		newOption.setAttribute('value',`${author}`);
		document.getElementById("author").append(newOption);
	}
}


  function selectItemByValue(elmnt, value){
    for(let i=0; i < elmnt.options.length; i++)
    {
      if(elmnt.options[i].value == value)
        elmnt.selectedIndex = i;
    }
  }


document.getElementById("rec").addEventListener("click",function(){
	if(document.getElementById("rec2").style.display==''
		||document.getElementById("rec2").style.display=='none'){
		document.getElementById("rec").style.display="none";
		document.getElementById("rec2").style.display="block";
		return false;
	}
})
document.getElementById("rec2").addEventListener("click",function(){
	if(document.getElementById("rec").style.display==''
		||document.getElementById("rec").style.display=='none'){
		document.getElementById("rec2").style.display="none";
		document.getElementById("rec").style.display="block";
		}
		return false;
})
