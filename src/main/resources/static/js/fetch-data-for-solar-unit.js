$(document).ready(function () {
    fetchAllForSolarUnit('pvpanels');
    fetchAllForSolarUnit('batteries');
    fetchAllForSolarUnit('controllers');
    fetchAllForSolarUnit('inverters');
    $("#mySolarUnits").on('click', fetchMySolarUnits);
    fetchAllForSolarPlant();
    $("#mySolarPlants").on('click', fetchMySolarPlants);
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
                '<td>' + x.acpower + '</td>' +
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

function fetchMySolarUnits(){
    const container=$("#userData")
    fetch(`http://localhost:8080/solar_units/all-for-user`)
        .then(responseHandler)
        .then(json=> {
            json.forEach((x, y)=>{
                if (y === 0) {
                    container.empty();
                    container
                        .append('<table id="mySolarUnits" class="table table-striped">' +
                            '<thead><tr scope="row">' +
                            '<th scope="col">#</th>' +
                            '<th scope="col">Panels Model</th>' +
                            '<th scope="col">Panels Count</th>' +
                            '<th scope="col">Inverter Model</th>' +
                            '<th scope="col">Controller Model</th>' +
                            '<th scope="col">Batteries Model</th>' +
                            '<th scope="col">Batteries Count</th>' +
                            '<th scope="col">Orientation</th>' +
                            '<th scope="col">Inclination</th>' +
                            '</tr>' +
                            '</thead><tbody>');
                }
                $('#userData table')
                    .append('<tr>' +
                        '<td>' + (y + 1) + '</td>' +
                        '<td>' + x.panelsModel + '</td>' +
                        '<td>' + x.panelsCount + '</td>' +
                        '<td>' + x.inverterModel + '</td>' +
                        '<td>' + x.chargeControllerModel + '</td>' +
                        '<td>' + x.batteryTypeModel + '</td>' +
                        '<td>' + x.batteryCellsCount + '</td>' +
                        '<td>' + x.orientation + '</td>' +
                        '<td>' + x.inclination+ '</td>' +
                        '</tr>');
            })
        });
}

function fetchAllForSolarPlant(){
    const container=$("#solarUnitsTableContainer")
    fetch(`http://localhost:8080/solar_units/all-for-user`)
        .then(responseHandler)
        .then(json=> {
            json.forEach((x, y)=>{
                if (y === 0) {
                    container.empty();
                    container
                        .append('<table id="mySolarUnits" class="table table-striped">' +
                            '<thead><tr scope="row">' +
                            '<th scope="col">#</th>' +
                            '<th scope="col">Panels Model</th>' +
                            '<th scope="col">Panels Count</th>' +
                            '<th scope="col">Inverter Model</th>' +
                            '<th scope="col">Controller Model</th>' +
                            '<th scope="col">Batteries Model</th>' +
                            '<th scope="col">Batteries Count</th>' +
                            '<th scope="col">Orientation</th>' +
                            '<th scope="col">Inclination</th>' +
                            '</tr>' +
                            '</thead><tbody>');
                }
                $('#solarUnitsTableContainer table')
                    .append('<tr>' +
                        '<td>' + (y + 1) + '</td>' +
                        '<td>' + x.panelsModel + '</td>' +
                        '<td>' + x.panelsCount + '</td>' +
                        '<td>' + x.inverterModel + '</td>' +
                        '<td>' + x.chargeControllerModel + '</td>' +
                        '<td>' + x.batteryTypeModel + '</td>' +
                        '<td>' + x.batteryCellsCount + '</td>' +
                        '<td>' + x.orientation + '</td>' +
                        '<td>' + x.inclination+ '</td>' +
                        '<td> <input type="checkbox" name="solarUnitsIds" value="'+x.id+'"/></td>'+
                        '</tr>');
            })
        });
}

function fetchMySolarPlants(){
    const container=$("#userData")
    fetch(`http://localhost:8080/solar_plants/all-for-user`)
        .then(responseHandler)
        .then(json=> {
            json.forEach((x, y)=>{
                if (y === 0) {
                    container.empty();
                    container
                        .append('<table id="mySolarPlants" class="table table-striped">' +
                            '<thead><tr scope="row">' +
                            '<th scope="col">#</th>' +
                            '<th scope="col">Town</th>' +
                            '<th scope="col">Municipality</th>' +
                            '<th scope="col">Country</th>' +
                            '<th scope="col">Units count</th>' +
                            '<th scope="col">Details</th>' +
                            '</tr>' +
                            '</thead><tbody>');
                }
                $('#userData table')
                    .append('<tr>' +
                        '<td>' + (y + 1) + '</td>' +
                        '<td>' + x.town + '</td>' +
                        '<td>' + x.municipality + '</td>' +
                        '<td>' + x.country + '</td>' +
                        '<td>' + x.solarUnitsCount + '</td>' +
                        '<td>' + '<a href="/solar_plants/details/'+x.id+'">Details</a>'+
                        '</td>' +
                        '</tr>');
            })
        });
}

function responseHandler(response) {
    if(response.status >= 400){
        throw new Error(response.statusText)
    }
    return response.json();
}

$('#clearCustomerData').on('click', ()=>{
    $('#userData').empty();
});