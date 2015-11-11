package eu.thingsandstuff.pipeline.example

import eu.thingsandstuff.pipeline.Project

class MicroserviceProject implements Project {

    private final String qualifiedName

    MicroserviceProject(String qualifiedName) {
        this.qualifiedName = qualifiedName
    }

    @Override
    String getQualifiedName() {
        return qualifiedName
    }
}
