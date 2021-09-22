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

function fieldChecker() {
    let fieldsAtCheckout = document.querySelectorAll(".form-control");
    logOutput.push("FIELD STATUSES ON CHECKOUT EVENT")
    for (let field of fieldsAtCheckout) {
        let fieldStatus = field.id + " length: " + field.value.length.toString();
        logOutput.push(fieldStatus);
    }
}

function checkoutButtonHandler(e) {
    fieldChecker();
    apiGet("http://0.0.0.0:8888/api/adminlog", logOutput);
}


async function apiGet(url, payload) {
    let response = await fetch(`${url}?logoutput=${payload}`, {
        method: 'GET',
    })
}