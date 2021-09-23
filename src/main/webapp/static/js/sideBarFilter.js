import {apiGet} from "./fetcher.js";

export function sidebarFilterExport () {sidebarFilter().then(() => {return null;})}
import {addToCartExport} from "./addToCart.js";
import {getNormalProductCard} from "./htmlFactory.js";

async function sidebarFilter () {
    const categoryMenuButtons = document.getElementsByClassName("category-menu-element");
    const supplierMenuButtons = document.getElementsByClassName("supplier-menu-element");
    const urlCategory = "/api/category?category=";
    const urlSupplier = "/api/supplier?supplier=";
    const urlNameSearch = "/api/search_name";
    const cardContainer = document.getElementById('products');
    const menuNameContainer = document.getElementById('current-menu');
    const searchByName = document.getElementById('search-by-name');

    searchByName.addEventListener('input', () => {
        fetch(`${urlNameSearch}?name=${searchByName.value}`)
            .then(response => response.json())
            .then(data => {
            let newContent = "";
            for (let product of data)
                newContent += getNormalProductCard(product);
            cardContainer.innerHTML = newContent;
            addToCartExport();
        }
    )
    });
    addLinkEventListeners(supplierMenuButtons, menuNameContainer, urlSupplier, cardContainer);
    addLinkEventListeners(categoryMenuButtons, menuNameContainer, urlCategory, cardContainer);
    filterForAllProducts(cardContainer);
}

function addLinkEventListeners(menuButtons, menuNameContainer, url, cardContainer) {
    for (const menuButton of menuButtons) {
        menuButton.addEventListener('click', () => {
            menuNameContainer.innerHTML = `<strong>${menuButton.innerText}</strong>`
            fetch(`${url}${menuButton.id}`)
                .then(response => response.json())
                .then(data => {
                    let newContent = "";
                    for (let product of data)
                        newContent += getNormalProductCard(product) + `<br>`;
                    cardContainer.innerHTML = newContent;
                    addToCartExport();
                });
        });
    }
}

function chooseRightLink(allProductLink) {
    for (let link of document.querySelectorAll(".category-menu-element"))
        if (link.textContent === "All products") allProductLink = link;
    return allProductLink;
}

function filterForAllProducts(cardContainer) {
    let allProductLink = null;
    allProductLink = chooseRightLink(allProductLink);
    allProductLink.addEventListener("click", async () => {
            let data = await apiGet("/api/session/recommend");
            let newContent = "";
            for (let product of data) {
                newContent += getNormalProductCard(product);
            }
            cardContainer.innerHTML = newContent;
        }
    );
}