job('ejemplo-job-DSL-2') {
	description('Job DSL de ejemplo para el curso de Jenkins')
  	scm {
      git('https://github.com/VictorVizcarra/jenkins.job.parametrizado.git', 'main') { node ->
        node / gitConfigName('VictorVizcarra')
        node / gitConfigEmail('vizcarraunison1@gmail.com')
      }
    }
  	parameters {
      stringParam('nombre', defaultValue = 'Julian', description = 'Parametro de cadena para el job booleano')
      choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
      booleanParam('agente', false)
    }
    triggers {
      cron('H/7 * * * *')
    }
  	steps {
        shell('bash jobscript.sh')
    }
  publishers {
       	mailer('vizcarraunison1@gmail.com', true, true)
    slackNotifier {
    	notifyAborted(true)
      	notifyEveryFailure(true)
      	notifyNotBuilt(false)
      	notifyUnstable(false)
      	notifyBackToNormal(true)
      	notifySuccess(true)
      	notifyRepeatedFailure(false)
      	startNotification(false)
      	includeTestSummary(false)
      	includeCustomMessage(false)
      	customMessage(null)
      	sendAs(null)
      	commitInfoChoice('NONE')
      	teamDomain(null)
      	authToken(null)
                       
      }
    }
}
