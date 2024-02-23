<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="boardConfig.jsp"%>
<link href="assets/css/ykm/boardComment.css" rel="stylesheet">
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

		<!-- ======= Bordered Tabs ======= -->
		<section class="section">
			<div class="row">
				<div class="card">
					<div class="card-body">
						<div class="container-md">
							<div class="row comment-form">
								<div class="col-md">
									<h3>Комментарии</h3>
									<p>
										Вы вошли как <a href="#">Alexa Poruch</a>
									</p>
									<form method="POST" action="">
										<div class="form-group">
											<textarea id="editor" class="form-control"></textarea>
										</div>
										<div class="form-group text-right">
											<button class="btn btn-secondary comment-send" type="button">Отправить</button>
										</div>
									</form>
								</div>
							</div>
							<div class="row">

								<div class="col-md">

									<ul class="list-unstyled comment">
										<li>
											<div class="row">
												<div class="col-sm-2 text-center">
													<img class="avatar d-none d-md-block"
														src="https://www.gravatar.com/avatar/a2c4fd58ec293b5cb556fde9685ee31e?d=mp&s=200"
														alt="Alexa Poruch" />
												</div>
												<div class="col-md-10">
													<div class="card">
														<div class="card-header p-0 px-3">
															<div class="row py-3">
																<div class="mx-3 user">
																	<a class="isAuthor" href="#">Alexa Poruch</a>
																</div>
																<div class="ml-auto mr-4">
																	<i class="fas fa-reply" data-toggle="tooltip"
																		title="Ответить"></i>
																</div>
															</div>
														</div>
														<!-- card-header -->
														<div class="card-body">
															<p class="card-text">Lorem ipsum dolor sit amet,
																consectetur adipiscing elit. Videamus animi partes,
																quarum est conspectus illustrior; Ita credo. Quae
																contraria sunt his, malane?</p>
															<p>
																Ea possunt paria non esse. Non semper, inquam; <b>Duo
																	Reges: constructio interrete.</b> <b>Omnis enim est
																	natura diligens sui.</b> <i>Invidiosum nomen est,
																	infame, suspectum.</i> Quid Zeno?
															</p>
															<div class="row">
																<div class="ml-auto pr-3">
																	<i class="fas fa-thumbs-up mr-1" data-toggle="tooltip"
																		title="Нравится"></i> <i
																		class="fas fa-thumbs-up fa-rotate-180"
																		data-toggle="tooltip" title="Не нравится"></i>
																</div>
															</div>
														</div>
														<!-- card-body -->
													</div>
													<!-- card -->
												</div>
												<!-- col-9 -->
											</div>
											<!-- row -->
										</li>

									</ul>

								</div>
							</div>
						</div>
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
