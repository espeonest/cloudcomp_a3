const cartGrid = document.getElementById("cart-items-grid");

const API_URL = "http://localhost:8081/cart/";

async function loadCart() {
  try {
    const response = await fetch(API_URL + "items", {credentials: "include"});
    const viewModel = await response.json();
    showCartItems(viewModel);
  } catch (error) {
    console.error("Error loading cart:", error);
  }
}

async function showCartItems(viewModel) {
  cartGrid.innerHTML = "";
  var counter = 0;
  viewModel.contents.forEach((shoe) => {
    const card = document.createElement("div");
    card.className = "cart-card";

    card.innerHTML = `
        <img src="${shoe.image}" alt="${shoe.name}" class="shoe-image">
        <h3>${shoe.name}</h3>
        <p>${shoe.description}</p>
        <p>Cost: $${shoe.price}</p>
        <p>SKU: ${shoe.sku}</p>
        <!-- Remove from Cart Button -->
        <button id="btn-${viewModel.entryIds[counter]}" class="remove-cart-btn">
         Remove
        </button>
      `;
    cartGrid.appendChild(card);
    console.log("Counter value: " + counter);
    console.log("Current entry value: " + viewModel.entryIds[counter]);
    document
      .getElementById(`btn-${viewModel.entryIds[counter]}`)
      .addEventListener("click", function (event) {
        remove(viewModel.entryIds[counter]);
      });
    counter++;
  });
  document
    .getElementById(`btn-empty`)
    .addEventListener("click", function (event) {
      removeAll();
    });
}

async function remove(entryId) {
  try {
    console.log(entryId);
    const response = await fetch(`${API_URL}${entryId}`, {
      method: "DELETE",
      credentials: "include",
    });
    console.log((await response.json()).contents);
    loadCart();
  } catch {
    console.error("Problem removing from cart");
  }
}

async function removeAll() {
  try {
    const response = await fetch(API_URL + "emptycart", {
      method: "DELETE",
      credentials: "include",
    });
    loadCart();
    return false;
  } catch {
    console.error("Problem emptying cart");
  }
}

loadCart();
