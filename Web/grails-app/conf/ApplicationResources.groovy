modules = {
	   
	core {
		resource url:'css/bootstrap/bootstrap.css'
		resource url:'js/jquery.js' , disposition: 'head'
		resource url:'js/bootstrap/bootstrap.js'
		resource url:'css/font-awesome.css' 
	}
	ace{
		resource url:'css/ace/ace.min.css'
		resource url:'css/ace/ace-skins.css' 
		resource url:'css/prisma.css'
		resource url:'js/ace/ace-extra.min.js' ,disposition: 'head'
		resource url:'js/ace/fuelux.wizard.min.js' 
		resource url:'js/ace/ace-elements.min.js' 
		resource url:'js/home.js' , disposition: 'head'
		resource url:'js/ace/bootstrap-datepicker.min.js'
		resource url:'js/cloud/wordcloud2.js'
		resource url:'js/ace/ace.min.js' 
		
	}
	chartPie{
		resource url:'js/chart/jquery.easy-pie-chart.js'
		resource url:'js/jquery-ui-1.10.3.full.min.js',disposition: 'head'
		
	}
     editable{
		resource url:'css/bootstrap/bootstrap-editable.css'
		resource url:'js/bootstrap/bootstrap-editable.js'
		resource url:'js/bootstrap/bootbox.js'
		resource url:'js/jquery.autosize.js'
	}
	highcharts {
		dependsOn 'core'
		resource url:'js/chart/highcharts.js', exclude:'minify'
		resource url:'js/chart/exporting.js', exclude:'minify'
		resource url:'js/chart/export-csv.js'
		resource url:'js/chart/prismaChart.js', exclude:'minify'
		
	}
	datepicker{
		dependsOn 'core'
		resource url:'css/daterangepicker-bs3.css'
		resource url:'js/datepicker/moment.js'
		resource url:'js/datepicker/daterangepicker.js'
		resource url:'js/datepicker/prismaDatepicker.js'
	}
	indexcss {
		dependsOn 'core'
		resource url:'css/index.css'
	}
	indexjs {
		resource url:'js/jquery.easing.js'
		resource url:'js/modernizr.js'
		resource url:'js/waypoints.js'
		resource url:'js/jquery.knob.js'
		resource url:'js/main.js'
	}
	login {
		resource url:'css/login.css'
		resource url:'js/jquery.backstretch.min.js',disposition: 'head'
	}
	paginate{
		resource url:'js/jquery.bootpag.min.js'
	}
	
}