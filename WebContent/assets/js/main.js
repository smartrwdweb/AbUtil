function ajaxPOST(url1, data1, div) {
	$.ajax({
		type : "POST",
		url : url1,
		data : data1,
		success : function(response) {
			$(div).empty().append(response);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(thrownError + "\r\n" + xhr.statusText + "\r\n"
					+ xhr.responseText);
		}
	});
}

function ajaxGET(url1, data1, div) {
	$.ajax({
		type : "GET",
		url : url1,
		success : function(response) {
			$(div).empty().append(response);
		},
		error : function(xhr, ajaxOptions, thrownError) {
			console.log(thrownError + "\r\n" + xhr.statusText + "\r\n"
					+ xhr.responseText);
		}
	});

}

function copyToClipboard(text) {
    var sampleTextarea = document.createElement("textarea");
    document.body.appendChild(sampleTextarea);
    sampleTextarea.value = text; //save main text in it
    sampleTextarea.select(); //select textarea contenrs
    document.execCommand("copy");
    document.body.removeChild(sampleTextarea);
}

/*function copydata(){
    var copyText = document.getElementById("myInput");
    copyToClipboard(copyText.value);
}*/