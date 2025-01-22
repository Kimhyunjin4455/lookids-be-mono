# lookids-be-mono

# 🌟 Lookids 🌟
**TEAM: LOOKIDS**

> **신세계 I&C**와 **부산광역시**에서 주관하는 스파로스 아카데미 5기 소속 학생들이 진행한 **SNS 프로젝트** <br>
> ## 본 저장소는 프로젝트 당시 msa 구조로 개발한 것을 **모놀리식 아키텍처**로 변경한 것입니다.<br> 한정적 시간으로 인하여 apache kafka는 제거하지 않고 eda 형식을 유지중입니다.
> 
---

## 📚 프로젝트 소개

이 프로젝트는 반려동물을 키우는 사람들이 그들의 특별한 순간을 공유하고 소통할 수 있는 전문 SNS 플랫폼을 만드는 것을 목표로 합니다. 반려동물의 사진과 이야기로 소통하며, 사용자 간의 유대감을 강화하고 반려동물 중심의 커뮤니티를 형성하고자 합니다.

프론트엔드와 백엔드의 긴밀한 협업을 통해 효율적이고 체계적으로 진행되었습니다. 협업 도구로 Jira와 Notion을 활용하여 프로젝트의 진행 상황을 명확히 추적하고, 개발 중 발생하는 이슈를 신속히 해결할 수 있었습니다.

---

## 🗓️ 프로젝트 기간
**2024년 10월 2일** ~ **2024년 12월 16일**

---

## 👥 역할

| 이름       | 역할                   | 담당기능               |
| ---------- | ---------------------- |-------------------------|
| **김현진(본인)** | Team Leader, Back-End  | Auth, 알림신청(구독), 알림 | 
| **타 인원 역할** | -- | 인프라, 유저, 댓글, 배치, 채팅, 좋아요, 신고/문의, 검색, 지도, 팔로우/팔로잉, 차단, 피드 | 

---

## 👥 상세 역할

- **JWT 기반 로그인**을 구현하여 보안 강화
- Redis의  **메모리 기반, TTL**을 활용한 로그인 동시성 문제 방지 및 토큰/인증번호 등 임시 데이터를 처리
- 이벤트 체이닝 시 데이터 조회 효율성을 위한 **caffeine 캐싱 처리** 적용
- 서버에서 클라이언트 측으로의 **단방향 통신을 위해 SSE**(server sent event)를 이용한 알림 기능 구현
- **pwa**(progressive web app)의 **백그라운드 특성을 활용하여 FCM** 을 이용한 푸시 알림 구현
- 클라이언트 측 전송 시 발생한 **직렬화** 설정 문제에 대해 json 형변환을 통해 해결하여 이를 공유하여 타 인원들의 **문제 재발 방지에 기여**
- Google **SMTP**를 활용한 메일 전송 기능 개발


---

## 🚀 기능 소개

- **메인 페이지**: 추천 팻 프로필과 피드를 확인할 수 있습니다
  팔로우가 있다면 팔로우 유저의 피드를, 팔로잉을 하지 않았거나 로그인을 하지 않았다면 추천 피드를 표시합니다.
- **피드**: 사용자들이 반려동물 사진과 글을 업로드하며 자신의 일상을 기록하고 공유할 수 있는 기능을 제공합니다.
  좋아요와 댓글을 통해 사용자 간의 상호작용을 촉진합니다.
- **채팅**: 반려동물에 대한 정보와 경험을 사용자들 간에 쉽게 공유할 수 있도록 실시간 채팅 기능을 제공합니다.
  1:1 대화 및 그룹 채팅 지원으로 커뮤니케이션의 편리성을 높입니다.
- **피드 기반 위치 정보**: 피드에 업로드된 사진의 위치 정보를 활용하여 지도에 핀으로 표시합니다.
  방문한적 있던 추억의 장소들을 지도에서 핀으로 조회하실 수 있습니다.
- **유저 프로필**: 본인을 잘 표현할 수 있는 프로필 사진과 정보를 확인할 수 있습니다.
  작성한 피드와 좋아요한 피드, 팔로워와 팔로우 수, 작성한 피드수를 확인할 수 있습니다
- **펫 프로필**: 반려동물의 사진과 정보를 확인할 수 있습니다
  간단한 소개, 성별, 몸무게, 종 등 다양한 정보를 확인할 수 있습니다
