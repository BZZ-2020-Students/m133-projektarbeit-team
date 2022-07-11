
const userRole = getCookie("userRole");

document.addEventListener("DOMContentLoaded", () => {
    showNav(userRole);
    showHeadings();
    readTeam();

});

function showHeadings(){
    const sort = getSort();
    const ids = ["teamname", "gruendungsdatum", "teamID"];
    const labels = ["teamname", "gruendungsdatum", "teamID"];

    let row = document.getElementById("headings");
    row.innerText = "";
    row.insertCell(-1);

    for (let i=0; i<labels.length; i++) {
        let cell = row.insertCell(-1);
        if (ids[i] !== sort[0]) {
            cell.innerHTML = labels[i];
        } else if (sort[1] === "ASC") {
            cell.innerHTML = "&uarr;&nbsp;" + labels[i];
        } else {
            cell.innerHTML = labels[i] + "&darr;&nbsp;";
        }
        cell.id=ids[i];
        cell.addEventListener("click", setSort);
    }
}


function getSort(){
    let sortField = readStorage("sortField");
    let sortOrder = readStorage("sortOrder");
    if (!sortField || sortField.length === 0) {
        sortField = "teamName";
        sortOrder = "ASC";
    }
    return [sortField, sortOrder];
}

function setSort(){
    let sort = getSort();
    let field = event.target.id;
    let order = "ASC";
    if (field === sort[0]) {
        if (sort[1] === "ASC") {
            order = "DESC";
        }
    }
    sessionStorage.setItem("sortField", field);
    sessionStorage.setItem("sortOrder", order);
    readTeam();
}

function readTeam() {
    let url = "./resource/team/list";
    let sorting = getSort();
    url += "?sortField=" + sorting[0];
    url += "&sort=" + sorting[1];

    let filter = getFilter();
    url += "&filterField=" + filter[0];
    url += "&filter=" + filter[1];

    fetch(url , {
        headers: {"Authorization": "Bearer " + readStorage("token")}
    })
        .then(function(response) {
            if (response.ok){
                return response;
            } else if (response.status === 401) {
                window.location.href = "./login.html";
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showTeamList(data);
        })
        .catch(function(error) {
            console.log(error);
        });
}

function showTeamList(data){
    showHeadings();
    let tBody = document.getElementById("ListTeam");
    tBody.innerHTML = "";
    data.forEach(team => {
        let row = tBody.insertRow(-1);
        let button = document.createElement("button");
        if(userRole === "admin" || userRole === "user")
            button.innerHTML = "&#9998;";
        else
            button.innerHTML = "&#128065;";
        button.type = "button";
        button.name = "editTeam";
        button.setAttribute("data-teamID", team.teamID);
        button.addEventListener("click", editTeam);
        row.insertCell(-1).appendChild(button);

        row.insertCell(-1).innerHTML = team.teamName;
        row.insertCell(-1).innerHTML = team.gruendungsdatum;
        row.insertCell(-1).innerHTML = team.teamID;

        if (userRole === "admin") {
            button = document.createElement("button");
            button.innerHTML = "&#10006;";
            button.type = "button";
            button.name = "deleteTeam";
            button.setAttribute("data-teamID", team.teamID);
            button.addEventListener("click", deleteTeam);
            row.insertCell(-1).appendChild(button);
        }
    });

    if (userRole === "admin") {
        document.getElementById("addButton").innerHTML = "<a href='./teameditor.html'><button>Team hinzufügen</button></a>";
    }
}

function getFilter(){
    let filterField = readStorage("filterField");
    let filterOrder = readStorage("filterValue");
    if (!filterField || filterField.length === 0) {
        filterField = "";
        filterOrder = "";
    }
    return [filterField, filterOrder];
}

function deleteTeam(){
    const button = event.target;
    const teamID = button.getAttribute("data-teamID");

    fetch("./resource/team/delete/" + teamID, {
        method: "DELETE",
        headers: {"Authorization": "Bearer " + readStorage("token")}
    })
        .then(function(response) {
            if (response.ok){
                return response;
            } else if (response.status === 401) {
                window.location.href = "./team.html";
            } else {
                console.log(response);
            }
        })
        .catch(function(error) {
            console.log(error);
        });
}

function editTeam(){
    const button = event.target;
    const teamID = button.getAttribute("data-teamID");
    window.location.href = "./teameditor.html?id=" + teamID;

}


