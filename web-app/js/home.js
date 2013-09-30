/**
 *Jquery para marcar como active un elemento del menu lateral 
 */
function activeItemMenu(item,subItem,level){
	if(level==1){
		editClassItem(item,'active');
	}else{
		$("#"+item).siblings('.active').removeClass('active');
		$("#"+item).addClass('active open');
		editClassItem(subItem,'active');
	}
}
function editClassItem(item,clas){
	$("#"+item).siblings('.'+clas).removeClass(clas);
	$("#"+item).addClass(clas);
}
