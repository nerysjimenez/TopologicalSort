//package topologicalsort;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import java.util.HashMap;
import java.util.Stack;

/**
 * The College of Saint Rose
 * Spring 2017, Algorithms
 * Topological Sort Implementation using DFS and DFS-Visit
 * 
 * @author Nerys Jimenez
 */
public class TopologicalSort {
     static HashMap <Vertex, Integer> discovery = new HashMap <Vertex, Integer> ();
     static HashMap <Vertex, Integer> finished = new HashMap <Vertex, Integer> ();
     static Stack schedule = new Stack();
     static int time;  
     
     public static void main(String[] args) {
        Graph g = new Graph("nerys.txt");
        DFS(g); //calls topological sort
        
        //prints a list in order with -1 at the end
        while(!schedule.isEmpty())
            System.out.println(schedule.pop().hashCode());
        
        System.out.println(-1);
    }
     /**
      * Depth-First-Search
      * @param g Graph to be searched and sorted
      */
    public static void DFS(Graph g)
    {
        //Each vertex gets two labels discovery and finished.
        for(Vertex v : g.values())
        {
            discovery.put(v, null);
            finished.put(v, null);
        }
        
        //time-stamp to record at what point each vertex is discovered and finished
        time = 0;
        
        //call DFS_VISIT with each unvisited vertex
        for(Vertex v : g.values())
            if(discovery.get(v) == null)
                DFS_VISIT(v);         
        
    }
    
    /**
     * Visits every vertex in a directed graph
     * At the end of this method,
     * the stack 'shedule' will contain the topological ordering of the graph
     * @param v vertex to be visited
     */
    public static void DFS_VISIT(Vertex v)
    {
        time += 1; //record time of discovery
        discovery.put(v, time);
        
        for(Edge e : v.outEdges())
        {
            //cycle detection error checking
            if(discovery.get(e.destination()) != null && finished.get(e.destination()) == null)
                System.out.println("Cycle detection error...");
             
            if(discovery.get(e.destination()) == null)
                DFS_VISIT(e.destination()); 
        }
        schedule.push(v); //topological sort
        time += 1; //record finished time
        finished.put(v, time);
    }
}
