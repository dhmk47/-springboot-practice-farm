// header
const headerNavItems = document.querySelectorAll(".header-nav-box li");
const userMenu = document.querySelector(".user-menu");
const userMenuBtn = document.querySelector(".fa-caret-down");
const userDtlMenu = document.querySelector(".user-dtl-menu");
const userDtlMenuItems = document.querySelectorAll(".user-dtl-menu span");
const usernameBox = document.querySelector(".username-box");

//main
const loginBox = document.querySelector(".login-box");
const loginInputItems = document.querySelectorAll(".login-box input");
const loginBoxButtons = document.querySelectorAll(".signin-signup-button-box button");

const boardList = document.querySelector(".board-content-list");
const pageButtonBox = document.querySelectorAll(".page-button-box");

const preButton = document.querySelector(".pre-button");
const nextButton = document.querySelector(".next-button");

// 페이징 처리를 위한 Controller에서 받아온 값
const typeSpan = document.querySelector(".type-span");
const pageSpan = document.querySelector(".page-span");

let pageList = document.querySelectorAll(".page-list div");


// 게시판 구분짓는 flag
let userMenuFlag = false;

// 유저 정보
let userCode = 0;
let name = null;
let userFlag = false;
let managerflag = false;
let adminFlag = false;


let signinFlag = false;

// 페이징 처리
let type = typeSpan.textContent;
let page = parseInt(pageSpan.textContent);
let totalPage = 1;
let totalCount = 0;

$(userDtlMenu).fadeOut(0);

load();
setPageButton();


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
            return false;
        }
    }

    // $.ajax({
    //     type: "post",
    //     url: "/api/v1/user/signin",
    //     contentType: "application/json",
    //     data: JSON.stringify({
    //         "username": loginInputItems[0].value,
    //         "password": loginInputItems[1].value
    //     }),
    //     dataType: "json",
    //     success: (response) => {
    //         if(response.data != null) {
    //             alert("로그인 성공");
    //             signinFlag = true;
    //             if(response.data.roles.includes("ADMIN")){
    //                 adminFlag = true;
    //             }else {
    //                 userCode = response.data.userCode;
    //             }
    //             // 나중에 로그인 되었으면 세션에 저장해서 location으로 index
    //             load();
    //         }else {
    //             alert("회원정보가 옳바르지 않습니다.");
    //         }
    //     },
    //     error: errorMessage
    // });
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

// 이전 버튼
preButton.onclick = () => {
    let result = page % 5;
    let index = page;

    if(result == 1) {
        index -= 1;
    }else if(result == 2) {
        index -= 2;
    }else if(result == 3) {
        index -= 3;
    }else if(result == 4) {
        index -= 4;
    }else {
        index -= 5;
    }

    if(index > 0) {
        page = index;
        setPage(page);
        // load();
        // setPageButton("pre");
    }
}

document.querySelector(".select-page-button").onclick = () => {
    document.querySelector(".select-page-box").classList.toggle("visible");
}

document.querySelector(".select-page-box button").onclick = () => {
    page = parseInt(document.querySelector(".select-page-input").value);

    load();
    setPage(page);
    // setPageButton("setPage");
}

// 다음 버튼
nextButton.onclick = () => {

    let index = checkNextPage();

    if(totalPage > index - 1) {
        page = index;
        setPage(page);
        // load();
        // setPageButton("next");
    }
}

document.querySelector(".write-button button").onclick = () => {
    location.href = `/${type}/write`;
}

function setUserInfo(obj) {
    if(obj != null) {
        if(obj.roles.includes("USER")) {
            userFlag = true;
        }else if(obj.roles.includes("MANAGER")) {
            managerFlag = true;
        }else {
            adminFlag = true;
        }

        userCode = obj.usercode;
        name = obj.name;
        signinFlag = true;

        usernameBox.innerHTML = `${name}님 환영합니다.`
    }
}

function loadUser() {
    $.ajax({
        type: "get",
        url: "/api/v1/auth/user/principal/load",
        async: false,
        dataType: "json",
        success: (response) => {
            setUserInfo(response.data);
        },
        error: errorMessage
    });
}


