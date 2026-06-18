# MSA Study

Spring Cloud 기반 마이크로서비스 아키텍처(MSA)를 단계별로 학습한 실습 저장소입니다.

---

## 기술 스택

![Java](https://img.shields.io/badge/Java_17-007396?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.4-6DB33F?style=flat&logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud_2024-6DB33F?style=flat&logo=spring&logoColor=white)
![Spring Cloud Gateway](https://img.shields.io/badge/Spring_Cloud_Gateway-6DB33F?style=flat&logo=spring&logoColor=white)
![Netflix Eureka](https://img.shields.io/badge/Netflix_Eureka-E50914?style=flat&logo=netflix&logoColor=white)
![Spring WebFlux](https://img.shields.io/badge/Spring_WebFlux-6DB33F?style=flat&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC2727?style=flat&logoColor=white)

---

## 프로젝트 구조

```
msa/
├── eureka-server/         # 서비스 디스커버리 서버 (port 8761)
│   └── src/main/
│       ├── java/          # @EnableEurekaServer — 서비스 등록·조회 중앙 관리
│       └── resources/
│           └── application.yml
│
├── api-gateway/           # API 게이트웨이 (port 8080)
│   └── src/main/
│       ├── java/          # @EnableDiscoveryClient — 라우팅 및 로드밸런싱
│       └── resources/
│           └── application.yml   # /service1/** → SERVER-1, /service2/** → SERVER-2
│
├── server-1/              # 마이크로서비스 1 (port 8081)
│   └── src/main/
│       ├── java/
│       │   ├── Server1Controller.java   # GET /service1, GET /service1/biz
│       │   └── Service1Configuration.java  # WebClient Bean 설정
│       └── resources/
│           └── application.yml
│
└── server-2/              # 마이크로서비스 2 (port 8082)
    └── src/main/
        ├── java/
        │   └── Server2Controller.java   # GET /service2 — 주문관리
        └── resources/
            └── application.yml
```

---

## 실행 방법

각 서비스는 IntelliJ에서 독립적으로 열거나, 아래 순서대로 실행합니다.

```bash
# 1단계: Eureka Server 먼저 실행 (서비스 레지스트리)
1. IntelliJ → File > Open → eureka-server 선택
2. EurekaServerApplication.java 실행
3. http://localhost:8761 에서 Eureka 대시보드 확인

# 2단계: 마이크로서비스 실행
4. server-1 → Server1Application.java 실행 (port 8081)
5. server-2 → Server2Application.java 실행 (port 8082)

# 3단계: API Gateway 실행
6. api-gateway → ApiGatewayApplication.java 실행 (port 8080)

# 4단계: 게이트웨이를 통해 서비스 호출
curl http://localhost:8080/service1       # server-1 직접 응답
curl http://localhost:8080/service1/biz   # server-1 → server-2 연쇄 호출
curl http://localhost:8080/service2       # server-2 직접 응답
```

> 반드시 **Eureka Server → 마이크로서비스 → API Gateway** 순서로 실행하세요.  
> 순서가 바뀌면 서비스 등록이 되지 않아 라우팅이 실패할 수 있습니다.

---

## 학습 내용

| 서비스 | 주요 학습 내용 |
|--------|---------------|
| `eureka-server` | Spring Cloud Netflix Eureka 서버 구성, `@EnableEurekaServer`, 서비스 레지스트리 동작 원리 |
| `api-gateway` | Spring Cloud Gateway 라우팅 규칙, `lb://` 로드밸런싱, 게이트웨이 필터 체인 |
| `server-1` | `@EnableDiscoveryClient`, WebClient 비동기 논블로킹 HTTP 통신, 서비스 간 연쇄 호출 |
| `server-2` | 독립적 마이크로서비스 구성, Spring WebFlux Reactive API, Eureka 클라이언트 등록 |

---

## 아키텍처 흐름

```
Client
  │
  ▼
API Gateway (8080)          ← Spring Cloud Gateway
  ├── /service1/**  ─────►  Server-1 (8081)  ─── WebClient ──►  Server-2 (8082)
  └── /service2/**  ─────►  Server-2 (8082)
         │                       │                                    │
         └───────────────────────┴────────────────────────────────────┘
                                 │
                         Eureka Server (8761)   ← 서비스 등록·디스커버리
```

---

## 개발 환경

- **JDK 17** 이상 — [다운로드](https://download.oracle.com/java/17/archive/jdk-17.0.12_windows-x64_bin.exe)
- **IntelliJ IDEA** Ultimate (추천) / Community
- **Gradle 8.11.1** (Wrapper 포함 — `gradlew` 사용 시 별도 설치 불필요)
