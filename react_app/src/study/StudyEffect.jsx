import { useEffect, useState } from "react"

function StudyEffect(){

  const [count, setCount] = useState(0)

  function increase(){
    setCount(count+1);
  }

  //렌더링 할 떄마다 (Mount, status 변경)
  useEffect(()=>{
    console.log("Effect1")
  })

  //Mount(첫렌더링)할 때만 실행(status 변경 여부 상관없이)
  //의존성 배열
  useEffect(()=>{
    console.log("Effect2")
  }, [count])
  //빈배열은 2가 안나오고 count 넣으면 1,2 둘다 나옴
  
  //Mount 할때, count(특정한)status가 변경
  useEffect(()=>{
    console.log("Effect2")
  }, [count])


  useEffect(()=>{
    //Component가 Unmount될 때 실행하고 싶은 코드
    //clean up 코드
    return()=>{

    }
  })

  return (
    <>
      <h1>Use Effect</h1>
      <h1>{count}</h1>
      <button onClick={increase}>NEXT</button>
    </>
  )
}

export default StudyEffect