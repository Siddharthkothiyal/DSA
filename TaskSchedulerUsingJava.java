import java.util.*;

public class TaskSchedulerUsingJava {

    // Function to perform Topological Sort using Kahn's Algorithm
    public static List<Integer> topologicalSort(int tasks, int[][] dependencies) {
        // Creating an adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < tasks; i++) {
            graph.add(new ArrayList<>());
        }
        
        //array to store the indegree for each tasks
        int[] indegree = new int[tasks];
        
        // Building the graph and indegree array acc to dependencies
        for (int[] d : dependencies) {
            int from = d[0];
            int to = d[1];
            graph.get(from).add(to);
            indegree[to]++;
        }
        
        // queue with all tasks having indegree 0 
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < tasks; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        
        List<Integer> topo= new ArrayList<>();
        
        // Step 5: Process tasks in topological order
        while (!q.isEmpty()) {
            int task = q.poll();
            topo.add(task);
            
            // Decrease in-degree for all dependent tasks and add them to the queue if their in-degree becomes 0
            for (int it : graph.get(task)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }
        
        // If the number of tasks in the topological order is not equal to the total tasks,
        //there was a cycle
        if (topo.size() != tasks) {
            System.out.println("Topological sort is not possible because there exist a cycle in dependencies");
            return new ArrayList<>(); // Return empty list if a cycle is detected
        }
   
        return topo;
    }
    
    public static void main(String[] args) {
        // Example: Task dependencies as pairs (from, to), where "from" must come before "to"
        int tasks = 6;
        int[][] dependencies = {
            {5, 2},
            {5, 0},
            {4, 0},
            {4, 1},
            {2, 3},
            {3, 1}
        };
        // Get the topological order of tasks
        List<Integer> order = topologicalSort(tasks, dependencies);
        
        // Output 
        if (!order.isEmpty()) {
            System.out.println("The valid order of tasks is: " + order);
        }
    }
}
