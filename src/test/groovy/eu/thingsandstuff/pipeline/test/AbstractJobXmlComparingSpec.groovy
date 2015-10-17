package eu.thingsandstuff.pipeline.test

import javaposse.jobdsl.dsl.DslFactory
import javaposse.jobdsl.dsl.JobParent
import spock.lang.Specification
import spock.lang.Unroll

abstract class AbstractJobXmlComparingSpec extends Specification implements JobSpecTrait, XmlComparator {

    static private JobParent jobParent

    def setupSpec() {
        jobParent = createJobParent()
        setupJobs(jobParent)
    }

    final void 'should create exactly the expected jobs'() {
        expect:
            List<String> actualJobs = jobParent.referencedJobs*.name
            actualJobs == expectedJobs
    }

    @Unroll
    final void 'should generate proper XML for job "#job.name"'() {
        expect:
            String fileName = job.name + '.xml'
            def file = new File(expectedJobXmlsPath, fileName)
            compareXmls(file.path, job.node)

        where:
            job << jobParent.referencedJobs
    }

    abstract List<String> getExpectedJobs()

    abstract void setupJobs(DslFactory dslFactory)

    abstract String getExpectedJobXmlsPath()
}
