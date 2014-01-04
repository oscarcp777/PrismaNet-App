/**
 *Jquery para marcar como active un elemento del menu lateral 
 */
function activeItemMenu(item,subItem,level,nameItem){
	if(level==1){
		editClassItem(item,'active');
	}else{
		$("#"+item).siblings('.active').removeClass('active');
		$("#"+item).addClass('active open');
		editClassItem(subItem,'active');
	}
	$('#nameItem').text(nameItem);
}
function editClassItem(item,clas){
	$("#"+item).siblings('.'+clas).removeClass(clas);
	$("#"+item).addClass(clas);
}

function changeState(id){
	var parent=id.substring(0,id.length-3);
	var className = $("#"+id).attr('class');
	if(className=="icon last tooltips selected"){
		$("#"+id).removeClass("selected")
	}else{
		$("#"+parent +" > a").removeClass("selected")
		$("#"+id).addClass("selected");
		
	}
	
}
