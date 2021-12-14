const finalUrl = `http://localhost:8080/api/users/manage-users`;
let list = document.querySelector('#inputContainer1');
fetch(finalUrl)
    .then(response => response.json())
        .then(rep => {
        for (const u of rep) {
            let li = document.createElement('li');
            let a = document.createElement('a');
            a.textContent = `Username: ${u.username} ---- Role: ${u.role}`;
            li.classList.add('form-control', 'marginLeftInput');
            li.appendChild(a);
            list.appendChild(li);
        }
    }).catch(er => {
    console.log('Promise rejected');
    console.log(er);
})