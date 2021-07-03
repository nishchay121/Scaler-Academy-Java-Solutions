class Solution {

    private static class TrieNode {
        boolean isEnd;
        TrieNode[] childs;

        TrieNode() {
            isEnd = false;
            childs = new TrieNode[26];
        }
    }

    private static class Trie {
        private final TrieNode trieNode;

        Trie() {
            this.trieNode = new TrieNode();
        }

        public boolean checkIfWordExists(String word) {
            if (word.length() == 0)
                return false;
            TrieNode currentNode = this.trieNode;
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (currentNode.childs[currentChar - 'a'] == null)
                    return false;

                currentNode = currentNode.childs[currentChar - 'a'];
            }

            return currentNode.isEnd;
        }

        public void insert(String word) {
            if (word.length() == 0)
                return;
            TrieNode currentNode = this.trieNode;
            for (int i = 0; i < word.length(); i++) {

                char currentChar = word.charAt(i);
                if (currentNode.childs[currentChar - 'a'] == null)
                    currentNode.childs[currentChar - 'a'] = new TrieNode();
                currentNode = currentNode.childs[currentChar - 'a'];

            }
            currentNode.isEnd = true;

        }


    }


    public ArrayList<Integer> solve(ArrayList<String> inputDict, ArrayList<String> stringsToCheck) {

        ArrayList<Integer> output = new ArrayList<>();
        if (stringsToCheck.size() == 0) {
            return output;
        }

        Trie trie = new Trie();
        for (String currentString : inputDict)
            trie.insert(currentString);

        for (String currentString : stringsToCheck) {
            if (trie.checkIfWordExists(currentString))
                output.add(1);
            else
                output.add(0);
        }


        return output;

    }
}
