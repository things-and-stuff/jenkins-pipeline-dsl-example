package eu.thingsandstuff.pipeline.example.jobs

import eu.thingsandstuff.pipeline.JobType
import eu.thingsandstuff.pipeline.example.ProjectVersion

class DeployToProduction extends DummyJobDefinition {

    private final ProjectVersion projectVersion

    DeployToProduction(ProjectVersion projectVersion) {
        this.projectVersion = projectVersion
    }

    @Override
    JobType getJobType() {
        return new JobType(toJobTypeFromUpperUnderscore('DEPLOY_' + projectVersion.name() + '_TO_PRODUCTION'))
    }
}
