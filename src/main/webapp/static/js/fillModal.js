import {removeItemExport} from "./removeItem.js";
import {getCartContentCard, getRecommendedItemCard} from "./htmlFactory.js";
import {changeQuantityExport} from "./changeQuantity.js";
import {apiGet} from "./fetcher.js";

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
            getRecommendedItems().then(() => {return null;});
        }));
}

async function getRecommendedItems() {
    let data = await apiGet("/api/session/recommend");
    const container = document.querySelector(".row");
    const amountOfRecommendedItems = 4;
    let newRecommendations = "";
    for (let add = 0; add < amountOfRecommendedItems; add++)
        newRecommendations += getRecommendedItemCard(
            data[Math.floor((Math.random() * data.length) + 1)]
        );
    container.innerHTML = newRecommendations;
}
