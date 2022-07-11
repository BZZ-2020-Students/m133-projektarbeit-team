/**
 * 2fa
 *
 * @author  Nadina Shirin Amsler (shirin197)
 * @since   2022-07-11
 * @version 1.0
 */

/**
 * view-controller for 2fa-form
 */
document.addEventListener("DOMContentLoaded", () => {
    showSecret();
    document.getElementById("2faForm").addEventListener("submit", twoFAUser);
});

/**
 * 2fa-user-function
 * @param event
 */
function twoFAUser(event) {
    event.preventDefault();
    showMessage("", "info");

    fetch("./resource/user/2fa",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: "secret=" + document.getElementById("secret").value
        })
        .then(function (response) {
            if (!response.ok) {
                showMessage("Falsche Zahl", "error");
            } else loginSuccess(response)
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * login-success-function
 */
function loginSuccess() {
    window.location.href = "./team.html";
}

function showSecret() {
    const index = getCookie("secret");
    console.log(index);
    if (index != null) {
        document.getElementById("index").innerText = index;

    } else {
        window.location.href = "./index.html";
    }
}