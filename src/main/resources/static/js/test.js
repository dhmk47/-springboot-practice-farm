const btn = document.querySelector("body button");

btn.onclick = () => {
    getResult(); // 함수 안에서는 결과를 기다리지만 바깥에서는 정상적으로 진행되는 중
    alert("비동기식?"); // 그래서 이 명령문이 먼저 실행됩니다.
    for(let i = 0; i < 100; i++){
        console.log(i);
    }
}

function getData() {
    let data = null;

    // ajax 비동기 통신은 ajax 바깥에서 async await 를 사용해도 원하는 결과를 기대할 수 없음
    // success 안에 resolve를 넣어야 원하는 결과를 얻을 수 있음.

    // return new Promise((resolve, reject) => {
    //     $.ajax({
    //         type: "get",
    //         url: "/api/v1/test/list",
    //         dataType: "json",
    //         success: (response) => {
    //             if(response.data.length != 0) {
    //                 data = response.data;
    //                 alert("불러옴.");
    //                 // resolve(data); // 하지만 이러면 비동기식 처리가 맞는지는 강사님께 여쭤보기
    //             }else {
    //                 alert("불러오지 못함.");
    //             }
    //         }
    //     });
    //     resolve(data);
    // });

///////////////////////////////////////////////////////////////////////////

    // setTimeOut으로 시간을 지연시킬 경우 async await를 통해 원하는 결과를 얻을 수 있음.

    return new Promise((resolve, reject) => {
        setTimeout(() => {
            data = 12345;
            
            if(data != 0) {
                resolve(data);
            }else {
                reject("Data is Zero!");
            }
        }, 2000);
    });
}

async function getResult() {
    let result = await getData();
    alert(result);
    // async function 안에서는 await 의 결과를 기다리고 다음 명령문이 실행
    // 그래서 아래의 alert는 2초 기다렸다가 실행
    alert("async function 안에서의 비동기식?");
}