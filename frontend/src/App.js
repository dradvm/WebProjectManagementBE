import logo from "./logo.svg";
import GlobalStyles from "./GlobalStyles/globalStyle";
import Sidebar from "./Components/layouts/SidebarComponents/Sidebar/sidebar";
import Content from "./Components/layouts/Content/content";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import classNames from "classnames/bind";
import styles from "./App.module.scss";
import { createContext, useContext, useState } from "react";
import Header from "./Components/layouts/Header/header";

export const sidebarContext = createContext();
const cx = classNames.bind(styles);
function App() {
  const [sidebarIndexClicked, setSidebarIndexClicked] = useState(null);

  return (
    <sidebarContext.Provider
      value={{ sidebarIndexClicked, setSidebarIndexClicked }}
    >
      <GlobalStyles>
        <div className="App">
          <Header />
          <div className={cx("wrapper")}>
            <Sidebar />
            <Content />
          </div>
        </div>
      </GlobalStyles>
    </sidebarContext.Provider>
  );
}

export default App;
