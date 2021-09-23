import {apiGet} from "./fetcher.js";

export async function getTotalPrice() {
    let data = await apiGet("/api/session/get");
    let totalSum = 0;
    for (let product of data) {
        let currentAmount = parseFloat(product.amount) * parseFloat(product.defaultPrice);
        totalSum += currentAmount;
    }
    console.log(totalSum.toFixed(2).toString());
    document.querySelector("#total-amount-number").textContent =
        "Total amount: " + totalSum.toFixed(2).toString() + " $";
}