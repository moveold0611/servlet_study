import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';

const SInputLayout = css`
    display: flex;
    margin-bottom: 15px;
    width: 60%;
    height: 40px;
    & > input {
        width: 100%;
        height: 100%;
        text-align: center;
    }
`;

function Signup(props) {

    const naivgate = useNavigate();

    const [ signupUser, setSignupUser ] = useState({
        username: "",
        password: "",
        name: "",
        email: ""
    });

    const handleInputChange = (e) => {
        setSignupUser({
            ...signupUser,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmitClick = () => {
        const option = {
            params: {
                username: signupUser.username
            }
        }

        const signUp = async () => {
            let response = await axios.get("http://localhost:8080/servlet_study_ldh/auth/signup/duplicated/username", option);
                if(response.data) { // 반드시 위 코드가 응답받은 다음에 실행되어야 한다.
                                    // (await으로 동기처리)
                    alert("중복된 아이디입니다.");
                    console.log("signup fail")
                    return;
                }
            alert("사용 가능한 아이디입니다.");  
            try {
                response = await axios.post("http://localhost:8080/servlet_study_ldh/auth/signup", signupUser);
                if(!response.data) {
                    throw new Error(response);
                }
                alert("회원가입 성공")
                naivgate("/signin")
                console.log("signup success")
            } catch (error) {
                console.log(error);
                console.log("signup error")
                return;
            }
        }
        signUp();


        // const duplicateUsername = axios.get("http://localhost:8080/servlet_study_ldh/auth/signup/duplicated/username", option)
        // duplicateUsername
        // .then((response) => {
        //     console.log(response);
        //     const isDuplicateUsername = response.data;
        //     if(isDuplicateUsername) {
        //         alert("중복된 아이디입니다.");
        //     }else {
        //         alert("ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄹ.");
        //     }
        // }).catch((error) => {
        //     console.log("ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄻㄴㅇㄹ")
        // });
    }

    return (
        <>
            <h1>회원가입</h1>
            <div css={SInputLayout}>
                <input type="text" onChange={handleInputChange} name='username' placeholder='username'/>
            </div>
            <div css={SInputLayout}>
                <input type="password" onChange={handleInputChange} name='password' placeholder='password'/>
            </div>
            <div css={SInputLayout}>
                <input type="text" onChange={handleInputChange} name='name' placeholder='name'/>
            </div>
            <div css={SInputLayout}>
                <input type="text" onChange={handleInputChange} name='email' placeholder='email'/>
            </div>
            <div>
                <button onClick={handleSubmitClick}>가입하기</button>
            </div>
        </>
    );
}

export default Signup;