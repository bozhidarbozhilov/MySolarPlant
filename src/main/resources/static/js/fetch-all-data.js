$(document).ready(function () {
        $('#allPanelsAdmin').on('click', ()=>fetchAllForAdmin('panels'));
        $('#allControllersAdmin').on('click', ()=>fetchAllForAdmin('controllers'));
        $('#allBatteriesAdmin').on('click', ()=>fetchAllForAdmin('batteries'));
        $('#allInvertersAdmin').on('click', ()=>fetchAllForAdmin('inverters'));

    }
);

function fetchAllForAdmin(unit) {
    fetch(`http://localhost:8080/${unit}/all`)
        .then(responseHandler)
        .then((json)=> {
            console.log(json)
        });
}

function responseHandler(response) {
    if(response.status >= 400){
        throw new Error(response.statusText)
    }
    return response.json();
}