plugins {
    id("marine.application-conventions")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":wire"))
    testImplementation(project(":test"))
    runtimeOnly("org.liquibase:liquibase-core")
    runtimeOnly("com.h2database:h2")
}

testing {
    suites {
        val integrationTest by registering(JvmTestSuite::class) {
            dependencies {
                implementation(project())
                implementation(project(":shared"))
                implementation(project(":wire"))
                implementation(project(":test"))
            }
        }
    }
}