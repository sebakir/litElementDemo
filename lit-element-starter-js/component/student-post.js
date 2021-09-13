import {LitElement, html} from 'lit-element';

class StudentPost extends LitElement {
  render() {
    return html` <form id="myForm" @submit=${this.postFormData}>
      <div>
        <label for="ogrenciNum">Number</label>
        <input type="text" name="ogrenciNum" id="ogrenciNum" />
      </div>
      <div>
        <label for="ogrName">Name</label>
        <input type="text" name="ogrName" id="ogrName" />
      </div>
      <button type="submit">Gönder</button>
    </form>`;
  }

  postFormData(event) {
    event.preventDefault();
    //console.log('form gönderildi...');
    let form = this.shadowRoot.getElementById('myForm');
    //console.log(form);

    let formData = new FormData(form);

    fetch('http://localhost:8080/ogrenci/insert', {
      method: 'POST',
      body: formData,
    })
      .then((x) => x.json())
      .then((y) => console.log(y))
      .catch((err) => console.log(err));

    //let result = await response.json();

    console.log('Getting key: ' + formData.get('title'));
    console.log(formData);
  }
}

customElements.define('student-post', StudentPost);
