import {
  faComment,
  faDiagramProject,
  faList,
  faTrash,
} from "@fortawesome/free-solid-svg-icons";
import CreateFrom from "../CreateForm/createForm";
import SidebarItem from "../SidebarItem/sidebarItem";
import styles from "./sidebar.module.scss";
import classNames from "classnames/bind";
import { useContext } from "react";
import { sidebarContext } from "../../../../App";

const cx = classNames.bind(styles);
function Sidebar() {
  const { sidebarIndexClicked, setSidebarIndexClicked } =
    useContext(sidebarContext);
  const sidebarItems = [
    { name: "Trang chủ", icon: "faHouse" },
    { name: "Dự án", icon: "faDiagramProject" },
    { name: "Thông báo", icon: "faComment" },
    { name: "Danh mục Forms", icon: "faList" },
    { name: "Thùng rác", icon: "faTrash" },
  ];
  return (
    <div className={cx("wrapper")}>
      <CreateFrom />
      <div className={cx("sidebar-item-wrapper")}>
        {sidebarItems.map((item, index) => {
          return (
            <SidebarItem
              key={index}
              index={index}
              name={item.name}
              icon={item.icon}
              onClick={() => setSidebarIndexClicked(index)}
              active={sidebarIndexClicked === index}
            />
          );
        })}
      </div>
    </div>
  );
}

export default Sidebar;
