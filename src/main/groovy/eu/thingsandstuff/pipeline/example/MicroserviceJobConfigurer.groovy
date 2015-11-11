package eu.thingsandstuff.pipeline.example

import eu.thingsandstuff.pipeline.JobConfigurer
import javaposse.jobdsl.dsl.Job

class MicroserviceJobConfigurer implements JobConfigurer<MicroserviceProject> {
    @Override
    void preConfigure(Job job, MicroserviceProject microserviceProject) {

    }

    @Override
    void postConfigure(Job job, MicroserviceProject microserviceProject) {
        //TODO add email/slack notifications for all job failures
    }
}
