window.onload = function(){
	getUser();
	getUserReimbs();
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

async function getUserReimbs(){
	let reimbs = await fetch("http://localhost:8080/ers/serv2/getReimbs");
	reimbs = await reimbs.json();
	console.info(reimbs);
	if(reimbs.length==0){
		document.getElementById("h2").innerText= "You have no reimbursement ticket";
		document.getElementById("reimbTable").style.display="none";
	}
	for(reimb of reimbs){
		let newTR= document.createElement('tr');
		let idTD=document.createElement('td');
		/*let idText = document.createTextNode(`${reimb["id"]}`);
		idTD.appendChild(idText);*/
		idTD.innerHTML=`<a href="update-reimb.ers?currentReimbId=${reimb["id"]}">${reimb["id"]}</a>`;
		newTR.append(idTD);
		for(let i in reimb){
			if(i=="id"){
				continue;
			}
			if(i=="authorUserName"){
				continue;
			}
			if(i=="resolverId"){
				continue;
			}
			if(i=="authorId"){
				continue;
			}
			if(i=="submitted"){
				reimb[i]=reimb[i].substring(0,10);
			}
			if(i=="resolved"){
				reimb[i]=reimb[i].substring(0,10);
			}
			if(reimb["description"]==null || reimb["description"]==""){
				reimb["description"]="-";
			}
			if(reimb["resolved"]==null){
				reimb["resolved"]="-";
			}
			switch(reimb["statusId"]){
				case 1: 
					reimb["statusId"]="Pending";
					break;
				case 2: 
					reimb["statusId"]="Modify";
					break;
				case 3: 
					reimb["statusId"]="Rejected";
					break;
				case 4: 
					reimb["statusId"]="Approved";
					break;
				case 5: 
					reimb["statusId"]="Other";
					break;
			}
			switch(reimb["typeId"]){
				case 1: 
					reimb["typeId"]="Food";
					break;
				case 2: 
					reimb["typeId"]="Gas";
					break;
				case 3: 
					reimb["typeId"]="Taxi";
					break;
				case 4: 
					reimb["typeId"]="Bus";
					break;
				case 5: 
					reimb["typeId"]="Air Travel";
					break;
				case 6: 
					reimb["typeId"]="Clothing";
					break;
				case 7: 
					reimb["typeId"]="Other";
					break;
			}
			let newTD=document.createElement('td');
			let tdText = document.createTextNode(`${reimb[i]}`);
			newTD.appendChild(tdText);
			newTR.append(newTD);
		}
		document.getElementById("tbody").append(newTR);
	}	
}


/*$('.alert').alert()
$(".alert").delay(4000).slideUp(200, function() {
    $(this).alert('close');
});*/