grails {
	mongo {
		host = "localhost"
		port = 27017
		databaseName = "prismanet"
	}
}

dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
	logSql = true
}
hibernate {
	cache.use_second_level_cache = true //false
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
//	jdbc.batch_size=50
}
// environment specific settings
environments {
	development {
		dataSource {
			pooled = true
			dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:mysql://localhost:3306/prismanet"
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "fiuba"
			//url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
		}
		grails {
			mongo {
				host = "localhost"
				port = 27017
				databaseName = "prismanet"
			}
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
		}
		grails {
			mongo {
				host = "localhost"
				port = 27017
				databaseName = "prismanet"
			}
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			pooled = true
			properties {
			   maxActive = -1
			   minEvictableIdleTimeMillis=1800000
			   timeBetweenEvictionRunsMillis=1800000
			   numTestsPerEvictionRun=3
			   testOnBorrow=true
			   testWhileIdle=true
			   testOnReturn=true
			   validationQuery="SELECT 1"
			}
		}
		grails {
			mongo {
				host = "localhost"
				port = 27017
				databaseName = "prismanet"
			}
		}
	}
}
