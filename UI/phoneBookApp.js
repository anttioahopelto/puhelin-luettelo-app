const apiBaseUrl = 'http://localhost:8080/contacts';
const apiDeleteUrl = 'http://localhost:8080/deleteByIds';
const apiAddUrl = 'http://localhost:8080/addContact';

async function fetchContacts() {
    try {
        const response = await fetch(apiBaseUrl);
        if (response.ok) {
            const contacts = await response.json();
            document.getElementById('response').innerHTML = createTable(contacts);
        } else {
            document.getElementById('response').innerText = 'Failed to fetch contacts';
        }
    } catch (error) {
        document.getElementById('response').innerText = 'Error: ' + error.message;
    }
}

function createTable(contacts) {
    let table = '<table border="1">';
    table += '<tr><th>Select</th><th>ID</th><th>First Name</th><th>Last Name</th><th>Phone Number</th><th>Email</th><th>Address</th></tr>';
    contacts.forEach(contact => {
        table += `<tr>
                    <td><input type="checkbox" class="contactCheckbox" value="${contact.id}"></td>
                    <td>${contact.id}</td>
                    <td>${contact.firstName}</td>
                    <td>${contact.lastName}</td>
                    <td>${contact.phoneNumber}</td>
                    <td>${contact.email || ''}</td>
                    <td>${contact.address || ''}</td>
                  </tr>`;
    });
    table += '</table>';
    return table;
}

async function deleteSelectedContacts() {
    try {
        const selectedCheckboxes = document.querySelectorAll('.contactCheckbox:checked');
        const selectedIds = Array.from(selectedCheckboxes).map(checkbox => parseInt(checkbox.value));

        const response = await fetch(apiDeleteUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ ids: selectedIds })
        });

        if (response.ok) {
            const result = await response.json();
            alert(`Deleted ${result.deletedCount} contacts`);
            await fetchContacts();
        } else {
            document.getElementById('response').innerText = 'Failed to delete contacts';
        }
    } catch (error) {
        document.getElementById('response').innerText = 'Error: ' + error.message;
    }
}

function showAddContactForm() {
    document.getElementById('addContactForm').style.display = 'block';
}

function hideAddContactForm() {
    document.getElementById('addContactForm').style.display = 'none';
}

async function addContact() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const email = document.getElementById('email').value;
    const address = document.getElementById('address').value;

    const newContact = {
        firstName,
        lastName,
        phoneNumber,
        email,
        address
    };

    try {
        const response = await fetch(apiAddUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newContact)
        });

        if (response.ok) {
            alert('Contact added successfully');
            hideAddContactForm();
            await fetchContacts();
        } else {
            document.getElementById('response').innerText = 'Failed to add contact';
        }
    } catch (error) {
        document.getElementById('response').innerText = 'Error: ' + error.message;
    }
}
