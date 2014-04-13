/**

 *Jquery para marcar como active un elemento del menu lateral 
 */
function activeItemMenuLevel2(item,subItem){
	editClassItem(item,'active','active open');
	editClassItem(subItem,'active','active');
}
function activeItemMenuLevel3(item,level1,level2,nameItem){
	editClassItem(item,'active','active open');
	editClassItem(level1,'active','active open');
	editClassItem(level2,'active','active');
	$('#nameItem').text(nameItem);
}
function activeItemMenuLevel1(item){
	editClassItem(item,'active','active');
}
function editClassItem(item,classRemove,classNew){
	$("#"+item).siblings('.'+classRemove).removeClass(classRemove);
	$("#"+item).addClass(classNew);
}

function changeState(id, url, tweetId, conceptId, value){
	var parent=id.substring(0,id.length-3);
	var className = $("#"+id).attr('class');
	if(className.indexOf("selected")!=-1){
		$("#"+id).removeClass("selectedPOS")
		$("#"+id).removeClass("selectedNEQ")
		$("#"+id).removeClass("selectedNEU")
	}else{
		$("#"+parent +" > a").removeClass("selectedPOS")
		$("#"+parent +" > a").removeClass("selectedNEQ")
		$("#"+parent +" > a").removeClass("selectedNEU")
		if(value=="POS")
			$("#"+id).addClass("selectedPOS");
		if(value=="NEG")
			$("#"+id).addClass("selectedNEQ");
		if(value=="NEU")
			$("#"+id).addClass("selectedNEU");
		
	}
	data = {'conceptId': conceptId, 'tweetId': tweetId, 'value':value}
    doRequest(url, data, null, null, "POST");
}

function activeOpinion(id, value){
	if(value == "POSITIVE"){
		$("#"+id+"Pos").addClass("selectedPOS");
	}
	if(value == "NEGATIVE"){
		$("#"+id+"Neg").addClass("selectedNEQ");
	}
	if(value == "NEUTRAL"){
		$("#"+id+"Que").addClass("selectedNEU");
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

