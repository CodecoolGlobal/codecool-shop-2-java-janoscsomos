const requiredFields = document.querySelectorAll(".form-control");
const checkoutButton = document.querySelector("button");


for (let field of requiredFields) {
    field.addEventListener("change", fieldHandler);
}


checkoutButton.addEventListener("click", checkoutButtonHandler);


let logOutput = [];




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

async function getOrderDetails() {
    await getCart().then(data =>  {
        let orderOutput = [];
        //let orderId = document.getElementsByClassName("orderId")[0].id;
        //let customerName =
        let customerEmail = document.getElementById("email").value;
        orderOutput.push(customerEmail);
        let orderedItems = [];
        //let totalSum;
        for (let item of data) {
            orderedItems.push(item['name']);
        }
        return orderOutput;
    })
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
        let order;
        getOrderDetails().then(data => {order = data});
        apiGet("http://0.0.0.0:8888/api/order", order, "order");
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