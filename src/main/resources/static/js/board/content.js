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

// 댓글 리스트
const replyUl = document.querySelector("section ul");

// 댓글 리스트 더 불러오기 위한 변수
const showMoreReplyBox = document.querySelector(".show-more-reply-box");
let page = 1;
let index = 8;
let totalIndex = 0;

// 버튼
const modifyAndDeleteButtons = document.querySelectorAll(".user-board-menu button");
const replySubmitButton = document.querySelector(".reply-submit-button");


// 페이징 처리를 위한 Controller에서 받아온 값
const typeSpan = document.querySelector(".type-span");
const boardCodeSpan = document.querySelector(".board-code-span");

let signinFlag = false;

// 게시판 구분짓는 flag
let adminFlag = false;
let managerFlag = false;
let userFlag = false;

let userMenuFlag = false;

// 페이징 처리
let type = typeSpan.textContent;
let boardCode = parseInt(boardCodeSpan.textContent);

// 임시로 사용할 userCode
let userCode = 0;

// 게시글 데이터를 담을 객체
let data = {};

$(userDtlMenu).fadeOut(0);

enterContent(load());
enterReply();

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
    //             location.replace(`content?type=${type}&number=${boardCode}`);
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

// 게시글 수정버튼
modifyAndDeleteButtons[0].onclick = () => {
    saveContent();
    location.href = `/${type}?type=modify&number=${boardCode}`;
}

// 게시글 삭제 버튼
modifyAndDeleteButtons[1].onclick = () => {
    let result = confirm("정말 삭제 하시겠습니까?");

    if(result) {
        deleteBoard(boardCode);
    }
}

// input focus 중 Enter 입력시 댓글 추가
replyInput.onkeypress = () => {
    if(window.event.keyCode == 13) {
        replySubmitButton.click();
    }
}

// 댓글 작성 버튼 -> HashMap 이용해서 board_code를 key로 잡고 댓글을 value로 잡아서
// 계속해서 db 접근을 막을 수 있지만 현재 라이브 서버가 아니라서 서버가 재실행 될 때마다
// HashMap의 요소들이 초기화 되어서 우선은 db 접근
replySubmitButton.onclick = () => {
    addReply();
}

// 댓글 더보기 클릭
showMoreReplyBox.onclick = () => {
    if(page < totalIndex) {
        page++;
        enterReply();

    }else {
        return;
    }
}

