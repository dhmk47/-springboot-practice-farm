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

const boardList = document.querySelector(".board-content-list");

let signinFlag = false;

// 게시판 구분짓는 flag
let userMenuFlag = false;

let adminFlag = false;

let userCode = 0;

$(userDtlMenu).fadeOut(0);

load();

// 로고 클릭시 홈페이지 이동
document.querySelector("header h1").onclick = () => {
    location.href = "/index";
}

// 헤더 매뉴 -공지사항 -자유게시판 -QnA
headerNavItems[0].onclick = () => {
    location.href = "/notice";
}

headerNavItems[1].onclick = () => {
    location.href = "/board";
}

headerNavItems[2].onclick = () => {
    location.href = "/QnA";
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

document.querySelector(".write-button button").onclick = () => {
    location.href = "/notice/write";
}


function load() {
    if(signinFlag) {
        userMenu.style.display = "block";
        loginBox.style.visibility = "hidden";
    }else {
        userMenu.style.display = "none";
        loginBox.style.visibility = "visible";
    }

    boardLoad();
}

function boardLoad() {
    $.ajax({
        type: "get",
        url: "/api/v1/board/notice/all",
        dataType: "json",
        success: (response) => {
            if(response.data.length != 0) {


                for(board of response.data) {
                    boardList.innerHTML +=
                    `<ul class="board-content-list">
                        <li>
                            <span class="board-code">${board.boardCode}</span>
                            <span class="board-type">${board.boardType == 1 ? "[공지사항]" : board.boardType == 2 ? "[자유게시판]" : "[QnA]"}</span>
                            <span class="board-title">${board.boardTitle}</span>
                            <span class="writer-span">${board.userCode}</span>
                            <span class="date-span">2${board.updateDate}</span>
                        </li>
                    </ul>`;
                }
            }else {
                alert("게시글 불러오기 실패");
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