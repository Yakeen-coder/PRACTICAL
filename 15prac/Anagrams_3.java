//Yakeen Lucas
//Anagrams.java
//CSC 211 - Practical 15
//Used Claude (claude.ai) to help with this code.
//File was released yesterday, hence completed it the day before (Using MacOS). There only one git commit.

import java.io.*;
import java.util.*;

public class Anagrams_3 {

    public static String computeWordSignature(String inputWord) {
        char[] letterArray = inputWord.toCharArray();
        Arrays.sort(letterArray);
        return new String(letterArray);
    }

    public static String stripPunctuation(String rawToken) {
        String stripSet = "[]0123456789(,.;:_.!?-)";

        int leftIndex  = 0;
        int rightIndex = rawToken.length() - 1;

        while (leftIndex <= rightIndex && stripSet.indexOf(rawToken.charAt(leftIndex)) >= 0) {
            leftIndex++;
        }

        while (rightIndex >= leftIndex && stripSet.indexOf(rawToken.charAt(rightIndex)) >= 0) {
            rightIndex--;
        }

        return rawToken.substring(leftIndex, rightIndex + 1);
    }

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Usage: java Anagrams <inputfile>");
            System.exit(1);
        }

        String ulyssesFilePath = args[0];
        System.out.println("Data file: " + ulyssesFilePath);

        HashMap<String, Integer> wordCountMap = new HashMap<>();

        BufferedReader ulyssesReader = new BufferedReader(
            new InputStreamReader(new FileInputStream(ulyssesFilePath), "ISO-8859-1")
        );

        String currentLine = ulyssesReader.readLine();

        while (currentLine != null) {
            String[] rawTokenArray = currentLine.split("\\s+");

            for (String rawToken : rawTokenArray) {
                if (rawToken.isEmpty()) continue;

                String cleanedToken = stripPunctuation(rawToken);
                cleanedToken = cleanedToken.toLowerCase();

                if (cleanedToken.isEmpty()) continue;

                if (wordCountMap.containsKey(cleanedToken)) {
                    wordCountMap.put(cleanedToken, wordCountMap.get(cleanedToken) + 1);
                } else {
                    wordCountMap.put(cleanedToken, 1);
                }
            }

            currentLine = ulyssesReader.readLine();
        }

        ulyssesReader.close();
        System.out.println("Unique words found: " + wordCountMap.size());

        HashMap<String, List<String>> signatureToWordsMap = new HashMap<>();

        for (String singleWord : wordCountMap.keySet()) {
            String wordKey = computeWordSignature(singleWord);

            if (!signatureToWordsMap.containsKey(wordKey)) {
                List<String> freshAnagramGroup = new ArrayList<>();
                freshAnagramGroup.add(singleWord);
                signatureToWordsMap.put(wordKey, freshAnagramGroup);
            } else {
                signatureToWordsMap.get(wordKey).add(singleWord);
            }
        }

        PrintWriter tempAnagramsWriter = new PrintWriter(new FileWriter("anagrams"));

        for (String groupKey : signatureToWordsMap.keySet()) {
            List<String> anagramWordGroup = signatureToWordsMap.get(groupKey);

            if (anagramWordGroup.size() > 1) {
                String rotatingWordLine = String.join(" ", anagramWordGroup);
                tempAnagramsWriter.print(rotatingWordLine + "\\\\\n");

                for (int rotationCount = 0; rotationCount < anagramWordGroup.size() - 1; rotationCount++) {
                    int firstSpacePos = rotatingWordLine.indexOf(' ');
                    String firstWord  = rotatingWordLine.substring(0, firstSpacePos);
                    String remainder  = rotatingWordLine.substring(firstSpacePos + 1);
                    rotatingWordLine  = remainder + " " + firstWord;
                    tempAnagramsWriter.print(rotatingWordLine + "\\\\\n");
                }
            }
        }

        tempAnagramsWriter.close();

        BufferedReader unsortedReader = new BufferedReader(new FileReader("anagrams"));
        List<String> allAnagramLines = new ArrayList<>();
        String unsortedLine;

        while ((unsortedLine = unsortedReader.readLine()) != null) {
            allAnagramLines.add(unsortedLine);
        }
        unsortedReader.close();

        Collections.sort(allAnagramLines);

        new File("latex").mkdirs();
        PrintWriter latexWriter = new PrintWriter(new FileWriter("latex/theAnagrams.tex"));

        char currentInitialLetter = 'X';

        for (String anagramEntry : allAnagramLines) {
            if (anagramEntry.isEmpty()) continue;

            char entryInitialChar = anagramEntry.charAt(0);

            if (Character.toLowerCase(entryInitialChar) != Character.toLowerCase(currentInitialLetter)) {
                currentInitialLetter = entryInitialChar;
                latexWriter.println(
                    "\n\\vspace{14pt}\n\\noindent\\textbf{\\Large "
                    + Character.toUpperCase(currentInitialLetter)
                    + "}\\\\*[+12pt]"
                );
            }

            latexWriter.print(anagramEntry + "\n");
        }

        latexWriter.close();
        new File("anagrams").delete();
        System.out.println("Done. LaTeX output written to: latex/theAnagrams.tex");

        System.out.println("\n===== ANAGRAM DICTIONARY =====\n");
        int totalGroups = 0;
        for (String groupKey : signatureToWordsMap.keySet()) {
            List<String> groupWords = signatureToWordsMap.get(groupKey);
            if (groupWords.size() > 1) {
                Collections.sort(groupWords);
                System.out.println(groupKey + ": " + groupWords);
                totalGroups++;
            }
        }
        System.out.println("\nTotal anagram groups: " + totalGroups);
    }
}

// In order to run the code:

// 1) javac Anagrams_3.java
// 2) java Anagrams joyce1992_ulyssses.text
