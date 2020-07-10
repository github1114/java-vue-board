# board-app
```
로그인 및 글 쓰기,읽기,수정,삭제,리스트 조회기능이 구현 된 게시판입니다.
```
## 로그인 FLOW
```
BoardUML.png 파일의 uml을 첨부드립니다. 
```
## 테스트 정보
```
userId= test
password= test
```
## Backend 사용기술
```
java 1.8, spring-boot 2.3.1, JPA 2.3.1, spring-security, H2, JWT, gradle 6.4.1,  
```
## Frontend 사용기술
```
vue.js, vuex, Vuetify CSS Framework, JWT
```

## IDE
```
intelliJ 2020.1, VS code 1.46.1
```
## app 실행방법
```
back-end

1. IntelliJ IDE 에서 board 프로젝트를 open 합니다.
2. lombok plugin이 설치되지 않았다면 Marcket place에서 lombok을 설치합니다.
3. settings/build, excecute, deployment/compiler/annotation processors 에서 enable annotaion processing을 선택합니다.
3. gradle tool bar에서 task/build/build를 실행합니다.
4. spring-boot에서 run을 실행합니다.

(위 방법으로 정상적으로 실행되지 않을 시)

1. gradle tool bar에서 task/build/clean를 실행합니다.
2. gradle tool bar에서 전체 프로젝트 클릭 후 refresh를 실행합니다.
이후 3,4번을 실행합니다.

front-end

1. VS code에서 프로젝트의 board/src/frontend 경로를 open 합니다.
2. VS code 터미널에서 npm install 실행합니다.
3. VS code 터미널에서 npm run serve 실행합니다.


```




