import { useState, useRef } from "react"

function Add() {

  const [add, setAdd] = useState({
    boardTitle:"",
    boardWriter:"",
    boardContents:""
  })

  function inputChange(e){
    setAdd((prevState)=>({
      ...prevState,
      [e.target.name]:e.target.value
    }))
  }

  const title = useRef("title")
  const writer = useRef("writer")
  const contents = useRef("contents")

  function get(){
    const params = new URLSearchParams();
    params.append("boardTitle", add.boardTitle);
    params.append("boardWriter", add.boardWriter);
    params.append("boardContents", add.boardContents);
    
    fetch("http://localhost/notice", {
      method:"POST",
      body:params
    })
    .then(r=>r.json())
    .then(r=>console.log(r))
  }

  function get2(){
    console.log("title : ", title.current.value);
    console.log("writer : ", writer.current.value);
    console.log("contents : ", contents.current.value);

    const params = new URLSearchParams();
    params.append("boardTitle", add.title.current.value);
    params.append("boardWriter", add.writer.current.value);
    params.append("boardContents", add.contents.current.value);
  }

  function send(e){
    e.preventDefault();
    const formdata = new FormData(e.target); //FormData()는 <form></form>과 같음
    fetch("http://localhost/notice", {
      method:"POST",
      body:formdata
    })
    .then(r=>r.json())
    .then(r=>{
      if(r==true){
        console.log("등록")
      }
    })
  }

  return(
    <>
      <h1>Add Page</h1>
      <form onSubmit={send}>
        <input type="text" name="boardTitle" ref={title} onChange={inputChange}></input>
        <input type="text" name="boardWriter" ref={writer} onChange={inputChange}></input>
        <textarea name="boardContents" ref={contents} onChange={inputChange}></textarea>
        <button type="button" onClick={get}>USESTATE</button>
        <button type="button" onClick={get2}>USEREF</button>
        <button>USEFORM</button>
      </form>
    </>
  )

}

export default Add