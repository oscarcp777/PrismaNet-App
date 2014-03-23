grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility
    repositories {
        grailsCentral()
        mavenCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
		mavenRepo "http://twitter4j.org/maven2"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        runtime 'mysql:mysql-connector-java:5.1.21'
		compile 'org.twitter4j:twitter4j-core:3.0.4-SNAPSHOT'
		compile 'org.twitter4j:twitter4j-async:3.0.4-SNAPSHOT'
		compile 'org.twitter4j:twitter4j-stream:3.0.4-SNAPSHOT'
		compile 'org.twitter4j:twitter4j-media-support:3.0.4-SNAPSHOT'
    }

    plugins {
		runtime ":hibernate:$grailsVersion"
		compile ":mongodb:1.3.0"
        build(":tomcat:$grailsVersion",
              ":release:2.2.1",
              ":rest-client-builder:1.0.3") {
            export = false
        }
//		compile ":spring-security-core:1.2.7.3"
//		compile ":spring-security-ui:0.2"
		compile ":constraints:0.8.0"
    }
}
