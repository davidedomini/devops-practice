plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    id("org.jetbrains.dokka") version "1.8.20"
    id ("org.danilopianini.publish-on-central") version "5.0.7"
    id("org.danilopianini.git-sensitive-semantic-versioning-gradle-plugin") version "1.1.10"
    application
}

group = "io.github.davidedomini"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

application {
    mainClass.set("it.unibo.lss.HelloClassKt")
}

publishOnCentral {
    projectUrl.set("https://github.com/davidedomini/devops-practice")
    scmConnection.set("git:git@github.com:davidedomini/devops-practice")
}

publishing {
    publications {
        withType<MavenPublication> {
            pom {
                developers {
                    developer {
                        name.set("Davide Domini")
                        email.set("davide.domini@studio.unibo.it")
                        url.set("https://davidedomini.github.io/")
                    }
                }
            }
        }
    }
}

if(System.getenv("CI") == true.toString()) {
    signing {
        val signingKey: String? by project
        val signingPassword: String? by project
        useInMemoryPgpKeys(signingKey, signingPassword)
    }
}
