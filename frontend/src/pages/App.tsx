import Cakes from "./Cakes";
import About from "./About";
import NavbarComp from "../components/Navbar";
import PageNotFound from "./PageNotFound";
import { ToastContainer } from "react-toastify";
import "../styles/App.css";
import Cake from "./Cake";
import { Route, Routes } from "react-router-dom";

function App() {
  return (
    <div className="container fluid">
      <div id="content">
        <NavbarComp />

        <Routes>
          <Route path="/" element={<Cakes />} />
          <Route path="/cake/:id" element={<Cake />} />
          <Route path="/cake" element={<Cake />} />
          <Route path="/about" element={<About />} />
          <Route path="*" element={<PageNotFound />} />
        </Routes>
      </div>

      <ToastContainer autoClose={3000} hideProgressBar />
    </div>
  );
}

export default App;
