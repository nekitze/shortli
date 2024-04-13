var sortDirections = [];

document.querySelectorAll('#urls_table th').forEach(function (header, index) {
    if (header.classList.contains("sortable")) {
        header.addEventListener('click', function () {
            if (sortDirections[index] === undefined) {
                sortDirections[index] = false;
            }
            sortTable(index, sortDirections[index]);

            sortDirections[index] = !sortDirections[index];
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
    var table, rows, switching, i, x, y, shouldSwitch;
    table = document.getElementById("urls_table");
    switching = true;

    while (switching) {
        switching = false;
        rows = table.rows;

        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("td")[columnIndex];
            y = rows[i + 1].getElementsByTagName("td")[columnIndex];

            const xDateValue = Date.parse(x.innerHTML);
            const yDateValue = Date.parse(y.innerHTML);

            if(!isNaN(xDateValue) || !isNaN(yDateValue)) {
                if (reverse ? xDateValue > yDateValue : xDateValue < yDateValue) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}

function copyToClipboard(id) {
    var textBox = document.getElementById(id);
    navigator.clipboard.writeText(textBox.value == null ? textBox.textContent : textBox.value).then(function () {
        console.log('Copying to clipboard was successful!');
    }, function (err) {
        console.error('Could not copy text: ', err);
    });
}