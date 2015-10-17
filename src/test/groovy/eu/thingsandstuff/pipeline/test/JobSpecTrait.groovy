package eu.thingsandstuff.pipeline.test

import javaposse.jobdsl.dsl.JobManagement
import javaposse.jobdsl.dsl.JobParent
import javaposse.jobdsl.dsl.MemoryJobManagement

trait JobSpecTrait {

    JobParent createJobParent() {
        return createJobParent(new MemoryJobManagement())
    }

    JobParent createJobParent(JobManagement jobManagement) {
        JobParent jobParent = new TestJobParent()
        jobParent.setJm(jobManagement)
        return jobParent
    }

    static class TestJobParent extends JobParent {
        @Override
        Object run() {
            return null
        }
    }
}
