import {removeItemExport} from "./removeItem.js";
import {getCartContentCard} from "./htmlFactory.js";
import {changeQuantityExport} from "./changeQuantity.js";

export function fillModalExport() {fillModal();}

function fillModal() {
    const cartItemContainer = document.getElementById("current-item-container");
    fetch('/api/session/get')
        .then(response => (response.json())
        .then(data => {
            let newContent = "";
            for (let product of data) {
                newContent += getCartContentCard(product);
            }
            if (newContent === "") {
                cartItemContainer.innerHTML = '<h3>No item selected</h3><br><hr>';
            } else {
                cartItemContainer.innerHTML = newContent;
            }
            removeItemExport();
            changeQuantityExport();
        }));
}

function getRecommendedItems() {

}
