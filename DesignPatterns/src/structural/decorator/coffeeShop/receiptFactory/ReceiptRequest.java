package structural.decorator.coffeeShop.receiptFactory;

import java.util.List;

import structural.decorator.coffeeShop.order.Order;

/**
 * Record representing a request to create a receipt, containing the order and
 * a list of receipt decorators to be applied.
 */
public record ReceiptRequest(Order order, List<ReceiptType> receiptDecorators) {

}
