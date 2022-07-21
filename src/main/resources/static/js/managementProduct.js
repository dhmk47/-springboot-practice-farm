// header
const headerNavItems = document.querySelectorAll(".header-nav-box li");
const userMenu = document.querySelector(".user-menu");

const userMenuBtn = document.querySelector(".fa-caret-down");

const productDtlMenu = document.querySelector(".farm-product-dtl-menu");
const boardDtlMenu = document.querySelector(".board-dtl-menu");

const productAdminmenu = document.querySelectorAll(".farm-product-dtl-menu span");
const userDtlMenu = document.querySelector(".user-dtl-menu");
const userDtlMenuItems = document.querySelectorAll(".user-dtl-menu span");

// main
const customButtons = document.querySelectorAll(".custom-button-box button");
const inputItems = document.querySelectorAll("main input");
const checkButton = document.querySelector(".r-flex button");
const changeProductInfoBoxes = document.querySelectorAll(".show-product-info-box");

// 게시판 구분 짓는 플래그
let userMenuFlag = false;
let productMenuFlag = false;
let boardMenuFlag = false;


// 삭제버튼이면 input이 숨겨지기 때문에 flag 만들어서 if문 만들기
let removeFlag = false;

// 수정버튼이면 div박스 보여야 하기 때문에 구분 짓는 flag
let showDivFlag = false;

$(userDtlMenu).fadeOut(0);



// 로고 클릭시 홈페이지 이동
document.querySelector("header h1").onclick = () => {
    location.href = "/index";
}

// 헤더 매뉴
headerNavItems[0].onclick = () => {
    if(productMenuFlag){
        $(productDtlMenu).fadeOut(200);
        productMenuFlag = false;
    }else {
        if(boardMenuFlag){
            headerNavItems[1].click();
        }else if(userMenuFlag) {
            userMenuBtn.click();
        }
        $(productDtlMenu).fadeIn(200);
        productDtlMenu.style.display = "flex";
        productMenuFlag = true;
    }
    
}

headerNavItems[1].onclick = () => {
    if(boardMenuFlag){
        $(boardDtlMenu).fadeOut(200);
        boardMenuFlag = false;
    }else {
        if(productMenuFlag){
            headerNavItems[0].click();
        }else if(userMenuFlag) {
            userMenuBtn.click();
        }
        $(boardDtlMenu).fadeIn(200);
        boardDtlMenu.style.display = "flex";
        boardMenuFlag = true;
    }
}

headerNavItems[2].onclick = () => {
    if(signinFlag) {

    }else {
        alert("먼저 로그인을 진행해 주세요.")
    }
}

userMenuBtn.onclick = () => {
    if(userMenuFlag){
        $(userDtlMenu).fadeOut(200);
        userMenuFlag = false;
    }else {
        if(boardMenuFlag) {
            headerNavItems[1].click();
        }else if(productMenuFlag){
            headerNavItems[0].click();
        }
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
    if(productMenuFlag){
        $(productDtlMenu).fadeOut(200);
        productMenuFlag = false;
    }else if(boardMenuFlag){
        $(boardDtlMenu).fadeOut(200);
        boardMenuFlag = false;
    }else if(userMenuFlag) {
        $(userDtlMenu).fadeOut(200);
        userMenuFlag = false;
    }
}

// 헤더 메뉴 안의 기능
productAdminmenu[0].onclick = () => {   // 농산물 품목 전체 보기
    
}

productAdminmenu[1].onclick = () => {   // 농산물 관리
    
}

// main

customButtons[0].onclick = () => {
    if(removeFlag) {
        inputItems[1].style.display = "inline-block";
        inputItems[2].style.display = "inline-block";
        inputItems[3].style.display = "inline-block";
        removeFlag = false;
    }
    if(showDivFlag){
        hideProductDivBox();
        showDivFlag = false;
    }
    inputItems[0].placeholder = "추가할 농선물 이름";
    inputItems[1].placeholder = "추가할 농선물 가격";
    inputItems[2].placeholder = "추가할 농선물 계절";
    inputItems[3].placeholder = "추가할 농선물 재배 기간";

    
}

customButtons[1].onclick = () => {
    if(removeFlag) {
        inputItems[1].style.display = "inline-block";
        inputItems[2].style.display = "inline-block";
        inputItems[3].style.display = "inline-block";
        removeFlag = false;
    }
    if(!showDivFlag) {
        hideProductDivBox();
        showDivFlag = true;
    }
    
    inputItems[0].placeholder = "수정할 농선물 이름";
    inputItems[1].placeholder = "수정할 농선물 가격";
    inputItems[2].placeholder = "수정할 농선물 계절";
    inputItems[3].placeholder = "수정할 농선물 재배 기간";
}

customButtons[2].onclick = () => {
    if(showDivFlag){
        hideProductDivBox();
        showDivFlag = false;
    }
    removeFlag = true;
    inputItems[0].placeholder = "삭제할 농선물 이름";
    inputItems[1].style.display = "none";
    inputItems[2].style.display = "none";
    inputItems[3].style.display = "none";
}


checkButton


function hideProductDivBox() {
    changeProductInfoBoxes.forEach(i => {
        i.classList.toggle("hideAndShowProductDivBox");
    })
}

function errorMessage(request, status, error) {
    alert("요청실패");
    console.log(request.status);
    console.log(request.responseText);
    console.log(error);
}