var maxSnapshots = 10;
var snapshotInterval = 1500;
var numSnapshot;
var tbl;


$(function() { // when document is ready
	$("#start").submit(pitInit);
	} );
	
function pitInit() {
  $("#initArea").empty();
  tbl = $("<table border=1 cellpadding=20>");

  numSnapshot = 0;
	try {
		$.get("PITinit",pitInitReply);
		return false;
	} catch (e) {
		console.log(e.description);
		$("#initArea").append(e.description);
		return false;
		}
} 

function pitInitReply(data) {
	$("#initArea").html(data.message);
	setTimeout(takeSnapshot, snapshotInterval);
	}

function takeSnapshot() {
  try {
	  $.get("PITsnapshot", pitSnapshotReply);
          return false;
	} catch (e) {
		console.log(e.description);
		$("#initArea").append(e.description);
		return false;
		}
}

function pitSnapshotReply(data) {
    if (data == "Snapshot Failed") {
        $("#initArea").append("<h2>Snapshot #"+  ++numSnapshot+ " Failed</h2><br>");
        if (numSnapshot < maxSnapshots)
		setTimeout(takeSnapshot, snapshotInterval);
        return;
    }
    var rice = 0;
    var gold = 0;
    var oil = 0;
    var cocoa = 0;
    $(data).find(".rice").each(function() { rice += (parseInt($(this).text()) || 0)})
    $(data).find(".gold").each(function() { gold += (parseInt($(this).text()) || 0)})
    $(data).find(".oil").each(function() { oil += (parseInt($(this).text()) || 0)})
    $(data).find(".cocoa").each(function() { cocoa += (parseInt($(this).text()) || 0)})
    data = $(data).append('<tr align=center><td>Sum</td><td>'+rice+'</td><td>'+oil+'</td><td>'+gold+'</td><td>'+cocoa+'</td></tr>');   
    $("#initArea").append("<h2>Snapshot #"+ ++numSnapshot +"</h2>");
    $("#initArea").append(data);
    $("#initArea").append("<br>");
    if (numSnapshot < maxSnapshots)
	setTimeout(takeSnapshot, snapshotInterval);
}
