
quartz {
	autoStartup = true
	jdbcStore = false
	waitForJobsToCompleteOnShutdown = false
	exposeSchedulerInRepository = false

	props {
		scheduler.skipUpdateCheck = true
	}
}

environments {
	test {
		quartz {
			autoStartup = false
			jdbcStore = false
		}
	}
	development {
		quartz {
			// Toggle scheduled tasks for local "run-app" environment

			autoStartup = true
			jdbcStore = false
			props(jdbcProps)
		}
	}
	production {
		quartz {
			jdbcStore = false
			autoStartup = true
			props(jdbcProps)
		}
	}
}
