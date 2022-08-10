// header
const headerNavItems = document.querySelectorAll(".header-nav-box li");
const userMenu = document.querySelector(".user-menu");
const userMenuBtn = document.querySelector(".fa-caret-down");
const userDtlMenu = document.querySelector(".user-dtl-menu");
const userDtlMenuItems = document.querySelectorAll(".user-dtl-menu span");

//main
const titleInput = document.querySelector(".title-input input");
const contentInput = document.querySelector(".content-input textarea");

// 게시판 구분짓는 flag
let userMenuFlag = false;

let adminFlag = false;

let userCode = 0;

$(userDtlMenu).fadeOut(0);

// 로고 클릭시 홈페이지 이동
document.querySelector("header h1").onclick = () => {
    location.href = "/index";
}

// 헤더 매뉴 -공지사항 -자유게시판 -QnA
headerNavItems[0].onclick = () => {
    location.href = "/board?type=notice&page=1";
}

headerNavItems[1].onclick = () => {
    location.href = "/board?type=free&page=1";
}

headerNavItems[2].onclick = () => {
    location.href = "/board?type=question&page=1";
}

userMenuBtn.onclick = () => {
    if(userMenuFlag){
        $(userDtlMenu).fadeOut(200);
        userMenuFlag = false;
    }else {
        $(userDtlMenu).fadeIn(200);
        userMenuFlag = true;
    }
}

// 내 정보 보기
userDtlMenuItems[0].onclick = () => {

}

userDtlMenuItems[1].onclick = () => {
    alert("로그아웃");
    signinFlag = false;
    adminFlag = false;
    location.replace("/index");
}

// 회원탈퇴
userDtlMenuItems[2].onclick = () => {
    
}

// 헤더 메뉴 활성화시에 main 부분에 마우스 올라갈시 헤더 메뉴 접기
document.querySelector("main").onmouseover = () => {
   if(userMenuFlag) {
        $(userDtlMenu).fadeOut(200);
        userMenuFlag = false;
    }
}


/*  main  */

document.querySelector("article button").onclick = () => {
    let title = titleInput.value;
    let content = contentInput.value;

    $.ajax({
        type: "post",
        url: "/api/v1/board/new",
        data: JSON.stringify({
            boardTitle: title,
            boardContent: content,
            userCode: 20,
            boardType: 1
        }),
        contentType: "application/json",
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                alert("게시글 작성 성공\n게시글 코드: " + response.data.boardCode);
            }else {
                alert("게시글 작성 실패!");
            }
        },
        error: errorMessage
    });
}


function errorMessage(request, status, error) {
    alert("요청실패");
    console.log(request.status);
    console.log(request.responseText);
    console.log(error);
}

// function isEmpty(value) {
//     return value == null || value == undefined || value == "";
// }