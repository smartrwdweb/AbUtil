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