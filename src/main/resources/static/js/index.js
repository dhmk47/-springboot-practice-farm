// header
const headerNavItems = document.querySelectorAll(".header-nav-box li");
const userMenu = document.querySelector(".user-menu");
const userMenuBtn = document.querySelector(".fa-caret-down");
const productDtlMenu = document.querySelector(".farm-product-dtl-menu");
const boardDtlMenu = document.querySelector(".board-dtl-menu");
const userDtlMenu = document.querySelector(".user-dtl-menu");
const userDtlMenuItems = document.querySelectorAll(".user-dtl-menu span");

const productAdminmenu = document.querySelectorAll(".farm-product-dtl-menu span");
//main
const loginBox = document.querySelector(".login-box");
const loginInputItems = document.querySelectorAll(".login-box input");
const loginBoxButtons = document.querySelectorAll(".signin-signup-button-box button");

const customButtons = document.querySelectorAll(".custom-button-box button");

const searchInput = document.querySelector(".search-farm-product input");
const autoSearchBox = document.querySelector(".auto-search");

let signinFlag = false;

// 게시판 구분짓는 flag
let userMenuFlag = false;
let productMenuFlag = false;
let boardMenuFlag = false;

let adminFlag = false;

// 구매하기인지 판매하기인지 구분짓는 flag
let purchaseFlag = false;

// userCode를 담을 임시 변수
let userCode = null;

// 구매, 판매 박스
const dtlMenu = document.querySelector(".purchase-product-menu");
const dtlProductMenu = document.querySelector(".show-available-product-box");
const showProductList = document.querySelector(".show-available-product-box ul");
let dtlMenuFlag = false;
let dtlProductFlag = false;

let autoSearchFlag = false;
let autoSearchValues = null;

let array = ["사과", "오렌지", "수박", "딸기", "사과탕후루", "사과탕", "참외", "귤"];

// $(userMenu).fadeOut(0);
$(userDtlMenu).fadeOut(0);
$(dtlMenu).fadeOut(0);
$(dtlProductMenu).fadeOut(0);

load();

let timeout = true;
let delay = 200;

searchInput.onkeyup = () => {
	let keyCode = window.event.keyCode;
    if(timeout) {
        clearTimeout(timeout);
    }
    timeout = setTimeout(function() {
        search(keyCode);
    }, delay);
    autoSearchFlag = true;
    
}

searchInput.onfocus = () => {
    autoSearchBox.style.display = "block";
    search();
    autoSearchFlag = true;
}

// 자동검색창을 닫게 해주기 위해서 검색창이 아닌 곳을 클릭하면 none
document.querySelector("main").onclick = (event) => {
    if(!String(event.target).includes("HTMLLIElement") && !String(event.target).includes("HTMLInput")){
        autoSearchBox.style.display = "none";
        autoSearchFlag = false;
    }
} 

function search(keyCode) {
    let text = searchInput.value;
    document.querySelector(".auto-search-list").innerHTML = "";
    if(keyCode == 40 && autoSearchValues != null){         // down
        // objOver(autoSearchValues[0]);
        alert("down");
    }else if(keyCode == 38 && autoSearchValues != null){   // up
        alert("up");
    }
    for(let i = 0; i < array.length; i++){
        if(searchInput.value == ""){
            document.querySelector(".auto-search-list").innerHTML += 
            `<li onmouseover="objOver(this)" onmouseout="objOut(this)" onclick="add('${array[i]}')" style="width: 800px; text-align: center">${array[i]}</li>`;
        }else if(array[i].indexOf(text) > -1){
            document.querySelector(".auto-search-list").innerHTML += 
            `<li onmouseover="objOver(this)" onmouseout="objOut(this)" onclick="add('${array[i]}')" style="width: 800px; text-align: center">${array[i]}</li>`;
        }
    }
    autoSearchValues = document.querySelectorAll(".auto-search-list li");
}

// 마우스가 위에 있을 때 회색으로 처리, 입력창에 값 대입
function objOver(obj){
    searchInput.value = obj.textContent;
    obj.style.backgroundColor = "gray";
}

