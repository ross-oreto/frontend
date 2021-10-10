export class Hello {
    /**
     * @constructor
     * @param {string} [name]
     */
    constructor(name) {
        this.name = name ? name : 'world';
    }

    /**
     * @returns {string}
     */
    toString() {
        return `Hello ${this.name}`;
    }
}