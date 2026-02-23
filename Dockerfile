# 1. 실행 환경: Java 21 (또는 사용 중인 자바 버전에 맞게 17 등으로 변경 가능)
FROM eclipse-temurin:25-jdk-alpine

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 jar 파일을 도커 이미지 안으로 복사
COPY target/*.jar app.jar

# 4. 외부로 열어줄 포트 번호
EXPOSE 8080

# 5. 컨테이너가 켜질 때 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]