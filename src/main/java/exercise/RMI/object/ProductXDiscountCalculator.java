package exercise.RMI.object;

import RMI.ObjectService;
import RMI.ProductX;
import executor.RMI.RMIBase;

import java.rmi.registry.Registry;

public class ProductXDiscountCalculator extends RMIBase<ObjectService> {
    public ProductXDiscountCalculator(Registry registry) throws Exception {
        super(registry);
    }

    @Override
    protected Class<ObjectService> getServiceClass() {
        return ObjectService.class;
    }

    @Override
    protected String getStudentID() {
        return "B22DCCN541";
    }

    @Override
    protected String getQCode() {
        return "FYK3dk0v";
    }

    @Override
    public void execute() throws Exception {
        var product = (ProductX) service.requestObject(studentID, qCode);
        System.out.println("Product: " + product);

        product.setDiscount();
        System.out.println("Discounted: " + product);

        service.submitObject(studentID, qCode, product);
    }
}
