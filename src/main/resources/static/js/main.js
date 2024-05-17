var sortDirection = true

document.querySelectorAll('#urls_table th').forEach(function (header, index) {
    if (header.classList.contains("sortable")) {
        header.addEventListener('click', function () {

            switch (index) {
                case 1:
                    sortTableByText(index, sortDirection)
                    break
                case 6:
                    sortTableByDate(index, sortDirection);
                    break
                default:
                    sortTable(index, sortDirection);
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

function sortTableByText(columnIndex, reverse) {
    var table = document.getElementById("urls_table");
    var rows = Array.from(table.rows).slice(1);
    var sortedRows = rows.sort((a, b) => {
        var xValue = a.getElementsByTagName("td")[columnIndex].innerText;
        var yValue = b.getElementsByTagName("td")[columnIndex].innerText;

        if (xValue < yValue) {
            return reverse ? 1 : -1;
        } else if (xValue > yValue) {
            return reverse ? -1 : 1;
        } else {
            return 0;
        }
    });

    var tbody = document.querySelector('#urls_table tbody');
    sortedRows.forEach(row => tbody.appendChild(row));
}

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

        return reverse ? yValue - xValue : xValue - yValue;
    });

    var tbody = document.querySelector('#urls_table tbody');
    sortedRows.forEach(row => tbody.appendChild(row));
}

function copyToClipboard(id) {
    var textBox = document.getElementById(id);
    navigator.clipboard.writeText(textBox.value == null ? textBox.textContent : textBox.value).then(function () {
        console.log('Copying to clipboard was successful!');
    }, function (err) {
        console.error('Could not copy text: ', err);
    });
}