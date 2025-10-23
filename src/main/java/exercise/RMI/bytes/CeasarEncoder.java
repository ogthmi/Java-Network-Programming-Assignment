package exercise.RMI.bytes;

import RMI.ByteService;
import executor.RMI.RMIBase;

import java.rmi.registry.Registry;

public class CeasarEncoder extends RMIBase<ByteService> {
    public CeasarEncoder(Registry registry) throws Exception {
        super(registry);
    }

    @Override
    protected Class<ByteService> getServiceClass() {
        return ByteService.class;
    }

    @Override
    protected String getStudentID() {
        return "B22DCCN541";
    }

    @Override
    protected String getQCode() {
        return "ykEdhb1y ";
    }

    @Override
    public void execute() throws Exception {
        byte[] buffer = service.requestData(studentID, qCode);

        int shift = buffer.length;

        byte[] ceasar = new byte[buffer.length];

        for (int i = 0; i < ceasar.length; i++) {
            ceasar[i] = (byte) (buffer[i] + shift);
        }

        service.submitData(studentID, qCode, ceasar);
    }
}