function setUserInfo(obj) {
    if(obj != null) {
        if(obj.roles.includes("ADMIN")) {
            adminFlag = true;
        }else if(obj.roles.includes("MANAGER")) {
            managerFlag = true;
        }else {
            userFlag = true;
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

function load() {

    loadUser();

    if(signinFlag) {
        userMenu.style.display = "block";
        loginBox.style.visibility = "hidden";
    }else {
        userMenu.style.display = "none";
        loginBox.style.visibility = "visible";
    }

    data = null;
    replyUl.innerHTML = "";

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

// 수정, 삭제 버튼 보여주기
function showBtnMenu() {
    const userBoardMenu = document.querySelector(".user-board-menu");

    userBoardMenu.classList.remove("visible");
}

// 작성자와 로그인한 유저가 같은지 확인
function checkUser(writerCode) {
    return (userCode == writerCode) || (adminFlag || managerFlag);
}

// 게시글 내용 set
function enterContent(data) {
    titleText.innerHTML = `${data.boardTitle}`;
    contentText.innerHTML = `${data.boardContent}`;
    boardInfoBox[0].innerHTML += `${data.name}`;
    boardInfoBox[1].innerHTML += `${data.views}회`;
    boardInfoBox[2].innerHTML += `${data.time}`;

    if(checkUser(data.userCode)) {
        showBtnMenu();
    }
}

// 수정하기 클릭시 실행
function saveContent() {
    $.ajax({
        type: "post",
        url: `/api/v1/board/save/map/${data.boardCode}`,
        contentType: "application/json",
        data: JSON.stringify({
            boardTitle: data.boardTitle,
            boardContent: data.boardContent,
            boardType: type,
            importanceFlag: data.importanceFlag
        }),
        dataType: "json",
        success: (response) => {
            
        },
        error: errorMessage
    });
}

function deleteBoard(boardCode) {
    $.ajax({
        type: "delete",
        url: `/api/v1/board/${boardCode}`,
        dataType: "json",
        async: false,
        success: (response) => {
            if(response.data) {
                alert("삭제 성공");
                history.back();
            }
        },
        error: errorMessage
    });
}

// 댓글 불러오기
function getReplyList() {
    let replyList = null;

    $.ajax({
        type: "get",
        url: `/api/v1/content/reply/${boardCode}?page=${page}&index=${index}`,
        dataType: "json",
        async: false,
        success: (response) => {
            if(response.data.length != 0) {
                replyList = response.data;

                let totalCount = response.data[0].totalReplyCount;

                totalIndex = totalCount % 8 == 0 ? totalCount / 8 : Math.floor(totalCount / 8) + 1;
            }
        },
        error: errorMessage
    });

    return replyList;
}

// 댓글 set
function enterReply() {
    let replyList = getReplyList();

    if(replyList != null) {
        for(replyObj of replyList) {
            let result = (replyObj.userCode == userCode) || (adminFlag || managerFlag);
            let replyCode = replyObj.replyCode;

            replyUl.innerHTML += `
            <li>
                <div class="reply-writer-box">
                    <div>
                        <span class="writer-span">${replyObj.name}</span>
                        <span>${replyObj.time}</span>
                    </div>
                    <div class="repley-modify-delete-span-box ${result ? '' : 'visible'}">
                        <span class="modify-reply-span" onclick="modifyReply(${replyCode})">수정</span>
                        <span class="delete-reply-span" onclick="deleteReply(${replyCode})">삭제</span>
                    </div>
                </div>
                <div class="reply-info-box">
                    <span class="reply-${replyCode}">${replyObj.reply}</span>
                    <input type="text" class="modify-reply-input-${replyCode} modify-input visible">
                    <button type="button" class="modify-reply-button-${replyCode} modify-button visible" onclick="updateReply(${replyCode})">수정하기</button>
                </div>
            </li>
            `
        }
    
        setTotalIndex(totalIndex);
        toggleVisible();
    }

    
}

// 현재페이지가 종합페이지보다 작은지 확인
function checkPage() {
    return page < totalIndex;
}

function toggleVisible() {
    if(checkPage()) {
        showMoreReplyBox.classList.remove("visible");
    }else {
        showMoreReplyBox.classList.add("visible");
    }
}

function setTotalIndex(data) {
    totalIndex = data;
}

// 댓글 작성
function addReply() {
    $.ajax({
        type: "post",
        url: "/api/v1/content/reply",
        contentType: "application/json",
        data: JSON.stringify({
            boardCode: boardCode,
            reply: replyInput.value,
            userCode: userCode
        }),
        dataType: "json",
        success: (response) => {
            if(response.data) {
                reLoadPage();
            }
        },
        error: errorMessage
    });
}

function modifyReply(replyCode) {
    const replySpan = document.querySelector(`.reply-${replyCode}`);
    const modifyButton = document.querySelector(`.modify-reply-button-${replyCode}`);
    const modifyReplyInput = document.querySelector(`.modify-reply-input-${replyCode}`);

    toggleVisibleReplyAndInput(replySpan, modifyReplyInput, modifyButton);

    modifyReplyInput.value = replySpan.textContent;
}

function toggleVisibleReplyAndInput(reply, input, button) {
    reply.classList.toggle("visible");
    button.classList.toggle("visible");
    input.classList.toggle("visible");

}

// function removeVisible(dom) {
//     dom.classList.remove("visible");
// }

// function addVisible(dom) {
//     dom.classList.add("visible");
// }

function updateReply(replyCode) {
    let reply = getReply(replyCode);

    $.ajax({
        type: "put",
        url: `/api/v1/content/reply/${replyCode}`,
        contentType: "application/json",
        data: JSON.stringify({
            reply: reply
        }),
        dataType: "json",
        success: (response) => {
            if(response.data) {
                location.href = `/content?type=${type}&number=${boardCode}`
            }
        },
        error: errorMessage
    });
}

function getReply(replyCode) {
    return document.querySelector(`.modify-reply-input-${replyCode}`).value;
}

function deleteReply(replyCode) {
    let checkFlag = checkConfirm();

    if(checkFlag) {
        $.ajax({
            type: "delete",
            url: `/api/v1/content/reply/${replyCode}`,
            dataType: "json",
            success: (response) => {
                if(response.data) {
                    reLoadPage();
                }
            },
            error: errorMessage
        });
    }
}

function checkConfirm() {
    return confirm("정말 삭제 하시겠습니까?");
}

function reLoadPage() {
    location.replace(`content?type=${type}&number=${boardCode}`);
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