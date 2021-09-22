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


function checkoutButtonHandler(e) {
    apiGet("http://0.0.0.0:8888/api/adminlog", logOutput);
}


async function apiGet(url, payload) {
    let response = await fetch(`${url}?logoutput=${payload}`, {
        method: 'GET',
    })
}