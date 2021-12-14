
    let button = document.getElementById('profileBtn');
    let hiddenDiv = document.getElementById('hiddenFields');
    button.addEventListener('click',(e)=>{


        if (e.currentTarget.textContent == 'Show More'){

            document.getElementById('hiddenFields').style.display="inline"

            document.getElementById('profileBtn').textContent = 'Hide'
        }
        else if (e.currentTarget.textContent == 'Hide'){
            document.getElementById('hiddenFields').style.display="none"
            document.getElementById('profileBtn').textContent = 'Show More'
        }
    })
