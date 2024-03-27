# 어린이/장애인 안심귀가 모니터링 및 SOS 서비스

<img src="https://raw.githubusercontent.com/yhuj79/SOS-Service-Frontend/main/assets/thumbnail.png" width="600">

### 2023년 프로보노 ICT멘토링 프로젝트

SOS Service Frontend Repository

<!-- [:ballot_box_with_check: 전체 프로젝트 Repository]() -->

[:ballot_box_with_check: 시연 영상](https://www.youtube.com/watch?v=FUO20uc8-Eg&t=9s)

## :file_folder: 프로젝트 개요

* 명칭 : 어린이/장애인 안심귀가 모니터링 및 SOS 서비스
* 개발 인원 : 3명
* 개발 기간 : 2023.05.25 ~ 2023.09.20
* 목표
  * 지방자치단체 CCTV 관제센터 또는 민간 경비업체 응급출동과 연계하여
안심귀가 모니터링 및 SOS 서비스 개발을 통해 안전한 도시 만들기에 기여
* 주요 기능
  * 요청 시 위치 공유/실시간으로 공유
  * 보호자가 사용자의 위치를 실시간 모니터링, 알림을 통한 위급 상황 인지
* 개발 환경
  * Visual Studio Code, IntelliJ
  * React Native 0.68.2 / Spring Boot 2.7.12 / Java 11 / AWS EC2
* 형상 관리 툴 : Git, Gitlab
* 협업 툴
  * [Notion](https://www.notion.so/SOS-92e92bfdf8304aa293dfc5e83a842fff)

## :open_file_folder: 백엔드 개요

- Spring boot, Java, JPA 를 이용한 RESTful API 개발 (모니터링, 사용자 인증 API 구현)
- Junit를 이용한 단위 테스트 및 통합 테스트 적용 (테스트 커버리지 89% 달성)
- 프론트엔드와 백엔드 간의 효율적인 협업을 위해 REST Docs 이용, API Docs 제작
- Gitlab Runner로 CI 환경 구성 (테스트 수행 및 코드 커버리지 측정)
- 팀장 역할을 수행하여 개발 일정 조율 및 프로젝트 노션 페이지 기획

## Built With

<p>
  <img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00?style=flat&logo=openjdk&logoColor=white" height=25 />
  <img alt="Spring" src="https://img.shields.io/badge/spring-%236DB33F?style=flat&logo=spring&logoColor=white" height=25 />
  <img alt="AWS" src="https://img.shields.io/badge/AWS-%23FF9900?style=flat&logo=amazon-aws&logoColor=white" height=25 />
  <img alt="Socket" src="https://img.shields.io/badge/Socket.io-black?style=flat&logo=socket.io&logoColor=white" height=25 />
  <img alt="Gitlab CI" src="https://img.shields.io/badge/gitlab%20ci-%23181717?style=flat&logo=gitlab&logoColor=white" height=25 />
  <img alt="Gitlab CI" src="https://img.shields.io/badge/gitlab-%23181717?style=flat&logo=gitlab&logoColor=white" height=25 />
</p>

<div>
    <img src="https://raw.githubusercontent.com/LEE-sh1673/SOS-Service-Backend/main/assets/scenario_monitoring.png">
</div>

## :open_file_folder: 프론트엔드 개요

> [GitHub Repository](https://github.com/yhuj79/SOS-Service-Frontend)

## Built With

<p>
  <img alt="ReactNative" src="https://img.shields.io/badge/React_Native-20232A?style=flat&logo=react&logoColor=61DAFB" height=25 />
  <img alt="TailwindCss" src="https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=flat&logo=tailwind-css&logoColor=white" height=25 />
  <img alt="Socket" src="https://img.shields.io/badge/Socket-white?style=flat&logo=socketdotio&logoColor=black" height=25 />
  <img alt="Express" src="https://img.shields.io/badge/Google_Maps-5297F7?style=flat&logo=googlemaps&logoColor=F7D046" height=25 />
</p>

## About The Project

<div>
    <img align=top src="https://raw.githubusercontent.com/yhuj79/SOS-Service-Frontend/main/assets/1.png" width=200>
    <img align=top src="https://raw.githubusercontent.com/yhuj79/SOS-Service-Frontend/main/assets/2.png" width=200>
    <img align=top src="https://raw.githubusercontent.com/yhuj79/SOS-Service-Frontend/main/assets/3.png" width=200>
</div>

<br>

<img align=top src="https://raw.githubusercontent.com/yhuj79/SOS-Service-Frontend/main/assets/4.png" width=608>

<br>

<img align=top src="https://raw.githubusercontent.com/yhuj79/SOS-Service-Frontend/main/assets/5.png" width=608>

<br>

<img align=top src="https://raw.githubusercontent.com/yhuj79/SOS-Service-Frontend/main/assets/6.png" width=608>

### :alarm_clock: 제작 기간

- 2023.05.25 ~ 2023.09.20

### :gear: 개발 환경

- Visual Studio Code
- React Native 0.68.2
- Tailwind React Native Classnames 3.6.4

### :open_file_folder: 기능 목록

| 기능 | 설명 |
|:----:|:----:|
|  로그인  |  아이디, 비밀번호를 입력하여 로그인할 수 있다.  |
|  회원가입  |  회원 정보(아이디, 이름, 이메일, 비밀번호, 프로필, 연락처, 주소)를 입력하여 서비스에 가입할 수 있다.  |
|  내 정보  |  회원 정보(이름, 연락처, 이메일 등)를 조회할 수 있다.  |
|  보호자 정보  |  보호자 정보(이름, 연락처, 관계 등)를 조회할 수 있다.  |
|  보호 대상자 정보  |  보호 대상자 정보(이름, 연락처, 관계 등)를 조회할 수 있다.  |
|  내 위치  |  사용자의 위치를 조회할 수 있다.  |
|  보호 대상자 위치  |  보호 대상자의 위치를 조회할 수 있다.  |
|  보호 대상자 추가  |  보호 대상자를 추가할 수 있다.  |

## Reference

- [Git 알아보기, 버전 관리, ICT멘토링 엘리스코딩](https://elice.io/)
- [업무에 바로 적용하는 Gitlab 실전편1, 2, ICT멘토링 엘리스코딩](https://elice.io/)
- [React Native 완벽 가이드 [2023], Academind by Maximilian Schwarzmüller, udemy](https://www.udemy.com/course/react-native-2022-ko/)
- [Spring Boot Unit Testing with JUnit, Mockito and MockMvc, udemy](https://www.udemy.com/course/spring-boot-unit-testing/)
- [GitLab CI: Pipelines, CI/CD and DevOps for Beginners, udemy](https://www.udemy.com/course/gitlab-ci-pipelines-ci-cd-and-devops-for-beginners/)
