
# 👨‍👩‍👦‍👦 업비트 api 활용 코인 자동매매 웹사이트 프로젝트 👨‍👩‍👦‍👦 

> 코인 앱을 하루 종일 볼 필요 없이, 자동매매를 시작할 수 있습니다. 

## 📖 Description

당신은 주식이나 암호화폐와 같은 자산을 매매할 때 핸드폰이나 웹을 통해 실시간 차트를 확인하는데 많은 시간을 소비하며, 이로 인해 스트레스도 받고 본업에 집중하지 못하는 문제를 겪고 있었습니다. 이를 해결하기 위해 초기에는 파이썬으로 간단한 프로그램을 만들어 사용해 봤지만, 해당 프로그램이 실행되려면 컴퓨터가 계속 켜져 있어야 하는 불편함이 있었습니다.

이 문제를 해결하기 위해 서버를 구축하고 웹사이트로 프로그램을 구현한다면, 컴퓨터를 항상 켜둘 필요 없이 언제 어디서나 웹을 통해 접근하고 사용할 수 있을 것이라고 생각하게 되었습니다. 이를 계기로 프로젝트를 시작하게 된 것입니다.

이 프로젝트는 스트레스와 시간을 절약하면서도 더 효율적으로 자산을 관리할 수 있는 솔루션을 구축하는 것을 목표로 하고 있습니다.

## ⭐프로젝트 투입일 

<img src="https://github.com/user-attachments/assets/210e9ed2-e9f0-4932-a1ce-da6d63a52dd2" alt="프로젝트 투입"/>

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


## 👨‍💻 Developer
*  **구용억** ((https://github.com/Yong009))

