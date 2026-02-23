# 🚀 AI Learning Roadmap Generator (AI 학습 로드맵 생성기)

사용자가 원하는 학습 주제를 입력하면, Google Gemini AI가 최적화된 맞춤형 커리큘럼(로드맵)을 즉시 생성해 주는 웹 서비스입니다. 
단순한 API 호출을 넘어 **보안 강화, 데이터베이스 연동, Docker 컨테이너화 및 클라우드 CI/CD 배포**까지 백엔드 개발의 Full-cycle을 경험하기 위해 제작된 프로젝트입니다.

## 🔗 Demo
- **배포 주소:** [https://ai-practice-app.onrender.com]
- *(주의: 보안 상의 문제로 중지되었습니다.)*


---

## 🛠️ Tech Stack

**Backend**
- Java 25
- Spring Boot 4.0.0
- Spring Data JPA
- H2 Database (In-memory)

**Frontend**
- HTML / Vanilla JS
- Tailwind CSS (CDN)
- Marked.js (Markdown Rendering)

**AI & DevOps**
- Google Gemini 3 API
- Docker (Multi-stage Build)
- Render (Cloud PaaS)

---

## ✨ Key Features (주요 기능 및 기술적 고민)

### 1. 🤖 생성형 AI 연동 (Gemini API)
- Gemini API를 활용하여 사용자가 입력한 `topic`에 대한 전문적인 마크다운 형식의 학습 로드맵을 생성 및 반환합니다.

### 2. 🛡️ 보안 강화 (Passcode & POST Method)
- **무단 API 호출 방지:** 단순 GET 방식의 URL 파라미터 노출 취약점을 개선하여, `POST` 방식의 HTTP Body 통신으로 전환했습니다.
- **Passcode 인증:** 서버 환경 변수(`env`)에 저장된 Passcode와 사용자의 입력값이 일치할 때만 AI API를 호출하도록 컨트롤러 계층에서 방어 로직을 구현하여 요금 폭탄 공격을 차단했습니다.

### 3. 🗄️ 데이터베이스 연동 (JPA & H2)
- Spring Data JPA를 활용해 `Roadmap` Entity를 설계하고, 생성된 로드맵 기록을 H2 데이터베이스에 저장합니다.
- 메인 화면 진입 시 과거 검색 기록(History)을 불러와 태그 형태로 제공하며, 클릭 시 API 추가 호출 없이 DB 데이터를 즉시 렌더링합니다.

### 4. 🐳 Dockerizing 및 클라우드 배포
- `Dockerfile`을 작성하여 로컬 환경과 완벽히 동일하게 동작하는 독립적인 컨테이너 환경을 구축했습니다.
- Render와 GitHub를 연동하여 코드가 `main` 브랜치에 푸시될 때마다 자동으로 빌드 및 배포되는 CI/CD 파이프라인을 구성했습니다.

---

## 💻 How to Run Locally (로컬 실행 방법)

### 1. 환경 변수 설정
`src/main/resources/application.properties` 파일에 발급받은 API 키와 임의의 비밀번호를 설정합니다. (또는 시스템 환경변수로 주입)
```properties
google.api.key=YOUR_GEMINI_API_KEY
app.passcode=YOUR_PASSCODE
