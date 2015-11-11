package eu.thingsandstuff.pipeline.example.jobs

import eu.thingsandstuff.pipeline.JobType
import eu.thingsandstuff.pipeline.example.ProjectVersion

class SmokeTest extends DummyJobDefinition {

    private final ProjectVersion projectVersion

    SmokeTest(ProjectVersion projectVersion) {
        this.projectVersion = projectVersion
    }

    @Override
    JobType getJobType() {
        return new JobType(super.getJobType().name + '-' + toJobTypeFromUpperUnderscore(projectVersion.name()))
    }
}

