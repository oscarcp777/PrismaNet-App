// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

 grails.config.locations = [ "classpath:twitter4j.properties",
	 						 "classpath:facebook4j.properties",
                             "classpath:prisma-conf.properties"]

 if (System.properties["${appName}.config.location"]) {
    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
 }
def barra = File.separator
//Environment variable that contains a path
def logFile =  "${userHome}${barra}logs${barra}${appName}"
def catalinaBase = System.properties.getProperty('catalina.base')
if (!catalinaBase) catalinaBase = '.'
def logDirectory = "${catalinaBase}${barra}logs${barra}${appName}"
grails.project.groupId = 'com.prismanet' // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/img/*','/css/*','/js/*','/fonts/*','/plugins/*']
grails.resources.adhoc.includes = ['/img/**','/css/**', '/js/**','/fonts/**','/plugins/**']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
//         filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false
jobs.twitter.disable = false
jobs.facebook.disable = false
jobs.solr.disable = false
jobs.facebookPost.disable = false
jobs.monthlyConceptStats.disable = false
jobs.hourlyConceptStats.disable = false
jobs.hourlyTweet.disable = false

environments {
    development {
        grails.logging.jul.usebridge = true
		jobs.exec.jar.tomcat =false
		// log4j configuration
		log4j = {
		   appenders {
				console name: 'stdout', layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss} %-5p [%c{2}] %m%n')
		
				rollingFile name:'appLog', file: "${logFile}${barra}app.log", append: true,
					layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss} %-5p [%c{2}] %m%n'), threshold: org.apache.log4j.Level.INFO
					
				rollingFile name:'errorsLog', file: "${logFile}${barra}errors.log", append: true,
					layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss} %-5p [%c{2}] %m%n'), threshold: org.apache.log4j.Level.ERROR
			}
		   
		   //Para ver valores de filtros
		//   trace 'org.hibernate.type'
		//   debug 'org.hibernate.SQL'
		   
			debug 'grails.app.jobs'
			error  'org.codehaus.groovy.grails.web.servlet',        // controllers
				   'org.codehaus.groovy.grails.web.pages',          // GSP
				   'org.codehaus.groovy.grails.web.sitemesh',       // layouts
				   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
				   'org.codehaus.groovy.grails.web.mapping',        // URL mapping
				   'org.codehaus.groovy.grails.commons',            // core / classloading
				   'org.codehaus.groovy.grails.plugins',            // plugins
				   'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
				   'org.springframework',
				   'org.hibernate',
				   'net.sf.ehcache.hibernate'
				   
			info "grails.app" //,"com.prismanet"
				   additivity: false
					  
			root {
				   info 'appLog', 'stdout'
				   error 'errorsLog'
				   additivity = false
			   }
						  
		}
    }
    production {
        grails.logging.jul.usebridge = false
		jobs.exec.jar.tomcat =true
        // TODO: grails.serverURL = "http://www.changeme.com"
		// log4j configuration
		log4j = {
		   appenders {
				console name: 'stdout', layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss} %-5p [%c{2}] %m%n')
		
				rollingFile name:'appLog', file:"${logDirectory}.log", append: true,
					layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss} %-5p [%c{2}] %m%n'), threshold: org.apache.log4j.Level.INFO
					
				rollingFile name:'errorsLog', file:"${logDirectory}_error.log", append: true,
					layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm:ss} %-5p [%c{2}] %m%n'), threshold: org.apache.log4j.Level.ERROR
			}
		   
		   //Para ver valores de filtros
		//   trace 'org.hibernate.type'
		//   debug 'org.hibernate.SQL'
		   
			debug 'grails.app.jobs'
			error  'org.codehaus.groovy.grails.web.servlet',        // controllers
				   'org.codehaus.groovy.grails.web.pages',          // GSP
				   'org.codehaus.groovy.grails.web.sitemesh',       // layouts
				   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
				   'org.codehaus.groovy.grails.web.mapping',        // URL mapping
				   'org.codehaus.groovy.grails.commons',            // core / classloading
				   'org.codehaus.groovy.grails.plugins',            // plugins
				   'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
				   'org.springframework',
				   'org.hibernate',
				   'net.sf.ehcache.hibernate'
				   
			info "grails.app" //,"com.prismanet"
				   additivity: false
					  
			root {
				   info 'appLog', 'stdout'
				   error 'errorsLog'
				   additivity = false
			   }
						  
		}
    }
}



grails.gorm.failOnError=true
grails.gorm.autoFlush=false

grails.gorm.default.mapping = {
	  dynamicUpdate true
}

//para cambiar string vacios por null
grails.databinding.trimStrings = true
grails.databinding.convertEmptyStringsToNull = true
