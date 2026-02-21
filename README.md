# 🍳 BuBu Table (부부 테이블)
> **"오늘 퇴근 후, 함께 요리할까요?"** > 맞벌이 신혼부부를 위한 15분 완성 초간단 레시피 커뮤니티

![Main Screen Preview](https://images.unsplash.com/photo-1556910103-1c02745aae4d?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80)

---

## ✨ 프로젝트 개요
**BuBu Table**은 바쁜 일상을 보내는 맞벌이 부부들이 퇴근 후 소중한 저녁 시간을 함께 즐겁게 보낼 수 있도록 돕는 서비스입니다. 짧은 시간 안에 만들 수 있는 효율적인 레시피를 공유하고, 부부만의 주방 문화를 만들어가는 커뮤니티 공간입니다.

## 🚀 주요 기능

### 1. 회원 시스템 (Spring Security)
- **보안 로그인**: BCrypt 암호화를 적용한 안전한 회원가입 및 로그인.
- **맞춤형 닉네임**: "OOO 요리사님"과 같은 친근한 사용자 인터페이스 제공.

### 2. 스마트 레시피 추천
- **15분 퀵 레시피**: 퇴근 후 빠르게 완성할 수 있는 초간단 메뉴 제공.
- **냉장고 파먹기**: 남은 재료를 활용해 만들 수 있는 최적의 레시피 검색.

### 3. 부부 커뮤니티
- **우리집 밥상 자랑**: 직접 만든 요리 사진과 팁을 공유하는 게시판.
- **부부 공동 가계부**: 함께 장보고 관리하는 식비 관리 기능 (예정).

## 🛠 Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Security** (인증 및 권한 관리)
- **H2 Database** (개발용) / **MySQL** (운영용)

### Frontend
- **Thymeleaf** (View Engine)
- **Bootstrap 5** (UI 프레임워크)
- **JavaScript (Vanilla JS)**
- **HTML5/CSS3**

### Tools
- **Gradle** (Build Tool)
- **IntelliJ IDEA**
- **Git / GitHub**

## 📂 프로젝트 구조
```text
src/main/java/com/java/point/app
├── config          # 시큐리티 및 시스템 설정
├── login           # 로그인 관련 컨트롤러 및 서비스
├── member          # 회원 도메인 및 비즈니스 로직
├── common          # 공통 DTO 및 응답 처리
└── recipe          # 레시피 게시판 및 검색 로직 (진행중)
