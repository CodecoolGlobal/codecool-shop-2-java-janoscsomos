export function removeItemExport() {removeItem();}

import {getCartContentCard} from "./htmlFactory.js";
import {getTotalPayableExport} from "./getTotalPayable.js";
import {changeQuantityExport} from "./changeQuantity.js";

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
                        cartItemContainer.innerHTML = '<h3>No item selected</h3><br><hr>';
                    } else {
                        cartItemContainer.innerHTML = newContent;
                    }
                    removeItem();
                    getTotalPayableExport();
                    changeQuantityExport();

                })
        })
    }
}
