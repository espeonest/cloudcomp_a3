const shoesGrid = document.getElementById("shoes-grid");

const API_URL = "http://localhost:8081/shoes";
const CART_URL = "http://localhost:8081/cart/add/";

async function loadShoes() {
  try {
    const response = await fetch(API_URL);
    const shoes = await response.json();
    shoesGrid.innerHTML = "";

    shoes.forEach(shoe => {
      const card = document.createElement("div");
      card.classList.add("shoe-card");

      card.innerHTML = `
        <h3>${shoe.name}</h3>
        <p>${shoe.description}</p>
        <p>Cost: $${shoe.price}</p>
        <p>SKU: ${shoe.sku}</p>
        <!-- Add to Cart Button -->
        <button id="btn-${shoe.shoeID}" class="add-cart-btn">
          Add to Cart
        </button>
      `;
      shoesGrid.appendChild(card);
      document.getElementById(`btn-${shoe.shoeID}`).addEventListener("click", function(event){addToCart(shoe.shoeID)})
    });

  } catch (error) {
    console.error("Error loading shoes:", error);
  }
}

async function addToCart(id){
  try {
    const response = await fetch(CART_URL + id, {method: "POST", credentials: "include"});
    console.log((await response.json()).contents)
  } catch {
    console.error("Problem adding to cart");
  }
}

loadShoes();
