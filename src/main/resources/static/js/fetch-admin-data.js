$(document).ready(function () {
        $('#allUsers').on('click', (e)=>{
            e.stopPropagation();
            e.preventDefault();
            fetchAllUsers();
        });
        $('#allSolarUnits').on('click', (e)=>{
            e.stopPropagation();
            e.preventDefault();
            fetchAllSolarUnits('controllers');
        });
        $('#allSolarPlants').on('click', (e)=>{
            e.stopPropagation();
            e.preventDefault();
            fetchAllSolarPlants('batteries');
        });

    }
);

