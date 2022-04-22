import "../Components Styles/navbar.css";
const react = require("react");

function NavBar() {
  return (
    <div>
      <div className="app-name-container">
        <h1 className="app-name">APP NAME</h1>
      </div>
      <div class="navbar">
        <a class="active" href="#home">
          Home
        </a>
        <a href="#news">News</a>
        <a href="#contact">Contact</a>
        <a href="#about">About</a>
      </div>
    </div>
  );
}
export default NavBar;
