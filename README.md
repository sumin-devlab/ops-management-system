# 🖥️ OpsWatcher (Ops Management System)

서버 정보를 등록하고 CPU · 메모리 · 디스크 사용률을 기반으로  
서버 상태를 자동으로 판단하는 모니터링 대시보드입니다.

---

## 🎬 실행 영상

※ demo.mp4 다운로드 후 실행하면 동작 모습을 확인할 수 있습니다.

<video src="demo.mp4" controls width="700"></video>

---

## 📌 주요 기능

- 회원가입 / 로그인 (Spring Security)
- 서버 등록 / 조회 / 삭제 (CRUD)
- CPU / 메모리 / 디스크 사용률 표시
- 사용률 기반 상태 자동 분류
  - 정상(NORMAL): 60% 미만
  - 경고(WARNING): 60% 이상
  - 위험(DANGER): 80% 이상
- 5초 자동 새로고침 (실시간 모니터링 느낌)

---

## 🛠 기술 스택

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Thymeleaf
- MySQL
- HTML / CSS

---

## ⚙ 실행 방법

### 1. DB 생성

```sql
CREATE DATABASE opswatcher;
```

### 2. application.properties 설정

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/opswatcher
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

### 3. 실행

```bash
./gradlew bootRun
```

또는 IntelliJ에서 실행

### 4. 접속

```txt
http://localhost:8080/login
```

---

## 💡 핵심 구현 포인트

- 서버 정보를 DB에 저장하고 대시보드에 반영
- CPU / 메모리 / 디스크 사용률 기반 상태 자동 판단 로직 구현
- Thymeleaf를 활용한 동적 데이터 렌더링
- 자동 새로고침으로 실시간 모니터링 느낌 구현

---

## 📈 개선 방향

- 실제 서버 자원 API 연동
- 관리자 권한 분리
- 서버 수정 기능 추가
- 서버 장애 로그 기능 추가

---

## 👤 Developer

하수민 (sumin-devlab)
