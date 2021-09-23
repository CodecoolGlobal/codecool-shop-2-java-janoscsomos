export function changeQuantityExport() {changeQuantity()}

import {fillModalExport} from "./fillModal.js";

function changeQuantity() {
    const addButtons = document.getElementsByClassName('add-button');
    for (const addButton of addButtons) {

        addButton.addEventListener('click', () => {
            console.log(addButton.id);
            fetch(`/api/session/quantity?item=${addButton.id}&relation=add`).then(() => {fillModalExport();});

        })
    }
    const minusButtons = document.getElementsByClassName('minus-button');
    for (const minusButton of minusButtons) {
        minusButton.addEventListener('click', () => {
            fetch(`/api/session/quantity?item=${minusButton.id}&relation=minus`).then(() => {fillModalExport();});
        })
    }
}
