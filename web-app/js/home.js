/**
 *Jquery para marcar como active un elemento del menu lateral 
 */
function activeItemMenuLevel2(item,subItem,nameItem){
	editClassItem(item,'active','active open');
	editClassItem(subItem,'active','active');
	$('#nameItem').text(nameItem);
}
function activeItemMenuLevel3(item,level1,level2,nameItem){
	editClassItem(item,'active','active open');
	editClassItem(level1,'active','active open');
	editClassItem(level2,'active','active');
	$('#nameItem').text(nameItem);
}
function activeItemMenuLevel1(item,nameItem){
	editClassItem(item,'active','active');
	$('#nameItem').text(nameItem);
}
function editClassItem(item,classRemove,classNew){
	$("#"+item).siblings('.'+classRemove).removeClass(classRemove);
	$("#"+item).addClass(classNew);
}

function changeState(id, url, tweetId, conceptId, value){
	var className = $("#"+id).attr('class');
	if(className=="icon last tooltips selected"){
		return
	}
	$('.icon.last.tooltips').removeClass("selected")
	$("#"+id).addClass("selected");
	data = {'conceptId': conceptId, 'tweetId': tweetId, 'value':value}
    doRequest(url, data, null, null, "POST");
}

function activeOpinion(id, value){
	if(value == "POSITIVE"){
		$("#"+id+"Pos").addClass("selected");
	}
	if(value == "NEGATIVE"){
		$("#"+id+"Neg").addClass("selected");
	}
	if(value == "NEUTRAL"){
		$("#"+id+"Que").addClass("selected");
	}
		
}

function doRequest(url,data,callback, errorHandler, method) {
    $.ajax({
            url: url,
            type: method,
            data: data,
            cache: false,
            success: callback,
            error: errorHandler
          });
};

