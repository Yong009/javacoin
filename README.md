
# 👨‍👩‍👦‍👦 업비트 api 활용 코인 자동매매 웹사이트 프로젝트 👨‍👩‍👦‍👦 

> 코인 어플로 하루종일 볼 필요 없이 사이트를 통해 작성시키면 알아서 자동매매 시작 👉 http://coinsabu.com   id:test pw:test


## 📖 Description

늘 핸드폰이나 웹을 이용해 늘 차트를 보면서 매매하며 지금 하는 일에 집중못하고 시간을 쓰며, 스트레스를 받아왔습니다.

그래서 이거 전에 했던 초창기 모델을 파이썬을 이용해서 프로그램을 만들어와서 써봤습니다.

단점이 있었습니다. 그럼 사용시 컴퓨터가 켜진 상태에서 그 프로그램을 돌려야 한다는 것입니다.

그럼 서버 구축해서 웹사이트로 구현한다면 늘 컴퓨터를 켜서 실행하지 않아도 되지 왆을까 생각이 들어 프로젝트에 들어가게 되었습니다.


## :baby_chick: Demo

## ⭐ Main Feature
### 로그인
- 스프링 시큐리티 구현

    <details>
    <summary>로그인 화면</summary>
       
    <img src="https://github.com/user-attachments/assets/71a5ef2d-b168-4291-9ef6-d96db078e5c5" alt="로그인화면"/>
        
    </details>

### 자동매매
- 스케줄러 사용
- 업비트 api 활용

    <details>
    <summary>자동매매 화면</summary>
       
    <img src="https://github.com/user-attachments/assets/317b6a38-9312-409c-9cd4-da3cf4461c63" alt="로그인화면"/>
        
    </details>

### 잔고
- 업비트 api 활용하여 데이터 가공
- api키 저장 및 조회
- 코인 보유시 매도 기능 

## 💻 Getting Started
(↑해당 프로젝트 설치 및 실행 방법)

### Installation
```
npm install
```
### Develop Mode
```
npm run dev
```
### Production
```
npm run build
```

## 🔧 Stack
- **Language**: JavaScript
- **Library & Framework** : Node.js
- **Database** : AWS RDS (MariaDB)
- **ORM** : Sequelize
- **Deploy**: AWS EC2

## :open_file_folder: Project Structure

```markdown
src
├── common
│   ├── config
│   ├── types
│   └── utils
│       ├── types
│       └── utils
├── controller
├── entity
├── infrastructure
│   ├── express
│   └── typeorm
├── repository
└── ser
```

## 🔨 Server Architecture
(↑서버 아키텍처에 대한 내용을 그림으로 표현함으로써 인프라를 어떻게 구축했는 지 한 눈에 보여줄 수 있다.)
![](https://docs.aws.amazon.com/gamelift/latest/developerguide/images/realtime-whatis-architecture-vsd.png)

## ⚒ CI/CD
- github actions를 활용해서 지속적 통합 및 배포
- `feature` 브랜치에서 `dev`로 Pull Request를 보내면, CI가 동작된다.
- `dev`에서 `master`로 Pull Request를 보내면, CI가 동작되고 Merge가 되면, 운영 리소스에 배포된다.

## 👨‍💻 Role & Contribution

**Frontend (Web)**

- 관리자 페이지 (Vue.js) 개발
- 전체 아키텍처 구성

**Devops**

- CI/CD 구축 (Docker, Github Action)
- 서버 모니터링

**etc**

- 전체 개발 일정 및 이슈 관리

## 👨‍👩‍👧‍👦 Developer
*  **박재성** ([jaeseongDev](https://github.com/jaeseongDev))
*  **고성진** ([seongjin96](https://github.com/seongjin96))
*  **조연희** ([yeoneei](https://github.com/yeoneei))
