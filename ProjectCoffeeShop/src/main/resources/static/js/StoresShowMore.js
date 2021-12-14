let container = document.getElementById('containerForStores');
let buttons = document.querySelectorAll('#containerForStores button');
for (const button of buttons) {
    button.addEventListener('click',(e)=>{
        if (e.target.textContent == 'Show Address'){
            e.target.parentNode.children[2].style.display = 'inline';
            e.target.textContent = "Hide Address"
            e.target.parentNode.children[0].classList.add('resizePhotoStoresSmall');
        }else{
            e.target.parentNode.children[2].style.display = 'none'
            e.target.textContent = "Show Address"
             e.target.parentNode.children[0].classList.remove('resizePhotoStoresSmall');


        }

    })
}