<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardRegisteForm.css" rel="stylesheet">
</head>
<body>
	<!-- ======= header ======= -->
	<%@ include file="../header.jsp"%>

	<main id="main" class="main">
		<div class="pagetitle">
			<h1>공모전 스터디 게시판</h1>
			<nav style="--bs-breadcrumb-divider: '-';">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="main">Home</a></li>
					<li class="breadcrumb-item active"><a href="boardContest">공모전</a></li>
					<li class="breadcrumb-item active"><a href="boardStudy">스터디</a></li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->
		<section class="section">
			<div class="row">
				<div class="card">
					<div class="card-body">
						<div class="title-input">
						<label for="boradTitle" class="form-label"></label><input
							type="text" id="boardTitle" class="form-control"
							aria-describedby="titleHelpBlock" placeholder="제목에 핵심 내용을 요약해보세요.">
						</div>
							
						<!-- Quill Editor Default -->
						<div class="ql-toolbar ql-snow">
							<span class="ql-formats"><span class="ql-header ql-picker"><span
									class="ql-picker-label" tabindex="0" role="button"
									aria-expanded="false" aria-controls="ql-picker-options-0"><svg
											viewBox="0 0 18 18"> <polygon class="ql-stroke"
												points="7 11 9 13 11 11 7 11"></polygon> <polygon
												class="ql-stroke" points="7 7 9 5 11 7 7 7"></polygon> </svg></span><span
									class="ql-picker-options" aria-hidden="true" tabindex="-1"
									id="ql-picker-options-0"><span tabindex="0"
										role="button" class="ql-picker-item" data-value="1"></span><span
										tabindex="0" role="button" class="ql-picker-item"
										data-value="2"></span><span tabindex="0" role="button"
										class="ql-picker-item" data-value="3"></span><span
										tabindex="0" role="button" class="ql-picker-item"></span></span></span><select
								class="ql-header" style="display: none;"><option
										value="1"></option>
									<option value="2"></option>
									<option value="3"></option>
									<option selected="selected"></option></select></span><span class="ql-formats"><button
									type="button" class="ql-bold">
									<svg viewBox="0 0 18 18"> <path class="ql-stroke"
											d="M5,4H9.5A2.5,2.5,0,0,1,12,6.5v0A2.5,2.5,0,0,1,9.5,9H5A0,0,0,0,1,5,9V4A0,0,0,0,1,5,4Z"></path> <path
											class="ql-stroke"
											d="M5,9h5.5A2.5,2.5,0,0,1,13,11.5v0A2.5,2.5,0,0,1,10.5,14H5a0,0,0,0,1,0,0V9A0,0,0,0,1,5,9Z"></path> </svg>
								</button>
								<button type="button" class="ql-italic">
									<svg viewBox="0 0 18 18"> <line class="ql-stroke" x1="7"
											x2="13" y1="4" y2="4"></line> <line class="ql-stroke" x1="5"
											x2="11" y1="14" y2="14"></line> <line class="ql-stroke"
											x1="8" x2="10" y1="14" y2="4"></line> </svg>
								</button>
								<button type="button" class="ql-underline">
									<svg viewBox="0 0 18 18"> <path class="ql-stroke"
											d="M5,3V9a4.012,4.012,0,0,0,4,4H9a4.012,4.012,0,0,0,4-4V3"></path> <rect
											class="ql-fill" height="1" rx="0.5" ry="0.5" width="12" x="3"
											y="15"></rect> </svg>
								</button>
								<button type="button" class="ql-link">
									<svg viewBox="0 0 18 18"> <line class="ql-stroke" x1="7"
											x2="11" y1="7" y2="11"></line> <path
											class="ql-even ql-stroke"
											d="M8.9,4.577a3.476,3.476,0,0,1,.36,4.679A3.476,3.476,0,0,1,4.577,8.9C3.185,7.5,2.035,6.4,4.217,4.217S7.5,3.185,8.9,4.577Z"></path> <path
											class="ql-even ql-stroke"
											d="M13.423,9.1a3.476,3.476,0,0,0-4.679-.36,3.476,3.476,0,0,0,.36,4.679c1.392,1.392,2.5,2.542,4.679.36S14.815,10.5,13.423,9.1Z"></path> </svg>
								</button></span><span class="ql-formats"><button type="button"
									class="ql-list" value="ordered">
									<svg viewBox="0 0 18 18"> <line class="ql-stroke" x1="7"
											x2="15" y1="4" y2="4"></line> <line class="ql-stroke" x1="7"
											x2="15" y1="9" y2="9"></line> <line class="ql-stroke" x1="7"
											x2="15" y1="14" y2="14"></line> <line
											class="ql-stroke ql-thin" x1="2.5" x2="4.5" y1="5.5" y2="5.5"></line> <path
											class="ql-fill"
											d="M3.5,6A0.5,0.5,0,0,1,3,5.5V3.085l-0.276.138A0.5,0.5,0,0,1,2.053,3c-0.124-.247-0.023-0.324.224-0.447l1-.5A0.5,0.5,0,0,1,4,2.5v3A0.5,0.5,0,0,1,3.5,6Z"></path> <path
											class="ql-stroke ql-thin"
											d="M4.5,10.5h-2c0-.234,1.85-1.076,1.85-2.234A0.959,0.959,0,0,0,2.5,8.156"></path> <path
											class="ql-stroke ql-thin"
											d="M2.5,14.846a0.959,0.959,0,0,0,1.85-.109A0.7,0.7,0,0,0,3.75,14a0.688,0.688,0,0,0,.6-0.736,0.959,0.959,0,0,0-1.85-.109"></path> </svg>
								</button>
								<button type="button" class="ql-list" value="bullet">
									<svg viewBox="0 0 18 18"> <line class="ql-stroke" x1="6"
											x2="15" y1="4" y2="4"></line> <line class="ql-stroke" x1="6"
											x2="15" y1="9" y2="9"></line> <line class="ql-stroke" x1="6"
											x2="15" y1="14" y2="14"></line> <line class="ql-stroke"
											x1="3" x2="3" y1="4" y2="4"></line> <line class="ql-stroke"
											x1="3" x2="3" y1="9" y2="9"></line> <line class="ql-stroke"
											x1="3" x2="3" y1="14" y2="14"></line> </svg>
								</button></span><span class="ql-formats"><button type="button"
									class="ql-clean">
									<svg class="" viewBox="0 0 18 18"> <line
											class="ql-stroke" x1="5" x2="13" y1="3" y2="3"></line> <line
											class="ql-stroke" x1="6" x2="9.35" y1="12" y2="3"></line> <line
											class="ql-stroke" x1="11" x2="15" y1="11" y2="15"></line> <line
											class="ql-stroke" x1="15" x2="11" y1="11" y2="15"></line> <rect
											class="ql-fill" height="1" rx="0.5" ry="0.5" width="7" x="2"
											y="14"></rect> </svg>
								</button></span>
						</div>
						<div class="quill-editor-default ql-container ql-snow">
							<div class="ql-editor" data-gramm="false" contenteditable="true">
								<p>내용을 입력하세요</p>
							</div>
							<div class="ql-clipboard" contenteditable="true" tabindex="-1"></div>
							<div class="ql-tooltip ql-hidden">
								<a class="ql-preview" rel="noopener noreferrer" target="_blank"
									href="about:blank"></a><input type="text" data-formula="e=mc^2"
									data-link="https://quilljs.com" data-video="Embed URL"><a
									class="ql-action"></a><a class="ql-remove"></a>
							</div>
						</div>
						<!-- End Quill Editor Default -->
					</div>
					<div class="btn-container">
					<form action="boardRegisteWrite수정" method="get">
						<input type="submit" class="btn btn-primary" value="submit">
					</form>
					<form action="boardRegisteWrite수정" method="get">
						<input type="submit" class="btn btn-secondary" value="reset">
					</form>
					</div>
				</div>
			</div>
		</section>


		<!-- ======= aSidebar ======= -->
		<%@ include file="../asidebar.jsp"%>




		<!-- ======= Pagination ======= -->
		<section>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">«</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">4</a></li>
					<li class="page-item"><a class="page-link" href="#">5</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">»</span>
					</a></li>
				</ul>
			</nav>
		</section>
	</main>

	<!-- ======= Footer ======= -->
	<%@ include file="../footer.jsp"%>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>
</html>
