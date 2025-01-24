$(document).ready(function() {
    console.log("ready!");

    var button = $("#submit_button");
    var searchBox = $("#search_text");
    var resultsTable = $("#results table tbody");
    var resultsWrapper = $("#results");

    button.on("click", function() {
        $.ajax({
            method: "POST",
            contentType: "application/json",
            data: createRequest(),
            url: "procesar_datos",
            dataType: "json",
            success: onHttpResponse
        });
    });

    function createRequest() {
        var searchQueryTmp = searchBox.val();

        var frontEndRequest = {
            searchQuery: parseInt(searchQueryTmp)
        };

        return JSON.stringify(frontEndRequest);
    }

    function onHttpResponse(data, status) {
        if (status === "success") {
            console.log(data);
            addResults(data);
        } else {
            alert("Error al conectarse al servidor: " + status);
        }
    }

    function addResults(data) {
        resultsTable.empty();

        if (data) {
            var cadena = data.cadena;
            var cantidad = data.cantidad; // Debería devolver "asdf"

            resultsWrapper.show();
            resultsTable.append(
                "<thead><tr><th>Valor n:</th><th>Resultados:</th></tr></thead>" +
                "<tr><td>" + cadena + "</td><td>" + cantidad + "</td></tr>"
            );
        } else {
            resultsWrapper.hide();
            alert("No se recibieron resultados.");
        }
    }
});
