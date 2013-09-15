modules = {
	    application {
	        resource url:'js/application.js'
	    }
	core {
		resource url:'css/bootstrap.css', disposition: 'head'
		resource url:'js/jquery.js', disposition: 'head'
		resource url:'js/bootstrap.js' 
		resource url:'css/font-awesome.css'
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