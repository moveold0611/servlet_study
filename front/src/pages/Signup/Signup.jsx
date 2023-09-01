import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

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

    const [ singupUser, setSignupUser ] = useState({
        username: "",
        password: "",
        name: "",
        email: ""
    });

    const handleInputChange = (e) => {
        setSignupUser({
            ...singupUser,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmitClick = () => {
        const option = {
            params: {
                username: singupUser.username
            }
        }

        axios.get("http://localhost:8080/servlet_study_ldh/auth/signup/duplicated/username", option)
        .then((response) => {
            axios.post("http://localhost:8080/servlet_study_ldh/auth/signup", singupUser)
            .then((response) => {
                alert(response.data);
                naivgate("/signin");
            })
        }).catch((error) => {
            alert("중복된 아이디입니다.");
        });
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