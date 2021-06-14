package com.example.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String string = "I am a string. Yes I am.";
        System.out.println(string);
        //replaceAll() replace every I to You
        String yourString = string.replaceAll("I", "You");
        System.out.println(yourString);

        String alphanumeric = "abcDeeeF12Ghhijkl99z";
        //. means any char will match
        System.out.println(alphanumeric.replaceAll(".", "Y"));
        //^ means to match at beginning of string
        System.out.println(alphanumeric.replaceAll("^abcD", "YYY"));
        //matches() return true if this entire string match the given regex, partial match return false
        System.out.println(alphanumeric.matches("^hello")); //false
        System.out.println(alphanumeric.matches("^abcD")); //false
        //$ is end of a string
        System.out.println(alphanumeric.replaceAll("99z$", "END"));
        //[abc] match char a or b or c
        System.out.println(alphanumeric.replaceAll("[aei]", "X"));
        //[][] match any single char in first bracket which is followed by any single char in second bracket
        System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X"));
        System.out.println("adaebdbecdceab".replaceAll("[abc][de]", "X"));
        System.out.println("Harry".replaceAll("[Hh]arry", "Harry"));
        //[^] match any char expect the char in bracket
        System.out.println(alphanumeric.replaceAll("[^ej]", "X"));
        //[a-zA-Z] match a to z, or A to Z inclusive
        System.out.println(alphanumeric.replaceAll("[a-fA-F3-8]", "X"));
        //(?i) is case insensitive matching for ASCII char
        System.out.println(alphanumeric.replaceAll("(?i)[a-f3-8]", "X"));
        //\d match for a digit [0-9], note that in "" need to use \\
        System.out.println(alphanumeric.replaceAll("\\d", "X"));
        //\D match for a non digit [^0-9]
        System.out.println(alphanumeric.replaceAll("\\D", "X"));
        String hasWhiteSpace = "I have blanks, \t a tab and a newline \n";
        //\s match for a whitespace char, \t, \n [ \t\n\x0B\f\r]
        //"" will remove all those char
        System.out.println(hasWhiteSpace.replaceAll("\\s", ""));
        //remove tab char only
        System.out.println(hasWhiteSpace.replaceAll("\t", ""));
        //\S match for non whitespace char [^\s]
        System.out.println(hasWhiteSpace.replaceAll("\\S", ""));
        //\w match for a word char [a-zA-Z_0-9]
        System.out.println(alphanumeric.replaceAll("\\w", "X"));
        //\W match for non word char [^\w]
        System.out.println(alphanumeric.replaceAll("\\W", "X"));
        //\b match for a word's boundary
        //eg: "I have space".replaceAll("\\b","X"); will return XIX XhaveX XspaceX
        //which it search for boundary of every word and replace with X
        System.out.println(alphanumeric.replaceAll("\\b", "X"));
        //X{n} match X for exactly n times
        //same as ^abcDeee
        System.out.println(alphanumeric.replaceAll("^abcDe{3}", "YYY"));
        //X+ match X for one or more times
        System.out.println(alphanumeric.replaceAll("^abcDe+", "YYY"));
        //X* match X for 0 or more times, which can with or without X
        System.out.println(alphanumeric.replaceAll("^abcDe*", "YYY"));
        //X{n,m} match for X at n times but not more than m times
        System.out.println(alphanumeric.replaceAll("^abcDe{2,5}", "YYY"));
        //match h for 1 or more times, i for 0 or more times, and j
        System.out.println(alphanumeric.replaceAll("h+i*j", "YYY"));

        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub-heading</h2>");
        htmlText.append("<p>This is a paragraph</p>");
        htmlText.append("<p>This is another paragraph</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary</p>");

        //this regex means any occurrence of <h2>, can have anything thing before and after <h2>
        //String h2Pattern = ".*<h2>.*";
        String h2Pattern = "<h2>";
        //compile() compile the given regex into a Pattern
        //enable case insensitive matching
        //Pattern pattern = Pattern.compile(h2Pattern,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
        Pattern pattern = Pattern.compile(h2Pattern);
        //matcher() create a matcher that match given input (String/StringBuilder) against this Pattern
        Matcher matcher = pattern.matcher(htmlText);
        //matches() return true if entire matcher match the pattern
        System.out.println(matcher.matches());

        //reset this matcher, which discard all its explicit state info and set its append position to 0,
        //set to its default region, which is its entire char sequence
        matcher.reset();
        int count = 0;
        //find() scan input looking for next occurence that match the pattern, search from beginning of this
        //matcher, or if previous call of this method was successful and matcher has not been reset, will
        //start at first char not yet been match by previous match
        //return true if next subsequence of input match this matcher's pattern
        while (matcher.find()) {
            count++;
            //start() return index of the first char matched
            //end() return offset after last char matched
            System.out.println("Occurrence " + count + ": " + matcher.start() + " to " + matcher.end());
        }

        //() is group
        //X* is greedy quantifier, X*? is reluctant quantifier
        //.*? will take minimum char required
        String h2GroupPattern = "(<h2>.*?</h2>)";
        Pattern groupPattern = Pattern.compile(h2GroupPattern);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());
        groupMatcher.reset();

        while (groupMatcher.find()) {
            //group() return input subsequence that match the given group during previous match operation
            //group 0 means entire pattern, so group index start at 1
            //group 1 is (<h2>.*?</h2>), so it will return the sequence that match this group
            System.out.println("Occurrence " + groupMatcher.group(1));
        }

        //X+ is 1 or more times
        String h2TextGroup = "(<h2>)(.+?)(</h2>)";
        Pattern h2TextPattern = Pattern.compile(h2TextGroup);
        Matcher h2TextMatcher = h2TextPattern.matcher(htmlText);
        while (h2TextMatcher.find()) {
            //group 2 is (.+?), which is to get the text between the tag
            System.out.println("Occurrence " + h2TextMatcher.group(2));
        }

        //logical operators
        //X|Y means either X or Y
        System.out.println("harry".replaceAll("[H|h]arry", "Larry"));
        String tvTest = "tstvtkt";
        //find all t is not follow by v
        //String tNotVRegExp = "t[^v]";
        //(?!X) is lookahead, which means not consume char, which !X means not X
        //last char is t will be include also
        //will not include v in the match
        String tNotVRegExp = "t(?!v)";
        Pattern tNotVPattern = Pattern.compile(tNotVRegExp);
        Matcher tNotVMatcher = tNotVPattern.matcher(tvTest);
        count = 0;
        while (tNotVMatcher.find()) {
            count++;
            System.out.println("Occurrence " + count + ": " + tNotVMatcher.start() + " to " + tNotVMatcher.end());
        }

        //real world example
        //sample of US phone number: (800) 123-4567
        //^ match beginning, () is group, [] match any char inside, need to escape ( cos it is being used in regex
        //match ( for 1 time, take 3 digit, a ), a space, 3 digit, -, 4 digit, end, which means if there is
        //anything behind phone num pattern then wont match
        //^([\(]{1}[0-9]{3}[\)]{1}[ ]{1}[0-9]{3}[\-]{1}[0-9]{4})$
        String patternNum = "^([(]{1}[0-9]{3}[)]{1}[ ]{1}[0-9]{3}[\\-]{1}[0-9]{4})$";
        String phone1 = "1234567890"; //false
        String phone2 = "(123) 456-7890"; //true
        String phone3 = "123 456-7890"; //false
        String phone4 = "(123)456-7890"; //false
        System.out.println("phone1 = " + phone1.matches(patternNum));
        System.out.println("phone2 = " + phone2.matches(patternNum));
        System.out.println("phone3 = " + phone3.matches(patternNum));
        System.out.println("phone4 = " + phone4.matches(patternNum));

        //credit number visa, start with 4, 13-16 digit
        //^4[0-9]{12}([0-9]{3})?$
        //^ beginning, start with 4, 12 digits, a group contain 3 digit, X? will take once or not at all, end
        //sample: 4111 1111 1111 1111 (will remove space first)
        String visa1 = "4444444444444"; // true
        String visa2 = "5444444444444"; // false
        String visa3 = "4444444444444444";  // true
        String visa4 = "4444";  // false
        String visaPattern = "^4[0-9]{12}([0-9]{3})?$";
        System.out.println("visa1 " + visa1.matches(visaPattern));
        System.out.println("visa2 " + visa2.matches(visaPattern));
        System.out.println("visa3 " + visa3.matches(visaPattern));
        System.out.println("visa4 " + visa4.matches(visaPattern));

        //challenge 1
        String challenge1 = "I want a bike.";
        //matches() return true only if this entire string match the given regex, if partial match will
        //return false also
        System.out.println(challenge1.matches("I want a bike."));

        //challenge 2
        String challenge2 = "I want a ball.";
        //String reg = "I want a b[ia][kl][el].";
        //\w is any word char, X+ is one or more times
        //String reg = "I want a \\w+.";
        String reg = "I want a (bike|ball).";
        System.out.println(challenge1.matches(reg));
        System.out.println(challenge2.matches(reg));

        //challenge 3
        //multiple matcher
        String regChallenge3 = "I want a (bike|ball).";
        Pattern regPattern = Pattern.compile(regChallenge3);
        Matcher matcherPattern = regPattern.matcher(challenge1);
        System.out.println(matcherPattern.matches());
        matcherPattern = regPattern.matcher(challenge2);
        System.out.println(matcherPattern.matches());

        //challenge 4
        String challenge4 = "Replace all blanks with underscores.";
        System.out.println(challenge4.replaceAll(" ", "_"));
        System.out.println(challenge4.replaceAll("\\s", "_"));

        //challenge 5
        String challenge5 = "aaabccccccccdddefffg";
        System.out.println(challenge5.matches("a{3}bc{8}d{3}ef{3}g"));
        System.out.println(challenge5.matches("[abcdefg]+"));
        System.out.println(challenge5.matches("[a-g]+"));

        //challenge 6
        System.out.println(challenge5.matches("^a{3}bc{8}d{3}ef{3}g$"));

        //challenge 7
        String challenge7 = "abcd.135";
        System.out.println(challenge7.matches("^[a-zA-Z]+.\\d+"));

        //challenge 8
        //find number of occurrence of pattern, and print out only numbers
        String challenge8 = "abcd.135uvqz.7tzik.999";
        //put numbers into a group, so will print out numbers only
        Pattern c8Pattern = Pattern.compile("[a-zA-Z]+.(\\d+)");
        Matcher c8Matcher = c8Pattern.matcher(challenge8);
        int c8Count = 0;
        while (c8Matcher.find()) {
            c8Count++;
            System.out.println(c8Matcher.group(1));
        }
        System.out.println(c8Count);

        //challenge 9
        String challenge9 = "abcd.135\tuvqz.7\ttzik.999\n";
        //put numbers into a group, so will print out numbers only
        Pattern c9Pattern = Pattern.compile("[a-zA-Z]+.(\\d+)\\s");
        Matcher c9Matcher = c9Pattern.matcher(challenge9);
        int c9Count = 0;
        while (c9Matcher.find()) {
            c9Count++;
            //System.out.println(c9Matcher.group(1));
            System.out.println("start: " + c9Matcher.start(1) + " end: " + (c9Matcher.end(1) - 1));
        }
        System.out.println(c9Count);

        //challenge 11
        String challenge11 = "{0,2},{0,5},{1,3},{2,4}";
        //for one and more digit, eg 11
        Pattern c11Pattern = Pattern.compile("\\{(\\d+,\\d+)}");
        //X+? match for one or more times, is reluctant quantifier
        //Pattern c11Pattern = Pattern.compile("\\{(.+?)}");
        Matcher c11Matcher = c11Pattern.matcher(challenge11);
        int c11Count = 0;
        while (c11Matcher.find()) {
            c11Count++;
            System.out.println(c11Matcher.group(1));
        }

        //challenge 12
        //US zip code
        String challenge12 = "11111";
        System.out.println(challenge12.matches("\\d{5}"));
        //challenge 13
        String challenge13 = "11111-1111";
        System.out.println(challenge12.matches("\\d{5}-\\d{4}"));
        //challenge 14
        //? means 0 or once
        System.out.println(challenge12.matches("\\d{5}(-\\d{4})?"));
        System.out.println(challenge13.matches("\\d{5}(-\\d{4})?"));


    }
}
