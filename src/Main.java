import java.util.*;

public class Main {

    public static void solve(int A, int[][] B) {
        Map<Integer,ArrayList<int[]>> map = new HashMap<>();

        for(int i=0;i<A;i++){
            int x = B[i][0]-1;
            int y = B[i][1]-1;
            int d = B[i][2];
            ArrayList<int[]> t = map.getOrDefault(x,new ArrayList<int[]>());
            t.add(new int[]{y,d});
            map.put(x,t);
            t = map.getOrDefault(y,new ArrayList<int[]>());
            t.add(new int[]{x,d});
            map.put(y,t);
        }
        for(Map.Entry<Integer,ArrayList<int[]>> e : map.entrySet()){
            for(int[]t : e.getValue()){
                System.out.println(e.getKey()+","+t[0]+" -> "+t[1]);
            }
        }

        int[] parent = new int[A];
        int[] key = new int[A];
        Arrays.fill(key,Integer.MAX_VALUE);
        Set<Integer> seen = new HashSet<>();

        parent[0] = -1;
        key[0] = 0;

        for(int count=0;count<A-1;count++){
            int u = getMin(key,seen);
            seen.add(u);
            for(int []t:map.get(u)){
                if(!seen.contains(t[0]) && key[t[0]]>t[1]){
                    key[t[0]] = t[1];
                    parent[t[0]] = u;
                }
            }
        }

        for(int i=0;i<key.length;i++){
            System.out.println("Min dis for "+i+" -> "+key[i]);
        }
    }

    public static int getMin(int[]key,Set<Integer> seen){
        int min = Integer.MAX_VALUE;
        int index = -1;
        for(int i=0;i<key.length;i++){
            if(key[i]<min && !seen.contains(i)){
                min = key[i];
                index = i;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        int graph[][] = new int[][] { {1,2,1 },
                { 2,3,2 },
                { 3,4,4 },
                { 1,4,3 }};
        solve(4,graph);
    }
}
