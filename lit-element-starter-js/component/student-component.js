import {LitElement, html} from 'lit-element';

class StudentPage extends LitElement {
  static get properties() {
    return {data: Object};
  }

  connectedCallback() {
    super.connectedCallback();
    this.fetchDataFromUrl();
  }

  fetchDataFromUrl() {
    fetch('http://localhost:8080/ogrenci/getAll')
      .then((response) => response.json())
      .then((x) => {
        this.data = x;
      })
      .catch((err) => console.log(err));
  }
  render() {
    return this.data
      ? html` <link
            rel="stylesheet"
            href="../node_modules/bootstrap/dist/css/bootstrap.min.css"
          />

          <div>
            <h2>Ogrenciler</h2>
            <ul class="list-group">
              ${this.data.map(
                (u) => html`<li class="list-group-item bg-dark text-white">
                  <h3>id : ${u.id}</h3>
                  <p>Ogrenci numarası : ${u.number}</p>
                  <p>Ogrenci name: ${u.name}</p>
                </li> `
              )}
            </ul>
          </div>`
      : html`<span>Gelmedi</span>`;
  }
}

customElements.define('student-component', StudentPage);
