# to-do-list

REST API

<br/>

## Authroity ( /auth/* )
### Login
  - /login, Method.POST

<br/>

### Logout
  - /logout, Method.POST

<br/>

### Register
  - /register, Method.POST

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
  - /recommand, Method.GET 계발중
  - /daily, Method.GET 계발중
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

<br/>

## 설계 조건
### Login 요구 사항
  - Redis에 위치한 IP와 접속한 IP가 불일치 일경우 이전 Token으로 접근 불가
  - Redis는 하나의 이메일을 가지고 있어야함

<br/>

### API 요구 사항
  - /user 패턴으로 들어오는 요청은 모두 토큰을 가지고 있어야함

<br/>

### Register 요구 사항
  - 이메일, 패스워드를 정규표현식으로 조건을 생성
  - 이메일은 중복 불가

<br/>

### Heart 요구 사항
  - 한 번 누른 Heart는 중복 불가

