
function loadTopicsAtPage(u) {
	var pgnum = parseInt(document.getElementById("crtpg").value);
	
	if (u == 0) {
		pgnum--;
	} else {
		pgnum++;
	}
	
	document.location.href = "Topic?pg=" + pgnum;
}

function goToTopicsAtPage() {
    var pgn = document.getElementById("topicpage").value;
	
	if (pgn.length == 0) {
		pgn = document.getElementById("topicpage2").value;
	}
	
	document.location.href = "Topic?pg=" + pgn;
}

function createNewTopic() {
	var topicCont = $("#topicCont").val();
	var s_course = $("#s_course").val();
	
	$.post("AddTopic", {
		'topicCont' : topicCont,
		's_course' : s_course
	}, function( data ) {  
		alert( "Data Loaded: " + data );
	});
}

function myFunction() {
	   
    var x = document.getElementById("fname");
    x.value = x.value.toUpperCase();


}