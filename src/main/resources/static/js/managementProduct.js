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
const mainBox = document.querySelector(".r-flex");
const checkButton = document.querySelector(".r-flex button");
const changeInputDivBoxes = document.querySelectorAll(".hideAndShowtoggle");
const changeProductInfoBoxes = document.querySelectorAll(".show-product-info-box");
const submitButton = document.querySelectorAll(".main-button button");

// main section
const sectionBox = document.querySelector("main section");
const sectionUlBox = document.querySelector("main section ul")
// const sectonButtonDivBox = document.querySelector("main section div");
// const sectionButtons = document.querySelector("main section button");

// 게시판 구분 짓는 플래그
let userMenuFlag = false;
let productMenuFlag = false;
let boardMenuFlag = false;

// main box display 확인 flag
let mainBoxFlag = true;

// 삭제버튼이면 input이 숨겨지기 때문에 flag 만들어서 if문 만들기
let removeFlag = false;

// 수정버튼이면 div박스 보여야 하기 때문에 구분 짓는 flag
let modifyFlag = false;

// 삭제된 농산물 다시 추가 버튼이면 section 보여야 하기 때문에 구분 짓는 flag
let sectionFlag = false;

// 해당 품목이 있는지 없는지 확인 하는 flag
let permissionFlag = false;

// confirm 확인용 변수
let result = false;

// 값을 입력했는지 안 했는지 확인 하느 flag
let hasValue = false;

// 추가 화면인지 확인 하는 flag
let addProductFlag = true;

// 중복체크 후에 이름을 변경했는지 확인하기 위한 flag
let checkedProductName = null;

// 수정창에서만 사용하는 div박스가 켜져있는지 확인하는 flag
let showProductDivFlag = false;

// response에서 가져온 값 저장 할 변수
let productCode = -1;
let getProductName = null;
let getProductPrice = null;
let getProductSeason = null;
let getProductGrowDay = null;

// 위의 변수를 배열에 저장
let getProductInfoList = new Array();

// 삭제된 농산물 코드를 담을 배열
let checkCode = new Array();

// 삭제된 정보를 key(code) value(object) 로 저장할 map
let map = new Map();

// 삭제된 농산물 추가를 누를때 모든 농산물의 object를 담을 배열
let objList = new Array();

// delay
let timeout = true;
let delay = 500;

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
    location.href = "/product/management";
}

// main

customButtons[0].onclick = () => {
    for(let i = 0; i < inputItems.length; i++) {
        if(!isEmpty(inputItems[i].value)) {
            hasValue = true;
            result = confirm("변경사항이 저장되지 않습니다.\n그래도 진행 하시겠습니까?");
            if(result) {
                inputItems.forEach(i => i.value = "");
                checkedProductName = null;
            }
            break;
        }
    }
    if(permissionFlag && result) {

        if(result && !removeFlag) {     // yes이고 삭제창에서 넘어온 것이 아닐때
            toggleInputItems();
            permissionFlag = false;
        }else if(result) {    // yes이고 삭제창에서 넘어왔을 때
            permissionFlag = false;
        }else {
            return;
        }
    }
    if(showProductDivFlag){
        toggleProductDivBox();
        showProductDivFlag = false;
    }
    
    if(!mainBoxFlag) {  // 삭제된 농산물 다시 추가였을때
        mainBox.style.display = "flex";
        mainBoxFlag = true;
        sectionBox.style.display = "none";
        sectionFlag = false;
    }

    addProductFlag = true;
    removeFlag = false;
    modifyFlag = false;
    submitButton[1].style.display = "none";
    inputItems[0].placeholder = "추가할 농선물 이름";
    inputItems[1].placeholder = "추가할 농선물 가격";
    inputItems[2].placeholder = "추가할 농선물 계절";
    inputItems[3].placeholder = "추가할 농선물 재배 기간";
    submitButton[0].innerHTML = "추가하기"

    
}

