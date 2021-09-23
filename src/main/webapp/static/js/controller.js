import {addToCartExport} from "./addToCart.js";
import {handleSidebar} from "./handleSideBar.js";
import {sidebarFilterExport} from "./sideBarFilter.js";

function addEventListeners() {
    handleSidebar();
    sidebarFilterExport();
    addToCartExport();
}

window.onload = addEventListeners;