import eu.thingsandstuff.pipeline.JenkinsVariables
import eu.thingsandstuff.pipeline.PipelineTemplateBuilder
import eu.thingsandstuff.pipeline.example.MicroservicePipelineTemplate
import eu.thingsandstuff.pipeline.example.MicroserviceProject
import javaposse.jobdsl.dsl.DslFactory

def projects = [
        new MicroserviceProject('sample-microservice'),
        new MicroserviceProject('example-microservice'),
]

new PipelineTemplateBuilder(this as DslFactory, JenkinsVariables.from(this))
        .buildAll(MicroservicePipelineTemplate.INSTANCE, projects)