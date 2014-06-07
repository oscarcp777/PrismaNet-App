modules = {
	   
	core {
		resource url:'css/bootstrap.css', disposition: 'head'
		resource url:'js/jquery.js', disposition: 'head'
		resource url:'js/bootstrap.js' 
		resource url:'css/font-awesome.css'
	}
	ace{
		resource url:'css/ace/ace.min.css'
		resource url:'css/ace/ace-skins.min.css'
		resource url:'css/prisma.css'
		resource url:'js/ace/ace.min.js'
		resource url:'js/ace/ace-extra.min.js'
		resource url:'js/ace/ace-elements.min.js'
		resource url:'js/home.js',disposition: 'head'
		resource url:'js/ace/bootstrap-datepicker.min.js'
		resource url:'js/cloud/wordcloud2.js'
		
	}
	chartPie{
		resource url:'js/chart/jquery.easy-pie-chart.js'
		resource url:'js/jquery-ui-1.10.3.full.min.js',disposition: 'head'
		
	}
	highcharts {
		dependsOn 'core'
		resource url:'js/chart/highcharts.js'
		resource url:'js/chart/prismaChart.js'
		resource url:'js/chart/exporting.js'
		
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
}