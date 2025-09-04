import { Link } from "react-router-dom"

function Header(){

  return(
    <>
      <h1>Header line</h1>
      <Link to="/">Home</Link>
      <Link to="/notice/list">Notice</Link>
      <Link to="/member/Login">Login</Link>


      <Link to="/study/param?num=10&name=winter">StudyParam</Link>
      <Link to="/study/param/10/winter">StudyParam2</Link>
      <Link to="/study/param" state={{age:10, user:"winter"}}>StudyParam3</Link>
    </>
  )
}

export default Header