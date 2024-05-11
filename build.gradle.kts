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

testing {
    suites {
        withType<JvmTestSuite> {
            dependencies {
                implementation(project(":shared"))
                implementation(project(":wire"))
                implementation(project(":test"))
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