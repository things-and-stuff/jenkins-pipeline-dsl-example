package eu.thingsandstuff.pipeline.example.jobs

import eu.thingsandstuff.pipeline.JenkinsVariables
import eu.thingsandstuff.pipeline.example.MicroserviceProject
import javaposse.jobdsl.dsl.Job

abstract class DummyJobDefinition extends MicroserviceJobDefinition {

    @Override
    void configure(Job job, MicroserviceProject microserviceProject, JenkinsVariables jenkinsVariables) {
        def delaySeconds = 2
        job.with {
            steps {
                systemGroovyCommand("""
                    out.println("This is just a dummy placeholder for '${this.getJobLabel()}' job.")
                    out.println("Will terminate in ${delaySeconds} seconds.")
                    Thread.sleep(${delaySeconds} * 1000)
                """.stripIndent())
            }
        }
    }
}
