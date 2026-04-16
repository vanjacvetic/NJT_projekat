const CART_KEY = "food_cart";

export function getCart() {
  const cart = localStorage.getItem(CART_KEY);
  return cart ? JSON.parse(cart) : [];
}

export function saveCart(cart) {
  localStorage.setItem(CART_KEY, JSON.stringify(cart));
}

export function addToCart(product) {
  const cart = getCart();

  const existing = cart.find((item) => item.id === product.id);

  if (existing) {
    existing.kolicina += 1;
  } else {
    cart.push({
      id: product.id,
      naziv: product.naziv,
      opis: product.opis,
      cena: product.cena,
      slika: product.slika,
      jedinicaMere: product.jedinicaMere,
      kolicina: 1,
    });
  }

  saveCart(cart);
}

export function removeFromCart(id) {
  const cart = getCart().filter((item) => item.id !== id);
  saveCart(cart);
}

export function updateCartQuantity(id, novaKolicina) {
  const cart = getCart().map((item) =>
    item.id === id
      ? { ...item, kolicina: novaKolicina < 1 ? 1 : novaKolicina }
      : item
  );

  saveCart(cart);
}

export function clearCart() {
  localStorage.removeItem(CART_KEY);
}

export function getCartTotal() {
  const cart = getCart();
  return cart.reduce((sum, item) => sum + Number(item.cena) * Number(item.kolicina), 0);
}