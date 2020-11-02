Collathon
===
collathon project in chungnam national univ

team member
---
### Back End(Spring Boot)
Kihwan.Kim

Seonghwan.Cho

### Front End(React Native)
Gyeongmin.Kim

Taeyong.Jeong

port number
---
Spring boot : 8090

React native : 19000, 19001

### 프로그램 UI 화면 구성도

![bikehub 전체 화면 구성도](https://user-images.githubusercontent.com/19687080/97854713-2ec7f900-1d3d-11eb-9eee-4ada7614d430.png)

* Main
    * 애플리케이션 실행시 보이는 화면
    * 로그인시 출발 및 도착, 지도 번튼을 클릭할 수 있음
    * 애플리케이션 실행 시 GPS가 켜져 있지 않으면 실행 불가능
* Signup/Login
    * 회원 가입시 자동으로 로그인 페이지로 넘어감
    * 사용자 정보를 입력하여 회원 등록하는 페이지
* map
    * 현재 나의 위치에서 근방에 있는 모든 자전거 정보를 보여줌
* Departure
    * 자전거 대여를 위해 QR코드 촬영 화면으로 넘어감
    * 자전거 대여중이면 QR코드 스캔이 비활성화 되며, Using 페이지로 넘어감
* Arrive
    * 도착 후 자전거 QR코드 다시 촬영하여 반납 요청을 보냄(GPS정보 자전거 번호 정보를 보냄)

### 백 엔드 처리 구성도

![DB connection](https://user-images.githubusercontent.com/19687080/97854883-620a8800-1d3d-11eb-84d7-a84e88463128.png)

* Rent Controller
    * 사용자가 자전거를 대여 요청할 경우 처리해주는 로직을 담당한다.
    * 자전거 대여 요청시 Bicycle DB에 사용자 정보를 넣어서 사용 기록을 남긴다.
* Bicycle Controller
    * 자전거 등록 및 위치 정보를 받아 오기 위한 로직을 수행한다.
* User Controller
    * 사용자 로그인, 회원가입 등 회원정보 처리 등의 로직을 담당한다.

### 클래스 다이어 그램

![class diagram](https://user-images.githubusercontent.com/19687080/97854912-69ca2c80-1d3d-11eb-9cf5-58b64697c094.PNG)
