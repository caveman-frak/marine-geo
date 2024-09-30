plugins {
    id("marine.application-ui-conventions")
}

dependencies {
}

testing {
    suites {
        withType<JvmTestSuite> {
            dependencies {
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