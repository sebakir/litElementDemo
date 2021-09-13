import { LitElement, html } from 'lit-element';

class CustomElement extends LitElement{
 
    static get properties(){
        return {
            title: {type: String},
            paragraph : {type: String},
            numbers: {type: Array},
            isEnabled: {type: Boolean}
        }
    }


    constructor() {
        super();
        this.title = 'Burası değişirse';
        this.paragraph = 'Yazı'; 
        this.numbers = [1,2,3,4,5,6];
        this.isEnabled = true;
    }
    render(){
        return html `
                    <input type="text" .value=${this.title}>  
                    <h3 id=${this.title}>${this.title}</h3>
                     <p class="">${this.paragraph}</p>
                    <ul>
                        ${this.numbers.map(n=> html `<li>${n}</li>`)}
                    </ul> 
                    <button ?disabled=${this.isEnabled}>Tıkla</button>
                    `;
    }
}

customElements.define('sample-element',CustomElement);