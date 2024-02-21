<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <!-- ======= Sidebar ======= -->
 <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link " href="index.html">
          <i class="bi bi-grid"></i>
          <span>워크스페이스</span>
        </a>
      </li><!-- End Dashboard Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-envelope-fill"></i><span>쪽지</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/msg/recievebox">
              <i class="bi bi-circle"></i><span>받은 쪽지함</span>
            </a>
          </li>
          <li>
            <a href="/msg/sendbox">
              <i class="bi bi-circle"></i><span>보낸 쪽지함</span>
            </a>
          </li>
          <li>
            <a href="/msg/storebox">
              <i class="bi bi-circle"></i><span>쪽지 보관함</span>
            </a>
          </li>
          <li>
            <a href="/msg/trashbox">
              <i class="bi bi-circle"></i><span>휴지통</span>
            </a>
          </li>
        </ul>
      </li><!-- End Components Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
          <i class="bi bi-layout-text-window-reverse"></i><span>정보게시판</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="forms-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
          <li>
            <a href="/board/study">
              <i class="bi bi-circle"></i><span>공모전 / 스터디</span>
            </a>
          </li>
          <li>
            <a href="/board/free">
              <i class="bi bi-circle"></i><span>자유 / 질문</span>
            </a>
          </li>
        </ul>
      </li><!-- End Forms Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#charts-nav" data-bs-toggle="collapse" href="/address">
          <i class="bi bi-people-fill"></i><span>주소록</span>
        </a>
      </li><!-- End Charts Nav -->
    </ul>

  </aside><!-- End Sidebar-->