customButtons[1].onclick = () => {
    for(let i = 0; i < inputItems.length; i++) {
        if(!isEmpty(inputItems[i].value)) {
            hasValue = true;
            result = confirm("변경사항이 저장되지 않습니다.\n그래도 진행 하시겠습니까?");
            if(result) {
                inputItems.forEach(i => i.value = "");
                checkedProductName = null;
            }
            break;
        }
    }
    if(permissionFlag) {
        if(result && !removeFlag) {     // yes이고 삭제창에서 넘어온 것이 아닐때
            toggleInputItems();
            permissionFlag = false;
        }else if(result) {    // yes이고 삭제창에서 넘어왔을 때
            permissionFlag = false;
        }else {
            return;
        }
    }
    addProductFlag = false;
    removeFlag = false;
    if(!modifyFlag) {
        modifyFlag = true;
    }

    if(!mainBoxFlag) {  // 삭제된 농산물 다시 추가였을때
        mainBox.style.display = "flex";
        mainBoxFlag = true;
        sectionBox.style.display = "none";
        sectionFlag = false;
    }
    
    inputItems[0].placeholder = "수정할 농선물 이름";
    inputItems[1].placeholder = "수정할 농선물 가격";
    inputItems[2].placeholder = "수정할 농선물 계절";
    inputItems[3].placeholder = "수정할 농선물 재배 기간";
    submitButton[0].innerHTML = "수정하기"
}

customButtons[2].onclick = () => {
    for(let i = 0; i < inputItems.length; i++) {
        if(!isEmpty(inputItems[i].value)) {
            hasValue = true;
            result = confirm("변경사항이 저장되지 않습니다.\n그래도 진행 하시겠습니까?");
            if(result) {
                inputItems.forEach(i => i.value = "");
                checkedProductName = null;
            }
            break;
        }
    }
    if(permissionFlag) {
        if(result && !removeFlag) {     // yes이고 추가,수정창에서 넘어왔을 때
            toggleInputItems();
            permissionFlag = false;
        }else if(result) {              // yes이고 삭제창에서 삭제창을 눌렀을 때
            permissionFlag = false;
        }else {
            return;
        }
    }
    if(showProductDivFlag){
        toggleProductDivBox();
        showProductDivFlag = false;
    }
    
    if(!mainBoxFlag) {  // 삭제된 농산물 다시 추가였을때
        mainBox.style.display = "flex";
        mainBoxFlag = true;
        sectionBox.style.display = "none";
        sectionFlag = false;
    }

    submitButton[1].style.display = "none";
    modifyFlag = false;
    addProductFlag = false;
    removeFlag = true;
    inputItems[0].placeholder = "삭제할 농선물 이름";
    submitButton[0].innerHTML = "제거하기"
}

// 삭제된 농산물 리스트 보여주는 버튼
customButtons[3].onclick = () => {
    if(mainBoxFlag) {
        mainBox.style.display = "none";
        mainBoxFlag = false;
    }
    if(!sectionFlag) {

        sectionBox.style.display = "flex";
        sectionFlag = true;

        sectionUlBox.innerHTML = "";

        $.ajax({
            type: "get",
            url: "/api/v1/product/list/delete",
            dataType: "json",
            success: (response) => {
                if(response.data.length != 0) {

                    
                    
                    for(let i = 0; i < response.data.length; i++) {
                        let obj = response.data[i];

                        let productObj = null;

                        productObj = {
                            productCode: obj.product_code,
                            productName: obj.product_name,
                            price: obj.price,
                            season: obj.season,
                            growDay: obj.grow_day
                        };


                        map.set(obj.product_code, productObj);

                        
                        sectionUlBox.innerHTML += 
                            `<li class="product-list deleted-${obj.product_code}"><input type="checkbox" onchange="checkBoxClick('${obj.product_code}')">
                            <span>${obj.product_name}</span>
                            <span>${obj.price}</span>
                            <span>${obj.season}</span>
                            <span>${obj.grow_day}</span>
                            </li>`;
                        };

                }else {
                    sectionUlBox.innerHTML += 
                        `<li class="no-product-list">삭제된 농산물이 없습니다.</li>`;
                }
            },
            error: errorMessage
        });

    }
}