- **댓글&대댓글**: 피드에 대한 의견을 남길 수 있습니다
  댓글에 대한 의견도 대댓글로 남길 수 있으며 댓글에 좋아요를 누를 수 있습니다
- **마이 페이지**: 내 프로필을 수정할 수 있습니다.
  팻 프로필을 등록할 수 있으며 내가 작성한 피드와 좋아요를 누른 피드를 확인할 수 있습니다
- **회원가입 &로그인**: 아이디와 비밀번호를 입력하여 로그인할 수 있습니다.
  구글, 카카오, 네이버 소셜 회원가입과 로그인을 지원합니다
- **검색**: 유저, 피드, 팻프로필을 검색할 수 있습니다
  연관 검색을 통해 전체 검색어를 입력하지 않아도 결과를 확인할 수 있습니다



---

## ⚙️ 기술 스택

- BE: Spring boot(3.2.8), JDK(17), Gradle(8.8), IntelliJ(2024.1.4)
- alarm-service: Spring boot(3.3.10), swagger(2.2.0)

![spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![spring_boot](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![spring_security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![Spring Batch](https://img.shields.io/badge/Spring_Batch-6DB33F?style=for-the-badge&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAeRk5mAAAABGdBTUEAALGPC/xhBQAAAAJcEhZcwAADsMAAA7DAcdvqGQAABJ5RVh0U29mdHwgQmF0Y2hWb3JrZXI=)
![JPA](https://img.shields.io/badge/JPA-0072B8?style=for-the-badge&color=0072B8)
![QueryDSL](https://img.shields.io/badge/QueryDSL-3B3B3B?style=for-the-badge&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAeRk5mAAAABGdBTUEAALGPC/xhBQAAAAJcEhZcwAADsMAAA7DAcdvqGQAABJ5RVh0U29mdHwgQmF0Y2hWb3JrZXI=)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON-Web-Tokens&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-23A03A?style=for-the-badge&logo=Gradle&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Firebase Cloud Messaging](https://img.shields.io/badge/FCM-FFCA28?style=for-the-badge&logo=Firebase&logoColor=black) ![SMTP](https://img.shields.io/badge/SMTP-FF5733?style=for-the-badge&logo=Mail.Ru&logoColor=white) ![SSE](https://img.shields.io/badge/SSE-000000?style=for-the-badge&logo=Serverless&logoColor=white)   ![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-231F20?style=for-the-badge&logo=Apache%20Kafka&logoColor=white)

- FE: Next.js(14.2.5), Node.js(20.5.1), TypeScript(5.1.3)

![Next.js](https://img.shields.io/badge/Next.js-000000?style=for-the-badge&logo=Next.js&logoColor=white)
![Node.js](https://img.shields.io/badge/Node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=TypeScript&logoColor=white)

- INFRA: Ubuntu(Ubuntu 20.04 LTS), Docker(27.3.1), Docker-compose(2.29.1)

![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=Amazon%20AWS&logoColor=white)
![EC2](https://img.shields.io/badge/Amazon%20EC2-FF9900?style=for-the-badge&logo=Amazon%20EC2&logoColor=white)
![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)
![S3](https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white)
![Vercel](https://img.shields.io/badge/Vercel-000000?style=for-the-badge&logo=Vercel&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=for-the-badge&logo=GitHub%20Actions&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white)
![Docker Compose](https://img.shields.io/badge/Docker%20Compose-2496ED?style=for-the-badge&logo=Docker&logoColor=white) ![Spring Config](https://img.shields.io/badge/Spring%20Config-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)   ![Eureka](https://img.shields.io/badge/Eureka-5C2D91?style=for-the-badge&logo=Netflix&logoColor=white)   ![Spring Gateway](https://img.shields.io/badge/Spring%20Gateway-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)
- DATABASE: MySQL(8.0.39), ElasticSearch(7.10.1)

![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white)
![Elasticsearch](https://img.shields.io/badge/Elasticsearch-005571?style=for-the-badge&logo=Elasticsearch&logoColor=white)
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white">
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=MongoDB&logoColor=white)



- COMMON: Swagger(2.0.2), Kibana(7.10.1)

![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Kibana](https://img.shields.io/badge/Kibana-005571?style=for-the-badge&logo=Kibana&logoColor=white)
![Figma](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white)
<img src="https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white">


- TOOL: intellj, vscode
  <img src="https://img.shields.io/badge/IntelliJIDEA-1e88e5?&style=for-the-badge&logo=intellij-idea&logoColor=white"> ![VSCode](https://img.shields.io/badge/VSCode-007ACC?style=for-the-badge&logo=Visual%20Studio%20Code&logoColor=white)   ![MySQL Workbench](https://img.shields.io/badge/MySQL%20Workbench-4479A1?style=for-the-badge&logo=MySQL&logoColor=white)   ![MongoDB Compass](https://img.shields.io/badge/MongoDB%20Compass-47A248?style=for-the-badge&logo=MongoDB&logoColor=white)


---

## 🎯 프로젝트 목표

1. **Msa를 적용하여 서비스간의 에러 전파 방지 및 유연한 확장성**
    - 우리의 반려동물 SNS 서비스는 MSA(Microservices Architecture)와 EDA(Event-Driven Architecture)를 기반으로 설계되었습니다. 이를 통해 사용자, 게시물, 댓글, 지도 기능 등 주요 기능을 독립적인 마이크로서비스로 분리하여 운영하며, 서비스 간 효율적이고 실시간으로 데이터를 처리할 수 있는 구조를 구현합니다.

2. **EDA 를 활용한 비동기 처리**
    - EDA를 통해 서비스 간 이벤트 기반 통신을 구현하여, 실시간 데이터 처리가 필요한 기능에 최적화된 구조를 제공하고 있습니다. 예를 들어, 사용자가 반려동물 사진을 업로드하거나 댓글을 작성하면 해당 이벤트가 즉시 전송되어 관련 서비스들이 빠르게 반응할 수 있습니다.

3. **프론트엔드와 백엔드 협업**
    - 프론트엔드와 백엔드 간의 긴밀한 협업을 통해 사용자 경험을 극대화하는 통합된 웹 서비스를 구축합니다.
    - Jira를 통해 스프린트 계획, 작업 진행 상황 추적, 이슈 관리 등을 수행하여 개발 일정을 효율적으로 관리합니다.
    - Notion을 활용해 프로젝트 문서화, API 명세 공유, 디자인 가이드 작성 등 팀원 간 지식을 공유하고 커뮤니케이션합니다.

4. **주요 웹 기술 적용**
    - Spring cloud, Kafka, turboRepo 등 최신 기술 트렌드를 반영하여 실무에서의 문제 해결 능력 향상시킵니다.

---

## 🏆 주요 성과
- msa 구조에서 이벤트 기반의 알림 기능을 구현했을 때 서비스간의 event-chaning 발생시에 대해 데이터 처리 속도를 개선하고<br> 사용자별로 다르게 캐싱되는 데이터를 위해 **caffeine**을 도입하였고 약 **200배**의 조회 성능 향상
- **Apache Kafka** 기반의 메시지 브로커와 데이터 스트리밍을 통해 서비스 간 **독립적인 통신**을 실현하고 **책임 모호성**을 해결
- 유저의 어플리케이션 상태에 맞는 알림을 제공하고자 FCM 푸시 알림과 SSE를 활용해 상황에 맞는 알림 방식(백그라운드, 포그라운드, 종료 상황)을 제공하여 **사용자 경험을 2배 개선**
- **ElasticSearch**를 활용한 고속 검색 기능 구현<br>
  : 검색 데이터 약 500,000개 기준. JPA search 379 ms -> Elasticsearch 12 ms
- **견고한 시스템 설계**<br>
  : msa의 적극적인 도입으로 에러 전파를 방지하고 비동기 처리와 LB을 통해 시스템 안정성을 향상
- **협업 체계 확립**<br>
  : JIRA, Github, Notion 등 협업툴을 적극적으로 사용하고 개발 사항을 공유하며
  다인 체제의 협업 플로우를 확립

---

<br/>

## 🔧 서버 구성 및 데이터베이스


![아키텍처흐름도](https://github.com/user-attachments/assets/a8282d39-549f-4fe7-bf68-3c3bc2e8afc2)

### 2d 아키텍처
![msaArchitecture](https://github.com/user-attachments/assets/cf93fef5-24f6-4323-b1bb-3d8e3487d407)


---

| Auth - 회원가입 |
| --- |
|  |
| ![SignUp](https://github.com/user-attachments/assets/1e7077a9-c006-4ef9-a5ea-91d3413fdc2e) |

| Auth - 로그인 |
| --- |
|  | 
|  ![SignIn](https://github.com/user-attachments/assets/4f04cb1c-0641-4533-b4cc-8aa943f5ab36) |

| 알림 - 이벤트 기반 |
| --- |
|  |
| ![notification](https://github.com/user-attachments/assets/868ca0ec-640e-4011-ae73-4b6ee3e2094b)
|  |


---

| RDB - MySQL |
| --- |
| <img src="https://github.com/user-attachments/assets/7e2ffe70-15ac-4e94-b144-e8b236b6c802" height="400" width="700" /> |
|  |
| NoSQL - MongoDB |
| <img src="https://github.com/user-attachments/assets/1139e32c-67b8-4500-bfab-a978194c5413" height="400" width="700" /> | 



---

## 🔎 화면 구성

| 메인 페이지 | 회원가입 | 로그인&소셜로그인 |
| --- | --- | --- |
| <img src="https://github.com/user-attachments/assets/e5c727ca-619d-4239-a771-1830fd56f931" height="300" width="200" /> | <img src="https://github.com/user-attachments/assets/bf4e7eb6-8535-4f0c-9f76-af60bb213b6b" height="300" width="200" /> | <img src="https://github.com/user-attachments/assets/c6e5c19b-b757-4bfd-8839-1d151e5713f0" height="300" width="180" /> <img src="https://github.com/user-attachments/assets/06f54f8c-0372-4151-aacf-68b1fc68aa66" height="300" width="180" /> |

| 마이페이지 | 검색 | 유저 프로필 |
| --- | --- | --- |
| <img src="https://github.com/user-attachments/assets/11613340-8e7e-4e8d-9523-79111198b3f4" height="300" width="180" /> <img src="https://github.com/user-attachments/assets/6053140f-149b-43e2-bb27-cfeefc4e759a" height="300" width="180" /> | <img src="https://github.com/user-attachments/assets/9fef2654-d228-4946-b561-3550690ca7b6" height="300" width="200" /> | <img src="https://github.com/user-attachments/assets/571068ca-7794-444a-9869-f107f3235379" height="300" width="200" /> | 


| 채팅 | 지도 | 댓글&대댓글 |
| --- | --- | --- |
| <img src="https://github.com/user-attachments/assets/5fb75db2-ceec-484f-9b02-652f96d89ed8" height="300" width="200" /> | <img src="https://github.com/user-attachments/assets/d3d1cbcd-9264-4fe0-9c23-7d4ef2ada55b" height="300" width="200" /> | <img src="https://github.com/user-attachments/assets/5c70fd35-3e1a-4c99-a779-c8ab4ba59b05" height="300" width="200" /> | 

| 피드 상세 | 팔로우&팔로잉 리스트 |  |
| --- | --- | --- |
| <img src="https://github.com/user-attachments/assets/20124ff0-2c4d-415f-8f8f-af07d9454970" height="300" width="200" /> | <img src="https://github.com/user-attachments/assets/157a4b7b-c52d-41e6-8539-31f6a052cc6e" height="300" width="200" />  <img src="https://github.com/user-attachments/assets/27fb7c39-4565-43cd-9543-24680bd3ab24" height="300" width="200" /> |




---

<!-- ## 🌐 사용해보기 -->
<!-- > [프로젝트 사이트](https://www.lookids.online) 에서 확인 가능 -->



## 📄 라이센스

MIT 라이선스

Copyright (c) 2024 lookids.online

본 소프트웨어 및 관련 문서 파일(이하 “소프트웨어”)의 사본을 취득하는 모든 사람에게 무료로 소프트웨어를 사용, 복사, 수정, 병합, 게시, 배포, 재실시, 판매할 수 있는 권리를 포함하여 소프트웨어를 제한 없이 다룰 수 있는 권한을 무료로 부여합니다. 단, 다음 조건을 충족해야 합니다:

위 저작권 표시와 이 허가 표시가 소프트웨어의 모든 사본 또는 중요한 부분에 포함되어야 합니다.

본 소프트웨어는 “있는 그대로” 제공되며, 명시적이든 묵시적이든 어떠한 종류의 보증도 제공되지 않습니다. 여기에는 상품성, 특정 목적에의 적합성 및 비침해성에 대한 보증이 포함되지만 이에 국한되지 않습니다. 저자는 어떠한 경우에도 소프트웨어와 관련되거나 소프트웨어의 사용 또는 기타 거래에서 발생하는 어떠한 청구, 손해 또는 기타 책임에 대해 책임을 지지 않습니다.

MIT License

Copyright (c) 2024 lookids.online

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