function load() {

    loadUser();

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
        url: `/api/v1/board/${type}/list`,
        async: false,
        data: {
            page: page,
            totalCount: 8
        },
        dataType: "json",
        success: (response) => {
            if(response.data.length != 0) {

                boardList.innerHTML = "";

                totalCount = response.data[0].totalCount;

                totalPage = totalCount % 8 == 0 ? totalCount / 8 : Math.floor(totalCount / 8) + 1;

                document.querySelector(".select-page-box div").innerHTML = 
                `<input class="select-page-input" type="text"> / ${totalPage}`;

                for(board of response.data) {
                    boardList.innerHTML +=
                    `
                    <li>
                        <span class="board-code">${board.boardCode}</span>
                        <span class="board-type">${board.boardType == 1 ? "[공지사항]" : board.boardType == 2 ? "[자유게시판]" : "[QnA]"}</span>
                        <span class="board-title" onclick="showContent(${board.boardType}, ${board.boardCode})">
                        ${board.boardTitle}</span>
                        <span class="board-reply-count">${board.totalReply != 0 ? "["+board.totalReply+"]" : ""}</span>
                        <span class="writer-span">${board.name}</span>
                        <span class="views-span">${board.views}</span>
                        <span class="date-span">${board.time}</span>
                    </li>
                    `;
                }
            }else {
                alert("게시글 불러오기 실패");
            }
        },
        error: errorMessage
    });
}

function showContent(boardType, boardCode) {
    location.href = `/content?type=${boardType == 1 ? "notice" : boardType == 2 ? "free" : "question"}&number=${boardCode}`;
}

function setPage(index) {
    // pageList = document.querySelectorAll(".page-list div");
    page = index;
    
    // pageList.forEach(page => {
    //     page.classList.remove("set-color");
    //     page.style.backgroundColor = "";
    // });

    // obj.style.backgroundColor = "gray";

    location.href = `/board?type=${type}&page=${page}`;
    // load();
}

function setColor(obj) {
    obj.classList.toggle("set-color");
}

function setPageButton() {
    pageButtonBox[0].innerHTML = "";
    
    let pageIndex = 0;
    for(let i = 0; i < totalPage; i++) {

        if(i < 5 && pageIndex + 1 < totalPage + 1) {
            pageIndex = page - ((page - 1) % 5) + i;
        }else {
            break;
        }

        // if(type == "pre" && i < 5) {
        //     pageIndex = page - 4 + i;
        // }else if(i < 5 && pageIndex + 1 < totalPage + 1) {
        //     pageIndex = page - ((page - 1) % 5) + i;

        // }else{
        //     break;
        // }
        
        pageButtonBox[0].innerHTML += `<div class="page${pageIndex}" onmouseover="setColor(this)" onmouseout="setColor(this)" onclick="setPage(${pageIndex}, this)">${pageIndex}</div>`;
    }

    // if(type == "pre") {
    //     pageButtonBox[0].querySelectorAll("div")[4].click();

    // }else if(type == "setPage") {
    //     pageButtonBox[0].querySelectorAll("div")[page % 5 == 0 ? 4 : page % 5 - 1].click();
    // }else {
    //     pageButtonBox[0].querySelectorAll("div")[0].click();

    // }

    document.querySelector(`.page${page}`).style.backgroundColor = "gray";

    if(page < 6) {
        preButton.classList.add("set-disable");
    }else {
        preButton.classList.remove("set-disable");
    }

    if(checkNextPage() < totalPage + 1) {
        nextButton.classList.remove("set-disable");
    }else {
        nextButton.classList.add("set-disable");
    }
    
}

function checkNextPage() {
    let result = page % 5;
    let index = page;

    if(result == 1) {
        index += 5;
    }else if(result == 2) {
        index += 4;
    }else if(result == 3) {
        index += 3;
    }else if(result == 4) {
        index += 2;
    }else {
        index += 1;
    }
    
    return index;
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