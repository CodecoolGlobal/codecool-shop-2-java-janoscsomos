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
    console.log(logOutput);
}