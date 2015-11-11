package eu.thingsandstuff.pipeline.example

import eu.thingsandstuff.pipeline.StageNameConfigurer
import javaposse.jobdsl.dsl.Job

import static eu.thingsandstuff.pipeline.example.JenkinsVariable.*


class DeliveryPipelineStageNameConfigurer implements StageNameConfigurer {

    @Override
    void configure(Job job, String stageName, String jobLabel) {
        job.with {
            deliveryPipelineConfiguration(stageName, jobLabel)
            wrappers {
                deliveryPipelineVersion(PIPELINE_VERSION.envReference, true)
            }
        }
    }
}