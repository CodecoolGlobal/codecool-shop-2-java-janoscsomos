getCart().then(data => {
    let idP = document.getElementById("order-id");
    idP.textContent = "Order ID: " + localStorage.getItem("orderId");
    let detailsDiv = document.getElementById("order-details");
    let totalSum = 0;
    for (let item of data) {
        let itemName = document.createElement('p');
        itemName.textContent = item['amount'] + " " + item['name'];
        detailsDiv.appendChild(itemName);
    }
    for (let item of data) {
        totalSum += parseFloat(item['defaultPrice']) * parseFloat(item['amount']);
    }
    let sum = document.createElement('p');
    sum.textContent = totalSum.toString() + " USD"
    detailsDiv.appendChild(sum);
}).then(clearCart);

async function clearCart() {
    await fetch("http://0.0.0.0:8888/api/session/removeAll", {method: 'GET',})
}

async function getCart() {
    let response = await fetch("http://0.0.0.0:8888/api/session/get", {
        method: 'GET',
    })
    if (response.status === 200) {
        let data = response.json()
        return data
    }
}
