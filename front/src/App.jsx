import { Global } from "@emotion/react";
import MainLayout from "./components/MainLayout/MainLayout";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import React from 'react';
import { Route, Routes } from "react-router-dom";
import Signup from "./pages/Signup/Signup";
import Asynchronous from "./pages/Asynchronous/Asynchronous";
import Signin from "./pages/Signin/Signin";
import Main from "./pages/Main/Main";

const SCommon = css`
    * {
      box-sizing: border-box;
    }
`;

function App() {
  return (
    <>
      <Global styles={SCommon}/>
      <MainLayout >
        <Routes>
          <Route path="/main" element={ <Main />}/> 
          <Route path="/signin" element={ <Signin />}/>
          <Route path="/signup" element={<Signup />}/>
          <Route path="/async" element={<Asynchronous />}/>
        </Routes>
      </MainLayout>
    </>
  );
}

export default App;
