import { useNavigate } from "react-router-dom"

export default function Logout(){

  sessionStorage.removeItem("accessToken")
  localStorage.removeItem("accessToken")

  const nav = useNavigate()
  nav("/")

}