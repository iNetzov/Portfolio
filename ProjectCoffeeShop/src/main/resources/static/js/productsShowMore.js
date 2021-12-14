 let container = document.getElementById('containerForProducts');
 let buttons = document.querySelectorAll('#containerForProducts button');
 for (const button of buttons) {
     button.addEventListener('click',(e)=>{
         if (e.target.textContent == 'More Details'){
             e.target.parentNode.children[3].style.display = 'inline';
             e.target.textContent = "Less Details"
             e.target.parentNode.children[0].classList.add('resizePhotoProductsSmall');
         }else{
             e.target.parentNode.children[3].style.display = 'none'
             e.target.textContent = "More Details"
             e.target.parentNode.children[0].classList.remove('resizePhotoProductsSmall');

         }

     })
 }