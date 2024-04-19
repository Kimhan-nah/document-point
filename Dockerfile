# 기반 이미지로 openjdk 이미지를 사용합니다.
FROM maven:3.8-openjdk-17 AS builder

# 작업 디렉토리를 생성합니다.
WORKDIR /app

# Maven 프로젝트 파일을 복사합니다.
COPY pom.xml .
COPY src ./src

# Maven으로 프로젝트를 빌드합니다.
RUN mvn clean package

# 실행 가능한 JAR 파일을 빌드합니다.
RUN mv target/*.jar app.jar

# 실행 가능한 JAR 파일을 실행합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]
