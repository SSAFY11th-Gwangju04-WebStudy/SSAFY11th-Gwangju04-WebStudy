<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/confirm.jsp"%>
<div class="row justify-content-center">
	<div class="col-lg-8 col-md-10 col-sm-12">
		<h2 class="my-3 py-3 shadow-sm bg-light text-center">
			<mark class="sky">글목록</mark>
		</h2>
	</div>
	<div class="col-lg-8 col-md-10 col-sm-12">
		<div class="row align-self-center mb-2">
			<div class="col-md-2 text-start">
				<button type="button" id="btn-mv-register"
					class="btn btn-outline-primary btn-sm">글쓰기</button>
			</div>
			<div class="col-md-7 offset-3">
				<form class="d-flex" id="form-search" action="">
					<input type="hidden" name="pgno" value="1" /> <select name="key"
						id="key" class="form-select form-select-sm ms-5 me-1 w-50"
						aria-label="검색조건">
						<option selected>검색조건</option>
						<option value="subject">제목</option>
						<option value="userid">작성자</option>
					</select>
					<div class="input-group input-group-sm">
						<input type="text" name="word" id="word" class="form-control"
							placeholder="검색어..." />
						<button id="btn-search" class="btn btn-dark" type="button">검색</button>
					</div>
				</form>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr class="text-center">
					<th scope="col">글번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">조회수</th>
					<th scope="col">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="article" items="${articles}">
					<tr class="text-center">
						<th scope="row">${article.articleNo}</th>
						<td class="text-start"><a href="#"
							class="article-title link-dark" data-no="${article.articleNo}"
							style="text-decoration: none">
								${article.subject} </a></td>
						<td>${article.userId}</td>
						<td>${article.hit}</td>
						<td>${article.registerTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="row">${navigation.navigator}</div>
</div>
</div>
<form id="form-param" method="get" action="">
	<input type="hidden" name="pgno" id="pgno" value="${pgno}"> <input
		type="hidden" name="key" value="${key}"> <input type="hidden"
		name="word" value="${word}">
</form>
<form id="form-no-param" method="get" action="${root}/article/view">
	<input type="hidden" name="pgno" value="${pgno}"> <input
		type="hidden" name="key" value="${key}"> <input type="hidden"
		name="word" value="${word}"> <input type="hidden"
		id="articleno" name="articleno" value="">
</form>
<script>
	let titles = document.querySelectorAll(".article-title");
	titles.forEach(function(title) {
		title.addEventListener("click", function() {
			document.querySelector("#articleno").value = this
					.getAttribute("data-no");
			document.querySelector("#form-no-param").submit();
		});
	});

	document.querySelector("#btn-mv-register").addEventListener("click",
			function() {
				let form = document.querySelector("#form-param");
				form.setAttribute("action", "${root}/article/write");
				form.submit();
			});

	document.querySelector("#btn-search").addEventListener("click", function() {
		let form = document.querySelector("#form-search");
		form.setAttribute("action", "${root}/article/list");
		form.submit();
	});

	let pages = document.querySelectorAll(".page-link");
	pages.forEach(function(page) {
		page.addEventListener("click", function() {
			document.querySelector("#pgno").value = this.parentNode
					.getAttribute("data-pg");
			let form = document.querySelector("#form-param");
			form.setAttribute("action", "${root}/article/list");
			form.submit();
		});
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
