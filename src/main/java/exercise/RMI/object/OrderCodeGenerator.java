package exercise.RMI.object;

import RMI.ObjectService;
import RMI.Order;
import executor.RMI.RMIBase;

import java.rmi.registry.Registry;

public class OrderCodeGenerator extends RMIBase<ObjectService> {
    public OrderCodeGenerator(Registry registry) throws Exception {
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
        return "jcDtSWZd ";
    }

    @Override
    public void execute() throws Exception {
        var order = (Order) service.requestObject(studentID, qCode);
        System.out.println("Order: " + order);

        order.setOrderCode();
        System.out.println("After generation: " + order);

        service.submitObject(studentID, qCode, order);
    }
}
