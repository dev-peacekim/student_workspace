<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Blueberry</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/blueberry-logo.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link
    href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
    rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet" type="text/css">
  <link href="assets/css/lhs/admin.css" rel="stylesheet" type="text/css">
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script src="https://kit.fontawesome.com/0b22ed6a9d.js" crossorigin="anonymous"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>

<body>
   <!-- ======= Header ======= -->
    <%@ include file="admin_header.jsp" %>

    <!-- ======= Sidebar ======= -->
    <%@ include file="admin_aside.jsp" %>
  <!-- End Sidebar-->

  <main id="main" class="main">
      <section>
        <div class="main-div">
          <div class="main-top">
            <div class="pagetitle">
              <h1>관리자전용 페이지</h1>
            </div>

            <div class="d-flex justify-content-around">
              <div class="main-top-content">
                <div class="content-title fnt-nanum fnt-size20">
                  회원<span class="small-title">(신규 가입자)</span>
                </div>
                <div class="content-detail">
                  0&nbsp;<span class="small-title cl-pupple">
                    (0)<i class="bi bi-arrow-up-short fnt-20"></i>
                  </span>
                </div>
              </div>
              <div class="main-top-content">
                <div class="content-title fnt-nanum fnt-size20 cl-gray">
                  프로젝트<span class="small-title">(신규 프로젝트)</span>
                </div>
                <div class="content-detail">
                  0&nbsp;<span class="small-title cl-pupple">
                    (0)<i class="bi bi-arrow-up-short fnt-20"></i>
                  </span>
                </div>
              </div>
              <div class="main-top-content">
                <div class="content-title fnt-nanum fnt-size20">
                  댓글<span class="small-title">(신규 댓글)</span>
                </div>
                <div class="content-detail">
                  0&nbsp;<span class="small-title cl-pupple">
                    (0)<i class="bi bi-arrow-up-short fnt-20"></i>
                  </span>
                </div>
              </div>
              <div class="main-top-content">
                <div class="content-title fnt-nanum fnt-size20">
                  게시글<span class="small-title">(신규 게시글)</span>
                </div>
                <div class="content-detail">
                  0&nbsp;<span class="small-title cl-pupple">
                    (0)<i class="bi bi-arrow-up-short fnt-20"></i>
                  </span>
                </div>
              </div>
            </div>
          </div>
          <div class="main-mid d-flex justify-content-between">
            <div class="mid-left border-radius10">
              <!-- 그래프 영역 시작-->
              <div class="card-body">
                <div class="d-flex justify-content-between">
                  <div class="fnt-nanum fnt-20">신규 회원</div>
                  <div class="fnt-nanum">(Day)</div>
                </div>
                <!-- Bar Chart -->
                <canvas
                  id="barChart"
                  style="
                    max-height: 400px;
                    display: block;
                    box-sizing: border-box;
                    height: 223px;
                    width: 446px;
                  "
                  width="446"
                  height="223"
                ></canvas>
                <script>
                  document.addEventListener("DOMContentLoaded", () => {
                    new Chart(document.querySelector("#barChart"), {
                      type: "bar",
                      data: {
                        labels: [
                          "January",
                          "February",
                          "March",
                          "April",
                          "May",
                          "June",
                          "July",
                        ],
                        datasets: [
                          {
                            label: "Bar Chart",
                            data: [65, 59, 80, 81, 56, 55, 40],
                            backgroundColor: [
                              "rgba(255, 99, 132, 0.2)",
                              "rgba(255, 159, 64, 0.2)",
                              "rgba(255, 205, 86, 0.2)",
                              "rgba(75, 192, 192, 0.2)",
                              "rgba(54, 162, 235, 0.2)",
                              "rgba(153, 102, 255, 0.2)",
                              "rgba(201, 203, 207, 0.2)",
                            ],
                            borderColor: [
                              "rgb(255, 99, 132)",
                              "rgb(255, 159, 64)",
                              "rgb(255, 205, 86)",
                              "rgb(75, 192, 192)",
                              "rgb(54, 162, 235)",
                              "rgb(153, 102, 255)",
                              "rgb(201, 203, 207)",
                            ],
                            borderWidth: 1,
                          },
                        ],
                      },
                      options: {
                        scales: {
                          y: {
                            beginAtZero: true,
                          },
                        },
                      },
                    });
                  });
                </script>
                <!-- End Bar CHart -->
              </div>
              <!-- 그래프 영역 끝-->
            </div>
            <div class="mid-right">
              <div class="mid-right-top">
                <div class="right-title fnt-nanum">신규 문의글</div>
                <div>
                  <div class="card border-none">
                    <div class="card-body padding-none">
                      <!-- Default Table -->
                      <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">번호</th>
                            <th scope="col">제목</th>
                            <th scope="col">작성일</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <th scope="row">1</th>
                            <td>Brandon Jacob</td>
                            <td>2016-05-25</td>
                          </tr>
                          <tr>
                            <th scope="row">2</th>
                            <td>Bridie Kessler</td>
                            <td>2014-12-05</td>
                          </tr>
                          <tr>
                            <th scope="row">3</th>
                            <td>Ashleigh Langosh</td>
                            <td>2011-08-12</td>
                          </tr>
                          <tr>
                            <th scope="row">4</th>
                            <td>Angus Grady</td>
                            <td>2012-06-11</td>
                          </tr>
                          <tr>
                            <th scope="row">5</th>
                            <td>Raheem Lehner</td>
                            <td>2011-04-19</td>
                          </tr>
                        </tbody>
                      </table>
                      <!-- End Default Table Example -->
                    </div>
                  </div>
                </div>
              </div>
              <div class="mid-right-bottom">
                <div class="right-title fnt-nanum">회원 탈퇴 신청</div>
                <div>
                  <div class="card border-none">
                    <div class="card-body padding-none">
                      <!-- Table with hoverable rows -->
                      <table class="table table-hover">
                        <thead>
                          <tr>
                            <th scope="col">번호</th>
                            <th scope="col">이름</th>
                            <th scope="col">신청일</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <th scope="row">1</th>
                            <td>Brandon Jacob</td>
                            <td>2016-05-25</td>
                          </tr>
                          <tr>
                            <th scope="row">4</th>
                            <td>Angus Grady</td>
                            <td>2012-06-11</td>
                          </tr>
                          <tr>
                            <th scope="row">5</th>
                            <td>Raheem Lehner</td>
                            <td>2011-04-19</td>
                          </tr>
                        </tbody>
                      </table>
                      <!-- End Table with hoverable rows -->
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  <!-- End #main -->

  <!-- ======= Footer ======= -->
    <%@ include file="admin_footer.jsp" %>
  <!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
      class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <!-- <script src="assets/vendor/apexcharts/apexcharts.min.js"></script> -->
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- <script src="assets/vendor/chart.js/chart.umd.js"></script> -->
  <!-- <script src="assets/vendor/echarts/echarts.min.js"></script> -->
  <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

</body>

</html>