function objOut(obj){
    obj.style.backgroundColor = "white";
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

productAdminmenu[0].onclick = () => {
    
}

productAdminmenu[1].onclick = () => {
    if(adminFlag) { // 관리자일 경우 새로운 getMapping 요청
        location.href = "/product/management";
    }else {
        toggleDtlBox();
        purchaseFlag = true;
    }
}

customButtons[0].onclick = () => {
    $(dtlProductMenu).fadeOut(0);
    purchaseFlag = true;
    growFlag = false;
    document.querySelector(".purchase-product-menu h1").innerHTML = "농산물 구매하기";
    document.querySelector(".purchase-box input").placeholder = "구매할 농산물을 입력하세요.";
    document.querySelector(".purchase-box button").innerHTML = "구매하기";
    document.querySelector(".show-product-button").innerHTML = "구매 가능한 농산물 보기";
}

customButtons[1].onclick = () => {
    $(dtlProductMenu).fadeOut(0);
    purchaseFlag = false;
    growFlag = false;
    document.querySelector(".purchase-product-menu h1").innerHTML = "농산물 판매하기";
    document.querySelector(".purchase-box input").placeholder = "판매할 농산물을 입력하세요.";
    document.querySelector(".purchase-box button").innerHTML = "판매하기";
    document.querySelector(".show-product-button").innerHTML = "판매 가능한 농산물 보기";
}

customButtons[2].onclick = () => {
    $(dtlProductMenu).fadeOut(0);
    purchaseFlag = false;
    growFlag = true;
    document.querySelector(".purchase-product-menu h1").innerHTML = "농산물 재배하기";
    document.querySelector(".purchase-box input").placeholder = "재배할 농산물을 입력하세요.";
    document.querySelector(".purchase-box button").innerHTML = "재배하기";
    document.querySelector(".show-product-button").innerHTML = "재배 가능한 농산물 보기";
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
    let obj = null;
    if(purchaseFlag) {  // 구매 가능한 품목 나타내기
        $.ajax({
            type: "get",
            url: "/api/v1/product/list",
            dataType: "json",
            success: (response) => {
                if(response.data.length != 0) {

                    showProductList.innerHTML = "";

                    for(let i = 0; i < response.data.length; i++){
                        obj = response.data[i];

                        showProductList.innerHTML += 
                        `<li class="product-list-title">${obj.product_name}<br></li>
                        <li class="product-list">개당 가격: ${obj.price}</li>`;
                    }
                }else {
                    showProductList.innerHTML = `<li class="no-product-list">아직 구매 가능한 농산물이 없습니다.</li>`
                }
            },
            error: errorMessage
        });
    }else if(!purchaseFlag && !growFlag) {    // 판매 가능한 품목 나타내기
        $.ajax({
            type: "get",
            url: `/api/v1/product/list/${userCode}`,
            dataType: "json",
            success: (response) => {
                if(response.data.length != 0) {

                    console.log(response.data);
                    
                    showProductList.innerHTML = "";

                    for(let i = 0; i < response.data.length; i++){
                        obj = response.data[i];

                        showProductList.innerHTML +=
                        `<li class="product-list-title">${obj.product_name}<br></li>
                        <li class="product-list">개당 가격: ${obj.price}</li>
                        <li class="product-list">수량: ${obj.amount}</li>`;
                    }

                }else {
                    showProductList.innerHTML = '<li class="no-product-list">아직 판매 가능한 농산물이 없습니다.</li>'
                }
            }
        });

    }else if(growFlag) {        // 재배 가능한 품목 나타내기
        $.ajax({
            type: "get",
            url: "/api/v1/product/list",
            dataType: "json",
            success: (response) => {
                if(response.data.length != 0) {

                    showProductList.innerHTML = "";

                    for(let i = 0; i < response.data.length; i++){
                        obj = response.data[i];

                        showProductList.innerHTML += 
                        `<li class="product-list-title">${obj.product_name}<br></li>
                        <li class="product-list">재배 기간: ${obj.grow_day}</li>`;
                    }
                }else {
                    showProductList.innerHTML = `<li class="no-product-list">아직 재배 가능한 농산물이 없습니다.</li>`
                }
            },
            error: errorMessage
        });
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

    $.ajax({
        type: "post",
        url: "/api/v1/user/signin",
        data: {
            "username": loginInputItems[0].value,
            "password": loginInputItems[1].value
        },
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

// 자동검색창이 열려있을때 로그인 input창이 focus면 자동검색창 닫기
loginInputItems[0].onfocus = () => {
    if(autoSearchFlag) {
        autoSearchBox.style.display = "none";
        autoSearchFlag = false;
    }
}
// 위와 동일
loginInputItems[1].onfocus = () => {
    if(autoSearchFlag) {
        autoSearchBox.style.display = "none";
        autoSearchFlag = false;
    }
}

function load(){
    if(signinFlag) {
        userMenu.style.display = "block";
        loginBox.style.visibility = "hidden";
    }else {
        userMenu.style.display = "none";
        loginBox.style.visibility = "visible";
    }

    $.ajax({
        type: "get",
        url: "/api/v1/product/new/list",
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                for(let i = 0; i < response.data.length; i++){
                    document.querySelector(".new-farm-product").innerHTML += 
                    `<span class="new-product"> ${response.data[i].product_name}</span>`;
                }
            }else {
                document.querySelector(".new-farm-product").innerHTML = 
                "새롭게 추가된 농산물이 없습니다.";
            }
        },
        error: errorMessage
    });

    $.ajax({
        type: "get",
        url: "/api/v1/product/modify/list",
        dataType: "json",
        success: (response) => {
            if(response.data != null) {

                const showProductInfoChangeBox = document.querySelector(".show-product-change");

                let obj = null;
                for(let i = 0; i < response.data.length; i++) {
                    obj = response.data[i];

                    showProductInfoChangeBox.innerHTML =
                    `<span class="change-product-title">${obj.updatedName}<br></span>`;
                    
                    if(obj.originalName != obj.updatedName) {
                        showProductInfoChangeBox.innerHTML +=
                        `<span class="change-product">이름: ${obj.originalName} -> ${obj.updatedName}</span>`;
                    }
                    if(obj.originalPrice != obj.updatedPrice) {
                        showProductInfoChangeBox.innerHTML +=
                        `<span class="change-product">가격: ${obj.originalPrice} -> ${obj.updatedPrice}</span>`;
                    }
                    if(obj.originalSeason != obj.updatedSeason) {
                        showProductInfoChangeBox.innerHTML +=
                        `<span class="change-product">계절: ${obj.originalSeason} -> ${obj.updatedSeason}</span>`;
                    }
                    if(obj.originalGrowDay != obj.updatedGrowDay) {
                        showProductInfoChangeBox.innerHTML +=
                        `<span class="change-product">재배기간: ${obj.originalGrowDay} -> ${obj.updatedGrowDay}</span>`;
                    }
                }
            }else {
                showProductInfoChangeBox.innerHTML =
                "변경된 농산물이 없습니다.";
            }
        },
        error: errorMessage
    })
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

function errorMessage(request, status, error) {
    alert("요청실패");
    console.log(request.status);
    console.log(request.responseText);
    console.log(error);
}

function isEmpty(value) {
    return value == null || value == undefined || value == "";
}