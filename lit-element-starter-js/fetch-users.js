import {LitElement, html} from 'lit-element';

class FetchUsers extends LitElement {
  static get properties() {
    return {data: Object};
  }

  connectedCallback() {
    super.connectedCallback();
    this.fetchDataFromUrl();
  }

  fetchDataFromUrl() {
    fetch('https://jsonplaceholder.typicode.com/users')
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
            <h2>Kullanıcılar</h2>
            <ul class="list-group">
              ${this.data.map(
                (u) => html`<li class="list-group-item bg-dark text-white">
                  <h3>${u.username}</h3>
                  <p>${u.name} - ${u.email}</p>
                  <p>${u.address.city} - ${u.address.street}</p>
                </li> `
              )}
            </ul>
          </div>`
      : html`<span>Gelmedi</span>`;
  }
}

customElements.define('fetch-users', FetchUsers);
