class Solution {

    public int[] minBitwiseArray(List<Integer> numbers) {
        int size = numbers.size();
        int[] resultArray = new int[size];

        for (int index = 0; index < size; index++) {
            int currentValue = numbers.get(index);

            if (currentValue == 2) {
                resultArray[index] = -1;
            } else {
                int modifiedValue = currentValue;
                int bitIndex = 0;
                int tempValue = currentValue;

                while (tempValue % 2 == 1) {
                    tempValue /= 2;
                    bitIndex++;
                }

                modifiedValue = modifiedValue ^ (1 << (bitIndex - 1));
                resultArray[index] = modifiedValue;
            }
        }
        return resultArray;
    }
}