import { useNavigate } from "react-router-dom";

export default function Login(){

  const nav = useNavigate()

  function login(e){
    e.preventDefault();

    const form = new FormData(e.target);

    let all = Object.fromEntries(form.entries())

    console.log(all)

    fetch("http://localhost/api/member/login", {
      method:"POST",
      body:form
    })
    //.then(r=>r.json())
    .then(r=>{
      const header = r.headers;
      console.log(header.get("accessToken"))
      localStorage.setItem("accessToken", header.get("accessToken"))
      sessionStorage.setItem("accessToken", header.get("accessToken"))
      nav("/")
    })
    .catch(e=>console.log(e))

  }

  return(
    <>
      <h1>Login page</h1>
      <form onSubmit={login}>
        <div>
          <input type="text" name="username"></input>
          <input type="password" name="password"></input>
          <button>Login</button>
        </div>
      </form>
    </>
  )
}