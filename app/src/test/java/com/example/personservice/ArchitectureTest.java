package com.example.personservice;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAnyPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/** Covers what Gradle's module boundaries cannot: which frameworks each module may use. */
@AnalyzeClasses(packages = "com.example.personservice", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

    private static final String BASE = "com.example.personservice";

    @ArchTest
    static final ArchRule domainDoesNotUsePersistenceOrWeb = noClasses()
            .that().resideInAPackage(BASE + ".domain..")
            .should().dependOnClassesThat().resideInAnyPackage(
                    "jakarta.persistence..", "org.hibernate..", "org.springframework.data..",
                    "org.springframework.web..", "jakarta.servlet..");

    @ArchTest
    static final ArchRule domainOnlyUsesServiceAndTransactionSpring = noClasses()
            .that().resideInAPackage(BASE + ".domain..")
            .should().dependOnClassesThat(resideInAPackage("org.springframework..")
                    .and(not(resideInAnyPackage("org.springframework.stereotype..", "org.springframework.transaction.."))))
            .as("domain should only use Spring's @Service and @Transactional");

    @ArchTest
    static final ArchRule sharedDependsOnNoOtherModule = noClasses()
            .that().resideInAPackage(BASE + ".shared..")
            .should().dependOnClassesThat().resideInAnyPackage(
                    BASE + ".domain..", BASE + ".persistence..", BASE + ".api..");

    @ArchTest
    static final ArchRule apiDoesNotDependOnPersistence = noClasses()
            .that().resideInAPackage(BASE + ".api..")
            .should().dependOnClassesThat().resideInAPackage(BASE + ".persistence..");

    @ArchTest
    static final ArchRule persistenceDoesNotDependOnApi = noClasses()
            .that().resideInAPackage(BASE + ".persistence..")
            .should().dependOnClassesThat().resideInAPackage(BASE + ".api..");

    @ArchTest
    static final ArchRule entitiesLiveInPersistence = classes()
            .that().areAnnotatedWith("jakarta.persistence.Entity")
            .should().resideInAPackage(BASE + ".persistence..");

    @ArchTest
    static final ArchRule restControllersLiveInApi = classes()
            .that().areAnnotatedWith("org.springframework.web.bind.annotation.RestController")
            .should().resideInAPackage(BASE + ".api..");

    @ArchTest
    static final ArchRule domainFeaturesHaveNoCycles = slices()
            .matching(BASE + ".domain.(*)..").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule persistenceFeaturesHaveNoCycles = slices()
            .matching(BASE + ".persistence.(*)..").should().beFreeOfCycles();

    @ArchTest
    static final ArchRule apiFeaturesHaveNoCycles = slices()
            .matching(BASE + ".api.(*)..").should().beFreeOfCycles();
}
