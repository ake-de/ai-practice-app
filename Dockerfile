# --- 1단계: 요리하기 (소스 코드를 빌드해서 .jar 파일 만들기) ---
FROM eclipse-temurin:25-jdk AS builder
WORKDIR /app
# 깃허브에 있는 모든 소스 코드를 도커 안으로 복사
COPY . .
# 권한을 주고 메이븐으로 빌드 실행
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# --- 2단계: 서빙하기 (만들어진 .jar 파일만 가져와서 가볍게 실행하기) ---
FROM eclipse-temurin:25-jdk
WORKDIR /app
# 1단계(builder)에서 완성된 .jar 파일만 복사해 옴
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
# 서버 실행!
ENTRYPOINT ["java", "-jar", "app.jar"]