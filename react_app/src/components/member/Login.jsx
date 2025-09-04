export default function Login(){

  function login(e){
    e.preventDefault();

    const form = new FormData(e.target);

    let all = Object.fromEntries(form.entries())

    console.log(all)

    fetch("http://localhost/api/member/login", {
      method:"POST",
      body:form
    })
    .then(r=>r.json())
    .then(r=>console.log(r))
    .catch(e=>console.log(e))

  }

  return(
    <>
      <h1>Login page</h1>
      <form>
        <div onSubmit={login}>
          <input type="text" name="username"></input>
          <input type="password" name="password"></input>
          <button>Login</button>
        </div>
      </form>
    </>
  )
}