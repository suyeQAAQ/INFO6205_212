# INFO6205
Assignment

Our project is based on genetic algorithm, combined with the idea of K-means algorithm in clustering algorithm to solve the clustering problem of points in two-dimensional coordinate system. For example, given or randomly generating N1 points (if N1 is 100), looking for N2 cluster centers for these 100 points (assuming N2 is 10), the algorithm will iterate to a relative optimal solution after iterating. We have found 10 cluster centers for these 100 points so that the sum of the distances of all points to the cluster center to which they belong is relatively minimal.
In the project, Hint: we treat each solution set as an individual. The number of populations is P1. We construct the Point class and the GA class. In the GA class, we set three ArrayList static properties, which are stored separately:
Arraylist of N1 points to be solved
P1 solution sets: each solution set contains N2 points (ie possible cluster centers)
P1 chromosomes: Each chromosome contains consecutive N1 genes. The meaning of each gene expression is the cluster center to which the corresponding point of the gene belongs in N1 points, so the range of each gene is (1~N2).

Algorithm execution process:
1. Randomly generate P (100) individuals to form the initial population popList, and call the coding function encode() according to each individual to obtain P genes to form the gene library chromos.
2. Call the distance calculation function getDistance() to get the distance parameter of each individual (set), and then call the getPopIndex() method to sort the existing population by fitness (the fitness comes from the getFitness() method), and keep it after sorting. The original pointer of the individual makes it easy to find each individual and its sorted position.
3. Set the selection parameter a to 0.001 and obtain the selected probability of each individual according to Equation 1. The intermediate population is selected according to the selected probability of the individual, and the intermediate population at this time is a gene that is easier to express.
4. Call the corss() and mutation() methods in the intermediate population for hybridization and mutation, and set the probability of hybridization to Pc, and the probability of mutation is Pm. The group obtained at this time is a new generation group. When the individuals of this group are tightened the same or identical, the genetic end point is reached.

The various methods of steps 2 and 3 are hierarchical call relationships, and the outermost method is the selection method selection(), that is, the method of obtaining the intermediate group.
Recursive call: selection(), cross(), and mutation() are the above 2, 3, and 4 steps. When the individuals of the obtained group are infinitely tight or identical, the genetic end point is reached. The individual is the relative superior solution given by this algorithm.

Focuses on:
1. We have decoded and encoded the tails of the selection(), cross(), and mution() methods, that is, calling the decode() and encode() methods, and constantly updating the static properties popList and chromos, meaning that each The idea of ​​retaining better genes in one iteration is our improvement on the genetic algorithm for clustering problems.
2. The idea of ​​calling the decode() and encode() methods multiple times is that each group corresponds to a gene pool, and when we optimize the gene pool, we decode and encode it to update the gene pool and population.
