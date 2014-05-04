grails {
	mongo {
		host = "localhost"
		port = 27017
		databaseName = "prismanet"
	}
}

dataSource {
	pooled = true
	jmxExport = true
    driverClassName = "com.mysql.jdbc.Driver"
	username = "root"
	password = "fiuba"
	logSql = false
}
hibernate {
	cache.use_second_level_cache = false
	cache.use_query_cache = false
//  cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
	cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
//    singleSession = true // configure OSIV singleSession mode
	jdbc.batch_size=50
}

// environment specific settings
environments {
	development {
		dataSource {
			pooled = true
			dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
			dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
//			url = "jdbc:mysql://192.168.0.2:3306/prismanet?autoReconnect=true"
			url = "jdbc:mysql://localhost:3306/prismanet?autoReconnect=true"
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "fiuba"
			properties {
			   // See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
			   jmxEnabled = true
			   initialSize = 5
			   maxActive = 50
			   minIdle = 5
			   maxIdle = 25
			   maxWait = 10000
			   maxAge = 10 * 60000
			   timeBetweenEvictionRunsMillis = 5000
			   minEvictableIdleTimeMillis = 60000
			   validationQuery = "SELECT 1"
			   validationQueryTimeout = 3
			   validationInterval = 15000
			   testOnBorrow = true
			   testWhileIdle = true
			   testOnReturn = false
			   jdbcInterceptors = "ConnectionState;StatementCache(max=200)"
			   defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
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
			pooled = true
			dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
			dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:mysql://localhost:3306/prismanet?autoReconnect=true"
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "fiuba"
			properties {
			   // See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
			   jmxEnabled = true
			   initialSize = 5
			   maxActive = 50
			   minIdle = 5
			   maxIdle = 25
			   maxWait = 10000
			   maxAge = 10 * 60000
			   timeBetweenEvictionRunsMillis = 5000
			   minEvictableIdleTimeMillis = 60000
			   validationQuery = "SELECT 1"
			   validationQueryTimeout = 3
			   validationInterval = 15000
			   testOnBorrow = true
			   testWhileIdle = true
			   testOnReturn = false
			   jdbcInterceptors = "ConnectionState;StatementCache(max=200)"
			   defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
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
