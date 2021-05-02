let counter = 0;
let reimbs;
let dumVar=[];
window.onload = function() {
	getUser();
	getUserReimbs();
}
/*let rs="";*/
rsRef = [];


async function getUser() {
	let ersUser = await fetch("http://localhost:8080/ers/serv2/getUser");
	ersUser = await ersUser.json();
	let newA = document.createElement('a');
	newA.id = "newAId";
	newA.setAttribute('href', 'view-user.ers');
	let aText = document.createTextNode(ersUser.userName);
	newA.appendChild(aText);
	document.getElementById("id2").append(newA);
	console.log(ersUser);
}

async function getUserReimbs() {
	reimbs = await fetch("http://localhost:8080/ers/serv2/getFReimbs");
	reimbs = await reimbs.json();
	/*rsRef=reimbs;*//*whay the rsRef keeps changing!!!!*/
	rsRef = reimbs;
	console.info(reimbs);
	if (reimbs.length == 0) {
		document.getElementById("h2").innerText = "There are no reimbursement ticket";
		document.getElementById("reimbTable").style.display = "none";
	}

	tableFiller(reimbs, "tbody");
	
}

function tableFiller(reimbs, tableId) {
	for (k of rsRef) {
		if (document.getElementById(`${k["id"]}${counter-1}`) != null) {
			document.getElementById(`${k["id"]}${counter-1}`).style.display="none";
		}
		}
		for (reimb of reimbs) {
			let newTR = document.createElement('tr');
			newTR.id = `${reimb["id"]}${counter}`;
			let idTD = document.createElement('td');
			let imTD=document.createElement('td');
			idTD.innerHTML=`<a href="update-reimb.ers?currentReimbId=${reimb["id"]}">${reimb["id"]}</a>`;
			if(reimb.receipt==""){
				imTD.innerHTML=" ";
			}else{
				imTD.innerHTML=`<img style="max-width:25px;" src="data:image/jpeg;base64,${reimb.receipt}">`;
			}
			
			newTR.append(idTD);
			newTR.append(imTD);
			for (let i in reimb) {
				if (i == "id" || i == "resolverUserName" || i == "resolverId" || i == "authorId"||i=="receipt") {
					continue;
				}
				if (i == "submitted" || i == "resolved") {
					reimb[i] = reimb[i].substring(0, 10);
				}
				if (reimb["description"] == null || reimb["description"] == "") {
					reimb["description"] = "-";
				}
				if (reimb["resolved"] == null) {
					reimb["resolved"] = "-";
				}
				switch (reimb["statusId"]) {
					case 1:
						reimb["statusId"] = "Pending";
						break;
					case 2:
						reimb["statusId"] = "Modify";
						break;
					case 3:
						reimb["statusId"] = "Rejected";
						break;
					case 4:
						reimb["statusId"] = "Approved";
						break;
					case 5:
						reimb["statusId"] = "Other";
						break;
				}
				switch (reimb["typeId"]) {
					case 1:
						reimb["typeId"] = "Food";
						break;
					case 2:
						reimb["typeId"] = "Gas";
						break;
					case 3:
						reimb["typeId"] = "Taxi";
						break;
					case 4:
						reimb["typeId"] = "Bus";
						break;
					case 5:
						reimb["typeId"] = "Air Travel";
						break;
					case 6:
						reimb["typeId"] = "Clothing";
						break;
					case 7:
						reimb["typeId"] = "Other";
						break;
				}
				let newTD = document.createElement('td');
				let tdText = document.createTextNode(`${reimb[i]}`);
				newTD.appendChild(tdText);
				newTR.append(newTD);
				
				
				let newSel = document.createElement('option');
				newSel.innerHTML=`${reimb["authorUserName"]}`;
				newSel.value=`${reimb["authorUserName"]}`;
				if(!(dumVar.includes(`${reimb["authorUserName"]}`))){document.getElementById("auth").append(newSel);}
				dumVar.push(`${reimb["authorUserName"]}`);
				
			}
			document.getElementById(tableId).append(newTR);
		}
		counter++;
	}

var nonLinearStepSlider = document.getElementById('slider-non-linear-step');

noUiSlider.create(nonLinearStepSlider, {
	start: [100, 2000],
	range: {
		'min': [0, 30],
		'30%': [100, 300],
		'50%': [500, 1000],
		'70%': [2000, 5000],
		'90%': [7000, 20000],
		'max': [200000]
	}
});
var nonLinearStepSliderValueElement = document.getElementById('slider-non-linear-step-value');
nonLinearStepSlider.noUiSlider.on('update', function(values) {
	values[0] = values[0].substring(0, values[0].length - 3);
	values[1] = values[1].substring(0, values[1].length - 3);
	nonLinearStepSliderValueElement.innerHTML = values.join(' - ') + ' $';
});



document.getElementById('link1').addEventListener('click', function() {
	if (document.getElementById('myCon').style.display == '' ||
		document.getElementById('myCon').style.display == 'none') {
		document.getElementById('myCon').style.display = 'flex';
		return false;
	}
	if (document.getElementById('myCon').style.display == 'flex') {
		document.getElementById('myCon').style.display = 'none';
	}
});

let dummy=0;
document.getElementById("filterButton").addEventListener("click", function() {
	let rs = [];
	for (x in rsRef) {
		rs[x] = rsRef[x];
	}

	if (rs == "") {
		alert("Resources are being fetched from the server, please try again!");
		return false;
	}
	/*	document.getElementById("reimbTable").style.display="none";
		document.getElementById("reimbTable2").style.display="block";*/
	let min = nonLinearStepSlider.noUiSlider.get()[0];
	let max = nonLinearStepSlider.noUiSlider.get()[1];
	let currency = document.getElementById("cur").value;
	let author = document.getElementById("auth").value;
	let status = document.getElementById("stat").value;
	let type = document.getElementById("typ").value;
	
	/*for (r of rs) {
		if (r.amount < min || r.amount > max
			|| ((r.ersCurrency).toLowerCase() != currency && currency != "any")
			|| ((r.authorUserName).toLowerCase() != author.toLowerCase() && author != "any")
			|| ((r.statusId).toLowerCase() != status.toLowerCase() && status != "any")
			|| ((r.typeId).toLowerCase() != type.toLowerCase() && type != "any")
		) {
			if (rs.indexOf(r) != -1) {
				rs.splice((rs.indexOf(r)-dummy), 1);
				console.log(rs);
				dummy++;
			}
		}
	}*/
/*	for (r in rs) {*/
	for (r=0; r< rs.length;r++) {
		if (rs[r].amount < min || rs[r].amount > max
			|| ((rs[r].ersCurrency) != currency && currency != "any")
			|| ((rs[r].authorUserName).toLowerCase() != author.toLowerCase() && author != "any")
			|| ((rs[r].statusId).toLowerCase() != status.toLowerCase() && status != "any")
			|| ((rs[r].typeId).toLowerCase() != type.toLowerCase() && type != "any")
		) {
			if (r != -1) {
				rs.splice(r, 1);
				r=r-1;
			}
		}
	}
	tableFiller(rs, "tbody");

});





function Clone(x) {
	for (p in x)
		this[p] = (typeof (x[p]) == 'object') ? new Clone(x[p]) : x[p];
}
