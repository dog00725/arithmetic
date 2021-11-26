package PracticeQuestions;

public class BuddyStrings {
    public boolean buddyStrings(String s, String goal) {
        if(null == s || null == goal){
            return false;
        }
        if (s.length() != goal.length()){
            return false;
        }
        int[] sWordCount = new int[26];
        int[] goalWordCount = new int[26];
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int sWordIndex = s.charAt(i) - 'a';
            int goalWordIndex = goal.charAt(i) - 'a';
            sWordCount[sWordIndex]++;
            goalWordCount[goalWordIndex]++;
            if (sWordIndex != goalWordIndex){
                sum++;
            }
        }
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            if (sWordCount[i] != goalWordCount[i]){
                return false;
            }
            if (sWordCount[i] > 1){
                flag = true;
            }
        }
        return sum == 2 || (sum == 0 && flag);
    }

    public static void main(String[] args) {
        BuddyStrings buddyStrings = new BuddyStrings();
        System.out.println(buddyStrings.buddyStrings("acb","abc"));
    }
}
