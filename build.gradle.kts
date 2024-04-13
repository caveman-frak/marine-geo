plugins {
    id("marine.application-conventions")
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":wire"))
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("io.github.wimdeblauwe:htmx-spring-boot-thymeleaf:3.2.0")

    testImplementation(project(":test"))
    runtimeOnly("org.liquibase:liquibase-core")
    runtimeOnly("com.h2database:h2")
}

testing {
    suites {
        val integrationTest by getting(JvmTestSuite::class) {
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