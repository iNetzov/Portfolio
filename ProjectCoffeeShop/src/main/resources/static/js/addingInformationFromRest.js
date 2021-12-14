
const finalUrl =`http://localhost:8080/api/logs/all`;
let list = document.querySelector('#inputContainer');
console.log("hi");


fetch(finalUrl)
                .then(responce => responce.json())
                        .then(rep=>{
                            for (const l of rep) {
                                let li = document.createElement('li');
                                let a = document.createElement('a');
                                a.textContent =`Client: ${l.clientName} -- Order Id: ${l.orderId} -- Done: ${l.done} --Product: ${l.productName} -- Store: ${l.storeName}`;
                                li.classList.add('form-control', 'marginLeftInput');
                                 li.appendChild(a);
                                 list.appendChild(li);
                             }
                        }).catch(er =>{
                            console.log('Promise rejected');
                            console.log(er);
})