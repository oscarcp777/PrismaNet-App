grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve true // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		  mavenRepo "http://twitter4j.org/maven2"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

        runtime 'mysql:mysql-connector-java:5.1.22'
		compile 'org.twitter4j:twitter4j-core:3.0.4-SNAPSHOT'
		compile 'org.twitter4j:twitter4j-async:3.0.4-SNAPSHOT'
		compile 'org.twitter4j:twitter4j-stream:3.0.4-SNAPSHOT'
		compile 'org.twitter4j:twitter4j-media-support:3.0.4-SNAPSHOT'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.10.2"
        runtime ":resources:1.2.RC2"
		compile ":fields:1.3"
		compile ":twitter-bootstrap:2.3.2"
		compile ":less-resources:1.3.3.2"
		
        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.5"

        build ":tomcat:$grailsVersion"

        runtime ":database-migration:1.3.2"

        compile ':cache:1.0.1'
		
		compile ":create-domain-uml:0.5"
		
		compile ":constraints:0.8.0"
		
		compile ":quartz:1.0-RC9"
		
		compile ":mongodb:1.3.0"
		
		compile ':gson:1.1.4'
		
		
    }
}
