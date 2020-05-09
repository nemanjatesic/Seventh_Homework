'use strict';

function loadCompanies() {
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let result = JSON.parse(this.response);

            let table = document.getElementById("avio_kompanije");

            for (let i = 0; i < result.length; i++) {
                let option = document.createElement('option');
                option.text = result[i].name
                option.value = JSON.stringify(result[i])
                table.appendChild(option)
            }
        }
    };
    xhttp.open("GET", "/rest/avioni/kompanije", true);
    xhttp.send();
}

function loadTickets(filterOneWay = null) {
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let result = JSON.parse(this.response);

            let table = document.getElementById("tickets-tbl");

            let oldTBody = table.tBodies[0];
            let newTBody = document.createElement("tBody");

            for (let i = 0 ; i < result.length ; i++) {
                let bRow = document.createElement("tr");

                let tdOneWay = document.createElement("td");
                tdOneWay.setAttribute('class', 'column1')
                tdOneWay.innerHTML = result[i].one_way;
                let tdFrom = document.createElement("td");
                tdFrom.setAttribute('class', 'column2')
                tdFrom.innerHTML = result[i].from;
                let tdTo = document.createElement("td");
                tdTo.setAttribute('class', 'column3')
                tdTo.innerHTML = result[i].to;
                let tdDepartDate = document.createElement("td");
                tdDepartDate.setAttribute('class', 'column4')
                tdDepartDate.innerHTML = result[i].depart_date;
                let tdReturnDate = document.createElement("td");
                tdReturnDate.setAttribute('class', 'column5')
                tdReturnDate.innerHTML = result[i].return_date;
                let tdCompany = document.createElement("td");
                tdCompany.setAttribute('class', 'column6')
                //let tmp = document.createElement("a")
                //tmp.setAttribute('href', result[i].avionskaKompanija.id)
                tdCompany.innerHTML = result[i].avionskaKompanija.name;
                //tmp.appendChild(tdCompany)


                bRow.appendChild(tdOneWay);
                bRow.appendChild(tdFrom);
                bRow.appendChild(tdTo);
                bRow.appendChild(tdDepartDate);
                bRow.appendChild(tdReturnDate);
                bRow.appendChild(tdCompany);

                newTBody.appendChild(bRow)
            }

            table.replaceChild(newTBody, oldTBody)
        }
    };

    if (filterOneWay == null)
        xhttp.open("GET", "/rest/avioni/karte", true);
    else {
        if (filterOneWay) {
            xhttp.open("GET", "/rest/avioni/filterKarte/true", true);
        }else {
            xhttp.open("GET", "/rest/avioni/filterKarte/false", true);
        }
    }
    xhttp.send();
}

function createTicket(one_way, from, to, depart_date, return_date, avionskaKompanija) {
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            loadTickets();
        }
    };

    if (!return_date) return_date = null
    else return_date = new Date(return_date.toString())
    depart_date = new Date(depart_date.toString())

    xhttp.open("POST", "/rest/avioni", true);
    xhttp.setRequestHeader("Content-Type", "application/json");

    let avionskaKompanijaJson = JSON.parse(avionskaKompanija);
    xhttp.send(JSON.stringify({one_way: one_way, from:from, to:to, depart_date:depart_date, return_date:return_date, avionskaKompanija: avionskaKompanijaJson}));
}

function validateTicket(one_way, from, to, depart_date, return_date, avionskaKompanija) {
    if (!from){ alert('Unesite polje from'); return false;}
    if (!to){ alert('Unesite polje to'); return false;}
    if (!avionskaKompanija){ alert('Unesite polje avionskaKompanija'); return false;}
    if (!depart_date) {alert('Unesite depart date'); return false;}
    if (!one_way) {
        if (!return_date) {
            alert('Unesite return date')
            return false
        }
        if (return_date <= depart_date) {
            alert('Return date mora biti nakon depart date-a')
            return false
        }
    }
    if (from === to) {
        alert('Mesto polaska i destinacija ne mogu biti isto mesto')
        return false
    }
    return true
}

function processFormTicket(e) {
    if (e.preventDefault) e.preventDefault();
    let formData = new FormData(e.target);

    let one_way = (formData.get("one_way_checkbox") != null);
    let from = formData.get("place_from");
    let to = formData.get("place_to");
    let depart_date = formData.get("depart_date");
    let return_date = formData.get("return_date");
    let avionskaKompanija = formData.get("avio_kompanije")
    
    if (!validateTicket(one_way, from, to , depart_date, return_date, avionskaKompanija))
        return

    createTicket(one_way, from, to , depart_date, return_date, avionskaKompanija);

    // Obavezno vratiti false da bi se pregazilo default-no ponasanje prilikom submit-a.
    return false;
}

function processFormFilter(e) {
    if (e.preventDefault) e.preventDefault();
    let formData = new FormData(e.target);

    let filterType = formData.get("filter_types")

    switch (filterType) {
        case 'all': loadTickets(); break;
        case 'one_way_true' : loadTickets(true); break;
        case 'one_way_false' : loadTickets(false); break;
    }

    // Obavezno vratiti false da bi se pregazilo default-no ponasanje prilikom submit-a.
    return false;
}

window.onload = function () { loadCompanies(); loadTickets();};

//document.getElementById("load-users-btn").addEventListener("click", loadUsers);
document.getElementById("return_date").disabled = false
document.getElementById("one_way_checkbox").onclick = function() {
    document.getElementById("return_date").disabled = this.checked
    if (this.checked)
        document.getElementById("return_date").value = ""
}

let tikcetForm = document.getElementById('add-ticket-form');
if (tikcetForm.attachEvent) {
    tikcetForm.attachEvent("submit", processFormTicket);
} else {
    tikcetForm.addEventListener("submit", processFormTicket);
}

let filterForm = document.getElementById('filter_form')
if (filterForm.attachEvent) {
    filterForm.attachEvent("submit", processFormFilter);
} else {
    filterForm.addEventListener("submit", processFormFilter);
}

// Dohvatanje query parametara iz url-a
let urlParams = new URLSearchParams(window.location.search);
let myParam = urlParams.get('myParam');
console.log(myParam);