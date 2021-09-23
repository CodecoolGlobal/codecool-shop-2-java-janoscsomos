export function removeItemExport() {removeItem();}

import {getCartContentCard} from "./htmlFactory.js";

function removeItem() {
    const removeButtons = document.getElementsByClassName('remove-item-from-cart');
    const cartItemContainer = document.getElementById("current-item-container");
    for (const removeButton of removeButtons) {
        removeButton.addEventListener('click', () => {
            fetch(`/api/session/remove?item=${removeButton.id}`)
                .then(response => response.json())
                .then(data => {
                    let newContent = "";
                    for (let product of data) {
                        newContent += getCartContentCard(product);
                    }
                    if (newContent === "") {
                        cartItemContainer.innerHTML = "No item selected yet!";
                    } else {
                        cartItemContainer.innerHTML = newContent;
                    }
                    removeItem();
                })
        })
    }
}