function checkDeletedProduct() {
    let loadProductFlag = false;
    $.ajax({
        type: "get",
        url: `/api/v1/product/deleted/check/${inputItems[0].value}`,
        async: false,
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                if(confirm("저번에 삭제했던 품목이 있습니다.\n정보를 불러오시겠습니까?")) {
                    let deletedObj = response.data;

                    permissionFlag = true;
                    toggleInputItems();

                    inputItems[1].value = deletedObj.price;
                    inputItems[2].value = deletedObj.season;
                    inputItems[3].value = deletedObj.grow_day;
                    productCode = deletedObj.product_code;

                    loadProductFlag = true;
                }
            }
        },
        error: errorMessage
    });

    return loadProductFlag;
}

// 농산물 체크 버튼
checkButton.onclick = () => {
    if (isEmpty(inputItems[0].value)) {
        alert("이름을 입력해주세요");
        return;
    }else if(checkedProductName == inputItems[0].value){
        return;
    }

    if(addProductFlag) {
        let loadProductFlag = checkDeletedProduct();

        if(loadProductFlag) {
            return;
        }
    }

    $.ajax({
        data: "get",
        url: `/api/v1/product/${inputItems[0].value}`,
        dataType: "json",
        success: (response) => {
            if(response.data != null) {
                alert("데이터가 존재합니다.");
                
                if(!removeFlag && !addProductFlag) {   // 삭제 버튼창이 아니라면 토글
                    toggleInputItems();
                    permissionFlag = true;
                }else if(removeFlag){
                    permissionFlag = true;
                    productCode = response.data.product_code;
                }

                if(modifyFlag) {    // 수정창에서는 체크후에 기존 값들 쭉 나오게끔
                    getProductInfoList.push(response.data.product_name);
                    getProductInfoList.push(response.data.price);
                    getProductInfoList.push(response.data.season);
                    getProductInfoList.push(response.data.grow_day);

                    inputItems[1].value = response.data.price;
                    inputItems[2].value = response.data.season;
                    inputItems[3].value = response.data.grow_day;
                    productCode = response.data.product_code;

                    submitButton[1].style.display = "inline-block"
                    toggleProductDivBox();
                    showProductDivFlag = true;

                    let index = 0;
                    changeProductInfoBoxes.forEach(input => {
                        input.innerHTML = `<span style="padding-left: 40px">${inputItems[index].value} -> </span>`;
                        index++;
                    });
                }

            }else {
                alert("데이터가 존재하지 않습니다.");
                if(addProductFlag) {
                    permissionFlag = true;
                    toggleInputItems();
                }else if(permissionFlag){
                    permissionFlag = false;
                    toggleInputItems();
                }
                
                if(showProductDivFlag) {
                    toggleProductDivBox();
                    showProductDivFlag = false;
                }
            }
        },
        error: errorMessage
    });
    checkedProductName = inputItems[0].value;
}

inputItems[0].onblur = () => {
    if(checkedProductName != inputItems[0].value && permissionFlag && !modifyFlag) {
        alert("농산물 확인 후에 이름을 변경하지 마세요.");
        inputItems.forEach(i => i.value = "");
        checkedProductName = null;
        permissionFlag = false;
        toggleInputItems();
    }
}

inputItems[0].onkeyup = () => {
    if(modifyFlag && permissionFlag) {
        setDelay(0);
    }
    
}

inputItems[1].onkeyup = () => {
    if(modifyFlag && permissionFlag) {
        setDelay(1);
    }

}

inputItems[2].onkeyup = () => {
    if(modifyFlag && permissionFlag) {
        setDelay(2);
    }

}

inputItems[3].onkeyup = () => {
    if(modifyFlag && permissionFlag) {
        setDelay(3);
    }

}

