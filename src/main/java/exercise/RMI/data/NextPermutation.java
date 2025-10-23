package exercise.RMI.data;

import RMI.DataService;
import executor.RMI.RMIBase;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NextPermutation extends RMIBase<DataService> {
    public NextPermutation(Registry registry) throws Exception {
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
        return "eHjM9q2O";
    }

    @Override
    public void execute() throws Exception {
        String serverResponse = (String) service.requestData(studentID, qCode);
        System.out.println("Server response: " + serverResponse);

        List<Integer> numbers = Arrays.stream(serverResponse.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        String nextCmb = nextPermutation(numbers);
        System.out.println("Next combination: " + nextCmb);

        service.submitData(studentID, qCode, nextCmb);
    }

    protected String nextPermutation(List<Integer> nums) {
        int i = nums.size() - 2;
        while (i >= 0 && nums.get(i) >= nums.get(i + 1)) i--;

        if (i >= 0) {
            int j = nums.size() - 1;
            while (nums.get(j) <= nums.get(i)) j--;
            Collections.swap(nums, i, j);
            Collections.reverse(nums.subList(i + 1, nums.size()));
        } else {
            nums.sort(Integer::compareTo);
        }

        return nums.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

}
