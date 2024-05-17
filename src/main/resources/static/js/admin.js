function searchTable() {
    var searchText = document.getElementById('searchInput').value.toLowerCase();
    var rows = document.querySelectorAll('#urls_table tbody tr');

    rows.forEach(function(row) {
        var cells = row.getElementsByTagName('td');
        var foundMatch = false;

        for (var i = 0; i < cells.length; i++) {
            if (cells[i].textContent.toLowerCase().indexOf(searchText) > -1) {
                foundMatch = true;
                break;
            }
        }

        if (foundMatch) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    });
}

function sortTableByDate(columnIndex, reverse) {
    var table = document.getElementById("urls_table");
    var rows = Array.from(table.rows).slice(1);
    var sortedRows = rows.sort((a, b) => {
        var xDateValue = Date.parse(a.getElementsByTagName("td")[columnIndex].innerHTML);
        var yDateValue = Date.parse(b.getElementsByTagName("td")[columnIndex].innerHTML);
        return reverse ? yDateValue - xDateValue : xDateValue - yDateValue;
    });

    var tbody = document.querySelector('#urls_table tbody');
    sortedRows.forEach(row => tbody.appendChild(row));
}