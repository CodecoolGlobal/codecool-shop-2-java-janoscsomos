getCart().then(data => {
    let idP = document.getElementById("order-id");
    idP.textContent = "Order ID: " + localStorage.getItem("orderId");
    let detailsDiv = document.querySelector("#product-list");
    let totalSum = 0;
    let prodNumber = 0;
    let newProdList = "";
    for (let item of data) {
        newProdList += item['amount'] + " " + item['name'] + "(s), ";
        prodNumber++;
    }
    detailsDiv.textContent = newProdList.substring(0, newProdList.length - 2);
    for (let item of data) {
        totalSum += parseFloat(item['defaultPrice']) * parseFloat(item['amount']);
    }
    let sum = document.createElement('p');
    document.querySelector("#item-number").textContent = prodNumber.toString() + " items";
    document.querySelector(".subtotal").textContent = "$" + totalSum.toFixed(2).toString();
    document.querySelector(".total-price").textContent = "$" + (totalSum + 1.80).toFixed(2).toString();
    detailsDiv.appendChild(sum);
}).then(clearCart);

async function clearCart() {
    await fetch("/api/session/removeAll", {method: 'GET',})
}

async function getCart() {
    let response = await fetch("/api/session/get", {
        method: 'GET',
    })
    if (response.status === 200) {
        let data = response.json()
        return data
    }
}
