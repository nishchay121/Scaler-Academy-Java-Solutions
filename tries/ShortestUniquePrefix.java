class Solution {

    private static class TrieNode {
        boolean isEnd;
        int prefixCount;
        TrieNode[] childs;

        TrieNode() {
            this.isEnd = false;
            this.prefixCount = 0;
            this.childs = new TrieNode[26];
        }

    }

    private static class Trie {

        private final TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }


        public String getPrefix(String input) {
            if (input.length() == 0)
                return "";

            StringBuilder output = new StringBuilder();
            TrieNode currentNode = root;
            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                output.append(currentChar);
                if (currentNode.childs[currentChar - 'a'].prefixCount == 1) {
                    break;
                } else {
                    currentNode = currentNode.childs[currentChar - 'a'];
                }
            }

            return output.toString();
        }


        public void insert(String word) {
            if (word.length() == 0)
                return;

            TrieNode currentNode = this.root;
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (currentNode.childs[currentChar - 'a'] == null) {
                    currentNode.childs[currentChar - 'a'] = new TrieNode();
                }
                currentNode.childs[currentChar - 'a'].prefixCount++;
                currentNode = currentNode.childs[currentChar - 'a'];
            }
            currentNode.isEnd = true;
        }

    }


    public ArrayList<String> prefix(ArrayList<String> inputList) {
        ArrayList<String> output = new ArrayList<>();
        if (inputList.size() == 0)
            return output;
        Trie trie = new Trie();
        for (String currentWord : inputList)
            trie.insert(currentWord);


        for (String currentWord : inputList)
            output.add(trie.getPrefix(currentWord));


        return output;
    }
}
