package RMI;

import java.io.Serializable;

public class Order implements Serializable {
    private String id, customerCode, orderDate, shippingType, orderCode;
    private static final long serialVersionUID = 20241132L;

    public Order(String id, String customerCode, String orderDate, String shippingType, String orderCode) {
        this.id = id;
        this.customerCode = customerCode;
        this.orderDate = orderDate;
        this.shippingType = shippingType;
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerCode='" + customerCode + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", shippingType='" + shippingType + '\'' +
                ", orderCode='" + orderCode + '\'' +
                '}';
    }

    public void setOrderCode() {
        String shippingPart = shippingType.substring(0, 2).toUpperCase();
        String customerPart = customerCode.substring(customerCode.length() - 3);

        String[] tokens = orderDate.split("-");
        String datePart = tokens[2] + tokens[1];

        orderCode = shippingPart + customerPart + datePart;
    }
}
