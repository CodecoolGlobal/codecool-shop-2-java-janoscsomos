import {removeItemExport} from "./removeItem.js";
import {getCartContentCard, getRecommendedItemCard} from "./htmlFactory.js";
import {changeQuantityExport} from "./changeQuantity.js";
import {getTotalPayableExport} from "./getTotalPayable.js";
import {apiGet} from "./fetcher.js";

export function fillModalExport() {fillModal();}

function fillModal() {
    const cartItemContainer = document.getElementById("current-item-container");
    const cartButtonNumber = document.getElementById('number-cart-contains')
    fetch('/api/session/get')
        .then(response => (response.json())
        .then(data => {
            let itemCounter = 0;
            let newContent = "";
            for (let product of data) {
                itemCounter += 1;
                newContent += getCartContentCard(product);
            }
            if (newContent === "") {
                cartItemContainer.innerHTML = '<h3>No item selected</h3><br><hr>';
            } else {
                cartItemContainer.innerHTML = newContent;
            }
            cartButtonNumber.innerText = itemCounter.toString();
            removeItemExport();
            changeQuantityExport();
            getTotalPayableExport();
        }));
}

export async function getRecommendedItems() {
    let data = await apiGet("/api/session/recommend");
    const container = document.querySelector(".row");
    const amountOfRecommendedItems = 4;
    let newRecommendations = "";
    for (let add = 0; add < amountOfRecommendedItems; add++)
        newRecommendations += getRecommendedItemCard(
            data[Math.floor((Math.random() * data.length) + 1)]
        );
    container.innerHTML = newRecommendations;
    for (let button of container.querySelectorAll(".mb-2")) {
        button.addEventListener(
            "click", async (clickEvent) => {
                clickEvent.preventDefault();
                await fetch(`api/session/add?productName=${button.parentElement.id}`);
                fillModalExport();
            }
        );
    }
}
