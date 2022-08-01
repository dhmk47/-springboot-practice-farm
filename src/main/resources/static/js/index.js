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
const showMoneyBox = document.querySelector(".show-money");

const rewardInfoBox = document.querySelector(".show-reward-info-box");

const searchInput = document.querySelector(".search-farm-product input");
const autoSearchBox = document.querySelector(".auto-search");

let signinFlag = false;

// 게시판 구분짓는 flag
let userMenuFlag = false;
let productMenuFlag = false;
let boardMenuFlag = false;

let adminFlag = false;

// 구매하기인지 판매하기인지 구분짓는 flag

// 재배중인지 확인하는 flag
let growingFlag = false;

let purchaseFlag = false;

// 유저정보를 담을 임시 변수
let userCode = 0;
let money = 0;
let amount = 0;
let purchasePrice = 0;

// 구매, 판매 박스
const productInputItems = document.querySelectorAll(".purchase-box input");
const productNameInput = document.querySelector(".product-name");
const productAmountInput = document.querySelector(".amount-input");

const dtlMenu = document.querySelector(".purchase-product-menu");
const dtlProductMenu = document.querySelector(".show-available-product-box");
const showProductList = document.querySelector(".show-available-product-box ul");
let dtlMenuFlag = false;
let dtlProductFlag = false;

let autoSearchFlag = false;
let autoSearchValues = null;

