var sortDirection = true

document.querySelectorAll('#urls_table th').forEach(function (header, index) {
    if (header.classList.contains("sortable")) {
        header.addEventListener('click', function () {

            if (index !== 5) {
                sortTable(index, sortDirection);
            } else {
                sortTableByDate(index, sortDirection);
            }

            sortDirection = !sortDirection;
        });
    }
});

document.querySelectorAll('#urls_table th.sortable').forEach(function (header) {
    header.addEventListener('mouseover', function () {
        this.querySelector('i.fa-sort').style.display = 'inline';
    });
    header.addEventListener('mouseout', function () {
        this.querySelector('i.fa-sort').style.display = 'none';
    });
});

function sortTable(columnIndex, reverse) {
    var table = document.getElementById("urls_table");
    var rows = Array.from(table.rows).slice(1);
    var sortedRows = rows.sort((a, b) => {
        var xValue = parseInt(a.getElementsByTagName("td")[columnIndex].innerText);
        var yValue = parseInt(b.getElementsByTagName("td")[columnIndex].innerText);

        if (isNaN(xValue)) {
            xValue = a.getElementsByTagName("td")[columnIndex].innerText;
        }
        if (isNaN(yValue)) {
            yValue = b.getElementsByTagName("td")[columnIndex].innerText;
        }

        return reverse? yValue - xValue : xValue - yValue;
    });

    sortedRows.forEach(row => table.appendChild(row));
}

function sortTableByDate(columnIndex, reverse) {
    var table = document.getElementById("urls_table");
    var rows = Array.from(table.rows).slice(1); // Преобразование строк таблицы в массив объектов
    var sortedRows = rows.sort((a, b) => {
        var xDateValue = Date.parse(a.getElementsByTagName("td")[columnIndex].innerHTML);
        var yDateValue = Date.parse(b.getElementsByTagName("td")[columnIndex].innerHTML);
        return reverse? yDateValue - xDateValue : xDateValue - yDateValue;
    });

    sortedRows.forEach(row => table.appendChild(row));
}

function copyToClipboard(id) {
    var textBox = document.getElementById(id);
    navigator.clipboard.writeText(textBox.value == null ? textBox.textContent : textBox.value).then(function () {
        console.log('Copying to clipboard was successful!');
    }, function (err) {
        console.error('Could not copy text: ', err);
    });
}