# 간편한 서울 카페 조회 서비스 카페 체크인 README

## 팀원 구성
<div align="center">

<div align="center">

| **이준석** |
| :------: | 
| [<img src="https://avatars.githubusercontent.com/u/106502312?v=4" height=150 width=150> <br/> @JunRock](https://github.com/JunRock) |

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
| 메인 페이지  |  소개 페이지   |
| :-------------------------------------------: | :------------: |
|  <img width="329" src="https://private-user-images.githubusercontent.com/56196986/322185970-58717cad-82cc-489c-8b25-063fe2358f5d.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTI5OTM5MjksIm5iZiI6MTcxMjk5MzYyOSwicGF0aCI6Ii81NjE5Njk4Ni8zMjIxODU5NzAtNTg3MTdjYWQtODJjYy00ODljLThiMjUtMDYzZmUyMzU4ZjVkLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDA0MTMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwNDEzVDA3MzM0OVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTJhYWZiZTdmMDRjYTU1NjhlYTQ3ZjJiN2YwZTdhMzA3Yjg3Mzc4MmQzYWU4ZGZkMmYwMDkxMzI3NzMxZDMyZjMmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.StIJwKD-qkHa0cYYahuvne_JxmW9z3F-ZfYTnrX8uzU"/> |  <img width="329" src="<img width="649" alt="image" src="https://github.com/CafeCheckin/CafeCheckin/assets/56196986/10ebee86-ecd9-4dae-88d2-05eaebba2bea">
"/>|  
| 강좌 소개 페이지   |  강의 영상 페이지   |  
| <img width="329" src="https://user-images.githubusercontent.com/50205887/208038737-2b32b7d2-25f4-4949-baf5-83b5c02915a3.png"/>   |  <img width="329" src="https://user-images.githubusercontent.com/50205887/208038965-43a6318a-7b05-44bb-97c8-b08b0495fba7.png"/>     |

---



## DB구조도
![image](https://github.com/CafeCheckin/CafeCheckin/assets/56196986/b6c585b3-f1c0-48db-aeb0-ca1fc8ce7cdb)

### 기능정리
1. 유저 등록, 조회 수정, 삭제(탈퇴)
2. 카페 리뷰 등록, 조회, 수정, 삭제, 좋아요표시
3. 게시글 등록, 조회, 수정, 삭제, 좋아요 표시
4. 페이징 처리
5. Session Auth
