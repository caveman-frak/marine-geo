plugins {
    id("marine.application-conventions")
    id("marine.tailwind-build")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":wire"))
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