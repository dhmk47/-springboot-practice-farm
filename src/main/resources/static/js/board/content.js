// header
const headerNavItems = document.querySelectorAll(".header-nav-box li");
const userMenu = document.querySelector(".user-menu");
const userMenuBtn = document.querySelector(".fa-caret-down");
const userDtlMenu = document.querySelector(".user-dtl-menu");
const userDtlMenuItems = document.querySelectorAll(".user-dtl-menu span");

//main
const loginBox = document.querySelector(".login-box");
const loginInputItems = document.querySelectorAll(".login-box input");
const loginBoxButtons = document.querySelectorAll(".signin-signup-button-box button");

// 제목, 내용 박스, 게시글 정보
const titleText = document.querySelector(".title-text");
const contentText = document.querySelector(".content-text");
const boardInfoBox = document.querySelectorAll(".board-info-box span");

// 댓글
const replyInput = document.querySelector(".reply-input");

// 버튼
const modifyAndDeleteButtons = document.querySelectorAll(".user-board-menu button");
const replySubmitButton = document.querySelector(".reply-submit-button");


// 페이징 처리를 위한 Controller에서 받아온 값
const typeSpan = document.querySelector(".type-span");
const boardCodeSpan = document.querySelector(".board-code-span");

let signinFlag = false;

// 게시판 구분짓는 flag
let adminFlag = false;

// 페이징 처리
let type = typeSpan.textContent;
let boardCode = parseInt(boardCodeSpan.textContent);

// 임시로 사용할 userCode
let userCode = 2;

$(userDtlMenu).fadeOut(0);

enterContent(load());

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

// 내 정보 보기
userDtlMenuItems[0].onclick = () => {

}

// 로그아웃
userDtlMenuItems[1].onclick = () => {
    alert("로그아웃");
    signinFlag = false;
    adminFlag = false;
    location.replace("/index");
}

// 회원탈퇴
userDtlMenuItems[2].onclick = () => {
    
}

loginBoxButtons[0].onclick = () => {
    for(let i = 0; i < loginInputItems.length; i++){
        if(isEmpty(loginInputItems[i].value)){
            alert((i == 0 ? "아이디를"
            : "비밀번호를") + " 입력해 주세요.");
            return;
        }
    }

    $.ajax({
        type: "post",
        url: "/api/v1/user/signin",
        contentType: "application/json",
        data: JSON.stringify({
            "username": loginInputItems[0].value,
            "password": loginInputItems[1].value
        }),
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                alert("로그인 성공");
                signinFlag = true;
                if(response.data.roles.includes("ADMIN")){
                    adminFlag = true;
                }else {
                    userCode = response.data.userCode;
                }
                // 나중에 로그인 되었으면 세션에 저장해서 location으로 index
                load();
            }else {
                alert("회원정보가 옳바르지 않습니다.");
            }
        },
        error: errorMessage
    });
}

loginBoxButtons[1].onclick = () => {
    location.href = "/signup";
}

// 비밀번호 입력창에서 Enter키 누르면 로그인 버튼 클릭
loginInputItems[1].onkeypress = () => {
    if(window.event.keyCode == 13) {
        loginBoxButtons[0].click();
    }
}

// 게시글 수정버튼
modifyAndDeleteButtons[0].onclick = () => {

}

// 게시글 삭제 버튼
modifyAndDeleteButtons[1].onclick = () => {

}

// 댓글 작성 버튼 -> HashMap 이용해서 board_code를 key로 잡고 댓글을 value로 잡아서
// 계속해서 db 접근을 막을 수 있지만 현재 라이브 서버가 아니라서 서버가 재실행 될 때마다
// HashMap의 요소들이 초기화 되어서 우선은 db 접근
replySubmitButton.onclick = () => {
    addReply();
}



function load() {
    let data = null;

    $.ajax({
        type: "get",
        url: `/api/v1/board/${type}/${boardCode}`,
        async: false,
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                data = response.data;

            }
        },
        error: errorMessage
    });

    return data;
}

function enterContent(data) {
    titleText.innerHTML = `${data.boardTitle}`;
    contentText.innerHTML = `${data.boardContent}`;
    boardInfoBox[0].innerHTML += `${data.name}`;
    boardInfoBox[1].innerHTML += `${data.views}회`;
}

function addReply() {
    $.ajax({
        type: "post",
        url: "/api/v1/board/reply",
        contentType: "application/json",
        data: JSON.stringify({
            boardCode: boardCode,
            reply: replyInput.value,
            userCode: userCode
        }),
        dataType: "json",
        success: (response) => {
            if(response.data) {
                location.replace(`content?type=${type}&number=${boardCode}`);
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

function isEmpty(value) {
    return value == null || value == undefined || value == "";
}