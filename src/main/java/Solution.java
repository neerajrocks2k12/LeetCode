import java.util.*;

class Solution {
    public int climbStairs(int n) {
        if(n <= 3) return n;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        for(int i = 4; i <= n; i++)
            arr[i] = arr[i - 1] + arr[i - 2];
        return arr[n];
    }

    public boolean divisorGame(int N) {
        if(N % 2 == 0)
            return true;
        else return false;
    }

    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> myMap = new HashMap<>();
        for (String path : paths) {
            String[] splitOuter = path.split(" ");
            String currentRoot = splitOuter[0];
            int length = splitOuter.length;
            for (int i = 1; i < length; i++) {
                String[] splitInner = splitOuter[i].split("[\\(\\)]");
                String relPath = splitInner[0];
                String content = splitInner[1];
                String fullPath = currentRoot + "/" + relPath;
                if (myMap.containsKey(content)) {
                    List<String> list = myMap.get(content);
                    list.add(fullPath);
                    myMap.put(content, list);
                } else {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(fullPath);
                    myMap.put(content, list);
                }
            }
        }
        //System.out.println("hi");
        List<List<String>> ans = new ArrayList<>();
        Set<String> keys = myMap.keySet();
        for(String key:keys) {
            List<String> currentValues = myMap.get(key);
            if(currentValues.size() >= 2)
                ans.add(myMap.get(key));
        }
        return ans;
    }

}
