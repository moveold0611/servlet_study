import React from 'react';
import { css } from "@emotion/react";
/** @jsxImportSource @emotion/react */

async function printUserTest() {
    // async 함수
}

function Asynchronous(props) {
    /* 
    동기(Synchronous) : 순서대로 동작
    비동기(Asynchronous) : 순서대로 동작하지 않음
    ㄴ 서버가 응답할 때 까지 다음 코드가 실행되지 않는 문제를 해결하기 위해 사용

    callback을 사용하는 이유
    ㄴ 순서대로 동작하지 않는 비동기 코드들을 임의로 순서대로 동작하게 만들기 위해 사용
    */

    let num = 0;
    let click1 = 0;

    const handleClick = () => {
        num++;
        const click1 = () => {
            console.log(` ${num}1111`)
        }
        const click2 = () => {
            console.log(` ${num}2222`)
        }
        const clickAfter1 = () => {
            console.log("클릭1실행 후 실행") 
        }
        const clickAfter2 = () => {
            {console.log("클릭2실행 후 실행")}
        }

        const clickFx = (fx1, fx2) => {
            fx1(num);
            fx2();
        }

        setTimeout(clickFx, Math.random(2)* 1000, click1, clickAfter1);    // click, clickAfter1이 clickFx의 매개변수로
        setTimeout(clickFx, Math.random(2)* 1000, click2, clickAfter2);
    }

    const handleclick2 = () => {
        const p1 = new Promise((resolve, reject) => {
            let num = Math.random(4);
            if(Math.round(num % 2) === 0) {
                resolve("짝수"); // 아래의 result로
            }else {
                reject(new Error("홀수"));
            }
        });
        p1.then(result => {
            console.log(result);
            return "첫번째 then의 리턴";
        }).then(result => {
            console.log(result);
            return "두번째 then의 리턴";
        }).then(result => {
            console.log(result);
        }).catch (error => {
            console.log(error)
        });
    }

    

    const handleclick3 = () => {
        // await - 비동기 안에서 동기 처리 (순서를 기다려야 할 때)
        // async 안에서만 사용 가능
        const printUser2 = () => {
            return new Promise((resolve, reject) => {
                resolve("User2");
                reject(new Error("Error2"));
            });
        }
        // case 1
        const printUser = async () => {
            try {
                await printUser2().then((r) => {console.log(r)});
                // ㄴ (await) 이 함수가 실행될 때 까지 다음 함수가 실행되지 않음
                throw new Error("Test Error")                
            } catch (error) {
                console.log(error)
            }
            return "User1"
        }
        printUser().then(r => console.log(r));
        //

        // case 2
            // const printUser2 = () => {
            //     return new Promise((resolve, reject) => {
            //         resolve("User2");
            //         reject("Error2");
            //     });
            // }
            // printUser2().then(r => console.log(r));
            // printUser2().catch(error => console.log(error));
        //



    }

    return (
        <div>
            <button onClick={handleClick}>클릭</button>
            <button onClick={handleclick2}>이릭</button>
            <button onClick={handleclick3}>삼릭</button>
        </div>
    );
}

export default Asynchronous;