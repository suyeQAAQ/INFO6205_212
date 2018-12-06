# INFO6205
Final Project
You have some scatter points(2 dimension only) and you want to divide them into several kinds, but it's hard to get a specific logic to do that, so we solve this tough problem for you.
For the given point set of any size(at least 100), we can use the GA to find out best clustering scheme of those points.
You can set the number of cluster centers you want(1<number of centers<the size of the point set).
We will figure out the almost best cluster scheme for you: For each cluster, adding up all of the distances from one member point to it's center to get the summary. Then we add up all of the summaries of all clusters to get total summary. The best scheme is the one with smallest total summary, which means it has the most similarity withhin each cluster.

Further more, we use the tradition cluster algorithm k-means to do the cluster , it always stops at some "better" scheme, so we use GA to cross and mutate to find out the almost best scheme of all. The another advantage is that it does inpected by the init scheme.
