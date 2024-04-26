# 간편한 서울 카페 조회 서비스 카페 체크인 README

## 팀원 구성
<div align="center">

<div align="center">

| **이준석** |
| :------: | 
| [<img src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/422a81d3-b0b7-4b85-af31-a42a3c23c771" height=150 width=150> <br/> @JunRock](https://github.com/JunRock) |

</div>
</div>
<br>

## 개발 환경
- FRONT : HTML, CSS
- BACKEND : Spring Framework, Java11, Mysql, Spring Data Jpa, SMTP
- APIs : Kakao Map api
- 버전 및 이슈관리 : Github
- 서비스 배포 환경 : Spring Boot Apache Tomcat  <br>

## 주제
- 서울 내의 다양한 카페를 사용자에게 소개하고, 사용자가 쉽게 카페를 찾고 정보를 공유할 수 있는 플랫폼 제공.

## 요구사항
- 사용자는 원하는 지역구의 카페를 검색할 수 있어야 한다.
- 카페 상세 페이지에서는 카페의 영업 시간, 사용자 리뷰, 사진, 메뉴 등을 볼 수 있어야 한다.
- 사용자는 등록한 이메일을 통해 임시 비밀번호를 발급받을 수 있어야 한다.
- 사용자는 본인의 회원정보를 수정할 수 있어야 한다.
- 사용자는 카페에 대한 리뷰를 작성하고 평점을 줄 수 있어야 한다.
- 사용자는 본인이 찜한 카페의 목록을 조회, 삭제가 가능하여야 한다.
- 사용자 인증 시스템 구현 (회원가입, 로그인)
- 관리자 페이지에서는 카페 정보를 추가, 수정, 삭제할 수 있어야 한다.

## 참고사항 및 조건
- 모든 사용자 입력 데이터는 유효성 검사를 거쳐야 합니다.
- 사용자의 위치 정보는 사용자의 동의를 받아 사용합니다.

## 화면 구성 📺
|  홈페이지  |  메인 페이지   |
| :-------------------------------------------: | :------------: |
|  <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/a55006f1-d3e8-4d27-9d0d-31718141dbb8"/> |  <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/10ebee86-ecd9-4dae-88d2-05eaebba2bea">|  
| 게시글 목록 페이지   |  게사판 댓글 작성 페이지   |  
| <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/93514b68-1ec9-4ef9-80f2-78e9e22ef1a0"/>   |  <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/9e4bce96-a695-4cc7-9ccc-708a860b3bec"/>     |
| 댓글 상세보기 페이지   |  카페 상세 정보 페이지   |  
| <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/33f1fec1-d92a-4176-bfe0-214835b7da47"/>   |  <img width="500" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/94710406-e69f-4c5e-88bc-ddc161c0ccda"/>     |

---

## DB구조도
![image](https://github.com/CafeCheckin/CafeCheckin/assets/56196986/688eb59c-713d-4443-8d08-d1aea8bcbc78)

---

## System Architecture
<img width="639" alt="image" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/c4033998-9137-4de9-afde-1d202baf860d">


---

## 기능정리
1. 유저 등록, 조회 수정, 삭제(탈퇴)
2. 카페 리뷰 등록, 조회, 수정, 삭제, 좋아요표시
3. 게시글 등록, 조회, 수정, 삭제, 좋아요 표시
4. 페이징 처리
5. Session Auth
