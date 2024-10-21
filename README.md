# SpringCommunity 🌱
![js](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![js](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![js](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)  
![js](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![js](https://img.shields.io/badge/Microsoft_Azure-0089D6?style=for-the-badge&logo=microsoft-azure&logoColor=white)
![js](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)  
![js](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
---
[프로젝트를 진행하며 공부한 내용들](study_notes.md)  
[프로젝트를 진행하며 발생한 오류와 해결](troubleshooting.md)

---

<!-- TOC -->
* [SpringCommunity 🌱](#springcommunity-)
  * [프로젝트 개요 🌱](#프로젝트-개요-)
  * [프로젝트 소개 📖](#프로젝트-소개-)
  * [프로젝트 목적 🎯](#프로젝트-목적-)
  * [기술 스택 🛠](#기술-스택-)
  * [ERD 📊](#erd-)
  * [API 📝](#api-)
  * [주요 기능 ✨](#주요-기능-)
  * [배운 점 및 성장 경험 📚](#배운-점-및-성장-경험-)
  * [스크린샷 📸](#스크린샷-)
<!-- TOC -->

---
## 프로젝트 개요 🌱
* **프로젝트 명**: Spring Community
* **개발 기간**: 2023.08.17 ~ 2024.03.31
* **개발 인원**: 1명
* **주요 기능**
  * 사용자 : CRUD, Spring Security 회원가입 및 로그인, 권한 관리
  * 게시판 : CRUD, 좋아요/싫어요, 조회수, 페이징
  * 댓글 : CRUD, 대댓글, 페이징
  * 카테고리 : CRUD
* **개발 환경**: Java 17, Spring Boot 3.2.0, Gradle
* **간단한 소개**: Spring Boot로 개발한 커뮤니티 사이트


---
<br>

## 프로젝트 소개 📖
이 프로젝트는 Spring Boot를 기반으로 하여 회원가입, 로그인, 댓글 및 대댓글, 추천 기능 등을 포함하여, 사용자에게 보다 나은 소통의 장을 제공하고 정보를 효율적으로 관리하는 것을 목표로 합니다.

---
<br>

## 프로젝트 목적 🎯
- **기술 습득**: 최신 백엔드 기술 스택을 직접 적용하여 실제 프로젝트 개발 과정을 경험함으로써, 개발자로서의 기술적 역량을 강화합니다.
- **문제 해결**: 사용자의 관점에서 출발하여 실질적인 문제를 이해하고, 이를 해결하기 위해 적절한 기술을 적용하여 개선과 효율을 추구합니다.
- **전 과정 이해**: 프로젝트 기획부터 배포에 이르기까지의 전 과정을 직접 수행함으로써, 개발 프로세스에 대한 깊은 이해를 얻습니다.

---
<br>

## 기술 스택 🛠
- **Backend**: Java, Spring Boot, Spring Data JPA
- **Database**: H2(개발), MySQL(배포)
- **DevOps**: Docker, GitHub Actions
- **Server**: Azure VM - Linux(Ubuntu 22.04 LTS)
- **Test**: JUnit5, Mockito
- **ETC**: Azure Blob Storage

---
<br>

## ERD 📊
![image](https://github.com/JeongGiSeong/spring-community/assets/80134129/e4fda01c-c4aa-4087-8162-be43e914735a)

---
<br>

## API 📝
![image](https://github.com/JeongGiSeong/spring-community/assets/80134129/1c190ef3-fcc7-467d-b70d-aeef65994622)

---
<br>

## 주요 기능 ✨
- Thymeleaf를 활용한 동적 웹 페이지 렌더링
- Spring Security 기반의 인증 및 권한 관리
- 쿠키를 활용한 조회수 중복 방지
- Swagger를 활용한 API 문서 자동화
- GitHub Actions를 활용한 CI/CD 파이프라인 구축
- Docker를 활용한 이미지 빌드 및 Azure VM 배포

---
<br>

## 배운 점 및 성장 경험 📚
- **영속성 컨텍스트와 트랜잭션**

데이터의 일관성과 무결성을 유지하기 위한 영속성 컨텍스트와 트랜잭션 관리의 작동 원리를 이해하고, 관련 문제를 해결하는 능력을 키웠습니다.

- **Lazy Loading**

데이터베이스 쿼리 성능을 최적화하기 위해 Lazy Loading을 활용하는 방법을 배우고, 불필요한 데이터 로드를 방지하여 애플리케이션 성능을 향상시켰습니다.

- **CSRF 및 Spring Security**

CSRF 공격 방지를 위한 보안 설정과 Spring Security를 활용한 인증 및 권한 관리 구현 방법을 배워, 애플리케이션의 보안성을 강화하고 데이터를 안전하게 보호할 수 있었습니다.

- **Spring Security**

Spring Security의 필터와 인터셉터를 이해하고, 이를 통해 인증 및 권한 관리를 구현하는 방법을 배웠습니다. 이를 통해 애플리케이션의 보안성을 강화하고, 사용자 데이터를 보호할 수 있었습니다.

- **Linux 환경에서 배포**

Azure 가상 머신을 생성하고 SSH 포트 설정 문제를 해결하면서 클라우드 인프라 관리 능력을 향상시켰습니다. 또한, Let’s Encrypt를 통해 SSL 인증서를 발급받아 HTTPS 설정을 통해 보안성을 강화했습니다.

- **Docker 이미지 빌드 및 배포**

Docker를 사용하여 애플리케이션 이미지를 빌드하고 Azure VM에 배포하는 방법을 익혀, 컨테이너화된 애플리케이션의 관리 능력을 배웠습니다.

- **Swagger를 활용한 API 문서 자동화**

Swagger를 활용하여 API 문서를 자동으로 생성하고 관리하는 방법을 배웠습니다. 이를 통해 API의 가독성을 높이고, 개발자 간의 협업을 원활하게 할 수 있었습니다.

- **CI/CD 파이프라인 구축**

GitHub Actions를 활용하여 CI/CD 파이프라인을 구축하고 자동화된 빌드, 테스트, 배포 프로세스를 구현하여 개발 효율성을 높였습니다.

---
## 스크린샷 📸
- 메인 페이지  ![image](https://github.com/JungKiSung1012/spring-community/assets/80134129/5ff50bff-2a6b-41b7-94f2-13efe79918ee)
- 로그인 페이지  ![image](https://github.com/JungKiSung1012/spring-community/assets/80134129/5c9013ab-5220-4be2-a286-24791523128a)
- 게시글 조회 페이지  ![image](https://github.com/JungKiSung1012/spring-community/assets/80134129/cafbfd6e-1a00-4261-9fc3-454346a4da6e)
- 댓글/대댓글 ![image](https://github.com/JungKiSung1012/spring-community/assets/80134129/a0338e0a-862b-4bb9-bc44-1221af3477ce)
- Swagger API 문서 ![image](https://github.com/JungKiSung1012/spring-community/assets/80134129/b4a4e232-1a21-4ebc-84db-7be1210629d5)
