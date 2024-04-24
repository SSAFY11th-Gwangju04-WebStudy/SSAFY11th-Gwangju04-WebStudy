<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="orange">회원가입</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <form id="form-join" method="POST" action="">
            <div class="mb-3">
              <label for="username" class="form-label">이름 : </label>
              <input
                type="text"
                class="form-control"
                id="username"
                name="userName"
                placeholder="이름..."
              />
            </div>
            <div class="mb-3">
              <label for="userid" class="form-label">아이디 : </label>
              <input
                type="text"
                class="form-control"
                id="userid"
                name="userId"
                placeholder="아이디..."
              />
            </div>
            <div id="idcheck-result"></div>
            <div class="mb-3">
              <label for="userpwd" class="form-label">비밀번호 : </label>
              <input
                type="text"
                class="form-control"
                id="userpwd"
                name="userPwd"
                placeholder="비밀번호..."
              />
            </div>
            <div class="mb-3">
              <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
              <input type="text" class="form-control" id="pwdcheck" placeholder="비밀번호확인..." />
            </div>
            <div class="mb-3">
              <label for="emailid" class="form-label">이메일 : </label>
              <div class="input-group">
                <input
                  type="text"
                  class="form-control"
                  id="emailid"
                  name="emailId"
                  placeholder="이메일아이디"
                />
                <span class="input-group-text">@</span>
                <select
                  class="form-select"
                  id="emaildomain"
                  name="emailDomain"
                  aria-label="이메일 도메인 선택"
                >
                  <option selected>선택</option>
                  <option value="ssafy.com">싸피</option>
                  <option value="google.com">구글</option>
                  <option value="naver.com">네이버</option>
                  <option value="kakao.com">카카오</option>
                </select>
              </div>
            </div>
            <!-- 
            <div class="mb-3">
              <label for="sido" class="form-label">지역 : </label>
              <div class="input-group">
                <select class="form-select me-1" id="sido" aria-label="시도">
                  <option selected>시도선택</option>
                  <option value="1100000000">서울특별시</option>
                  <option value="2200000000">경기도</option>
                  <option value="3300000000">강원도</option>
                  <option value="4400000000">충청도</option>
                </select>
                <select class="form-select" id="gugun" aria-label="구군">
                  <option selected>구군선택</option>
                  <option value="1111000000">종로구</option>
                  <option value="1112000000">중구</option>
                  <option value="1113000000">서초구</option>
                  <option value="1114000000">강남구</option>
                </select>
              </div>
            </div>
             -->
            <div class="col-auto text-center">
              <button type="button" id="btn-join" class="btn btn-outline-primary mb-3">
                회원가입
              </button>
              <button type="button" class="btn btn-outline-success mb-3">초기화</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <script>
    let isUseId = false;
    document.querySelector("#userid").addEventListener("keyup", function () {
		let userid = this.value;
		console.log(userid);
  	 	let resultDiv = document.querySelector("#idcheck-result");
  	 	if(userid.length < 6 || userid.length > 16) {
  		 	resultDiv.setAttribute("class", "mb-3 text-dark");
  		 	resultDiv.textContent = "아이디는 6자 이상 16자 이하 입니다.";
  		 	isUseId = false;
  	 	} else {
  		 	fetch("${root}/user/" + userid)
	   		.then(response => response.text())
	   		.then(data => {
	   			console.log(data);
		 		if(data == 0) {
		   			resultDiv.setAttribute("class", "mb-3 text-primary");
	       			resultDiv.textContent = userid + "는 사용할 수 있습니다.";
	       			isUseId = true;
		 		} else {
		   			resultDiv.setAttribute("class", "mb-3 text-danger");
 		       		resultDiv.textContent = userid + "는 사용할 수 없습니다.";
 		     		isUseId = false;
		 		}
  		   });
  	 	}
    });
    
	document.querySelector("#btn-join").addEventListener("click", function () {
		if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#userid").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (document.querySelector("#userpwd").value != document.querySelector("#pwdcheck").value) {
          alert("비밀번호 확인!!");
          return;
        } else {
          let form = document.querySelector("#form-join");
          form.setAttribute("action", "${root}/user/join");
          form.submit();
        }
	});
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
