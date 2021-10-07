import {apiGet} from "./fetcher.js";

function changeContent() {
    document.querySelector("#main-title").textContent = "Login";
    document.querySelector("#name-input").innerHTML = "";
    document.querySelector("#second-password-input").innerHTML = "";
    document.replaceChild(
        `<a href="/registration">Registration</a>`,
        document.querySelector("#login-link")
    );

    const submitButton = document.querySelector("#submit-button")
    submitButton.textContent = "Login";
    submitButton.addEventListener("click", sendLoginRequest);

    function sendLoginRequest() {
        apiGet(`/api/login?email=${document.querySelector("#user-email").value}&password=${
            document.querySelector("#user-password-1").value
        }`).then(() => {return null;});
    }
}

function login() {
    const loginButton = document.querySelector("#login-link");
    loginButton.addEventListener("click", changeContent);
}

login();