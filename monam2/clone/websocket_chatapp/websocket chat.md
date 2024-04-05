## Websocket.io Chat App
1. 백엔드 세팅 : 데이터베이스(MongoDB), 웹소켓
2. 프론트엔드 세팅 : Vanila JS
3. 백-프 연결
4. 유저 이름 입력
5. 채팅

### 1. 백엔드 세팅

1-1. init

```jsx
npm init -y
npm i express, mongoose, cors dotenv http, socket.io, nodemon

nodemon은 npm으로 설치 후 npx로 실행해야 됨.
실행 : npx nodemon app.js
node app.js와 다른 점 -> js파일의 변경 요소를 트래킹해서 자동으로 반영함
```

1-2. DB

- 유저 정보
- 채팅 메세지 정보

users.js

```jsx
const mongoose = require("mongoose");

//유저 정보 스키마 설정
const userSchema = new mongoose.Schema({
    name: { //user info
        type: String,
        required: [true, "User must type name"],
        unique: true,
    },
    token: { //connet id
        type: String,
    },
    online: { //is online?
        type: Boolean,
        default: false,
    },
});
module.exports = mongoose.model("User", userSchema);
```

chat.js

```jsx
const mongoose = require("mongoose");

//채팅 정보 스키마 설정
const chatSchema = new mongoose.Schema(
    {
        chat: String,
        user: {
            id: {
                type: mongoose.Schema.ObjectId,
                ref: "User",
            },
            name: String,
        },
    },
    {timestamp: true}
);

module.exports = mongoose.model("Chat", chatSchema);
```

app.js

```jsx
const express = require("express");
const mongoose = require("mongoose");
const app = express();

mongoose.connect("", {
    useNewUrlParser : true,
    useUnifiedTopology: true,
}).then(()=>console.log("연결 완료"));

module.exports = app;
```

.env

```jsx
PORT=5001
DB=mongodb://localhost:27017/chat-app
```

1-3. 웹소켓 서버 생성

index.js

```jsx
const { createServer } = require("http");
const app = require("./app");
const { Server } = require("socket.io");
require("dotenv").config();

const httpServer = createServer(app);
const io = new Server(httpServer, {
  cors: {
    origin: "http://localhost:3000",
  },
});

httpServer.listen(process.env.PORT, ()=>{
    console.log("server listening on port", process.env.PORT);
})
```

io.js

```jsx
module.exports=function(io){
    io.on("connection", async(socket)=>{
        console.log("client is connected", socket.id);
    })
};
```

### 2. 프론트엔드 세팅

```jsx
...
├─public
├─src
│  └─components
│      ├─InputField
│      └─MessageContainer
└─utils
```

server.js

```jsx
import {io } from"socket.io-client";
const socket = io("http:localhost:5001");
export default socket;
```
