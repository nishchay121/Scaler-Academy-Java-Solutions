public class Solution {

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


        private int getMisMatchCount(String input) {
            return getMisMatchCountHelper(input, 0, this.root);
        }

        private int getMisMatchCountHelper(String input, int currentIndex, TrieNode root) {

            if (currentIndex == input.length())
                return 0;

            char currentChar = input.charAt(currentIndex);
            if (root.childs[currentChar - 'a'] != null)
                return getMisMatchCountHelper(input, currentIndex + 1, root.childs[currentChar - 'a']);
            else {

                int minMatchCount = 0;
                for (int i = 0; i < 26; i++) {
                    boolean flag = false;
                    if (root.childs[i] != null) {
                        flag = true;
                        int misMatchCountInSubTrie = getMisMatchCountHelper(input, currentIndex + 1, root.childs[i]);
                        minMatchCount = Math.min(minMatchCount, misMatchCountInSubTrie);
                    }
                    if (!flag)
                        return input.length() - currentIndex - 1;
                }


                return minMatchCount;
            }

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


    public String solve(ArrayList<String> inputList, ArrayList<String> wordsToCheck) {

        StringBuilder sb = new StringBuilder();
        if (inputList.size() == 0)
            return "";
        Trie trie = new Trie();
        for (String currentWord : inputList)
            trie.insert(currentWord);


        for (String currentWord : wordsToCheck) {
            if (trie.getMisMatchCount(currentWord) == 1)
                sb.append(1);
            else
                sb.append(0);
        }


        return sb.toString();
    }
}
