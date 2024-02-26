package Borisova.spring2024;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

public class ArchitectureTests {
    private static final String PACKAGE_NAME = "Borisova.spring2024";
    private final JavaClasses classes = new ClassFileImporter().importPackages(PACKAGE_NAME);

    @Test
    void LayeredArchitectureTest(){
        Architectures
                .layeredArchitecture()
                .consideringAllDependencies()
                .layer("domain").definedBy(PACKAGE_NAME + ".domain..")
                .layer("repositories").definedBy(PACKAGE_NAME + ".repositories..")
                .layer("services").definedBy(PACKAGE_NAME + ".services")
                .layer("extern").definedBy(PACKAGE_NAME + ".extern..")
                .whereLayer("repositories").mayOnlyBeAccessedByLayers("repositories", "services", "extern")
                .whereLayer("services").mayOnlyBeAccessedByLayers("services", "extern")
                .whereLayer("extern").mayNotBeAccessedByAnyLayer()
                .check(classes);
    }
}
