export function fillModalExport() {fillModal();}

import {getCartContentCard} from "./htmlFactory.js";

function fillModal() {
    const cartItemContainer = document.getElementById("current-item-container");
    fetch('/api/session/get')
        .then(response => (response.json())
        .then(data => {
            let newContent = "";
            for (let product of data) {
                newContent += getCartContentCard(product);
            }
            cartItemContainer.innerHTML = newContent;
        }));


}
