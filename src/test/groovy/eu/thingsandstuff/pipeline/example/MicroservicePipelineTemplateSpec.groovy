package eu.thingsandstuff.pipeline.example

import eu.thingsandstuff.pipeline.PipelineTemplateBuilder
import eu.thingsandstuff.pipeline.test.AbstractJobXmlComparingSpec
import javaposse.jobdsl.dsl.DslFactory

class MicroservicePipelineTemplateSpec extends AbstractJobXmlComparingSpec {

    List<String> expectedJobs = [
            'sample-microservice-build-and-publish',
            'sample-microservice-smoke-test-current',
            'sample-microservice-find-previous-production-version',
            'sample-microservice-smoke-test-previous',
            'sample-microservice-notify-of-production-deploy',
            'sample-microservice-deploy-current-to-production',
            'sample-microservice-tag-release',
            'sample-microservice-deploy-previous-to-production',
    ]
    String expectedJobXmlsPath = '/eu/thingsandstuff/pipeline/example'

    @Override
    void setupJobs(DslFactory dslFactory) {
        new PipelineTemplateBuilder(dslFactory, TestJenkinsVariables.INSTANCE)
                .build(MicroservicePipelineTemplate.INSTANCE, new MicroserviceProject('sample-microservice'))
    }

}
