import {apiGet} from "./fetcher.js";

function changeContent() {
    document.querySelector("#main-title").textContent = "Login";
    document.querySelector("#name-input").innerHTML = "";
    document.querySelector("#second-password-input").innerHTML = "";
    document.querySelector("#login-link").textContent = "Registration";
    document.querySelector("#login-link").addEventListener("click", () => {
        window.location.href = "/registration-and-login";
    })
    // Clone submit button to get rid of event listener
    const submitButton = document.querySelector("#submit-button")
    submitButton.textContent = "Login";
    const newSubmitButton = submitButton.cloneNode(true);
    // Define new event listener:
    newSubmitButton.addEventListener("click", (e) => {
        apiGet(`/api/login?email=${document.querySelector("#user-email").value}&password=${
            document.querySelector("#user-password-1").value
        }`).then(() => {return null;});
    });
    // Change the buttons:
    let buttonContainer = document.querySelector("#button-container");
    buttonContainer.innerHTML = "";
    buttonContainer.appendChild(newSubmitButton);
}

async function login() {
    const loginButton = document.querySelector("#login-link");
    await loginButton.addEventListener("click", changeContent);
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get("state") === "login") {
        changeContent();
    }
}

login();