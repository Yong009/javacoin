
# 👨‍👩‍👦‍👦 업비트 api 활용 코인 자동매매 웹사이트 프로젝트 👨‍👩‍👦‍👦 

> 코인 어플로 하루종일 볼 필요 없이 사이트를 통해 작성시키면 알아서 자동매매 시작 👉 http://coinsabu.com   id:test pw:test


## 📖 Description

늘 핸드폰이나 웹을 이용해 늘 차트를 보면서 매매하며 지금 하는 일에 집중못하고 시간을 쓰며, 스트레스를 받아왔습니다.

그래서 이거 전에 했던 초창기 모델을 파이썬을 이용해서 프로그램을 만들어와서 써봤습니다.

단점이 있었습니다. 그럼 사용시 컴퓨터가 켜진 상태에서 그 프로그램을 돌려야 한다는 것입니다.

그럼 서버 구축해서 웹사이트로 구현한다면 늘 컴퓨터를 켜서 실행하지 않아도 되지 왆을까 생각이 들어 프로젝트에 들어가게 되었습니다.


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
       
    <img src="https://github.com/user-attachments/assets/317b6a38-9312-409c-9cd4-da3cf4461c63" alt="자동매매 화면"/>
        
    </details>


### 잔고
- 업비트 api 활용하여 데이터 가공
- api키 저장 및 조회
- 코인 보유시 매도 기능 

    <details>
    <summary>잔고 화면</summary>
       
    <img src="https://github.com/user-attachments/assets/79221ab3-fa3d-4f58-968c-f02e706f0129" alt="잔고 화면"/>
        
    </details>


### 차트
- 업비트 api 활용하여 데이터 가공
- api 호출 주기를 이용하여 변화 감지 및 적용
- 간단한 코인 검색 기능

    <details>
    <summary>차트 화면</summary>
       
    <img src="https://github.com/user-attachments/assets/f61b3192-98e3-4d7a-ac18-f0cc78a3c84c" alt="차트 화면"/>
        
    </details>


### 관리자화면
- 회원들의 자동매매 사용 여부 확인
- 강제 자동매매 종료 기능
- 회원탈퇴
- 페이징

    <details>
    <summary>관리자 화면</summary>
       
    <img src="https://github.com/user-attachments/assets/035eeebf-e8d4-48f1-b1dd-4605154c4450" alt="관리자 화면"/>
        
    </details>


### 마이페이지
- 수정 기능

    <details>
    <summary>관리자 화면</summary>
       
    <img src="https://github.com/user-attachments/assets/9d8df70d-910f-400a-ab37-8e7d09f8fd8d" alt="마이페이지 화면"/>
        
    </details>


### 거래내역
- api 활용하여 매수/매도 내역 조회

    <details>
    <summary>거래내역 화면</summary>
       
    <img src="https://github.com/user-attachments/assets/f59687d8-ffb2-4877-8df1-25e1f732d22a" alt="거래내역 화면"/>
        
    </details>


### 게시판 및 댓글
- 페이징
- 게시판 글 누를 시 상세 페이지로
- 조회수 기능
- 글쓰기 ( 등록 및 수정 및 삭제 )
- 댓글 ( 등록 및 삭제 )

    <details>
    <summary>게시판 화면</summary>
       
    <img src="https://github.com/user-attachments/assets/09c962aa-c97d-4b4f-9554-5eb30c8f5e1c" alt="게시판 화면"/>
        
    </details>
    <details>
    <summary>댓글 화면</summary>
       
    <img src="https://github.com/user-attachments/assets/7f3d7393-a92a-4a49-a54f-67f849d58c4a" alt="댓글 화면"/>
        
    </details>

   
## 🔧 Stack
- **Language**: Java,JavaScript
- **Library & Framework** : Springboot
- **Database** : AWS RDS (Mysql)
- **ORM** : Sequelize
- **Deploy**: AWS EC2 ( ubuntu ) 

## 👨‍💻 Role & Contribution

**Frontend (Web)**

- bootstrap 활용
- ajax 사용

**Devops**
- 직접 jar or war 빌드
- aws ec2 배포
- 형상관리 : github


## 👨‍👩‍👧‍👦 Developer
*  **구용억** ((https://github.com/Yong009))

