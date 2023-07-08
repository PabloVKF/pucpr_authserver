import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.7"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
}

group = "br.pucpr"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")

	runtimeOnly("com.h2database:h2")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.13.5")
	testImplementation("io.kotest:kotest-runner-junit5-jvm:5.6.2")

	runtimeOnly("io.kotest:kotest-assertions-core:5.6.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

allOpen{
	annotation("jakarta.persistence.Entity")
}