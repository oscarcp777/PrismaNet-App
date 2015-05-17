grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		mavenRepo "http://twitter4j.org/maven2"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        runtime 'mysql:mysql-connector-java:5.1.27'
		compile 'org.twitter4j:twitter4j-core:4.0.3'
		compile 'org.twitter4j:twitter4j-async:4.0.3'
		compile 'org.twitter4j:twitter4j-stream:4.0.3'
		compile 'org.twitter4j:twitter4j-media-support:4.0.3'
		compile 'org.grails:grails-datastore-gorm:3.0.4.RELEASE'
  		compile 'org.grails:grails-datastore-core:3.0.4.RELEASE'
  		test 'org.grails:grails-datastore-simple:3.0.4.RELEASE'
  		compile 'org.facebook4j:facebook4j-core:2.2.1-Prisma-SNAPSHOT'
		  
		compile 'org.mongodb:mongo-java-driver:2.12.0-rc3'
	    compile 'org.apache.solr:solr-solrj:4.6.0'
    }

    plugins {
		build ":tomcat:7.0.52.1"
        build(":release:3.0.1",
              ":rest-client-builder:1.0.3") {
            export = false
        }
	    runtime ":hibernate4:4.3.4" //or ":hibernate:3.6.10.9"
		compile ':cache:1.1.1'
		runtime ":database-migration:1.3.8"
		runtime ":resources:1.2.7"
//		compile ":mongodb:2.0.1"
		compile ":constraints:0.8.0"
		compile ":spring-security-core:1.2.7.3"
    }
}
