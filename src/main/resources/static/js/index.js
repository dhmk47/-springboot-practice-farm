// header
const headerNavItems = document.querySelectorAll(".header-nav-box li");
const productDtlMenu = document.querySelector(".farm-product-dtl-menu");
const boardDtlMenu = document.querySelector(".board-dtl-menu");

const productAdminmenu = document.querySelectorAll(".farm-product-dtl-menu span");
//main
const loginInputItems = document.querySelectorAll(".login-box input");
const loginBoxButtons = document.querySelectorAll(".signin-signup-button-box button");

const searchInput = document.querySelector(".search-farm-product input");
const autoSearchBox = document.querySelector(".auto-search");

let signinFlag = false;

// 게시판 구분짓는 flag
let productMenuFlag = false;
let boardMenuFlag = false;

let adminFlag = false;

// 구매하기인지 판매하기인지 구분짓는 flag
let purchaseFlag = false;

// 구매, 판매 박스
const dtlMenu = document.querySelector(".purchase-product-menu");
const dtlProductMenu = document.querySelector(".show-available-product-box");
let dtlMenuFlag = false;
let dtlProductFlag = false;

let array = ["사과", "오렌지", "수박", "딸기", "사과탕후루", "사과탕"];

$(dtlMenu).fadeOut(0);
$(dtlProductMenu).fadeOut(0);

load();

let timeout = true;
let delay = 200;

searchInput.onkeyup = () => {
    if(timeout) {
        clearTimeout(timeout);
    }
    timeout = setTimeout(function() {
        search();
    }, delay);
}

searchInput.onfocus = () => {
    autoSearchBox.style.display = "block";
    search();
}

document.querySelector("main").onclick = (event) => {
    console.log(event.target);
    alert(event.target);
    if(!event.target.includes("li")){
        autoSearchBox.style.display = "none";
    }
}

function search() {
    let text = searchInput.value;
    document.querySelector(".auto-search-list").innerHTML = "";
    for(let i = 0; i < array.length; i++){
        if(searchInput.value == ""){
            document.querySelector(".auto-search-list").innerHTML += 
            `<li onclick="add('${array[i]}')">${array[i]}</li>`;
        }else if(array[i].indexOf(text) > -1){
            document.querySelector(".auto-search-list").innerHTML += 
            `<li onclick="add('${array[i]}')">${array[i]}</li>`;
        }
    }
}

function add(value) {
    // location.href = "/index";
}

document.querySelector("header h1").onclick = () => {
    location.href = "/index";
}

headerNavItems[0].onclick = () => {
    if(productMenuFlag){
        $(productDtlMenu).fadeOut(200);
        productMenuFlag = false;
    }else {
        if(boardMenuFlag){
            headerNavItems[1].click();
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

document.querySelector("main").onmouseover = () => {
    if(productMenuFlag){
        $(productDtlMenu).fadeOut(200);
        productMenuFlag = false;
    }else if(boardMenuFlag){
        $(boardDtlMenu).fadeOut(200);
        boardMenuFlag = false;
    }
}

productAdminmenu[1].onclick = () => {
    toggleDtlBox();
    purchaseFlag = true;
    document.querySelector(".purchase-product-menu h1").innerHTML = "농산물 구매하기";
    document.querySelector(".purchase-box input").placeholder = "구매할 농산물을 입력하세요.";
    document.querySelector(".purchase-box button").innerHTML = "구매하기";
    document.querySelector(".show-product-button").innerHTML = "구매 가능한 농산물 보기";
}

productAdminmenu[2].onclick = () => {
    toggleDtlBox();
    purchaseFlag = false;
    document.querySelector(".purchase-product-menu h1").innerHTML = "농산물 판매하기";
    document.querySelector(".purchase-box input").placeholder = "판매할 농산물을 입력하세요.";
    document.querySelector(".purchase-box button").innerHTML = "판매하기";
    document.querySelector(".show-product-button").innerHTML = "판매 가능한 농산물 보기";
}

productAdminmenu[3].onclick = () => {

}

document.querySelector(".xmark1").onclick = () => {
    toggleDtlBox();
    if(dtlProductFlag){
        $(dtlProductMenu).fadeOut(200);
    }
}

document.querySelector(".xmark2").onclick = () => {
    toggleProductDtlBox();
}

document.querySelector(".show-product-button").onclick = () => {
    $(dtlProductMenu).fadeIn(20);
    dtlProductFlag = true;
    if(purchaseFlag) {
        
    }else {
    }
}

loginBoxButtons[0].onclick = () => {
    for(let i = 0; i < loginInputItems.length; i++){
        if(isEmpty(loginInputItems[i].value)){
            alert((i == 0 ? "아이디를"
            : "비밀번호를") + " 입력해 주세요.");
            return;
        }
    }
}

loginBoxButtons[1].onclick = () => {
    location.href = "/signup";
}

function load(){
    if(signinFlag) {
        loginBoxButtons[0].innerHTML = "로그아웃";
        loginBoxButtons[1].innerHTML = "내정보 보기";
    }else {
        loginBoxButtons[0].innerHTML = "로그인";
        loginBoxButtons[1].innerHTML = "회원가입";
    }
    if(adminFlag){
        productAdminmenu[1].innerHTML = "농산물 추가";
        productAdminmenu[2].innerHTML = "농산물 제거";
        productAdminmenu[3].innerHTML = "농산물 수정";
    }else {
        productAdminmenu[1].innerHTML = "농산물 구매";
        productAdminmenu[2].innerHTML = "농산물 판매";
        productAdminmenu[3].innerHTML = "농산물 재배";
    }
}

function toggleDtlBox() {
    if(dtlMenuFlag){
        $(dtlMenu).fadeOut(200);
        dtlMenuFlag = false;
    }else {
        $(dtlMenu).fadeIn(200);
        dtlMenuFlag = true;
    }
}

function toggleProductDtlBox() {
    if(dtlProductFlag){
        $(dtlProductMenu).fadeOut(200);
        dtlProductFlag = false;
    }else {
        $(dtlProductMenu).fadeIn(200);
        dtlProductFlag = true;
    }
}

function isEmpty(value) {
    return value == null || value == undefined || value == "";
}