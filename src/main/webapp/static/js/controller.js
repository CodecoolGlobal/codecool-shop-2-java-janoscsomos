import {addToCartExport} from "./addToCart.js";
import {handleSidebar} from "./handleSideBar.js";
import {sidebarFilterExport} from "./sideBarFilter.js";
import {fillModalExport} from "./fillModal.js";

function addEventListeners() {
    fillModalExport();
    handleSidebar();
    sidebarFilterExport();
    addToCartExport();
}

window.onload = addEventListeners;