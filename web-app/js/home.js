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
