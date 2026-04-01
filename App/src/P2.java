import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + "(" + riskScore + ")";
    }
}

public class P2 {

    // Bubble Sort (ascending by riskScore)
    public static void bubbleSort(Client[] clients) {
        int n = clients.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                    swapped = true;
                    System.out.println("Swapped: " + clients[j] + " <-> " + clients[j + 1]);
                }
            }

            if (!swapped) break; // Early exit
        }

        System.out.println("\nBubble Sort (Ascending by RiskScore): " + Arrays.toString(clients));
        System.out.println("Total swaps: " + swaps);
    }

    // Insertion Sort (descending by riskScore, then accountBalance)
    public static void insertionSort(Client[] clients) {
        int n = clients.length;

        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;

            while (j >= 0 && compareDesc(clients[j], key) < 0) {
                clients[j + 1] = clients[j];
                j--;
            }
            clients[j + 1] = key;
        }

        System.out.println("\nInsertion Sort (Descending RiskScore, AccountBalance): " + Arrays.toString(clients));
    }

    // Comparator for descending riskScore, then accountBalance
    private static int compareDesc(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c1.riskScore, c2.riskScore);
        }
        return Double.compare(c1.accountBalance, c2.accountBalance);
    }

    // Get top N high-risk clients
    public static void topRiskClients(Client[] clients, int topN) {
        System.out.println("\nTop " + topN + " High-Risk Clients:");
        for (int i = 0; i < Math.min(topN, clients.length); i++) {
            System.out.println(clients[i].name + "(" + clients[i].riskScore + ")");
        }
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000.0),
                new Client("clientA", 20, 10000.0),
                new Client("clientB", 50, 7000.0),
        };

        // Bubble Sort (ascending)
        Client[] bubbleClients = Arrays.copyOf(clients, clients.length);
        bubbleSort(bubbleClients);

        // Insertion Sort (descending + account balance)
        Client[] insertionClients = Arrays.copyOf(clients, clients.length);
        insertionSort(insertionClients);

        // Top 3 high-risk clients
        topRiskClients(insertionClients, 3);
    }
}