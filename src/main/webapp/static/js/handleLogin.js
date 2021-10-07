import {apiGet} from "./fetcher.js";

function changeContent() {
    document.querySelector("#main-title").textContent = "Login";
    document.querySelector("#name-input").innerHTML = "";
    document.querySelector("#second-password-input").innerHTML = "";
    document.querySelector("#login-link").textContent = "Registration";

    const submitButton = document.querySelector("#submit-button")
    submitButton.textContent = "Login";
    const newSubmitButton = submitButton.cloneNode(true);
    newSubmitButton.addEventListener("click", (e) => {
        apiGet(`/api/login?email=${document.querySelector("#user-email").value}&password=${
            document.querySelector("#user-password-1").value
        }`).then(() => {return null;});
    });
    let buttonContainer = document.querySelector("#button-container");
    buttonContainer.innerHTML = "";
    buttonContainer.appendChild(newSubmitButton);
}

function login() {
    const loginButton = document.querySelector("#login-link");
    loginButton.addEventListener("click", changeContent);
}

login();