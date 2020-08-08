$(document).ready(function () {
    fetchAllForSolarUnit('pvpanels');
    fetchAllForSolarUnit('batteries');
    fetchAllForSolarUnit('controllers');
    fetchAllForSolarUnit('inverters');
});


function fetchAllForSolarUnit(unit) {
    fetch(`http://localhost:8080/${unit}/all_solar_unit`)
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
    const container = $("#panelsTableContainer");
    json.forEach((x, y) => {
        if (y === 0) {
            container.empty();
            container
                .append('<table id="panelsTable" class="pvpanels-solar-units disable-checkboxes table table-striped collapse">' +
                    '<thead><tr scope="row">' +
                    '<th scope="col">#</th>' +
                    '<th scope="col">Manufacturer</th>' +
                    '<th scope="col">Model</th>' +
                    '<th scope="col">Power</th>' +
                    '<th scope="col">Connector Type</th>' +
                    '<th scope="col">Voltage Pmax</th>' +
                    '<th scope="col">Current Pmax</th>'+
                    '<th scope="col">Choose</th></tr>' +
                    '</thead><tbody>');
        }
        $('#panelsTableContainer table')
            .append('<tr>' +
                '<td>' + (y + 1) + '</td>' +
                '<td>' + x.manufacturer + '</td>' +
                '<td>' + x.model + '</td>' +
                '<td>' + x.power + '</td>' +
                '<td>' + x.connector + '</td>' +
                '<td>' + x.voltageAtMaxPower + '</td>' +
                '<td>' + x.currentAtMaxPower + '</td>' +
                '<td> <input type="checkbox" id="panel" name="panelId" value="'+x.id+'"/></td>'+
                '</tr>');
    });
    disableCheckbox("pvpanels-solar-units");

}

function createBatteriesTable(json){
    const container = $("#batteriesTableContainer");
    json.forEach((x, y) => {
        if (y === 0) {
            container.empty();
            container
                .append('<table id="batteryTable" class="batteries-solar-units disable-checkboxes table table-striped collapse">' +
                    '<thead><tr scope="row">' +
                    '<th scope="col">#</th>' +
                    '<th scope="col">Manufacturer</th>' +
                    '<th scope="col">Model</th>' +
                    '<th scope="col">Capacity</th>' +
                    '<th scope="col">Voltage</th>' +
                    '<th scope="col">Connectors Type</th>' +
                    '<th scope="col">Terminals</th>'+
                    '<th scope="col">Choose</th></tr>' +
                    '</thead><tbody>');
        }
        $('#batteriesTableContainer table')
            .append('<tr>' +
                '<td>' + (y + 1) + '</td>' +
                '<td>' + x.manufacturer + '</td>' +
                '<td>' + x.model + '</td>' +
                '<td>' + x.capacity + '</td>' +
                '<td>' + x.voltage + '</td>' +
                '<td>' + x.connectionType + '</td>' +
                '<td>' + x.terminals + '</td>' +
                '<td> <input type="checkbox" id="battery" name="batteryId" value="'+x.id+'"/></td>'+
                '</tr>');
    });
    disableCheckbox("batteries-solar-units");

}

function createControllersTable(json){
    const container = $("#controllersTableContainer");
    json.forEach((x, y) => {
        if (y === 0) {
            container.empty();
            container
                .append('<table id="controllersTable" class="controllers-solar-units table table-striped collapse">' +
                    '<thead><tr scope="row">' +
                    '<th scope="col">#</th>' +
                    '<th scope="col">Manufacturer</th>' +
                    '<th scope="col">Model</th>' +
                    '<th scope="col">Power</th>' +
                    '<th scope="col">Voltage</th>' +
                    '<th scope="col">Current</th>' +
                    '<th scope="col">Choose</th></tr>' +
                    '</thead><tbody>');
        }
        $('#controllersTableContainer table')
            .append('<tr>' +
                '<td>' + (y + 1) + '</td>' +
                '<td>' + x.manufacturer + '</td>' +
                '<td>' + x.model + '</td>' +
                '<td>' + x.power + '</td>' +
                '<td>' + x.voltage + '</td>' +
                '<td>' + x.current + '</td>' +
                '<td> <input type="checkbox" id="controller" name="chargeControllerId" value="'+x.id+'"/></td>'+
                '</tr>');
    });
    disableCheckbox("controllers-solar-units")
}
function createInvertersTable(json){
    const container = $("#invertersTableContainer");
    json.forEach((x, y) => {
        if (y === 0) {
            container.empty();
            container
                .append('<table id="invertersTable" class="inverters-solar-units table table-striped collapse">' +
                    '<thead><tr scope="row">' +
                    '<th scope="col">#</th>' +
                    '<th scope="col">Manufacturer</th>' +
                    '<th scope="col">Model</th>' +
                    '<th scope="col">AC Power</th>' +
                    '<th scope="col">Max AC Power</th>' +
                    '<th scope="col">Max PV Power</th>' +
                    '<th scope="col">Choose</th></tr>' +
                    '</thead><tbody>');
        }
        $('#invertersTableContainer table')
            .append('<tr>' +
                '<td>' + (y + 1) + '</td>' +
                '<td>' + x.manufacturer + '</td>' +
                '<td>' + x.model + '</td>' +
                '<td>' + x.ACPower + '</td>' +
                '<td>' + x.maxACPower + '</td>' +
                '<td>' + x.maxPVPower + '</td>' +
                '<td> <input type="checkbox" id="inverter" name="inverterId" value="'+x.id+'"/></td>'+
                '</tr>');
    });
    disableCheckbox("inverters-solar-units");
}
function disableCheckbox(forClass) {
    const pvPanelsCheckbox = $(`.${forClass} input:checkbox`);
    pvPanelsCheckbox.on('change',function(){
        pvPanelsCheckbox.not(this).prop('disabled', this.checked);
    });
}

function responseHandler(response) {
    if(response.status >= 400){
        throw new Error(response.statusText)
    }
    return response.json();
}