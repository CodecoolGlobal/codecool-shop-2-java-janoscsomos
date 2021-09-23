const requiredFields = document.querySelectorAll(".form-control");
const checkoutButton = document.querySelector("button");


for (let field of requiredFields) {
    field.addEventListener("change", fieldHandler);
}


checkoutButton.addEventListener("click", checkoutButtonHandler);


let logOutput = [];
let orderOutput = [];

let cart = getCart();
console.log(cart);


function fieldHandler(e) {
    logOutput.push(`${e.target.id} input occurred`);
}

function fieldLog() {
    logOutput.push("FIELD STATUSES ON CHECKOUT EVENT")
    for (let field of requiredFields) {
        if (field.id === "email") {
            logOutput.push(field.id + ": " + field.value)
        } else {
            let validInput = field.value.length > 0 ? "yes" : "no"
            let fieldStatus = field.id + " valid input: " + validInput;
            logOutput.push(fieldStatus);
        }
    }
}

function getOrderDetails() {
    //let orderId =
    //let customerName =
    let customerEmail = document.getElementById("email").value;
    //let orderedItems =
    //let totalSum =
    orderOutput.push(customerEmail);
}

function fieldChecker() {
    let allOkay = true;
    for (let field of requiredFields) {
        if (field.value.length === 0) {
            allOkay = false;
        }
    }
    return allOkay;
}

function checkoutButtonHandler(e) {
    fieldLog();
    apiGet("http://0.0.0.0:8888/api/adminlog", logOutput, "logoutput");
    if (fieldChecker()) {
        getOrderDetails();
        apiGet("http://0.0.0.0:8888/api/order", orderOutput, "order");
    }
}


async function apiGet(url, payload, queryArgument) {
    let response = await fetch(`${url}?${queryArgument}=${payload}`, {
        method: 'GET',
    })
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