package com.spring.ecmmvc.service;

import com.spring.ecmmvc.dao.CartDao;
import com.spring.ecmmvc.dao.CartProductDao;
import com.spring.ecmmvc.model.Cart;
import com.spring.ecmmvc.model.CartProduct;
import com.spring.ecmmvc.model.CartProductId;
import com.spring.ecmmvc.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

/**
 * CartService provides business logic for managing shopping carts, including adding,
 * removing, and calculating the total price of products in the cart.
 */
@Service
public class CartService {

    private final ProductService productService;
    private final CartProductService cartProductService;
    private final CartDao cartDao;
    private final CartProductDao cartProductDao;

    /**
     * Constructor for CartService.
     *
     * @param productService      Service for managing product-related operations.
     * @param cartProductService  Service for managing cart-product relationships.
     * @param cartDao             DAO for accessing cart data.
     * @param cartProductDao      DAO for accessing cart-product data.
     */
    @Autowired
    public CartService(ProductService productService,
                       CartProductService cartProductService, CartDao cartDao, CartProductDao cartProductDao) {
        this.productService = productService;
        this.cartProductService = cartProductService;
        this.cartDao = cartDao;
        this.cartProductDao = cartProductDao;
    }

    /**
     * Removes a product from the cart by its ID.
     *
     * @param productId The ID of the product to remove.
     * @param cartId    The ID of the cart.
     */
    @Transactional
    public void removeProductFromCartById(int productId, int cartId) {
        Cart cart = cartDao.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartProductId cartProductId = new CartProductId(cartId, productId);
        cartProductService.deleteProductFromCart(cartProductId);
        cartDao.save(cart);
    }

    /**
     * Retrieves a cart by its ID.
     *
     * @param id The ID of the cart.
     * @return The cart with the specified ID.
     * @throws RuntimeException If the cart is not found.
     */
    public Cart getCartById(int id) {
        return cartDao.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Cart not found");
        });
    }

    /**
     * Adds a product to the cart by its ID.
     *
     * @param productId The ID of the product to add.
     * @param idCart    The ID of the cart.
     */
    @Transactional
    public void addProductByIdToCart(int productId, int idCart) {
        CartProductId cartProductId = new CartProductId(idCart, productId);

        if (cartProductService.productExist(cartProductId)) {
            cartProductService.incrementProductQuantity(cartProductId);
        } else {
            Product product = productService.getProductById(productId);
            Cart cart = getCartById(idCart);
            cart.getProducts().add(product);
            cartDao.save(cart);
            cartProductService.incrementProductQuantity(cartProductId);
        }
    }

    /**
     * Calculates the total price of all items in the cart.
     *
     * @param id The ID of the cart.
     * @return The total price of all items in the cart.
     * @throws RuntimeException If the cart is not found.
     */
    public BigDecimal calculatePriceOfItemById(Integer id) {
        Cart cart = cartDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Set<CartProduct> cartProducts = cartProductService.getCartProductsByCartId(cart.getIdCart());
        System.out.println(cartProducts);
        return cartProducts
                .stream()
                .map(this::calculatePriceOfProductById)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calculates the price of a product in the cart based on its quantity.
     *
     * @param cartProduct The cart-product relationship.
     * @return The total price of the product in the cart.
     */
    private BigDecimal calculatePriceOfProductById(CartProduct cartProduct) {
        Product product = productService.getProductById(cartProduct.getProductId());
        System.out.println(product.getPrice().multiply(new BigDecimal(cartProduct.getQuantity())));
        return product.getPrice().multiply(new BigDecimal(cartProduct.getQuantity()));
    }
}