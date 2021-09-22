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

function checkoutButtonHandler(e) {
    fieldChecker();
    apiGet("http://0.0.0.0:8888/api/adminlog", logOutput);
}


async function apiGet(url, payload) {
    let response = await fetch(`${url}?logoutput=${payload}`, {
        method: 'GET',
    })
}