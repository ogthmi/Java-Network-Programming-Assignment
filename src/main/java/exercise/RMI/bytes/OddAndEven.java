package exercise.RMI.bytes;

import RMI.ByteService;
import executor.RMI.RMIBase;

import java.rmi.registry.Registry;
import java.util.ArrayList;

public class OddAndEven extends RMIBase<ByteService> {
    public OddAndEven(Registry registry) throws Exception {
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
        return "XHp4lcHK";
    }

    @Override
    public void execute() throws Exception {
        byte[] buffer = service.requestData(studentID, qCode);

        ArrayList<Byte> oddBytes = new ArrayList<>(), evenBytes = new ArrayList<>();

        for (byte b: buffer){
            if ((b & 1) == 0) evenBytes.add(b);
            else oddBytes.add(b);
        }
        evenBytes.addAll(oddBytes);

        byte[] result = new byte[buffer.length];
        for (int i = 0; i < buffer.length; i++) result[i] = evenBytes.get(i);

        service.submitData(studentID, qCode, result);
    }
}
