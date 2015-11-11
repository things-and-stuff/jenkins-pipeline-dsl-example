package eu.thingsandstuff.pipeline.example.jobs

import eu.thingsandstuff.pipeline.example.ProjectVersion

class Jobs {
    static BUILD_AND_PUBLISH = new BuildAndPublish()
    static SMOKE_TEST_CURRENT_VERSION = new SmokeTest(ProjectVersion.CURRENT)
    static FIND_PREVIOUS_PRODUCTION_VERSION = new FindPreviousProductionVersion()
    static SMOKE_TEST_PREVIOUS_VERSION = new SmokeTest(ProjectVersion.PREVIOUS)
    static NOTIFY_OF_PRODUCTION_DEPLOY = new NotifyOfProductionDeploy()
    static DEPLOY_CURRENT_VERSION_TO_PRODUCTION = new DeployToProduction(ProjectVersion.CURRENT)
    static TAG_RELEASE = new TagRelease()
    static DEPLOY_PREVIOUS_VERSION_TO_PRODUCTION = new DeployToProduction(ProjectVersion.PREVIOUS)
}
