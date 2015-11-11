package eu.thingsandstuff.pipeline.example

import eu.thingsandstuff.pipeline.JobChain
import eu.thingsandstuff.pipeline.JobConfigurer
import eu.thingsandstuff.pipeline.PipelineBuilder
import eu.thingsandstuff.pipeline.PipelineTemplate
import eu.thingsandstuff.pipeline.StageNameConfigurer
import eu.thingsandstuff.pipeline.TriggerCondition
import eu.thingsandstuff.pipeline.example.jobs.Jobs
import eu.thingsandstuff.pipeline.link.CombinedJobChainLink

import static eu.thingsandstuff.pipeline.example.JenkinsVariable.APP_VERSION
import static eu.thingsandstuff.pipeline.example.JenkinsVariable.GIT_COMMIT
import static eu.thingsandstuff.pipeline.example.JenkinsVariable.PREV_APP_VERSION
import static eu.thingsandstuff.pipeline.link.AutoLink.auto
import static eu.thingsandstuff.pipeline.link.ManualLink.manual

class MicroservicePipelineTemplate implements PipelineTemplate<MicroserviceProject> {

    static final INSTANCE = new MicroservicePipelineTemplate()

    private MicroservicePipelineTemplate() {}

    @Override
    JobConfigurer<MicroserviceProject> createJobConfigurer() {
        return new MicroserviceJobConfigurer()
    }

    @Override
    StageNameConfigurer createStageNameConfigurer() {
        return new DeliveryPipelineStageNameConfigurer()
    }

    @Override
    void configurePipeline(PipelineBuilder<MicroserviceProject> pipelineBuilder, MicroserviceProject project) {
        pipelineBuilder.configure {
            stage('Build') {
                job Jobs.BUILD_AND_PUBLISH
            }
            stage('Run smoke & rollback tests') {
                job Jobs.SMOKE_TEST_CURRENT_VERSION
                job Jobs.FIND_PREVIOUS_PRODUCTION_VERSION
                job Jobs.SMOKE_TEST_PREVIOUS_VERSION
            }
            stage('Deploy to prod') {
                job Jobs.NOTIFY_OF_PRODUCTION_DEPLOY
                job Jobs.DEPLOY_CURRENT_VERSION_TO_PRODUCTION
                job Jobs.TAG_RELEASE
            }
            stage('Automatic rollback') {
                job Jobs.DEPLOY_PREVIOUS_VERSION_TO_PRODUCTION
            }

            chain(JobChain.of(Jobs.BUILD_AND_PUBLISH)
                    .then(auto(Jobs.SMOKE_TEST_CURRENT_VERSION)
                        .withPredefinedProperties(APP_VERSION, GIT_COMMIT)
                    )
                    .then(Jobs.FIND_PREVIOUS_PRODUCTION_VERSION)
                    .then(auto(Jobs.SMOKE_TEST_PREVIOUS_VERSION, TriggerCondition.UNSTABLE_OR_BETTER)
                        .withPredefinedProperties(PREV_APP_VERSION)
                    )
                    .then(manual(Jobs.DEPLOY_CURRENT_VERSION_TO_PRODUCTION))
                    .then(CombinedJobChainLink.of(
                        auto(Jobs.TAG_RELEASE, TriggerCondition.SUCCESS),
                        auto(Jobs.DEPLOY_PREVIOUS_VERSION_TO_PRODUCTION, TriggerCondition.UNSTABLE_OR_WORSE)
                    ))
            )
        }
    }
}
