import org.jooq.meta.jaxb.Logging

plugins {
    id("marine.application-conventions")
    id("marine.tailwind-build")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":wire"))
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("io.github.wimdeblauwe:htmx-spring-boot-thymeleaf:3.3.0")
}

jooq {
    configuration {
        logging = Logging.TRACE
        generator {
            database {
                name = "org.jooq.meta.extensions.jpa.JPADatabase"
                properties {
                    // A comma separated list of Java packages, that contain your entities
                    property {
                        key = "packages"
                        value = "uk.co.bluegecko.marine.geo.data.model," +
                                "uk.co.bluegecko.marine.geo.data.convertor," +
                                "uk.co.bluegecko.marine.geo.data.repository"
                    }
                    // Whether JPA 2.1 AttributeConverters should be auto-mapped to jOOQ Converters.
                    // Custom <forcedType/> configurations will have a higher priority than these auto-mapped converters.
                    // This defaults to true.
                    property {
                        key = "useAttributeConverters"
                        value = "true"
                    }
                    // The default schema for unqualified objects:
                    //
                    // - public: all unqualified objects are located in the PUBLIC (upper case) schema
                    // - none: all unqualified objects are located in the default schema (default)
                    //
                    // This configuration can be overridden with the schema mapping feature
                    property {
                        key = "unqualifiedSchema"
                        value = "public"
                    }
                    // Whether JPA 2.1 AttributeConverters should be auto-mapped to jOOQ Converters.
                    // Custom <forcedType/> configurations will have a higher priority than these auto-mapped converters.
                    // This defaults to true.
                    property {
                        key = "useAttributeConverters"
                        value = "true"
                    }
//                    property {
//                        key = "hibernate.physical_naming_strategy"
//                        value = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy"
//                    }
                }
            }
            generate {
                isFluentSetters = true
                isJavaTimeTypes = true
            }
            target {
                packageName = "uk.co.bluegecko.marine.geo.jooq"
                directory = "src/main/generated"
                isClean = true
            }
        }
    }
}

testing {
    suites {
        withType<JvmTestSuite> {
            dependencies {
                implementation(project(":shared"))
                implementation(project(":wire"))
                implementation(project(":test"))
                implementation(testFixtures(project(":shared")))
                implementation(testFixtures(project(":wire")))
            }
        }
    }
}

application {
    mainClass.set("uk.co.bluegecko.marine.geo.GeographicApplication")
}

tasks.processResources {
    dependsOn(tasks.named("buildCss"))
}