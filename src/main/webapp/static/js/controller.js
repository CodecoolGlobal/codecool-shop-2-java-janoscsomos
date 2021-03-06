import {addToCartExport} from "./addToCart.js";
import {handleSidebar} from "./handleSideBar.js";
import {sidebarFilterExport} from "./sideBarFilter.js";
import {fillModalExport, getRecommendedItems} from "./fillModal.js";

function addEventListeners() {
    fillModalExport();
    handleSidebar();
    sidebarFilterExport();
    addToCartExport();
    getRecommendedItems().then(() => {return null;});
}

window.onload = addEventListeners;