// 최종 submit버튼
submitButton[0].onclick = () => {
    if(sectionFlag) {
        objList.splice(0, objList.length);

        console.log("초기화후: " + objList);

        checkCode.forEach(code => {
            objList.push(map.get(parseInt(code)));
        });


        console.log("push후: " + objList);

        let productObjList = {
            objList: objList
        };

        $.ajax({
            type: "post",
            url: "/api/v1/product/deleted/new",
            data: JSON.stringify(productObjList),
            contentType: "application/json",
            dataType: "json",
            success: (response) => {
                if(response.data != 0) {
                    alert(response.data + "개의 농산물 다시 등록 성공");
                    location.replace("/product/management");
                    customButtons[3].click();
                }else {
                    alert("농산물 다시 등록 실패");
                }
            },
            error: errorMessage
        });

        return;
    }
    
    if(!permissionFlag) {
        alert("농산물 확인을 진행해 주세요.");
        return;
    }

    if(addProductFlag){ // 추가 요청
        if(inputItemsCheck(inputItems)) {
            $.ajax({
                type: "post",
                url: "/api/v1/product/new",
                contentType: "application/json",
                data: JSON.stringify({
                    productCode: productCode,
                    productName: inputItems[0].value,
                    price: inputItems[1].value,
                    season: inputItems[2].value,
                    growDay: inputItems[3].value
                }),
                dataType: "json",
                success: (response) => {
                    if(response.data != null) {
                        alert("농산물 추가 완료");
                        location.replace("/product/management");
                    }else {
                        alert("농산물 추가 실패");
                    }
                },
                error: errorMessage
            });
        }
        
    }else if(modifyFlag){           // 수정 요청
        if(inputItemsCheck(inputItems)) {
            $.ajax({
                type: "put",
                url: `/api/v1/product/modify/${productCode}`,
                contentType: "application/json",
                data: JSON.stringify({
                    productName: inputItems[0].value,
                    price: inputItems[1].value,
                    season: inputItems[2].value,
                    growDay: inputItems[3].value
                }),
                dataType: "json",
                success: (response) => {
                    if(response.data != null) {
                        alert("농산물 수정 완료");
                        location.replace("/product/management");
                    }else {
                        alert("농산물 수정 실패");
                    }
                },
                error: errorMessage
            });
        }
    }else {                         // 삭제 요청
        if(inputItemsCheck(inputItems[0])){
            $.ajax({
                type: "delete",
                url: `/api/v1/product/remove/${productCode}`,
                dataType: "json",
                success: (response) => {
                    if(response.data != null) {
                        alert("농산물 삭제 완료");
                        location.replace("/product/management");
                    }else {
                        alert("농산물 삭제 실패");
                    }
                },
                error: errorMessage
            });
        }
    }
}

submitButton[1].onclick = () => {
    inputItems.forEach(input => input.value = "");
    changeProductInfoBoxes.forEach(div => div.innerHTML = "");
    toggleInputItems();
    toggleProductDivBox();
    submitButton[1].style.display = "none";
    permissionFlag = false;
    checkedProductName = null;
    showProductDivFlag = false;
}

function checkBoxClick(index) {
    if(document.querySelector(`.deleted-${index} input`).checked){  // 체크되었다면 배열에 추가
        checkCode.push(index);
    }else {
        if(checkCode.indexOf(index) > -1) {     // 체크해제되었다면 배열에서 요소 제거
            let searchIndex = checkCode.indexOf(index);
            checkCode.splice(searchIndex, 1);

        }
    }
}

function inputItemsCheck(items) {
    for(let i = 0; i < items.length; i++) {
        if(isEmpty(items[i].value)) {
            alert(
                (i == 0 ? "이름을"
                : i == 1 ? "가격을"
                : i == 2 ? "계절을"
                : "재배 기간") + " 입력해 주세요."
            );
            return false;
        }
    }
    return true;
}

function toggleInputItems() {
    changeInputDivBoxes.forEach(i => {
        i.classList.toggle("hideAndShowInputDivBox");
    })
}

function toggleProductDivBox() {
    changeProductInfoBoxes.forEach(i => {
        i.classList.toggle("hideAndShowProductDivBox");
    })
}

function setDelay(index) {
    if(timeout) {
        clearTimeout(timeout);
    }
    timeout = setTimeout(function() {
        showNewValue(index);
    }, delay);
}

function showNewValue(index) {
    changeProductInfoBoxes[index].innerHTML = 
    `<span style="padding-left: 40px">${getProductInfoList[index]} -> ${inputItems[index].value}</span>`;
}

function isEmpty(value) {
    return value == undefined || value == "" || value == null;
}

function errorMessage(request, status, error) {
    alert("요청실패");
    console.log(request.status);
    console.log(request.responseText);
    console.log(error);
}