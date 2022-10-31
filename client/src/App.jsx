import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import Main from "./pages/Main.jsx";
import MyProfile from "./pages/MyProfile.jsx";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Main />}></Route>
          <Route path="/myprofile" element={<MyProfile />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
