package executor.RMI;

import java.rmi.registry.Registry;

public abstract class RMIBase<T> {
    protected String studentID = "B22DCCN541";
    protected String qCode;

    protected T service;

    public RMIBase(Registry registry) throws Exception {
        Class<T> serviceClass = getServiceClass();

        String registryName = (String) serviceClass.getField("REGISTRY_NAME").get(null);
        Object stub = registry.lookup(registryName);
        if (!serviceClass.isInstance(stub)) throw new ClassCastException();

        service = serviceClass.cast(stub);

        setStudentID();
        setQCode();
    }

    protected final void setStudentID() {
        this.studentID = getStudentID();
    }

    protected final void setQCode() {
        this.qCode = getQCode();
    }

    protected abstract Class<T> getServiceClass();

    protected abstract String getStudentID();

    protected abstract String getQCode();

    public abstract void execute() throws Exception;
}
