import {
    LitElement,
    html
} from 'lit-element'

class FetchPost extends LitElement {

    render() {
        return html `
        <form id="myForm" @submit=${this.postFormData}>
            
                <label for="title" >Başlık</label>
                <input type="text" name="title" id="title">
         
            <div>
                <label for="body" >İçerik</label>
                <input type="text" name="body" id="body">
            </div>
            <div>
                <label for="userId" >Kullanıcı no</label>
                <input type="text" name="userId" id="userId">
            </div>
            <button type="submit">Gönder</button>
        </form>`
    }


    postFormData(event) {
        event.preventDefault();
        //console.log('form gönderildi...');
        let form = this.shadowRoot.getElementById('myForm');
        //console.log(form);

        let formData = new FormData(form);   

        fetch('https://jsonplaceholder.typicode.com/posts', {
            method: 'POST',
            body: formData
          }).
        then(x=>x.json())
        .then(y=>console.log(y))
        .catch(err=>console.log(err));

     
      
          //let result = await response.json();
      
        console.log('Getting key: '+ formData.get('title'));
        console.log(formData);

    }
}

customElements.define('fetch-post', FetchPost);