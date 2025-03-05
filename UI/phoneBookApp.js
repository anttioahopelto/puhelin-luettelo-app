const apiBaseUrl = 'http://localhost:8080/contacts';

async function fetchContacts() {
    try {
        const response = await fetch(apiBaseUrl);
        if (response.ok) {
            const message = await response.text();
            console.log(message);
            document.getElementById('response').innerText = message;
        } else {
            document.getElementById('response').innerText = 'Failed to fetch message';
        }
    } catch (error) {
        document.getElementById('response').innerText = 'Error: ' + error.message;
    }
}
