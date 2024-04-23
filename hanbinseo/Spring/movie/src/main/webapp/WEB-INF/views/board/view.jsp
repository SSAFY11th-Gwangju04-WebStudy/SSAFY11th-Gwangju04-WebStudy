<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<c:if test="${article eq null}">
		<script>
		alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
		location.href = "${root}/article/list";
		</script>
	</c:if>
      <%@ include file="/WEB-INF/views/common/confirm.jsp" %>
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글보기</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-secondary px-5">${article.articleNo}. ${article.subject}</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                />
                <p>
                  <span class="fw-bold">${article.userId}</span> <br />
                  <span class="text-secondary fw-light"> ${article.registerTime} 조회 : ${article.hit} </span>
                </p>
              </div>
            </div>
            <div class="col-md-4 align-self-center text-end">댓글 : 17</div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              ${article.content}
            </div>
            <c:if test="${!empty article.fileInfos}">
			<div class="mt-3">
				<ul>
					<c:forEach var="file" items="${article.fileInfos}">
						<li>${file.originalFile} <a href="#" class="filedown" sfolder="${file.saveFolder}" sfile="${file.saveFile}" ofile="${file.originalFile}">[다운로드]</a>
					</c:forEach>
				</ul>
			</div>
			</c:if>
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
                글목록
              </button>
              <c:if test="${userinfo.userId eq article.userId}">
	              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
	                글수정
	              </button>
	              <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
	                글삭제
	              </button>
	              <form id="form-no-param" method="get" action="${root}/board">
				      <input type="hidden" id="npgno" name="pgno" value="${pgno}">
				      <input type="hidden" id="nkey" name="key" value="${key}">
				      <input type="hidden" id="nword" name="word" value="${word}">
				      <input type="hidden" id="articleno" name="articleno" value="${article.articleNo}">
				  </form>
				  <script>
		      		document.querySelector("#btn-mv-modify").addEventListener("click", function () {
				    	let form = document.querySelector("#form-no-param");
				   		form.setAttribute("action", "${root}/article/modify");
				    	form.submit();
				  	});
				      
					document.querySelector("#btn-delete").addEventListener("click", function () {
						if(confirm("정말 삭제하시겠습니까?")) {
							let form = document.querySelector("#form-no-param");
				      	  	form.setAttribute("action", "${root}/article/delete");
				          	form.submit();
						}
					});
				  </script>
              </c:if>
            </div>
          </div>
        </div>
      </div>
    </div>
    <form id="form-param" method="get" action="">
      <input type="hidden" id="pgno" name="pgno" value="${pgno}">
      <input type="hidden" id="key" name="key" value="${key}">
      <input type="hidden" id="word" name="word" value="${word}">
    </form>
    <form id="downform" action="${root}/article/download">
	  <input type="hidden" name="sfolder">
	  <input type="hidden" name="ofile">
	  <input type="hidden" name="sfile">
	</form>
    <script>
    document.querySelector("#btn-list").addEventListener("click", function () {
  	  let form = document.querySelector("#form-param");
  	  form.setAttribute("action", "${root}/article/list");
        form.submit();
    });
    let files = document.querySelectorAll(".filedown");
    files.forEach(function(file) {
    	file.addEventListener("click", function() {
    		document.querySelector("[name='sfolder']").value = file.getAttribute("sfolder");
    		document.querySelector("[name='ofile']").value = file.getAttribute("ofile");
    		document.querySelector("[name='sfile']").value = file.getAttribute("sfile");
    		document.querySelector("#downform").submit();
    	});
    });
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>