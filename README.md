# jenkins-pipeline-dsl-example

This is an example/bootstrap project for quickly getting up and running with 
[jenkins-dsl](https://github.com/jenkinsci/job-dsl-plugin#jenkins-job-dsl--plugin) in a sustainable way.
 
## Features

 - a one-command local jenkins setup: `./gradlew -b prepare_jenkins.gradle` (creates a `./runJenkins.{sh, bat}` script)
 - a [sample pipeline template](https://github.com/things-and-stuff/jenkins-pipeline-dsl-example/blob/master/src/main/groovy/eu/thingsandstuff/pipeline/example/MicroservicePipelineTemplate.groovy#L35)
   possible thanks to [jenkins-pipeline-dsl](https://github.com/things-and-stuff/jenkins-pipeline-dsl)
 - test utils letting you [easily test](https://github.com/things-and-stuff/jenkins-pipeline-dsl-example/blob/master/src/test/groovy/eu/thingsandstuff/pipeline/example/MicroservicePipelineTemplateSpec.groovy#L7)
   every single [XML node](https://github.com/things-and-stuff/jenkins-pipeline-dsl-example/tree/master/src/test/resources/eu/thingsandstuff/pipeline/example) 
   in the job definitions
   