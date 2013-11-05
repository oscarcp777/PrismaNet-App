modules = {
	    application {
	        resource url:'js/jquery.slimscroll.js'
	    }
	core {
		resource url:'css/bootstrap.css', disposition: 'head'
		resource url:'js/jquery.js', disposition: 'head'
		resource url:'js/bootstrap.js' 
		resource url:'css/font-awesome.css'
	}
	ace{
		resource url:'css/ace/ace.css'
		resource url:'css/ace/ace-skin.css'
		resource url:'js/ace/ace.js'
		resource url:'js/ace/ace-extra.js'
		resource url:'js/ace/ace-elements.js'
		resource url:'js/jquery.slimscroll.js'
		resource url:'js/home.js',disposition: 'head'
	}
	chartPie{
		resource url:'js/chart/jquery.flot.js',disposition: 'head'
		resource url:'js/chart/jquery.flot.pie.js'
		resource url:'js/chart/jquery.flot.resize.js'
		resource url:'js/chart/jquery.easy-pie-chart.js'
		resource url:'js/jquery-ui-1.10.3.custom.min.js'
		
	}
	highcharts {
		dependsOn 'core'
		resource url:'js/chart/highcharts.js'
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
	}
}