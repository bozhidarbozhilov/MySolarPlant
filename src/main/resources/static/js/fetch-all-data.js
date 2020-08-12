$(document).ready(function () {
        $('#allPanelsNav').on('click', (e)=>{
            e.stopPropagation();
            e.preventDefault();
            fetchAllPanelsForEdit('pvpanels');
        });
        $('#allControllersNav').on('click', (e)=>{
            e.stopPropagation();
            e.preventDefault();
            fetchAllPanelsForEdit('controllers');
        });
        $('#allBatteriesNav').on('click', (e)=>{
            e.stopPropagation();
            e.preventDefault();
            fetchAllPanelsForEdit('batteries');
        });
        $('#allInvertersNav').on('click', (e)=>{
            e.stopPropagation();
            e.preventDefault();
            fetchAllPanelsForEdit('inverters');
        });

    }
);

function fetchAllPanelsForEdit(unit) {
    console.log("text");
    fetch(`http://localhost:8080/${unit}/all`)
        .then(responseHandler)
        .then((json)=> {
            if(unit==='pvpanels'){
                createPanelsTable(json);
            }else if(unit==='batteries'){
                createBatteriesTable(json);
            }else if(unit==='controllers'){
                createControllersTable(json);
            }else if(unit==='inverters'){
                createInvertersTable(json);
            }
        });
}

function createPanelsTable(json){
    const container = $("#engineerData");
    console.log(container);
    json.forEach((x, y) => {
        if (y === 0) {
            container.empty();
            container
                .append('<table id="allPanelsTable" class="table table-striped">' +
                    '<thead><tr scope="row">' +
                    '<th scope="col">#</th>' +
                    '<th scope="col">Manufacturer</th>' +
                    '<th scope="col">Model</th>' +
                    '<th scope="col">Power</th>' +
                    '<th scope="col">Connector Type</th>' +
                    '<th scope="col">Voltage Pmax</th>' +
                    '<th scope="col">Current Pmax</th>'+
                    '<th scope="col">Actions</th></tr>' +
                    '</thead><tbody>');
        }
        $('#engineerData table')
            .append('<tr>' +
                '<td>' + (y + 1) + '</td>' +
                '<td>' + x.manufacturer + '</td>' +
                '<td>' + x.model + '</td>' +
                '<td>' + x.power + '</td>' +
                '<td>' + x.connector + '</td>' +
                '<td>' + x.voltageAtMaxPower + '</td>' +
                '<td>' + x.currentAtMaxPower + '</td>' +
                '<td><a href="/pvpanels/edit/'+x.id+'">Edit Panel</a></td>'+
                '<td><a href="/pvpanels/delete/'+x.id+'">Delete Panel</a></td>'+
                '</tr>');
    });

}

function createBatteriesTable(json){
    const container = $("#engineerData");
    json.forEach((x, y) => {
        if (y === 0) {
            container.empty();
            container
                .append('<table id="allBatteriesTable" class="table table-striped">' +
                    '<thead><tr scope="row">' +
                    '<th scope="col">#</th>' +
                    '<th scope="col">Manufacturer</th>' +
                    '<th scope="col">Model</th>' +
                    '<th scope="col">Capacity</th>' +
                    '<th scope="col">Voltage</th>' +
                    '<th scope="col">Connectors Type</th>' +
                    '<th scope="col">Terminals</th>'+
                    '<th scope="col">Actions</th></tr>' +
                    '</thead><tbody>');
        }
        $('#engineerData table')
            .append('<tr>' +
                '<td>' + (y + 1) + '</td>' +
                '<td>' + x.manufacturer + '</td>' +
                '<td>' + x.model + '</td>' +
                '<td>' + x.capacity + '</td>' +
                '<td>' + x.voltage + '</td>' +
                '<td>' + x.connectionType + '</td>' +
                '<td>' + x.terminals + '</td>' +
                '<td><a href="/batteries/edit/'+x.id+'">Edit Battery</a></td>'+
                '<td><a href="/batteries/delete/'+x.id+'">Delete Battery</a></td>'+
                '</tr>');
    });

}

function createControllersTable(json){
    const container = $("#engineerData");
    json.forEach((x, y) => {
        if (y === 0) {
            container.empty();
            container
                .append('<table id="allControllersTable" class="table table-striped">' +
                    '<thead><tr scope="row">' +
                    '<th scope="col">#</th>' +
                    '<th scope="col">Manufacturer</th>' +
                    '<th scope="col">Model</th>' +
                    '<th scope="col">Power</th>' +
                    '<th scope="col">Voltage</th>' +
                    '<th scope="col">Current</th>' +
                    '<th scope="col">Actions</th></tr>' +
                    '</thead><tbody>');
        }
        $('#engineerData table')
            .append('<tr>' +
                '<td>' + (y + 1) + '</td>' +
                '<td>' + x.manufacturer + '</td>' +
                '<td>' + x.model + '</td>' +
                '<td>' + x.power + '</td>' +
                '<td>' + x.voltage + '</td>' +
                '<td>' + x.current + '</td>' +
                '<td><a href="/controllers/edit/'+x.id+'">Edit Controller</a></td>'+
                '<td><a href="/controllers/delete/'+x.id+'">Delete Controller</a></td>'+
                '</tr>');
    });
}

function createInvertersTable(json){
    const container = $("#engineerData");
    json.forEach((x, y) => {
        if (y === 0) {
            container.empty();
            container
                .append('<table id="allInvertersTable" class="table table-striped">' +
                    '<thead><tr scope="row">' +
                    '<th scope="col">#</th>' +
                    '<th scope="col">Manufacturer</th>' +
                    '<th scope="col">Model</th>' +
                    '<th scope="col">AC Power</th>' +
                    '<th scope="col">Max AC Power</th>' +
                    '<th scope="col">Max PV Power</th>' +
                    '<th scope="col">Actions</th></tr>' +
                    '</thead><tbody>');
        }
        $('#engineerData table')
            .append('<tr>' +
                '<td>' + (y + 1) + '</td>' +
                '<td>' + x.manufacturer + '</td>' +
                '<td>' + x.model + '</td>' +
                '<td>' + x.acpower + '</td>' +
                '<td>' + x.maxACPower + '</td>' +
                '<td>' + x.maxPVPower + '</td>' +
                '<td><a href="/inverters/edit/'+x.id+'">Edit Inverter</a></td>'+
                '<td><a href="/inverters/delete/'+x.id+'">Delete Inverter</a></td>'+
                '</tr>');
    });
}

function responseHandler(response) {
    if(response.status >= 400){
        throw new Error(response.statusText)
    }
    return response.json();
}

$('#clearEngineerData').on('click', ()=>{
    $('#engineerData').empty();
});