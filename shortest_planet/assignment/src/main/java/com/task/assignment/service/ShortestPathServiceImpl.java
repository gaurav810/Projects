package com.task.assignment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.assignment.dao.PlanetDAO;
import com.task.assignment.dto.GraphDTO;
import com.task.assignment.model.DistancePlanet;
import com.task.assignment.model.Graph;
import com.task.assignment.model.Node;
import com.task.assignment.model.Planet;

@Service
public class ShortestPathServiceImpl implements ShortestPathService {

	@Autowired
	private PlanetDAO planetDAO; 
	
	@Override
	public Graph calculateShortestPathFromSource(Graph graph, Node source) {
		
		source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
	}

    private void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

	@Override
	public List<GraphDTO> shortestPathDetails(String sourceNodeName) {

		List<Planet> planets = planetDAO.listOfPlanets();
		Map<String, Node> nodesMap = new HashMap<>();
		
		for(Planet planet : planets) {
			Node node = new Node(planet.getName());
			nodesMap.put(planet.getName(), node);
		}
		
		Graph graph = new Graph();
		
		for (Map.Entry<String, Node> entry : nodesMap.entrySet())  {
			
			entry.getKey();
			entry.getValue();
			
			List<DistancePlanet> distancePlanets = planetDAO.listOfDistancePlanetsBySource(entry.getKey());
			for(DistancePlanet distancePlanet : distancePlanets) {
				entry.getValue().addDestination((Node) nodesMap.get(distancePlanet.getDestinationPlanet().getName()), distancePlanet.getDistance());
			}
			graph.addNode(entry.getValue());
		}
		
		graph = this.calculateShortestPathFromSource(graph, nodesMap.get(sourceNodeName));
		
		List<GraphDTO> graphDTOs = new ArrayList<>();
		
		for(Node node : graph.getNodes()) {
			
			String shortestPath = "";
			for(Node n : node.getShortestPath()) {
				System.out.print(n.getName() + "->");
				shortestPath = shortestPath + n.getName() + " - ";
			}
			shortestPath = shortestPath + node.getName();
			
			System.out.print(node.getName());
	        System.out.println();     
	        System.out.println(); 
	        
	        GraphDTO graphDTO = new GraphDTO();
	        graphDTO.setShortestPath(shortestPath);
	        graphDTO.setDestinatoinNode(node.getName());
	        graphDTO.setSourceNode(sourceNodeName);
	        graphDTO.setShortestDistance(node.getDistance());
	        
	        graphDTOs.add(graphDTO);
		}
		
		return graphDTOs;
	}
}
