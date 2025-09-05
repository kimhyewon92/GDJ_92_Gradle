// 한개뿐일때

import { Route, Routes } from "react-router-dom";
import Index from "../components/Index";
import List from "../components/board/List";
import Add from "../components/board/Add";
import StudyParam from "../study/StudyParam";
import Login from "../components/member/Login";
import Logout from "../components/member/Logout";


export default function AppRoutes(){
  return(
    <>
      <Routes>
        <Route path="/" element={<Index></Index>}></Route>
        <Route path="/notice/">
          <Route path="list" element={<List></List>}></Route>
          <Route path="add" element={<Add></Add>}></Route>
        </Route>
        <Route path="/member">
          <Route path="login" element={<Login></Login>}></Route>
          <Route path="logout" element={<Logout></Logout>}></Route>
        </Route>

        <Route>
          <Route path="/study/param" element={<StudyParam></StudyParam>}></Route>
          <Route path="/study/param/:num/:name" element={<StudyParam></StudyParam>}></Route>
        </Route>
      </Routes>
    </>
  )
}