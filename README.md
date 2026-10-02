# ZipRide Dispatch System
# Author: Jamie Choo Chung Sheng

## Project Overview
This project implements a simplified ride dispatch system called ZipRide using core data structures and algorithms in Java.

The system includes:
- Graph representation of city locations and roads
- BFS traversal
- DFS cycle detection
- Dijkstra shortest path algorithm
- Passenger and Driver hash tables with linear probing
- Max heap scheduling for pickup requests
- Merge sort and quick sort for pickup records
- Benchmark testing for sorting algorithms

---

# Modules Implemented

## Module 1 – Graph Algorithms
Implemented using adjacency list representation.

Features:
- Add locations
- Add roads
- Breadth-First Search (BFS)
- Depth-First Search (DFS) cycle detection
- Dijkstra shortest path algorithm

Files:
- Graph.java
- GraphEdge.java

---

## Module 2 – Hash Tables
Implemented using open addressing with linear probing.

Features:
- Insert records
- Search records
- Delete records
- Collision handling
- Load factor calculation

Files:
- PassengerHashTable.java
- DriverHashTable.java
- HashEntry.java
- Passenger.java
- Driver.java

---

## Module 3 – Heap Scheduling
Implemented using a max heap.

Features:
- Insert pickup request
- Extract highest-priority request
- Heap scheduling
- Priority calculation

Files:
- Heap.java
- PickupRequest.java
- DispatchSystem.java

---

## Module 4 – Sorting
Implemented without built-in sorting algorithms.

Algorithms:
- Merge Sort
- Quick Sort

Sorting Key:
- EstimatedPickupTime (ascending)

Files:
- Sorting.java
- PickupRecord.java

---

# Benchmark Testing
Benchmark testing compares:
- Merge sort
- Quick sort

Dataset sizes:
- 100 records
- 500 records
- 1000 records

Input conditions:
- Random
- Nearly sorted
- Reversed

Benchmarking is integrated with the graph module by computing EstimatedPickupTime using Dijkstra shortest path results.

---

# How to Compile

Compile all Java files:

```bash
javac *.java

Then run the Menu.java
```bash
java Menu.java
