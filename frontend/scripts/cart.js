const cartGrid = document.getElementById("cart-items-grid");

const API_URL = "http://localhost:8081/cart/items";

async function loadCart() {
  try {
    const response = await fetch(API_URL);
    const viewModel = await response.json();
    showCartItems(viewModel);
  } catch (error) {
    console.error("Error loading cart:", error);
  }
}

function showCartItems(viewModel) {
  cartGrid.innerHTML = "";

  viewModel.contents.forEach((shoe) => {
    const card = document.createElement("div");
    card.className = "cart-card";

    card.innerHTML = `
        <h3>${shoe.name}</h3>
        <p>${shoe.description}</p>
        <p>Cost: $${shoe.price}</p>
        <p>SKU: ${shoe.sku}</p>
        <!-- Remove from Cart Button -->
        <button class="remove-cart-btn">
         Remove
        </button>
      `;

    cartGrid.appendChild(card);
  });
}

loadCart();
