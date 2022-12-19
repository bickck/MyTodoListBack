# to-do-list

REST API

## Authroity ( /auth/* )
### Login /login
  - /login, Method.POST
  - /logout, Method.POST
  - /register, Method.POST

### Logout
  - /logout, Method.POST


### Register
  - /register, Method.POST

###
## USER ACTIONS (/user/* , /heart/*)

### QUOTE /user/quote
  - /user/quote/{id}, Method.POST => save
  - /user/quote/{id}, Method.PUT => update
  - /user/quote/{id}, Method.DELETE => delete

### TODO /user/todo  
  - /user/todo/{id}, Method.POST => save
  - /user/todo/{id}, Method.PUT => update
  - /user/todo/{id}, Method.DELETE => delete

### HEART ( /heart/* )
  - /heart/todo/{id}, Method.POST => save
  - /heart/todo/{uuid}, Method.DELETE => delete
  - /heart/quote/{id}, Method.POST => save
  - /heart/quote/{uuid}, Method.DELETE => delete


###
## API 
### HEART /heart/api
  - /todo/{id}, Method.POST => todo exist check
  - /quote/{id}, Method.POST => quote exist check

### IMAGE /image/api
  - /user/source/{filePath}/{fileName}, Method.GET => Todo Image
  - /source/{filePath}/{fileName}, Method.GET => User Profile Image
  
### USER /user/api
  - /intro, Method.GET => User Profile Data
  - /todos, Method.GET => 유저가 작성한 TODO
  - /quote, Method.GET => 유저가 작성한 QUOTE

### USER LIKE /user/like
  - /todos, Method.POST => 유저가 Heart를 누른 TODO
  - /quotes, Method.POST => 유저가 Heart를 누른 QUOTE

## 설계 조건
### Login 요구 사항
  - Redis에 위치한 IP와 접속한 IP가 불일치 일경우 이전 Token으로 접근 불가
  - Redis는 하나의 이메일을 가지고 있어야함

### API 요구 사항
  - /user 패턴으로 들어오는 요청은 모두 토큰을 가지고 있어야함

### Register 요구 사항
  - 이메일, 패스워드를 정규표현식으로 조건을 생성
  - 이메일은 중복 불가


### Heart 요구 사항
  - 한 번 누른 Heart는 중복 불가

