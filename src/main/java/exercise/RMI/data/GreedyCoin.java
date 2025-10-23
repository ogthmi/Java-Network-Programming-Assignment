package exercise.RMI.data;

import RMI.DataService;
import executor.RMI.RMIBase;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GreedyCoin extends RMIBase<DataService> {
    public GreedyCoin(Registry registry) throws Exception {
        super(registry);
    }

    @Override
    protected Class<DataService> getServiceClass() {
        return DataService.class;
    }

    @Override
    protected String getStudentID() {
        return "B22DCCN541";
    }

    @Override
    protected String getQCode() {
        return "f6IhcLZC";
    }

    @Override
    public void execute() throws Exception {
        int amount = (int) service.requestData(studentID, qCode);
        System.out.println("Amount: " + amount);
        service.submitData(studentID, qCode, greedyCoin(amount));
    }

    String greedyCoin(int amount){
        List<Integer> coins = List.of(10, 5, 2, 1);

        ArrayList<Integer> exchanges = new ArrayList<>();
        for (int coin: coins){
            while (amount - coin >= 0){
                exchanges.add(coin);
                amount -= coin;
            }
        }

        String result = exchanges.size() + "; " + exchanges.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        System.out.println("Exchanged result: " + result);

        return result;
    }
}