// 구매내용을 담을 객체
let obj = null;

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
    // if(timeout) {
    //     clearTimeout(timeout);
    // }
    // timeout = 
    setTimeout(function() {
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

// 마우스가 위에 있을 때 회색으로 처리
function objOver(obj){
    // searchInput.value = obj.textContent;
    obj.style.backgroundColor = "gray";
}

function objOut(obj){
    obj.style.backgroundColor = "bisque";
}

// 클릭시 입력창에 값 대입
function add(value) {
    searchInput.value = value;
    searchInput.focus();
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
        if(!signinFlag) {
            alert("로그인을 먼저 진행해 주세요.");
            return;
        }
        toggleDtlBox();
        customButtons[0].click();
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

document.querySelector(".purchase-box button").onclick = () => {
    if(growingFlag) {
        alert("이미 재배중입니다\n여러번 재배는 불가능합니다.");
        return;
    }

    if(!growFlag) {
        for(let i = 0; i < productInputItems.length; i++) {
            if(isEmpty(productInputItems[i].value)) {
                alert(
                    (i == 0 ? "농산물 이름을"
                    : "개수를") + " 입력해주세요."
                );
                return;
            }
        }
        
    }

    obj = null;

    if(purchaseFlag || growFlag) {
        checkProduct(productNameInput.value);

        if(obj == null) {
            alert("해당 농산물은 등록되어 있지 않습니다.");
            return;
        }
    }

    if(purchaseFlag) {                          // 구매버튼
        
        
        $.ajax({
            type: "get",
            url: `/api/v1/product/${productNameInput.value}`,
            dataType: "json",
            success: (response) => {
                if(response.data.length != 0) {
                    let price = null;

                    price = response.data.price * productAmountInput.value;

                    if(money - price < 0) {
                        alert(`구매 불가!\n부족 금액: ${(money - price) * -1}`);
                    }else {
                        let result = null;
                        result = confirm(`${productNameInput.value}을(를) ${productAmountInput.value}개 구매하시겠습니까?\n잔액: ${money - price}`);

                        if(result){
                            let flag = false;
                            flag = checkUserProduct(obj.productName, userCode);
                            if(!flag) {
                                let salesPrice = prompt("개당 얼마로 판매하시겠습니까?");
                                obj.price = salesPrice;
                            }
                            money = money - price;
                            if(updateUserMoney(money, userCode)) {
                                amount += parseInt(productAmountInput.value);
                                obj.amount = amount;
                                alert("obj.amount: " + obj.amount);
                                updateUserProduct(obj, flag);
                            }
                            
                        }
                    }
                }else {
                    alert("구매 불가!\n해당 농산물은 존재 하지 않습니다.");
                }
            },
            error: errorMessage
        });
    }else if(!purchaseFlag && !growFlag) {      // 판매버튼
        checkUserProduct(productNameInput.value, userCode);

    }else {                                     // 재배버튼
        let choice = false;
        let delay = obj.growDay;
        choice = confirm(obj.productName + "은(는) 재배 완료까지 " + delay + "초가 걸립니다.\n그래도 진행하시겠습니까?")

        if(choice) {

            // 1 ~ 10 사이의 난수 생성
            let randAmount = Math.floor((Math.random() * 10) + 1);
            
            let checkProductFlag = checkUserProduct(obj.productName, userCode);
            if(checkProductFlag) { // 해당 농산물이 존재한다면 개수만 update
                obj.amount = amount + randAmount;

            } else { // 없다면 가격 책정하고 개수 insert
                let salesPrice = prompt("개당 얼마로 판매하시겠습니까?");
                obj.price = salesPrice;
                obj.amount = randAmount;
            }
            // growUserProduct(obj, checkProductFlag, randAmount);

                
            setTimeout(() => {
                updateUserProduct(obj, checkProductFlag);
                alert(obj.productName + "을(를) " + randAmount + "개 재배완료 했습니다.");
                growingFlag = false;                    
            }, delay * 1000);

            growingFlag = true;

        }
    }
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

document.querySelector(".xmark3").onclick = () => {
    rewardInfoBox.style.display = "none";
}

document.querySelector(".show-product-button").onclick = () => {
    $(dtlProductMenu).fadeIn(20);
    dtlProductFlag = true;
    let obj = null;
    if(purchaseFlag) {  // 구매 가능한 품목 나타내기
        $.ajax({
            type: "get",
            url: "/api/v1/product/list/all",
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
            url: `/api/v1/product/list/user/${userCode}`,
            dataType: "json",
            success: (response) => {
                if(response.data.length != 0) {
                    
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
            url: "/api/v1/product/list/all",
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
                    money = response.data.money;
                    showMoneyBox.innerHTML = `보유금액: ${money}원`;
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

// 비밀번호 입력창에서 Enter키 누르면 로그인 버튼 클릭
loginInputItems[1].onkeypress = () => {
    if(window.event.keyCode == 13) {
        loginBoxButtons[0].click();
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

    if(userCode != 0) {
        $.ajax({
            type: "get",
            url: `/api/v1/product/deleted/list/user/${userCode}`,
            dataType: "json",
            success: (response) => {
                if(response.data.length != 0) {
                    
                    document.querySelector(".show-reward-info-box div").innerHTML = "";

                    rewardInfoBox.style.display = "block";

                     for(let i = 0; i < response.data.length; i++) {
                        let obj = response.data[i];
    
                        document.querySelector(".show-reward-info-box div").innerHTML += i != response.data.length - 1 ?
                        `<span class="show-reward-info-span">${obj.productName} ${obj.amount}개가 삭제되었습니다.<br>개당 구매한 금액: ${obj.purchasePrice}원<br>보상 금액: ${obj.amount * obj.purchasePrice}<br></span>`
                        : `<span class="show-reward-info-span">${obj.productName} ${obj.amount}개가 삭제되었습니다.<br>개당 구매한 금액: ${obj.purchasePrice}원<br>보상 금액: ${obj.amount * obj.purchasePrice}</span>`;
                     }
    
                }else {
                    alert("삭제된 농산물이 없습니다.");
                }
            },
            error: errorMessage
        });
    }

    $.ajax({
        type: "get",
        url: "/api/v1/product/new/list",
        dataType: "json",
        success: (response) => {
            if(response.data.length != 0) {
                for(let i = 0; i < response.data.length; i++){
                    document.querySelector(".new-farm-product").innerHTML += 
                    `<span class="new-product"> ${response.data[i].product_name}</span>`;
                }
            }else {
                document.querySelector(".new-farm-product").innerHTML = 
                `<span class="no-new-product">새롭게 추가된<br>농산물이 없습니다.</span?`;
            }
        },
        error: errorMessage
    });

    $.ajax({
        type: "get",
        url: "/api/v1/product/modify/list",
        dataType: "json",
        success: (response) => {

            const showProductInfoChangeBox = document.querySelector(".show-product-change");
            
            if(response.data.length != 0) {

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
                `<span class="no-change-product">변경된 농산물이 없습니다.</span>`;
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

function updateUserMoney(money, userCode) {
    let flag = false;
	$.ajax({
		type: "put",
		url: `/api/v1/user/money/${userCode}`,
        async: false,
        contentType: "application/json",
		data: JSON.stringify({
			money: money
		}),
		dataType: "json",
		success: (response) => {
			if(response.data != false) {
				alert("돈 업데이트 성공");
                showMoneyBox.innerHTML = `보유금액: ${money}원`;

                flag = true;
			}else {
				alert("돈 업데이트 실패");

                flag = false;
			}
		},
		error: errorMessage
	});

    if(flag) {
        return true;
    }else {
        return false;
    }
}

function checkProduct(productName) {

    let flag = false;

    $.ajax({
        type: "get",
        url: `/api/v1/product/${productName}`,
        async: false,
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                
                obj = null;
                
                obj = {
                    productCode: response.data.product_code,
                    productName: response.data.product_name,
                    price: response.data.price,
                    season: response.data.season,
                    amount: 0,
                    growDay: response.data.grow_day,
                    userCode: userCode,
                    purchasePrice: response.data.price
                };
            }else {
                obj = null;
            }
        }
    });
}

function checkUserProduct(productName, userCode) {
    let flag = false;

    $.ajax({
        type: "get",
        url: `/api/v1/product/users?productName=${productName}&userCode=${userCode}`,
        async: false,
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                if(purchaseFlag || growFlag) {   // 구매, 재배버튼 일때
                    amount = response.data.amount;
                    flag = true;

                }else if(!purchaseFlag && !growFlag) {  // 판매 버튼 일때
                    amount = response.data.amount - productAmountInput.value;
                    money = money + (response.data.price * productAmountInput.value);
                    purchasePrice = response.data.purchasePrice;
                    let result = false;

                    result = confirm(`${productName}을 ${productAmountInput.value}개 판매하시겠습니까?\n판매 후 잔액: ${money}`);

                    if(result) {
                        if(updateUserMoney(money, userCode)) {
                            updateUserProduct({productName: productName,
                                            amount: amount,
                                            purchasePrice: purchasePrice,
                                            userCode: userCode}, true);
                        }
                    }
                }
            }else {
                if(purchaseFlag || growFlag) {
                    flag = false;

                }else if(!purchaseFlag && !growFlag) {
                    alert("사용자가 가지고 있지 않는 농산물입니다.");
                }
            }
        },
        error: errorMessage
    });

    if(flag) {
        return true;
    }else {
        return false;
    }
}

/* 여러번 재배 가능하게끔..
function growUserProduct(obj, flag, randAmount) {
    setTimeout(() => {
        if(flag) {   // 사용자에게 해당 농산물이 있다면 update
            alert("640 농산물 있음!");
            
            $.ajax({
                type: "put",
                url: "/api/v1/product/users/new",
                data: obj,
                dataType: "json",
                success: (response) => {
                    if(response.data) {
                        alert("사용자 농산물 새롭게 업데이트 성공");
                        productInputItems.forEach(input => {
                            input.value = "";
                        });

                        alert(obj.productName + "을(를) " + randAmount + "개 재배완료 했습니다.");
    
                    }else {
                        alert("사용자 농산물 새롭게 업데이트 실패");
                    }
                },
                error: errorMessage
            });
    
        }else { // 없다면 insert
            alert("642 농산물 없음!");
            $.ajax({
                type: "post",
                url: "/api/v1/product/users/new",
                data: obj,
                dataType: "json",
                success: (response) => {
                    if(response.data != false) {
                        alert("사용자 농산물 추가 성공");
                        productInputItems.forEach(input => {
                            input.value = "";
                        });

                        alert(obj.productName + "을(를) " + randAmount + "개 재배완료 했습니다.");
    
                    }else {
                        alert("사용자 농산물 추가 실패");
                    }
                },
                error: errorMessage
            });
        }
    }, obj.growDay * 1000);
    
}
*/
function updateUserProduct(obj, flag) {
    if(flag) {   // 사용자에게 해당 농산물이 있다면 update
        alert("640 농산물 있음!");
        
        $.ajax({
            type: "put",
            url: "/api/v1/product/users/new",
            contentType: "application/json",
            data: JSON.stringify(obj),
            dataType: "json",
            success: (response) => {
                if(response.data) {
                    alert("사용자 농산물 새롭게 업데이트 성공");
                    productInputItems.forEach(input => {
                        input.value = "";
                    });

                }else {
                    alert("사용자 농산물 새롭게 업데이트 실패");
                }
            },
            error: errorMessage
        });

    }else { // 없다면 insert
        alert("642 농산물 없음!");
        $.ajax({
            type: "post",
            url: "/api/v1/product/users/new",
            contentType: "application/json",
            data: JSON.stringify(obj),
            dataType: "json",
            success: (response) => {
                if(response.data != false) {
                    alert("사용자 농산물 추가 성공");
                    productInputItems.forEach(input => {
                        input.value = "";
                    });

                }else {
                    alert("사용자 농산물 추가 실패");
                }
            },
            error: errorMessage
        });
    }
}

function isEmpty(value) {
    return value == null || value == undefined || value == "";
}