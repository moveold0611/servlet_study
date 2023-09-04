import React from 'react';
import { css } from "@emotion/react";
import { Link } from 'react-router-dom';
/** @jsxImportSource @emotion/react */

const SLayout = css`
  display: flex;
  justify-content: space-around;
  align-items: center;
  border-bottom: 1px solid #dbdbdb;
  width: 100%;
  justify-items: 70px;
`;

function MainHeader(props) {

    return (
        <div css={SLayout}>
            <Link to={"/main"}>메인</Link>
            <Link to={"/signin"}>로그인</Link>
            <Link to={"/signup"}>회원가입</Link>
            <Link to={"/async"}>비동기</Link>
        </div>
    );
}

export default MainHeader;