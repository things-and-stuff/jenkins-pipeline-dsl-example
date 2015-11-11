package eu.thingsandstuff.pipeline.example.jobs

import eu.thingsandstuff.pipeline.JobDefinition
import eu.thingsandstuff.pipeline.example.MicroserviceProject
import javaposse.jobdsl.dsl.Job

abstract class MicroserviceJobDefinition extends JobDefinition<Job, MicroserviceProject> {

    Class<Job> jobClass = Job

}
