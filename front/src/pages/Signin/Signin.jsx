import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';

function Signin(props) {
    const naivgate = useNavigate();
    const [ signinInput, setSigninInput ] = useState({
        username: "",
        password: "",
    });
    const handleInputChange = (e) => {
        setSigninInput({
            ...signinInput,
            [e.target.name]: e.target.value
        });
    }

    const handleSigninClick = async () => {
        try {
            const resp = await axios.post("http://localhost:8080/servlet_study_ldh/auth/signin", signinInput);
        // 정보 노출 방지를 위해 조회(get) 작업이지만 post 사용
        // resp을 응답 받은 후(await) if문 실행
        if(!resp.data) {
            alert("아이디 혹은 비밀번호가 일치하지 않습니다.")
            console.log("signin fail")
            return;
        }
        alert("로그인 성공");
        console.log("signin success")
        naivgate("/")
        } catch (error) {
            console.log(error)
            console.log("signin error")
        }
    };

    return (
        <>
            <h1>로그인</h1>
            <div><input type="text" name='username' onChange={handleInputChange} placeholder='username'/></div>
            <div><input type="password" name='password' onChange={handleInputChange} placeholder='password'/></div>
            <div><button onClick={handleSigninClick}>로그인</button></div>
        </>
    );
}

export default Signin;