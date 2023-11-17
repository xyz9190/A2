//Name:Phase Siddhi Mukund
//Roll no : 526

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class singlepass {
 public static double computeDiceCoefficient(List<String> doc1, List<String> doc2) {
 int common = 0;
 for (String term1 : doc1) {
 if (doc2.contains(term1)) {
 common++;
 }
 }
 return (2.0 * common) / (doc1.size() + doc2.size());
 }
 public static void main(String[] args) throws IOException {
 // Read document representatives from files
 List<List<String>> documents = new ArrayList<>();
 for (int i = 1; i <= 5; i++) {
 List<String> doc = new ArrayList<>();
 BufferedReader reader = new BufferedReader(new FileReader("doc" + i + ".txt"));
 String line;
 while ((line = reader.readLine()) != null) {
 doc.add(line.trim());
 }
 reader.close();
 documents.add(doc);
 }
 // Set a threshold for clustering
 double threshold = 0.59;
 // Initialize clusters
 List<List<List<String>>> clusters = new ArrayList<>();
 for (List<String> doc : documents) {
 List<List<String>> cluster = new ArrayList<>();
 cluster.add(doc);
 clusters.add(cluster);
 }
 // Perform single-pass clustering
 for (int i = 0; i < documents.size(); i++) {
 for (int j = i + 1; j < documents.size(); j++) {
 double similarity = computeDiceCoefficient(documents.get(i), documents.get(j));
 if (similarity >= threshold) {
 clusters.get(i).addAll(clusters.get(j));
 clusters.remove(j);
 j--;
 }
 }
 }
 // Print clusters
 int clusterNumber = 1;
 for (List<List<String>> cluster : clusters) {
 System.out.println("Cluster " + clusterNumber + ":");
 for (List<String> doc : cluster) {
 System.out.println(doc);
 }
 System.out.println();
 clusterNumber++;
 }
 }
}



