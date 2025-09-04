import Header from './layout/Header';
import AppRoutes from './route/AppRoutes';
import StudyProps from './study/StudyProps';

function App() {

  let age = 10;
  let m = {age:20, user:"iu"}

  return (
    <div>
      <Header></Header>
      {/* <StudyProps age="20" user="winter"></StudyProps> */}
      {/* <StudyProps m={m}></StudyProps> */}
      <AppRoutes></AppRoutes>
    </div>
  )
}

export default App