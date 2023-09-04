import React from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import img from '../../Imgs/_Nj_gludh_yE1Wf-YRP_-XNvdsfT0mvsh0z85jwnK_jWY8EyCPF_h9n3Gc6DPWSBdihzvPr2SgTP9ZnG4_PjhyqG8YSAH9dwB6g0fo9a0YNYR3_EyaYbwon4RRnJb1GtNOprJomrqe6cF1zB3QslEg.png';

const SMainContainer = css`
    display: flex;

    overflow: hidden;
`;

const SMainImg = css`
    position: absolute;
    transform: translate(-50% , 50%);
    top: -50%;
    left: 50%;
`;

function Main(props) {
    return (
        <div css={SMainContainer}>
            <img src={img} css={SMainImg}/>
        </div>
    );
}

export default Main;