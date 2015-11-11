package eu.thingsandstuff.pipeline.example

import eu.thingsandstuff.pipeline.JenkinsVariables
import eu.thingsandstuff.pipeline.Variable

enum JenkinsVariable implements Variable {

    PIPELINE_VERSION,
    GIT_COMMIT,
    APP_VERSION,
    PREV_APP_VERSION

    @Override
    String getReference() {
        return JenkinsVariables.reference(this)
    }

    String getEnvReference() {
        return JenkinsVariables.envReference(this)
    }
}
