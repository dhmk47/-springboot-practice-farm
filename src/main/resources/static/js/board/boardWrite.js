// header
const headerNavItems = document.querySelectorAll(".header-nav-box li");
const userMenu = document.querySelector(".user-menu");
const userMenuBtn = document.querySelector(".fa-caret-down");
const userDtlMenu = document.querySelector(".user-dtl-menu");
const userDtlMenuItems = document.querySelectorAll(".user-dtl-menu span");

//main
const titleInput = document.querySelector(".title-input input");
const contentInput = document.querySelector(".content-input textarea");

const submitButton = document.querySelector(".button-box button");

const importanceCheckBox = document.querySelector("#importance");

// 게시판 구분짓는 flag
let userMenuFlag = false;

let userFlag
let managerFlag
let adminFlag = false;

let userCode = 0;
let name = null;

let data = {};

// 게시판 구분
let boardType = document.querySelector(".board-type-span").textContent; // 어느 게시판인지
let type = document.querySelector(".type-span").textContent;            // 작성인지 수정인지
let boardCode = document.querySelector(".board-code-span").textContent;
let boardTypeNumber = boardType == "notice" ? 1 : boardType == "free" ? 2 : 3;

$(userDtlMenu).fadeOut(0);

load();

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
    location.href = "/board?type=QnA&page=1";
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

submitButton.onclick = () => {
    let title = titleInput.value;
    let content = contentInput.value;

    let importanceFlag = importanceCheckBox.checked;

    if(type != "modify") {  // 게시글 작성
        createBoard(title, content, importanceFlag);
    }else {                 // 게시글 수정
        updateBoard(title, content, importanceFlag);
    }
}

function updateBoard(title, content, importanceFlag) {
    $.ajax({
        type: "put",
        url: `/api/v1/board/${boardCode}`,
        contentType: "application/json",
        data: JSON.stringify({
            boardCode: boardCode,
            boardTitle: title,
            boardContent: content,
            importanceFlag: importanceFlag
        }),
        dataType: "json",
        success: (response) => {
            if(response.data) {
                history.back();
            }
        }
    });
}

function createBoard(title, content, importanceFlag) {
    $.ajax({
        type: "post",
        url: "/api/v1/board/new",
        data: JSON.stringify({
            boardTitle: title,
            boardContent: content,
            "userCode": userCode,
            boardType: boardTypeNumber,
            "importanceFlag": importanceFlag
        }),
        contentType: "application/json",
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                location.replace(`/content?type=${boardType}&number=${boardCode}`);
            }else {
                alert("게시글 작성 실패!");
            }
        },
        error: errorMessage
    });
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

        userCode = obj.userCode;
        name = obj.name;
        signinFlag = true;

        userMenu.style.display = "flex";

        document.querySelector(".username-box").textContent = `${name}님 환영합니다.`;
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

function toggleImportanceBox() {
    if(boardTypeNumber == 1) {
        document.querySelector(".button-box span").classList.toggle("visible");
        document.querySelector(".importance-label").classList.toggle("visible");
    }
}

function load() {
    loadUser();
    if(type == "modify") {
        loadBoard();
        setContent();
    }
    toggleImportanceBox();
}

function loadBoard() {
    $.ajax({
        type: "get",
        url: `/api/v1/board/load/map/${parseInt(boardCode)}`,
        dataType: "json",
        async: false,
        success: (response) => {
            if(response.data != null) {
                data = response.data;
            }
        },
        error: errorMessage
    });
}

function setContent() {
    titleInput.value = data.boardTitle;
    contentInput.value = data.boardContent;

    if(data.importanceFlag) {
        importanceCheckBox.setAttribute('checked', true);
    }

    submitButton.innerHTML = "수정하기";
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