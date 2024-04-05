function copyToClipboard(id) {
    var textBox = document.getElementById(id);
    navigator.clipboard.writeText(textBox.value).then(function() {
        console.log('Copying to clipboard was successful!');
    }, function(err) {
        console.error('Could not copy text: ', err);
    });
}