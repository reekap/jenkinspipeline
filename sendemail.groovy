#!/usr/bin/env groovy

/*============================================================
Shared Library
File Name: sendemail.groovy
Input:
	templateflag - Label to identify which template should be called
	email_Sub - Email Subject
	recipients - Email recipients

Dependencies - 	email template in ${JENKINS_HOME}/email-templetes
Call format - sendemail "${templateflag} "${email_Sub}" "${recipients}"

==============================================================*/

//Shared library for emails
def call(templateflag, email_Sub, recipients) {

	stage("email") {

		switch ("${templateflag}"){
			
			case "BUILD": //The template label to be called for any Build Error
				emailext body: '''${SCRIPT, template="email-build.html"}''',
						mimeType: 'text/html',
						subject: "${email_Sub}",
						to: "${recipients}"
				break
			case "BUILDERROR": //The template label to be called for any Build Error
				emailext body: '''${SCRIPT, template="email-buildError.html"}''',
						mimeType: 'text/html',
						subject: "${email_Sub}",
						to: "${recipients}"
				break
		}
	}
	return true
}
