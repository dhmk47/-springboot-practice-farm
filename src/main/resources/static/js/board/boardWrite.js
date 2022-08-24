// header
const headerNavItems = document.querySelectorAll(".header-nav-box li");
const userMenu = document.querySelector(".user-menu");
const userMenuBtn = document.querySelector(".fa-caret-down");
const userDtlMenu = document.querySelector(".user-dtl-menu");
const userDtlMenuItems = document.querySelectorAll(".user-dtl-menu span");

//main
// const imageUploadInput = document.querySelector(".image-upload-form");
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

// imageUploadInput.onchange = (e) => {
//     let reader = new FileReader();

//     reader.onload = (e) => {
//         let img = document.createElement("img");
//         img.setAttribute("src", e.target.result);
//         // document.querySelector(".image-preview").appendChild(img);
//         document.querySelector(".content-input textarea").appendChild(img);
//     }

//     reader.readAsDataURL(e.target.files[0]);

//     // let newImage = document.createElement("img");
//     // newImage.setAttribute("class", "img");
//     // newImage.src = URL.createObjectURL(file);
//     // contentInput.appendChild(newImage);

// }

submitButton.onclick = () => {
    // let title = titleInput.value;
    // let content = contentInput.value;

    let importanceFlag = importanceCheckBox.checked;

    let formData = new FormData(document.querySelector("form"));

    formData.append("importanceFlag", importanceFlag);
    
    formData.forEach((v, k) => {
        console.log("key: " + k);
        console.log("value: " + v);
    });

    if(type != "modify") {  // 게시글 작성
        
        formData.append("userCode", userCode);
        formData.append("boardType", boardTypeNumber);
        createBoard(formData);
    }else {                 // 게시글 수정
        formData.append("boardCode", boardCode);
        updateBoard(formData);
    }
}

function updateBoard(formData) {
    $.ajax({
        type: "put",
        url: `/api/v1/board/${boardCode}`,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        data: formData,
        dataType: "json",
        success: (response) => {
            if(response.data) {
                history.back();
            }
        }
    });
}

function createBoard(formData) {
    $.ajax({
        type: "post",
        url: "/api/v1/board/new",
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        data: formData,
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                location.replace(`/content?type=${boardType}&number=${response.data}`);
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