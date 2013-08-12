
quartz {
	autoStartup = false
	jdbcStore = false
	waitForJobsToCompleteOnShutdown = true
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

			autoStartup = false
			jdbcStore = false
			props(jdbcProps)
		}
	}
	production {
		quartz {
			jdbcStore = false
			autoStartup = false
			props(jdbcProps)
		}
	}
}
