const inputItems = document.querySelectorAll("main input");
const usernameFormBox = document.querySelector(".username-dtl-form");
const passwordFormBox = document.querySelector(".password-dtl-form");
const emailFormBox = document.querySelector(".email-dtl-form");
const selectBox = document.querySelector("select");
const buttons = document.querySelectorAll("main button");

let usernameFlag = false;
let username = null;
let email = null;

inputItems[1].onblur = () => {
    checkUsername();
    if(username != inputItems[1].value) {
        usernameFlag = false;
    }
}

inputItems[2].onblur = () => {
    checkPassword();
}

inputItems[3].onkeyup = () => {
    passwordValueCheck();
}

inputItems[3].onblur = () => {
    passwordValueCheck();
}

inputItems[4].onblur = () => {
    checkEmail();
}

selectBox.onchange = () => {
    checkEmail();
}

buttons[0].onclick = () => {
    username = inputItems[1].value;

    if(isEmpty(username)) {
        alert("아이디를 입력해 주세요.");
        return;
    }

    $.ajax({
        type: "get",
        url: `api/v1/user/check/${username}`,
        dataType: "json",
        success: (response) => {
            if(response.data != null){
                alert("이미 중복된 아이디입니다.")
                usernameFlag = false;
            }else {
                alert("사용가능한 아이디입니다.");
                usernameFlag = true;
            }
        },
        error: errorMessage
    })
}

buttons[1].onclick = () => {
    for(let i = 0; i < inputItems.length; i++) {
        if(isEmpty(inputItems[i].value)){
            alert(
                (i == 0 ? "이름을"
                : i == 1 ? "아이디를"
                : i == 2 ? "비밀번호를"
                : i == 3 ? "비밀번호 확인을"
                : "이메일을"
                ) + " 입력해 주세요"
            );
            return;
        }
    }

    if(checkUsername() && checkPassword() && passwordValueCheck() && checkEmail() && usernameFlag){
        $.ajax({
            type: "post",
            url: "api/v1/user/signup",
            contentType: "application/json",
            data: JSON.stringify({
                name: inputItems[0].value,
                username: inputItems[1].value,
                password: inputItems[2].value,
                email: email 
            }),
            dataType: "json",
            success: (response) => {
                alert("회원가입 성공");
                location.replace("/index");
            },
            error: errorMessage
        });
    }else {
        if(!usernameFlag) {
            alert("아이디 중복확인을 진행해 주세요.");
        }else {
            alert("양식을 다시 확인해 주세요.");
        }
    }
}

function checkUsername() {
    const regExr = /^[A-Za-z0-9]{4,10}$/;
    if((regExr).test(inputItems[1].value)) {
        usernameFormBox.style.display = "none";
        return true;
    }else {
        usernameFormBox.style.display = "inline-block";
        return false;
    }
}

function checkPassword() {
    const regExr = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$*_])[A-Za-z0-9!@#$*_]{8,16}$/;
    if((regExr).test(inputItems[2].value)) {
        passwordFormBox.style.display = "none";
        return true;
    }else {
        passwordFormBox.style.display = "inline-block";
        return false;
    }
}

function passwordValueCheck() {
    if(inputItems[2].value != inputItems[3].value){
        document.querySelector(".not-match-box").style.display = "block";
        return false;
    }else {
        document.querySelector(".not-match-box").style.display = "none";
        return true;
    }
}

function checkEmail() {
    let regExr = null;
    let emailFormFlag = false;
    email = inputItems[4].value;
    if(selectBox.options[selectBox.selectedIndex].text != "직접입력"){
        regExr = /^[A-Za-z0-9]+$/;
        emailFormFlag = (regExr).test(inputItems[4].value);
        email += selectBox.options[selectBox.selectedIndex].text;
    }else {
        regExr = /^[A-Za-z0-9]+@[A-Za-z0-9]+\.com$/
        emailFormFlag = (regExr).test(inputItems[4].value);
    }

    if(!emailFormFlag) {
        emailFormBox.style.display = "inline-block";
        return false;
    }else {
        emailFormBox.style.display = "none";
        return true;
    }
}

function isEmpty(value) {
    return value == "" || value == undefined || value == null;
}

function errorMessage(request, status, error) {
    alert("요청 실패!");
    console.log(request.status);
    console.log(request.responseText);
    console.log(error);
}