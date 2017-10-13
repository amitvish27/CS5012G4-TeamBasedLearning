function moveProbOrder(u,v) {
    document.location.href = "switchorder?dir=" +u + "&pid=" + v;
}

function loadProblemsAtPage(u) {
	var pgnum = parseInt(document.getElementById("crtpg").value);
	
	if (u == 0) {
		pgnum--;
	} else {
		pgnum++;
	}
	
	document.location.href = "listmath?pg=" + pgnum;
}