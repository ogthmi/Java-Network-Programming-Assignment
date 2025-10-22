package checker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SampleIO {
    public static void main(String[] args) {
        List<Integer> nums = List.of(447026,252145,160282,168931,69761,206744,89750,422567,241873,67312,347513,87833,213502,176067,301271,87467,370469,218724,419335,165518,415710,58954,354026,22394,69089,304936,258834,221034,352324,133265,287480,425078,123158,70760,208394,23714,249515,218622,55735,197948,286197,356981,391984,349459,439547,13241,371333,247787,225224,296150);

        ArrayList<Integer> sub = new ArrayList<>();

        for (int i = 1; i < nums.size() - 1; i++){
            if (nums.get(i + 1) > nums.get(i)){
                sub.add(nums.get(i));
            }
        }

        System.out.println("Subsequence: " + sub);

        String subStr = sub.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println((subStr + ";" + sub.size()));
    }

}
