/**
 * view-controller für das Login-Formular
 *loginform
 * @author  Nadina Shirin Amlser (shirin197)
 * @version 2.0
 * @since   2022-07-09
 */

$(document).ready(function () {
    $("loginform").submit(login);
});

function login(form){
    form.preventDefault();
    $
        .ajax({
            url: "./resource/user/login",
            dataType: 'text',
            type: "POST",
            data: $("#loginform").serialize()
        })
        .done(function (){
            window.location.href = "./index.html";
        })
        .fail(function (xhr, status, errorThrown){
            if (xhr.status === 404){
                $("#message").text("Falscher Benutzername oder Passwort");
            } else {
                $("#message").text("Es ist ein Fehler aufgetreten");
            }
        });

}

function sendLogoff(){

}

