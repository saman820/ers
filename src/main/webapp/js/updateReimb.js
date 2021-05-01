window.onload = function(){
	getUser();
	getManagers();
	getAuthors();
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
	console.log(reimb);
	document.getElementById('amount').setAttribute('value',reimb.amount);	
	selectItemByValue(document.getElementById('currency'),reimb.ersCurrency);	
	if(reimb.resolverUserName!=null){selectItemByValue(document.getElementById('managers'),reimb.resolverUserName);}	
	if(reimb.authorUserName!=null){selectItemByValue(document.getElementById('author'),reimb.authorUserName);}
	if(reimb.description!=null){document.getElementById('description').value= reimb.description;}
	if(reimb.typeId!=null){selectItemByValue(document.getElementById('typee'),reimb.typeId);}
	if(reimb.statusId!=null){selectItemByValue(document.getElementById('status'),reimb.statusId);}
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

