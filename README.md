# to-do-list

##### - Language    : Java
##### - FrameWork   : Spring boot
##### - DB          : MySql
##### - API         : JPA
##### - Server / OS : AWS , Ubuntu
##### - DevOps      : Jenkins
##### - Front Server URL :  https://bickck.github.io/MyTodoListFront/ 에서 배포 중
##### - Back Server URL :  https://www.mybackendpractice.link에서 배포 중 (돈이 없어서 잠시 중단....)

<br/>

## 전체적인 개발 조건
 #### 1. 2만원이내의 서버를 설계해야됨
 #### 2. DB 쿼리 요청은 최대 2번, 이미지 요청은 3번 이내로 설계해야됨


<br/>

## 설계 조건
### Login 요구 사항
  - Redis에 위치한 IP와 접속한 IP가 불일치 일경우 이전 Token으로 접근 불가
  - Redis는 하나의 이메일을 가지고 있어야함

<br/>

### Register 요구 사항
  - 이메일, 패스워드를 정규표현식으로 유효성 체크
  - 이메일은 중복 불가

<br/>

### API 요구 사항
  - /user 패턴으로 들어오는 요청은 모두 토큰을 가지고 있어야함
  - /user 패턴으로 나가는 응답은 갱신된 토큰(Redis에 들어있는)을 포함해야함
  - /todo/api, /quote/api, /image/api 패턴은 토큰이 필요하지 않음 (단, /image/api/user는 유저 프로필 이미지이기에 토큰 필요)

<br/>

### Heart 요구 사항
  - 한 번 누른 Heart는 중복 불가
  - 유저당 하나의 Heart를 누를 수 있음

<br/>

### Todo 요구 사항
  - Todo 이미지는 2개만 저장
  - Todo 이미지용 UUID와 Unique ID를 사용 
  - UUID는 유저에게 보여주는 filePath로 사용되며 서버의 Physical Path 경로 사용

<br/>

### Image 관리 요구 사항
  - Todo 이미지는 UUID를 사용하여 이미지 Physical Path 관리
  - Todo 이미지 삭제는 Physical 삭제 O, update를 사용하여 내용 변경, UUID로 생성된 경로는 재사용
  - User 이미지는 회원가입이 진행되면 기본 이미지를 생성 (front blank-profile-picture-gdf6b93f73_640.png 사용)
  - User 이미지 삭제는 Physical 삭제 x, update를 사용하여 내용 변경, UUID로 생성된 경로는 재사용


<br/>

### Quote 요구 사항
  - 

<br/>

### Comment 요구 사항
  -

<br/>

### UUID 생성 조건
  - 1 : N개의 생성이 필요할 때
  - Physical Path가 필요한 경우

<br/>

## Authroity ( /auth/* )
### Login
  - /login, Method.POST => redis token save

<br/>

### Logout
  - /logout, Method.POST => redis token delete

<br/>

### Register
  - /register, Method.POST => save

<br/>

## USER ACTIONS ( /user/* , /heart/* , /comment/* )

### QUOTE /user/quote
  - /user/quote/{id}, Method.POST => save
  - /user/quote/{id}, Method.PUT => update
  - /user/quote/{id}, Method.DELETE => delete

<br/>

### TODO /user/todo  
  - /user/todo/{id}, Method.POST => save
  - /user/todo/{id}, Method.PUT => update
  - /user/todo/{id}, Method.DELETE => delete

<br/>

### HEART ( /heart/* )
  - /heart/todo/{id}, Method.POST => save
  - /heart/todo/{uuid}, Method.DELETE => delete
  - /heart/quote/{id}, Method.POST => save
  - /heart/quote/{uuid}, Method.DELETE => delete

<br/>

### COMMENT ( /comment/* )
  - /{id}, Method.POST => save
  - /{id}, Method.PUT => update
  - /{id}, Method.DELETE => delete

<br/>

## API
### TODO /todo/api
  - /{id}, Method.GET
  - /mainpost, Method.GET
  - /recommand, Method.GET 개발중
  - /daily, Method.GET 개발중
  - /comment/{id}, Method.GET

<br/>

### QUOTE /quote/api
  - /{id}, Method.GET
  - /mainquote, Method.GET
  - /recommand, Method.GET 계발중
  - /daily, Method.GET 계발중

<br/>

### HEART /heart/api
  - /todo/{id}, Method.POST => todo exist check
  - /quote/{id}, Method.POST => quote exist check

<br/>

### IMAGE /image/api
  - /user/source/{filePath}/{fileName}, Method.GET => Todo Image
  - /source/{filePath}/{fileName}, Method.GET => User Profile Image
 
<br/>

### USER /user/api
  - /intro, Method.GET => User Profile Data
  - /todos, Method.GET => 유저가 작성한 TODO
  - /quote, Method.GET => 유저가 작성한 QUOTE

<br/>

### USER LIKE /user/like
  - /todos, Method.POST => 유저가 Heart를 누른 TODO
  - /quotes, Method.POST => 유저가 Heart를 누른 QUOTE

